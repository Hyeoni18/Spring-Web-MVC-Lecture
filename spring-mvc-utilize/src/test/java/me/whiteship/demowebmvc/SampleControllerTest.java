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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class) // Junit 어노테이션, 스프링에서 제공하는 Junit Runner. 스프링 테스트 실행할 때 효율적으로 실행할 수 있도록 도와줌. 내부적으로 애플리케이션 컨텍스트도 만들어줌. 테스트에서 사용할.
@WebMvcTest //web만 테스트 mock 을 사용할 수 있게 해줌.
public class SampleControllerTest extends TestCase {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {
        mockMvc.perform(post("/hello")) //mockMvc 에서 perform 을 하면 요청을 보내는건데, get으로 요청을 만들어 보낼 수 있음.
                .andDo(print())  //요청을 만들어 보내면 그 다음 이걸 하고 싶다. 요청과 응답을 출력하고 싶다.
//                .andExpect(status().isOk()) //어떤 결과를 기대한다.
                .andExpect(status().isMethodNotAllowed())
//                .andExpect(content().string("hello")) //본문의 있는 문자열이 hello 라고 나올 것이다.
        ;

        mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
        ;

        mockMvc.perform(put("/hello"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed())
        ;
    }

}