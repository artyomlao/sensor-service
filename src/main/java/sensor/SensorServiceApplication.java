package sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import sensor.config.SensorConfiguration;

@SpringBootApplication
@EnableScheduling
public class SensorServiceApplication {

	private static Environment env;

	public SensorServiceApplication(final Environment env) {
		SensorServiceApplication.env = env;
	}

	public static void main(String[] args) {
		SpringApplication.run(SensorServiceApplication.class, args);
		new SensorConfiguration(env).initUUID();
	}
}
