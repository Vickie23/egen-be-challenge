package io.egen.sensor.util;

import io.egen.sensor.dao.AlertDAO;
import io.egen.sensor.entity.Alert;
import io.egen.sensor.entity.SensorData;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

/**
 * Created by Subbu on 5/29/16.
 */

@Rule(name = "UnderWeight rule",
        description = "Create overweight rule when the value drops below 10% of base value")
public class UnderWeightRule {

    private final AlertDAO alertDAO;

    public UnderWeightRule(int baseValue) {
        UnderWeightRule.baseValue = baseValue;
        alertDAO = new AlertDAO();
    }

    /**
     * The user input which represents the data
     * that the rule will operate on.
     */
    private static int baseValue;
    private SensorData sensorData;

    @Condition
    public boolean checkWeightValue() {
        //The rule should be applied only if
        //the value drops below 10% of base value
        int limit = baseValue - SensorUtil.computePercent(baseValue, 10);
        return Integer.parseInt(sensorData.getValue()) < limit;
    }

    @Action
    public void createUnderWeightAlert() throws Exception {
        //When rule conditions are satisfied,
        //create alert
        System.out.println("Underweight! create alert");
        Alert alert = new Alert();
        alert.setTimeStamp(sensorData.getTimeStamp());
        alert.setAlertDescription("UnderWeight: " + sensorData.getValue());
        alertDAO.createAlert(alert);
    }

    public void setInput(SensorData input) {
        this.sensorData = input;
    }

}
