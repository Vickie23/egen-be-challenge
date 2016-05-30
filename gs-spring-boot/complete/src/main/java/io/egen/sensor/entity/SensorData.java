package io.egen.sensor.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by Subbu on 5/28/16.
 */

@Entity
public class SensorData {

    @Id
    private ObjectId id;

    Long timeStamp;
    String value;

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "timestamp='" + timeStamp + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
