package me.whiteship;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan
public class WebConfig {

    //뷰리졸버를 등록해볼거야.
    @Bean
    public ViewResolver viewResolver() {
        //우리가 기본으로 사용하게 되는 리졸버가 맞아.
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //근데 우리가 설정을 조금 해볼거야.
        viewResolver.setPrefix("/WEB-INF/"); //항상 여기 밑에 두겠다.
        viewResolver.setSuffix(".jsp"); //다 jsp 로 끝난다.
        return viewResolver;
    }
}
