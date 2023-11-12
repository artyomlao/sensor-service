package sensor.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sensor.config.SensorConfiguration;

@Component
public class SensorScheduler {

    private static String sensorName = SensorConfiguration.getServiceUUID();

    @Scheduled(cron = "*/3 * * * * *")
    public void sendData() {
    }
}
