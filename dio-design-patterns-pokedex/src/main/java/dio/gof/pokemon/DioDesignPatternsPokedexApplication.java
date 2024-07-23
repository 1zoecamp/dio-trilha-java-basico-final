package dio.gof.pokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DioDesignPatternsPokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(DioDesignPatternsPokedexApplication.class, args);
	}

}
