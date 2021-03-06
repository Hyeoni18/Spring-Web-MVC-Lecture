package hello.springjsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class EventController {

    @GetMapping("/events")
    public String getEvents(Model model) {
        Event event1 = new Event();
        event1.setName("스프링 웹 MVC 스터디 1");
        event1.setStart(LocalDateTime.of(2022,2,4,10,00));

        Event event2 = new Event();
        event2.setName("스프링 웹 MVC 스터디 2");
        event2.setStart(LocalDateTime.of(2022,2,4,14,00));

        List<Event> events = List.of(event1, event2);
        model.addAttribute("events", events);
        model.addAttribute("message", "Happy New Year");
        System.out.println(model);
        return "events/list";
    }
}
