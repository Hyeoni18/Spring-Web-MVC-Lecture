package me.whiteship.demowebmvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    // "/hello?name=boa" 요청에 보내는 파라미터들. 도 헤더와 마찬가지로 적용할 수 있음.

    @RequestMapping(
            value = "/hello"
            //,headers = HttpHeaders.FROM //문자열을 줄 수 있어. 이 헤더가 들어있는 요청만 처리할거야.
            //,headers = HttpHeaders.ACCEPT_LANGUAGE //그냥 ACCEPT 는 걸러지지가 않아. 응답 쪽에서 명시적으로 보내지 않더라도 매칭이 됨.
            //,headers = "!"+HttpHeaders.AUTHORIZATION // 이게 없어여 된다. ! 을 사용할 수 있음.
            ,headers = HttpHeaders.FROM + "=localhost" //정확히 일치하는 경우
            ,params = "name=spring"
    )
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
