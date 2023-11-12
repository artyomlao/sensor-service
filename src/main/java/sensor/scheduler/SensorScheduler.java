package sensor.scheduler;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sensor.config.SensorConfiguration;

import java.util.Random;

@Component
public class SensorScheduler {

    private final RestTemplate restTemplate = new RestTemplate();
    private Environment env;
    private final String measurementUrl = "/sensors/" + SensorConfiguration.getServiceUUID() + "/measurements";

    @Autowired
    public SensorScheduler(final Environment env) {
        this.env = env;
    }

    @Scheduled(cron = "*/3 * * * * *")
    public void sendData() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final JSONObject requestBody = new JSONObject();
        requestBody.put("value", new Random().nextDouble());
        requestBody.put("raining", new Random().nextBoolean());

        final String url = env.getProperty("server.url") + measurementUrl;
        final HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toString(), headers);

        restTemplate.postForObject(url, requestEntity, String.class);
    }
}
