package com.sescsp.eventsesc.core.openapi;

import com.fasterxml.classmate.TypeResolver;

import com.sescsp.eventsesc.api.v1.exceptionhandler.Problem;
import com.sescsp.eventsesc.api.v1.model.input.CategoryInputId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer{

    @Bean
    public Docket apiDocketV1(){
        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.sescsp.eventsesc.api"))
                .paths(PathSelectors.ant("/v1/**"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalGetResponseMessage())
                .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessage())
                .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessage())
                .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessage())
                .additionalModels(typeResolver.resolve(Problem.class))
                .apiInfo(apiInfoV1())
                .tags(new Tag("Eventos", "Gerencia os eventos"),
                        new Tag("Sess??es","Gerencia as sess??es dos eventos"),
                        new Tag("Ingressos","Gerencia os ingressos das sess??es")
                );
    }

    private List<ResponseMessage> globalDeleteResponseMessage() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .responseModel(new ModelRef("Problema"))
                        .message("Requisi????o inv??lida (erro do cliente)")
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .responseModel(new ModelRef("Problema"))
                        .message("Erro interno do servidor")
                        .build()
        );
    }

    private List<ResponseMessage> globalPostPutResponseMessage() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Requisi????o inv??lida (erro do cliente)")
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Erro interno do servidor")
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                        .message("Requisi????o recusada porque o payload est?? em um formato n??o suportado")
                        .build()
        );
    }

    private List<ResponseMessage> globalGetResponseMessage() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Erro interno do servidor")
                    .responseModel(new ModelRef("Problema"))
                    .build(),
                new ResponseMessageBuilder()
                    .code(HttpStatus.NOT_ACCEPTABLE.value())
                    .message("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
                    .build()
        );
    }

    private ApiInfo apiInfoV1(){
        return new ApiInfoBuilder()
                .title("EventSesc API")
                .description("API para gerenciamento de eventos e vendas de ingressos.")
                .version("1")
                .contact(new Contact("Sesc", "https://www.sescsp.org.br", "contato@sescsp.org.br"))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
