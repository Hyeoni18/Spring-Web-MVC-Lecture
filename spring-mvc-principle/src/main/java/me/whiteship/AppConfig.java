package me.whiteship;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(useDefaultFilters = false, includeFilters = @ComponentScan.Filter(Controller.class)) //default 필터를 사용하지 않고 오직 컨트롤러만 bean 으로 등록하겠다. 라고 설정
public class AppConfig {
}
