# sensor-emulator
Simple java program that acts like a sensor and sends information (in JSON format) every 5 seconds. Sample data looks like this,

```json
{
  "timeStamp": "1458062848734", 
  "value": "153"
}
``` 

where timeStamp indicates when this data was sent and the value indicates a metric.

## How to build it?
```
mvn clean package
```

This creates a jar file in target directory 'sensor-emulator-0.0.1-SNAPSHOT.jar'

## How to run it?
```
java -jar -Dbase.value=150 -Dapi.url=http://localhost:8080 sensor-emulator-0.0.1-SNAPSHOT.jar
```

where 'base.value' is the value from which the metrics should start from and 'url' is the RESTful endpoint that this emulator will try to send the metric to. 
