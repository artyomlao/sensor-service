package sensor.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SensorScheduler {

    @Scheduled(fixedRateString = "3000-15000")
    public void sendData() {

    }
}
