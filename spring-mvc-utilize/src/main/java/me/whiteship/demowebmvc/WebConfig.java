package me.whiteship.demowebmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) { //해당 메소드를 재정의 하면 됨.
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false); //이걸 세미콜론을 제거하지 않도록 설정하고
        configurer.setUrlPathHelper(urlPathHelper); //configurer에 설정해주면 돼.
        //기본적으로는 세미콜론을 없애나 봄. 세미콜론이 사라지면 MatrixVariable 바인딩이 안 돼.
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VisitTimeInterceptor());
        //내가 만든 인터셉터 적용
    }

    //내가 원하는 컨버터 추가.
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.extendMessageConverters(converters);
    }

    //이거를 쓰면 기본 메세지 컨버터를 아예 안 쓰게 되니 이건 가급적이면 쓰지 말자.
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }
}
