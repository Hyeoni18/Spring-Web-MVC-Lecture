package me.whiteship.demowebmvc;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.BindException;
import java.util.ArrayList;
import java.util.List;
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

    @PostMapping("/events")
    public String createEvent(@Validated @ModelAttribute Event event, BindingResult bindingResult
                        ,Model model) {
        if(bindingResult.hasErrors()) {
            return "/events/form";
        }
        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        model.addAttribute("eventList", eventList);

        //여기서는 DB에 save 를 했다는 가정
        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model) {
        //DB에서 값을 조회했다는 가정.
        Event event = new Event();
        event.setName("spring");
        event.setLimit(10);

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        model.addAttribute("eventList", eventList);

        return "/events/list";
    }
}
