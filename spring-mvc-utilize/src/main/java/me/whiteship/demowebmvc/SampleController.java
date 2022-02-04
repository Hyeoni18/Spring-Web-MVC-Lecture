package me.whiteship.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(method = RequestMethod.GET)
public class SampleController {

    @RequestMapping("/hello")
//    @RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.PUT}) //배열로 둘 다 허용한다. 라고 줄 수 있음.
//    @RequestMapping(value = "/hello", method = RequestMethod.GET) //url 패턴만 주면 모든 HTTP 메소드가 매핑이 됨. (get, post, delete...)
    @ResponseBody //그냥 응답으로 보내고 싶을 때
    public String hello() {
        return "hello";
    }
}
