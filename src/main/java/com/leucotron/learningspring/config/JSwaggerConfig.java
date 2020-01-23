package com.leucotron.learningspring.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author flavio
 */
@Configuration
@EnableSwagger2
public class JSwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageForGetAndDelete())
                .globalResponseMessage(RequestMethod.POST, responseMessageForPost())
                .globalResponseMessage(RequestMethod.PUT, responseMessageForPut())
                .globalResponseMessage(RequestMethod.DELETE, responseMessageForGetAndDelete())
                .apiInfo(metaData())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .tags(new Tag("User", "Endpoint for operations in users"))
                .tags(new Tag("Product", "Endpoint for operations in products"));
    }

    private List<ResponseMessage> responseMessageForGetAndDelete() {
        return new ArrayList<ResponseMessage>() {
            {
                addAll(commonResponseMessage());
                add(new ResponseMessageBuilder()
                        .code(404)
                        .message("Not found")
                        .build());
            }
        };
    }

    private List<ResponseMessage> responseMessageForPost() {
        return new ArrayList<ResponseMessage>() {
            {
                addAll(commonResponseMessage());
                add(new ResponseMessageBuilder()
                        .code(201)
                        .message("Created")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(409)
                        .message("Conflict")
                        .build());
            }
        };
    }

    private List<ResponseMessage> responseMessageForPut() {
        return new ArrayList<ResponseMessage>() {
            {
                addAll(commonResponseMessage());
                add(new ResponseMessageBuilder()
                        .code(404)
                        .message("Not found")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(409)
                        .message("Conflict")
                        .build());
            }
        };
    }

    private List<ResponseMessage> commonResponseMessage() {
        return new ArrayList<ResponseMessage>() {
            {
                add(new ResponseMessageBuilder()
                        .code(500)
                        .message("Internal server error")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(403)
                        .message("Permission denied")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(401)
                        .message("Unauthorized")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(400)
                        .message("Bad request")
                        .build());
            }
        };
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot application")
                .description("REST API for application services.")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer Token", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.ant("/api/v1/**"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        var authorizationScope = new AuthorizationScope("global", "accessEverything");
        var authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Bearer Token", authorizationScopes));
    }

}
