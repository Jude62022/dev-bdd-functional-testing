package com.dso.java.bdd.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // The configuration class needs to be annotated with @Configuration - the
@EnableSwagger2 // EableSwagger2:Annotation to Enable Swagger Documentation on the API
public class SpringFoxConfig {
	@Bean
	public Docket api() {
		// return a prepared docket instance. Docket is an object
		return new Docket(DocumentationType.SWAGGER_2).select()
			/* returns an instance of ApiSelectorBuilder, which provides control over the endpoints exposed by Swagger. */
				.apis(RequestHandlerSelectors.basePackage("com.dso.java.bdd.controller"))
				// scan the base package and create APIs for all of the classes within it.
				// .paths(PathSelectors.any()) // to generate documentation for all packages.
				.paths(PathSelectors.ant("/v1/**"))// which paths in our APIs do we want to create documentation for.
				.build().apiInfo(apiInfo()).securitySchemes(basicScheme());
	}

	Contact contact = new Contact("Dinesh", "https://github.com",
			".com");

	private ApiInfo apiInfo() {
		return new ApiInfo("DevSecOps REST API", "Sample API's for POC.", "1.0.0 V", "Proprietary", contact,
				"API License", "https://www.apache.org/", Collections.emptyList());
	}

	private List<SecurityScheme> basicScheme() {
		List<SecurityScheme> schemeList = new ArrayList<>();
		schemeList.add(new BasicAuth("basicAuth"));
		return schemeList;
	}

}
// 1.Adding application metadata to swagger.
// 2.Adding more details to API