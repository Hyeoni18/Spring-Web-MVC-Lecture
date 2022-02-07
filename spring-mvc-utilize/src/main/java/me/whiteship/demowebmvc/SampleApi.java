package me.whiteship.demowebmvc;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/events")
public class SampleApi {


    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody @Valid Event event, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build(); //테스트로 돌아가서
            //factory 메소드가 헷갈리는게 어떤건 build 를 해야 완성이 되는게 있어. ok 는 그냥 해야 되던데.
        }

        //return ResponseEntity.ok().build(); //body 를 채워서 요청 만드는게 끝났다고 생각하는건가? 오. 바디가 비워져 있으면 build 를 해야 함.
        return new ResponseEntity<Event>(event,HttpStatus.CREATED); //원래는 HttpStatus.CREATED 로 보낼 때 URI 를 만들어서 보내줘야 하는데 (location 헤더에) 근데 이렇게 하면 그냥 보낼 수 있지만 좋은 방법은 아님. 그냥 상태코드를 바꿔서 보낼 수 있다는 것을 보여주려고 실습함. 201 created 응답을 볼 수 있음.
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
