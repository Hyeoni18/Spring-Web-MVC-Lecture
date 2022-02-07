package me.whiteship.demowebmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SampleApiTest extends TestCase {

    @Autowired
    ObjectMapper objectMapper; //jackson2Present 에서 제공해주는 API 인데, 이걸 사용하면 객체를 JSON 문자열로 바꿀 수도 있고 JSON 문자열을 객체로 바꿀 수도 있음. 스프링 내부에서 bean 으로 등록해주기에 그냥 가져다 쓰기만 하면 됨. (JacksonAutoConfiguration 에서 설정된 것을 볼 수 있음)

    @Autowired
    MockMvc mockMvc;

    @Test
    public void createEvent() throws Exception {
        Event event = new Event();
        event.setName("naming");
        event.setLimit(-20);

        String json = objectMapper.writeValueAsString(event);

        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
            ).andDo(print())
                .andExpect(status().isBadRequest()) //값이 잘못되었을 경우 bedRequest 가 나올거야.
        ;
    }

}