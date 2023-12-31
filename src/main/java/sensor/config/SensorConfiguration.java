package sensor.config;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@Getter @Setter
public class SensorConfiguration {

    private static String serviceUUID;
    private static final RestTemplate restTemplate = new RestTemplate();
    private static Environment env;
    private static boolean initialized;

    private static final String registrationUrl = "/sensors/registration";

    @Autowired
    public SensorConfiguration(final Environment env) {
        SensorConfiguration.env = env;
    }

    public static void initUUID() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final JSONObject requestBody = new JSONObject();
        requestBody.put("name", env.getProperty("sensor.name"));

        final String url = env.getProperty("server.url") + registrationUrl;
        final HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toString(), headers);

        final String result = restTemplate.postForObject(url, requestEntity, String.class);
        final JSONObject responseBody = new JSONObject(result);

        serviceUUID = (String) responseBody.get("key");
        initialized = true;
    }

    public static String getServiceUUID() {
        if (!initialized) {
            initUUID();
        }

        return serviceUUID;
    }
}
