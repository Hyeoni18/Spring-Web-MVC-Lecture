package hello.exmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {

    @Autowired
    EventService eventService;

    //getMapping 에 requestMapping 이 붙어 있음. 스프링 4.3 이후 부터 추가된 기능.
    @GetMapping("/events")
    public String events(Model model) {
        //모델을 컨트롤러에 제공할 때 이곳에 직접 코딩을 해도 됨. 그렇지만 실습은 Service를 만들어서 사용할거야.
        model.addAttribute("events", eventService.getEvents());
        return "events"; //view를 찾는 문자열.
    }
}
