package me.whiteship.demowebmvc;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice(assignableTypes = {SampleController.class, SampleApi.class}) //그럼 이게 전역 컨트롤러가 됨. 그리고 적용할 범위도 지정할 수 있음 (스프링 4.0 부터 허용), 또는 패키지 이름으로도 적용할 수 있음.
public class BaseController {

    // 여기에 핸들러를 넣어주면 됨.
    @ExceptionHandler({EventException.class, RuntimeException.class})
    public String eventErrorHandler(RuntimeException ex, Model model) {
        model.addAttribute("message", "runtime error");
        return "error";
    }

    @InitBinder("event")
    public void initEventBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
        webDataBinder.addValidators(new EventValidator());
    }

    @ModelAttribute
    public void categories(Model model) {
        model.addAttribute("categories", List.of("study", "seminar", "hobby", "social"));
    }

    @ModelAttribute("categoryList")
    public List<String> categoryList(Model model) {
        return List.of("study", "seminar", "hobby", "cosial");
    }
}
