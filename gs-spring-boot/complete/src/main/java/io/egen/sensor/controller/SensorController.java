package io.egen.sensor.controller;

import io.egen.sensor.dao.SensorDAO;
import io.egen.sensor.entity.SensorData;
import io.egen.sensor.util.OverWeightRule;
import io.egen.sensor.util.UnderWeightRule;
import org.easyrules.api.RulesEngine;
import org.springframework.web.bind.annotation.*;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

@RestController
public class SensorController {


    private final SensorDAO sensorDAO;
    private final UnderWeightRule underWeightRule;
    private final OverWeightRule overWeightRule;
    // TODO: 5/29/16 Update base value from user input
    private final static int baseValue = 150;
    private final RulesEngine rulesEngine;

    public SensorController() {
        sensorDAO = new SensorDAO();

        /**
         * Create a rules engine and register the business rule
         */
        rulesEngine = aNewRulesEngine().build();

        underWeightRule = new UnderWeightRule(baseValue);
        overWeightRule = new OverWeightRule(baseValue);
        rulesEngine.registerRule(underWeightRule);
        rulesEngine.registerRule(overWeightRule);

    }

    @RequestMapping("/")
    public String index() {
        return "REST service is online";
    }

    @RequestMapping(value = "/sensor/create", method = RequestMethod.POST)
    public String consumeSensorData(@RequestBody SensorData sensorData) {
        if (sensorData != null) {
            System.out.println(sensorData);
            sensorDAO.persistSensorData(sensorData);

            // Set Inputs
            underWeightRule.setInput(sensorData);
            overWeightRule.setInput(sensorData);
            /**
             * Fire rules
             */
            rulesEngine.fireRules();
        }
        return "sensor data is stored";
    }

    @RequestMapping(value = "/sensor/read", method = RequestMethod.GET)
    public String readSensorData() {
        return String.valueOf(sensorDAO.readSensorData());
    }

    @RequestMapping(value = "/sensor/readByTimeRange ", method = RequestMethod.GET)
    public String readSensorDataByTimeRange(@RequestParam(value = "t1") String timeStamp1, @RequestParam(value = "t2") String timeStamp2) {
        System.out.println("T1: " + timeStamp1 + " T2: " + timeStamp2);
        return String.valueOf(sensorDAO.readSensorDataByTimeRange(Long.decode(timeStamp1), Long.decode(timeStamp2)));
    }

}
