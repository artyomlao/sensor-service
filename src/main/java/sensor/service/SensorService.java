package sensor.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sensor.config.SensorConfiguration;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

@Service
public class SensorService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Environment env;
    private final String measurementUrl = "/sensors/" + SensorConfiguration.getServiceUUID() + "/measurements";

    @Autowired
    public SensorService(final Environment env) {
        this.env = env;
    }

    public void sendMeasurementRequest() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final JSONObject requestBody = new JSONObject();
        requestBody.put("value", formatDouble(new Random().nextDouble() * 200 - 100));
        requestBody.put("raining", new Random().nextBoolean());

        final String url = env.getProperty("server.url") + measurementUrl;
        final HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toString(), headers);

        restTemplate.postForObject(url, requestEntity, String.class);
    }

    private Double formatDouble(final Double value) {
        final DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);

        decimalFormat.applyPattern("#,##0.00");
        return Double.valueOf(decimalFormat.format(value));
    }
}
