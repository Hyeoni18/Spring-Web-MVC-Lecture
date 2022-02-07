package me.whiteship.demowebmvc;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
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

    @ExceptionHandler({EventException.class,RuntimeException.class}) //여러 에러를 같이 처리하고 싶으면 여러 핸들러를 정의할 수 있음.
    public String eventErrorHandler(RuntimeException exception, Model model) { // 그럼 이럴 때는 둘 다 받을 수 있는 상위 타입으로 정의해야 해.
        model.addAttribute("message","runtime error");
        return "error";
    }

    @InitBinder("event") //그리고 여기에 값을 줄 수 있는데 여기에 주는 값을 문자열을 주면 이 모델객체, 이 이름의 모델 애트리뷰트를 바인딩 받을 때만 InitBinder 를 사용할 수 있음.
    public void initEventBinder(WebDataBinder webDataBinder) { //특이한 파라미터 하나로 받을 수 있음. WebDataBinder 는 반드시 있어야 함.
        //모든 요청 전에 이렇게 하면 InitBinder 라는 메소드를 호출하게 됨.
        //그리고 이 안에서 webDataBinder 를 사용해서 커스터마이징을 할 수 있음.
        webDataBinder.setDisallowedFields("id");  //먼저 바인딩과 관련된 설정. DisallowedFields를 사용하면 우리가 받고 싶지 않은 필드 값을 걸러낼 수 있음. 아이디는 이벤트를 저장할 때 생성하고 싶기 때문에 아이디 값을 폼이나 쿼리패스나 쿼리 파라미터나 받아오고 싶지 않음. 그래서 이렇게 설정을 해주면. 어디선가 보내더라도 걸러냄. 바인딩 하지 않음.
        //위는 블랙리스트 식으로 처리를 한거고 화이트리스트 기반으로 처리도 가능. 입력받고 싶은 필드만 정의 가능.
//        webDataBinder.setAllowedFields("limit");
        webDataBinder.addValidators(new EventValidator());
    }

    @ModelAttribute
    public void categories(Model model) {
        model.addAttribute("categories", List.of("study", "seminar", "hobby", "cosial")); //List.of는 java 9 이상부터 사용 가능
    }

    @ModelAttribute("categoryList") //이름은 여기에 정의 해주면 돼. 리턴 객체가 하나면 이런 식으로 하면 돼.
    public List<String> categoryList(Model model) { //굳이 void 가 아니라 리턴을 해줘도 돼.
        return List.of("study", "seminar", "hobby", "cosial");
    }

    @GetMapping("/events/form/name")
    public String eventsFormName(Model model) {
        throw new EventException(); //일부러 예외 발생.
//
//        Event event = new Event();
//        model.addAttribute("event", event);
//        return "/events/form-name";
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
