package me.whiteship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//이 컨트롤러를 쓰고 싶어. 어떤 요청을 처리하는 핸들러를 이런식으로 만들고 싶어. 그럼 우리는 스프링 MVC를 써야하는거야.
//그럼 이런 핸들러 쪽으로 요청을 dispatch 해줄 수 있는 , @RestController, @GetMapping 어노테이션을 이해하는 , return 응답을 HttpResponse 로 만들어줄 수 있는 DispatcherServlet 을 써야한다는거야.
@RestController
public class HelloController {

    @Autowired
    HelloService helloService; //bean 으로 등록된 거 가져다 씀.

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello, " + helloService.getName();
    }

    @GetMapping("/sample")
    public void sample() {}

}
