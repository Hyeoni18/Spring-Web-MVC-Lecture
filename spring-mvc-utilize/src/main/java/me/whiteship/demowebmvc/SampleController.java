package me.whiteship.demowebmvc;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.BindException;
import java.util.Map;

@Controller
public class SampleController {

    @GetMapping("/events/form")
    public String eventsForm(Model model) {
        Event newEvent = new Event();
        newEvent.setLimit(50);
        model.addAttribute("event", newEvent);
        return "/events/form";
    }

    @PostMapping("/events/name/{name}")
    @ResponseBody
    public Event hello(@Valid @ModelAttribute Event event, BindingResult bindingResult) {
        //limit 에 최소 값 0을 설정해두고 - 값이 넘어올 때. 바인딩이 되긴 함.
        //이후 @Valid 이 실행 되는거임. 그리고 여기서 오류가 나도 bindingResult 에 담김.
        //그래서 응답이 끝난 후 limit 에 값이 담겨있긴 하지만 error 도 담긴 것을 볼 수 있음
        //Field error in object 'event' on field 'limit': rejected value [-10]; codes [Min.event.limit,Min.limit,Min.java.lang.Integer,Min]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [event.limit,limit]; arguments []; default message [limit],0]; default message [must be greater than or equal to 0]
        if(bindingResult.hasErrors()) {
            System.out.println("=========================================");
            bindingResult.getAllErrors().forEach( c -> System.out.println(c) );
        }
        return event;
    }
}
