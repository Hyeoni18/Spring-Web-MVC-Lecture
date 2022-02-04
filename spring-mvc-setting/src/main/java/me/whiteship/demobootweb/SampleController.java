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

    @GetMapping("/message")
    //@ResponseBody 가 붙어 있으면 응답을, 그니까 리턴하는 값을 응답의 본문으로 넣어줌. 그런데 이 컨트롤러는 Rest 를 쓰고 있기에 기본적으로 적용됨.
    public String message(@RequestBody String body) { //RequestBody 는 요청 본문에 들어있는 메세지를 HTTP 메시지 컨버터를 사용해서 컨버전을 함.
        return body;
    }

    @GetMapping("/jsonMessage")
    public Person jsonMessage(@RequestBody Person person) {
        return person;
    }

}
