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

        //본문에 있는 객체를 ObjectMapper 로 어떻게 스트링으로 변환할 수 있냐면
        String json = objectMapper.writeValueAsString(event); //이렇게 하면 객체를 JSON 문자열로 변환할 수 있음.

        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //사용할 컨버터를 선택할 때 필요한 컨텐츠 타입을 알려주는게 좋아.
                .content(json)  //본문을 채우는거야
            ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("naming")) //값을 확인
                .andExpect(jsonPath("limit").value(-20));
    }

}