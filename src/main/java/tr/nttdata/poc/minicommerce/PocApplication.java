package tr.nttdata.poc.minicommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@ComponentScan(basePackages = "tr.nttdata.poc.minicommerce")
@EnableJpaRepositories
@EnableMongoRepositories
@EnableRedisRepositories
@EnableCaching
@SpringBootApplication
@EnableFeignClients
@RefreshScope
public class PocApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocApplication.class, args);
	}

}
