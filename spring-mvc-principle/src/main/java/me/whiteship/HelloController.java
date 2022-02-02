package me.whiteship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() { //public @ResponseBody String hello() 처럼 리턴 타입 위치에 적어도 돼, 동일함. 리턴 타입이 되거나 그런거 아님.
        return "Hello, " + helloService.getName();
    }

    @GetMapping("/sample")
    public String sample() {
        return "/WEB-INF/sample.jsp"; // return WEB-INF/sample.jsp; 이라고 작성 했을 때 view 로 인식을 못해서 error 발생.
    }

    //둘 다 문자열을 리턴하지만 ResponseBody 의 유무 차이가 있음.
}
