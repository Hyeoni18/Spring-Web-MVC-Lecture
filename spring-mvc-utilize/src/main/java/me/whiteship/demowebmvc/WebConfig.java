package me.whiteship.demowebmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

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

}
