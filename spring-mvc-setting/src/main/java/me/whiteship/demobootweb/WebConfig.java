//package me.whiteship.demobootweb;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.http.CacheControl;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.oxm.jaxb.Jaxb2Marshaller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.HandlerMapping;
//import org.springframework.web.servlet.config.annotation.*;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
////@EnableWebMvc //Configuration 이 붙은 클래스에 붙여주면 돼.
//public class WebConfig implements WebMvcConfigurer{
//
//    @Bean
//    public Jaxb2Marshaller jaxb2Marshaller() {
//        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
//        jaxb2Marshaller.setPackagesToScan(Person.class.getPackageName());
//        return jaxb2Marshaller;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new GreetingInterceptor()).order(0);
//        registry.addInterceptor(new AnotherInterceptor())
//                .addPathPatterns("/hi")
//                .order(-1);
//    }
//
//    //확장 포인트 중 하나, 뷰 리졸버와 관련 있는 메소드.
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        //이렇게 커스터마이징을 쉽게 할 수 있음.
//        registry.jsp("/WEB-INF", ".jsp");
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/mobile/**")
//                .addResourceLocations("classpath:/mobile/")
//                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
//    }
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new PersonFormatter());
//        //registry.addConverter(); //컨버터도 추가 가능, 포메터보다 좀 더 일반적인 용도임. 굳이 문자열이 아닌 일반적인 객체에서 또 다른 일반적인 객체로 변환할 수 있는 인터페이스임. 필요한 경우에 추가해서 쓰면 됨.
//    }
//}
