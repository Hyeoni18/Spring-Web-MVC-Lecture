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

    @ModelAttribute
    public void categories(Model model) {
        model.addAttribute("categories", List.of("study", "seminar", "hobby", "cosial")); //List.of는 java 9 이상부터 사용 가능
    }

    @ModelAttribute("categories") //이름은 여기에 정의 해주면 돼. 리턴 객체가 하나면 이런 식으로 하면 돼.
    public List<String> categoryList(Model model) { //굳이 void 가 아니라 리턴을 해줘도 돼.
        return List.of("study", "seminar", "hobby", "cosial");
    }

    @GetMapping("/events/form/name")
    @ModelAttribute //이렇게 어노테이션을 붙이면, 만약 모델 객체를 바로 리턴 하는 경우에
    public Event eventsFormName(Model model) {
        return new Event(); //이렇게 되면 리턴하는 객체를 자동으로 모델에 담아주는거야. 코드가 간결해짐. 사실 @ModelAttribute 어노테이션 생략해도 됨.
        //그럼 뷰 이름은? RequestToViewNameTranslator 라는 인터페이스가 요청의 이름과 정확히 일치하는 뷰 이름으로 리턴을 해줌. 그래서 사실 정확하지는 않아. 우리가 사용한 뷰 이름은 /events/form-name 이니까. 그냥 이런식으로도 할 수 있다,,, 자주 쓰이진 않음.
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
        redirectAttributes.addFlashAttribute("newEvent", event); // 객체를 넣을 수 있어. 그럼 이거는 session 에 들어가. 그리고 redirect 된 곳에서 사용이 되면 제거가 돼. 1회성 인거지. 그리고 session 으로 전달되기 때문에 uri 경로에 노출되지 않아.
        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model, @SessionAttribute LocalDateTime visitTime) {
        System.out.println(visitTime);

        Event spring = new Event();
        spring.setName("spring");
        spring.setLimit(10);

        Event event = (Event) model.asMap().get("newEvent");

        List<Event> eventList = new ArrayList<>();
        eventList.add(spring);
        eventList.add(event);
        model.addAttribute("eventList", eventList);

        return "/events/list";
    }
}
