package me.whiteship.demobootweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
//@EnableWebMvc //Configuration 이 붙은 클래스에 붙여주면 돼.
public class WebConfig implements WebMvcConfigurer{

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan(Person.class.getPackageName());
        return jaxb2Marshaller;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GreetingInterceptor()).order(0); //오더를 주지않으면 add한 순서로 적용이 됨.
        registry.addInterceptor(new AnotherInterceptor())
                .addPathPatterns("/hi") //핸들러를 hi 에만 적용하고 싶다. 특정 패턴을 추가할 수 있음.
                .order(-1); //명시적으로 추가해줘도 됨. 이 경우엔 핸들러 2가 먼저 실행.
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mobile/**") //우리가 어떤 패턴의 요청을 처리할 지
                .addResourceLocations("classpath:/mobile/", "file:/Users/name/files") //리소스를 어디서 찾아야 할 지
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES)) //여러가지 설정할 수 있는데 기본적으로 캐시만 설정을 해보기로 함.
        //여기서 리턴하는 리소스는 기본적으로 캐시와 관련된 헤더가 응답헤더에 추가되고, 기본적으로 이 응답은 10분동안 캐싱할거야. 리소스가 변경됐다면 10분이 지나지 않았어도 리소스를 다시 받아오겠지만.
                .resourceChain(true) //캐시를 쓸지 말지 , 운영은 true 개발은 false
//                .addResolver() //어떤 요청에 해당하는 리소스를 찾는 방법
//                .addTransformer() //응답으로 내보낼 리소스를 변경하는 방법.
        ;
    }

    // 추가적으로 http 메세지를 설정하고 싶다. 2가지 방법이 있는데 하나는 configure 메세지 컨버터를 사용하는건데.
    // 이걸 사용하면 컨버터를 등록할 수는 있는데 기본 컨버터는 다 사용 못하게 돼.
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }

    // 근데 기본 컨버터 + 추가적인걸 원하면 그냥 configure 말고 extent 를 사용 하면 돼
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.extendMessageConverters(converters);
    }
}
