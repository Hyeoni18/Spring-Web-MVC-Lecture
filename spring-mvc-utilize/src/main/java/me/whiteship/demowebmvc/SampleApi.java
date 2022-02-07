package me.whiteship.demowebmvc;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class SampleApi {


    @PostMapping
    public Event createEvent(@RequestBody @Valid Event event, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error);
                //이렇게 하면 error 처리를 커스텀 하게 할 수 있음.
            });
        }
        return event;
    }

//    //근데 HttpEntity 를 사용하게 되면
//    @PostMapping
//    public Event createEvent(HttpEntity<Event> request) {
//        request.getBody(); //이렇게 해주면 제네릭 타입에 지정을 해준 본문이 나옴. 그리고 본문의 타입으로 컨버전도 해줌.
//        request.getHeaders(); //RequestBody 랑 다른 점은 헤더에도 접근을 할 수 있음.
//        MediaType contentType = request.getHeaders().getContentType();
//        System.out.println(contentType);
//        //이렇게 하면 어노테이션을 쓰지 않아도 되지만 바디 타입을 지정해줘야 해.
//        return request.getBody();
//    }
}
