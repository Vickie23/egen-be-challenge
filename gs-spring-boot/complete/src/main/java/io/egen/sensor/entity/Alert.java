package io.egen.sensor.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by Subbu on 5/28/16.
 */

@Entity
public class Alert {

    @Id
    private ObjectId id;

    Long timeStamp;
    String alertDescription;

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAlertDescription() {
        return alertDescription;
    }

    public void setAlertDescription(String alertDescription) {
        this.alertDescription = alertDescription;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "id=" + id +
                ", timeStamp=" + timeStamp +
                ", alertDescription='" + alertDescription + '\'' +
                '}';
    }
}
