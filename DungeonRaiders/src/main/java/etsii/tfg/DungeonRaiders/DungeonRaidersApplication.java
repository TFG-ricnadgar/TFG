package etsii.tfg.DungeonRaiders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class }) // No esta definido el data source por eso el
																		// exclude
public class DungeonRaidersApplication {

	public static void main(String[] args) {
		SpringApplication.run(DungeonRaidersApplication.class, args);
	}

}
