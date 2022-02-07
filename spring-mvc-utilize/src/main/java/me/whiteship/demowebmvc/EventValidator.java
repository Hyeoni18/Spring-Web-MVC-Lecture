package me.whiteship.demowebmvc;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EventValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) { //어떠한 도메인 클래스에 대한 벨리데이션을 지원하는거냐. 를 판단하는 메소드.
        return Event.class.isAssignableFrom(clazz); //이벤트 클래스를 벨리데이션 할 때 이 벨리데이션을 사용하겠다. 라고 구현한거야.
    }

    @Override
    public void validate(Object target, Errors errors) { //실제 벨리데이터라는 메소드가 호출될 때 이 타입으로 (타겟 객체)
        Event event  = (Event)target;   //우리가 검증해야 하는 타겟 객체가 들어와. 그럼 이벤트 객체를 검증한다고 했으니 이벤트 객체로 변환할 수 있어.
        if (event.getName().equalsIgnoreCase("aaa")) {  //여기서 커스텀한 벨리데이션을 하면 됨. 이름이 aaa 이기를 바라지 않아.
            errors.rejectValue("name", "wrongValue", "the value is not allowed"); //들어오면 값을 거부할거야.
        }
    }
}
