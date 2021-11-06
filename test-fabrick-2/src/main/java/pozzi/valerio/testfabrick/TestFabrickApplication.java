package pozzi.valerio.testfabrick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//import pozzi.valerio.testfabrick.exceptions.RestTemplateResponseErrorHandler;

@SpringBootApplication
public class TestFabrickApplication {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(TestFabrickApplication.class, args);
    }

}
