package sensor.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sensor.service.SensorService;

import java.util.Random;

@Component
public class SensorScheduler {

    private final SensorService sensorService;

    @Autowired
    public SensorScheduler(final SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Scheduled(cron = "*/3 * * * * *")
    public void sendData() {
        try {
            Thread.sleep(new Random().nextLong(12000));
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }

        sensorService.sendMeasurementRequest();
    }
}
