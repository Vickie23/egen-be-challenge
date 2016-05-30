package io.egen.sensor.controller;

import io.egen.sensor.dao.AlertDAO;
import io.egen.sensor.dao.SensorDAO;
import io.egen.sensor.entity.SensorData;
import io.egen.sensor.util.OverWeightRule;
import io.egen.sensor.util.UnderWeightRule;
import org.easyrules.api.RulesEngine;
import org.springframework.web.bind.annotation.*;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

@RestController
public class AlertController {


    private AlertDAO alertDAO;

    public AlertController() {
        alertDAO = new AlertDAO();
    }

    @RequestMapping(value = "/alert/read", method = RequestMethod.GET)
    public String readAlerts() {
        return String.valueOf(alertDAO.readAlerts());
    }

    @RequestMapping(value = "/alert/readByTimeRange ", method = RequestMethod.GET)
    public String readAlertsByTimeRange(@RequestParam(value = "t1") String timeStamp1, @RequestParam(value = "t2") String timeStamp2) {
        System.out.println("T1: " + timeStamp1 + " T2: " + timeStamp2);
        return String.valueOf(alertDAO.readAlertsByTimeRange(Long.decode(timeStamp1), Long.decode(timeStamp2)));
    }

}
