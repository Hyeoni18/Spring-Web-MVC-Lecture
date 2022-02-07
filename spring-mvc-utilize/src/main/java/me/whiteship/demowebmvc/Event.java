package me.whiteship.demowebmvc;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class Event {

    private Integer id;

    @NotBlank
    private String name;

    @Min(0)
    private Integer limit;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) //내가 받고 싶은 포매팅을 이 어노테이션으로 하면 됨. 날짜에 대한 값을 어떻게 받을거냐. 문자열이잖아. 그 문자열의 패턴을 정의하는거지. pattern = "yyyy-MM-dd" 이렇게 해도 되는데 저렇게 정의 할거면 iso 로 하는게 좋음. 타입 세이프하게 이렇게 작성하는게 좋아.
    private LocalDate startDate; //지원하긴 함. 근데 보통 포매터 설정을 날짜 타입 때문에 하니까. 날짜 포매팅을 알아보자.

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
