package hello.exmvc;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor //lombok 어노테이션을 사용하면, 컴파일 시점에 자동으로 getter, setter, 생성자, builder 등의 내용이 추가된 것을 확인할 수 있다.
public class Event {

    private String name;

    private int limitOfEnrollment;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;
}
