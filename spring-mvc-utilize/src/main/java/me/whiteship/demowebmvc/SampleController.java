package me.whiteship.demowebmvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

@Controller
public class SampleController {

    @GetMapping("/events")
    @ResponseBody
    public ResponseEntity<String> events() {
        ResponseEntity<String> build = ResponseEntity.ok().build(); //ok 응답을 보내면 ResponseEntity가 만들어짐.
        //ResponseEntity 에는 status 코드, 응답 헤더, 응답 본문 등을 다 세팅할 수 있음.
        //RestAPI 를 좀 더 심도 있게 만드려면 결국에 ResponseEntity를 쓰게 됨.
        //그냥 단순한 타입을 넘기려면 String 반환값을 적어줘도 되지만, 우리가 어떤 조건에 따라 (핸들러 로직에 따라) status 코드도 다르게 하고 싶고, 응답 헤더도 다르게 하고 싶고, 응답 본문도 다르게 하고 싶으면 ResponseEntity 를 쓰게 될거야.
        return build;
    }

    @GetMapping("/events/{id}")
    @ResponseBody
    public String eventWithId(@PathVariable int id) {
        return "events is "+id;
    }

    @GetHelloMapping
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @DeleteMapping("/events/{id}")
    @ResponseBody
    public String removeEvents(@PathVariable int id) {
        return "event "+ id;
    }

}
