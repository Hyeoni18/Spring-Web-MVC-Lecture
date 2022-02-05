package me.whiteship.demowebmvc;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // Junit 어노테이션, 스프링에서 제공하는 Junit Runner. 스프링 테스트 실행할 때 효율적으로 실행할 수 있도록 도와줌. 내부적으로 애플리케이션 컨텍스트도 만들어줌. 테스트에서 사용할.
@WebMvcTest //web만 테스트 mock 을 사용할 수 있게 해줌.
public class SampleControllerTest extends TestCase {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {
        mockMvc.perform(get("/hello/boa.jsp"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello boa"))
                .andExpect(handler().handlerType(SampleController.class)) //핸들러 타입은 컨트롤러가 처리할 것이고
                .andExpect(handler().methodName("hello")) //핸들러의 메서드 이름
        ;
    }

}