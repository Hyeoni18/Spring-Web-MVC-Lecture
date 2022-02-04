package me.whiteship.demobootweb;

import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    // preHandle 1 전처리 작업
    // preHandle 2
    // 요청 처리
    // postHandler 2 후처리 작업
    // postHandler 1
    // 뷰 랜더링
    // afterCompletion 2 작업이 마무리 된 후
    // afterCompletion 1

    @GetMapping("/hello")
    public String hello(@RequestParam("id") Person person) {
        return "hello " + person.getName();
    }

   /* @GetMapping("/message")
    public String message(@RequestBody String body) {
        return body;
    }

    @GetMapping("/jsonMessage")
    public Person jsonMessage(@RequestBody Person person) {
        return person;
    }*/

}
