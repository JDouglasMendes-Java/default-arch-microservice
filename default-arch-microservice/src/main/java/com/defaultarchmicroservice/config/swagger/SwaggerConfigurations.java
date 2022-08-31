package com.defaultarchmicroservice.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
public class SwaggerConfigurations {

    @Bean
    public Docket forumApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("jdouglas-mendes/default-ar-microservice"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .globalOperationParameters(List.of(
                        new ParameterBuilder()
                                .name("Authorization")
                                .description("Header for token JWT")
                                .modelRef(new ModelRef("String"))
                                .parameterType("Header")
                                .required(false)
                                .build()));
    }
}
