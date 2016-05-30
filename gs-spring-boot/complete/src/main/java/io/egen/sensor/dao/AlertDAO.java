package io.egen.sensor.dao;

import com.mongodb.MongoClient;
import io.egen.sensor.entity.Alert;
import io.egen.sensor.entity.Alert;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by Subbu on 5/28/16.
 */
public class AlertDAO {

    private Morphia morphia;
    private Datastore datastore;
    private final static String DB_NAME = "egen_iot";

    MongoClient mongo = null;

    public AlertDAO() {
        try {
            mongo = new MongoClient();
            morphia = new Morphia();
            datastore = morphia.createDatastore(mongo, DB_NAME);
            morphia.mapPackage("io.egen.sensor.entity");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void createAlert(Alert alert) {
        System.out.println("Creating new Alert: " + alert);
        datastore.save(alert);
    }

    public ArrayList<Alert> readAlerts() {
        ArrayList<Alert> AlertList = (ArrayList<Alert>) datastore.createQuery(Alert.class)
                .asList();
        System.out.println(AlertList + " size: " + AlertList.size());

        return AlertList;
    }

    public ArrayList<Alert> readAlertsByTimeRange(Long timeStamp1, Long timeStamp2) {
        ArrayList<Alert> AlertList = (ArrayList<Alert>) datastore.createQuery(Alert.class)
                .filter("timeStamp >=", timeStamp1)
                .filter("timeStamp <=", timeStamp2)
                .asList();
        System.out.println(AlertList + " size: " + AlertList.size());

        return AlertList;
    }
}
