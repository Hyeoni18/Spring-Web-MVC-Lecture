package me.whiteship.demowebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(consumes = MediaType.APPLICATION_XML_VALUE)
public class SampleController {

    @RequestMapping(
            value = "/hello",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE //flain 텍스트를 원하는 요청만 처리
    ) //문자열로 application/json 이렇게 써줘도 되지만 오타가 날 수도 있고 귀찮기도 하니 MediaType.class 활용.
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
