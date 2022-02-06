package me.whiteship.demowebmvc;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public Event hello(@Validated(Event.ValidateName.class) @ModelAttribute Event event, BindingResult bindingResult) {
        //Event 에는 NotBlank 를 보면 그룹으로 지정할 수가 있음. 근데 @Valid 는 그룹을 설정할 수 없음.
        if(bindingResult.hasErrors()) {
            System.out.println("=========================================");
            bindingResult.getAllErrors().forEach( c -> System.out.println(c) );
        }
        return event;
    }
}
