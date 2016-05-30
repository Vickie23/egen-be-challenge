package io.egen.sensor.dao;

import com.mongodb.MongoClient;
import io.egen.sensor.entity.SensorData;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Subbu on 5/28/16.
 */
public class SensorDAO {


    private Morphia morphia;
    private Datastore datastore;
    private final static String DB_NAME = "egen_iot";
    /*
        protected SensorDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
            super(mongoClient, morphia, dbName);
        }
    */
    MongoClient mongo = null;

    public SensorDAO() {
        try {
            mongo = new MongoClient();
            morphia = new Morphia();
            datastore = morphia.createDatastore(mongo, DB_NAME);
            morphia.mapPackage("io.egen.sensor.entity");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void persistSensorData(SensorData sensorData) {
        System.out.println("Persisting sensor Data: " + sensorData);
        datastore.save(sensorData);
    }

    public ArrayList<SensorData> readSensorData() {
        ArrayList<SensorData> sensorDataList = (ArrayList<SensorData>) datastore.createQuery(SensorData.class)
                .asList();
        System.out.println(sensorDataList + " size: " + sensorDataList.size());

        return sensorDataList;
    }

    public ArrayList<SensorData> readSensorDataByTimeRange(Long timeStamp1, Long timeStamp2) {
        ArrayList<SensorData> sensorDataList = (ArrayList<SensorData>) datastore.createQuery(SensorData.class)
                .filter("timeStamp >=", timeStamp1)
                .filter("timeStamp <=", timeStamp2)
                .asList();
        System.out.println(sensorDataList + " size: " + sensorDataList.size());

        return sensorDataList;
    }
}
