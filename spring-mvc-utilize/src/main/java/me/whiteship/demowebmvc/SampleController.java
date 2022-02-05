package me.whiteship.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(method = RequestMethod.GET, value = "hello")
public class SampleController {

    @RequestMapping("/boa")
    @ResponseBody
    public String hello() {
        return "hello boa";
    }

//    @RequestMapping("/**")
//    @ResponseBody
//    public String helloName() {
//        return "hello myname";
//    }
}
