package me.whiteship.demobootweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //그냥 쓰고 있는 핸들러 매핑이, 이렇게 빈을 등록한거와 마찬가지로 동작함
    //왜냐면 디스패처 서블릿에서 그냥 new 해서 등록한거랑 같은거.
    @Bean
    public HandlerMapping handlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
        //그런데 어떤거를 설정할 수 있는지는 bean 을 직접 등록할 때 알 수 있음.
        handlerMapping.setInterceptors(); //가장 흔히 사용되는 설정 중 하나.
        //핸들러 매핑은 어떤 요청이 들어왔을 때 처리할 수 있는 핸들러를 찾는 인터페이스임.
        //근데 핸들러를 찾아서 요층을 처리하기 전에 서블릿의 필터와 비슷한 핸들러 인터셉터가 저거임.
        //모든 핸들러 매핑에 설정을 할 수가 있음. 핸들러 인터셉터는 bean 으로 등록이 될 수 있기에 스프링 IoC의 장점을 활용할 수 있음.
        //그래서 서블릿보다 좀 더 유연할 수 있음.
        handlerMapping.setOrder(Ordered.HIGHEST_PRECEDENCE); //가장 높은 우선순위를 가지고 핸들러 매핑을 등록하게 됨.
        return handlerMapping;
    }

    //어댑터도 매핑과 같음.
    @Bean
    public HandlerAdapter handlerAdapter() {
        RequestMappingHandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();
        handlerAdapter.setMessageConverters(); //컨트롤러에서 @ReqeustBody 를 사용하면 여기에 해당하는 값은 요청 본문에 있는 메세지를 이 파라미터에 바인딩을 시켜줄 때 써. @ResponseBody 도 응답을 보낼 때 리턴한 값을 응답 본문에 쓸 때도 사용함.
        return handlerAdapter;
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan(Person.class.getPackageName());
        return jaxb2Marshaller;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GreetingInterceptor()).order(0);
        registry.addInterceptor(new AnotherInterceptor())
                .addPathPatterns("/hi")
                .order(-1);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mobile/**")
                .addResourceLocations("classpath:/mobile/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
    }

}
