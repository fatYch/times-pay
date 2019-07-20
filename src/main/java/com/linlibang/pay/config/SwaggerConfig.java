package com.linlibang.pay.config;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;

/**
 * 描述：
 *
 * @author yanpeicai
 * date 2018/1/29--15:02
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${swagger.enable}")
	private boolean enableSwagger;

	@Value("${swagger.context-path}")
	private String contextPath;

	private final ServletContext servletContext;

	@Autowired
	public SwaggerConfig(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Bean
	public Docket wyApi() {

		//noinspection Guava
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("TIMES-PAY")
				.enable(enableSwagger)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.linlibang.pay.module"))
//				.paths(Predicates.not(PathSelectors.ant("/api/crm/*")))
//				.paths(Predicates.not(PathSelectors.ant("/api/pmscrm/*")))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getWyApiInfo())
				.pathProvider(new RelativePathProvider(servletContext) {
					@Override
					public String getApplicationBasePath() {
						return contextPath;
					}
				})
				.securitySchemes(apiKey())
				.securityContexts(securityContexts());
	}

	private ApiInfo getWyApiInfo() {

		return new ApiInfoBuilder()
				.title("TIMES-PAY")
				.description("银联支付文档")
				.version("1.0.0")
				.contact(new Contact("肥美的洪洪哥","http://blog.yaoch.top","yaocanhong@timesgroup.cn"))
				.build();
	}

	private List<ApiKey> apiKey() {
		List<ApiKey> apiKeyList = Lists.newArrayList();
		apiKeyList.add(new ApiKey("tokenId", "tokenId", "query"));
		return apiKeyList;
	}

	private List<SecurityContext> securityContexts() {
		//noinspection Guava
		return Collections.singletonList(SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(Predicates.not(PathSelectors.ant("/user/login")))
//				.forPaths(PathSelectors.regex("^(?!login).*$"))
				.build());
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Collections.singletonList(new SecurityReference("tokenId", authorizationScopes));
	}

}
