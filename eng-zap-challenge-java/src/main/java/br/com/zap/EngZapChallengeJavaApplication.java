package br.com.zap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableCaching
@EnableScheduling
public class EngZapChallengeJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EngZapChallengeJavaApplication.class, args);
	}

}
