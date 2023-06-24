package id.co.indivara.miniproject.hospital.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("id.co.indivara.miniproject.hospital.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo(){
       ApiInfo apiInfo = new ApiInfo(
               "API Hospital",
               "Mini Project Java Development Traine 12",
               "v1.0",
               "Terms of service",
               new Contact("Yaumul Majid","coba.com","yaumulmajid@gmail.com"),
               "Apache License",
               "www.apache.com",
               Collections.emptyList()
       );
        return apiInfo;
    }
}
