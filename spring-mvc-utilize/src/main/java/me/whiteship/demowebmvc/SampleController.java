package me.whiteship.demowebmvc;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.BindException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("event")
public class SampleController {

    @GetMapping("/events/form/name")
    public String eventsFormName(Model model) {
        model.addAttribute("event", new Event());
        return "/events/form-name";
    }

    @PostMapping("/events/form/name")
    public String eventsFromNameSubmit(@Validated @ModelAttribute Event event, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "/events/form-name";
        }

        return "redirect:/events/form/limit";
    }

    @GetMapping("/events/form/limit")
    public String eventsFormLimit(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);
        return "/events/form-limit";
    }

    @PostMapping("/events/form/limit")
    public String eventsFormLimitSubmit(@Validated @ModelAttribute Event event, BindingResult bindingResult
    , SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            return "/events/form-limit";
        }
        sessionStatus.setComplete();
        redirectAttributes.addAttribute("name", event.getName());
        redirectAttributes.addAttribute("limit",event.getLimit());
        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model, @SessionAttribute LocalDateTime visitTime
                        ,@ModelAttribute("newEvent") Event event) { //SessionAttributes 에서 사용한 이름이랑 같이 사용하면 안 돼. 세션에서 먼저 찾아봐. 근데 없기 때문에 error 가 날거야. Expected session attribute 'event'
        System.out.println(visitTime);

        Event spring = new Event();
        spring.setName("spring");
        spring.setLimit(10);

        List<Event> eventList = new ArrayList<>();
        eventList.add(spring);
        eventList.add(event);
        model.addAttribute("eventList", eventList);

        return "/events/list";
    }
}
