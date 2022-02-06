package me.whiteship.demowebmvc;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest extends TestCase {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void eventForm() throws Exception {
        MockHttpServletRequest request =
        mockMvc.perform(get("/events/form"))
                .andDo(print())
                .andExpect(view().name("/events/form"))
                .andExpect(model().attributeExists("event"))
                .andExpect(request().sessionAttribute("event", notNullValue()))
                .andReturn().getRequest()
        ;
        Object event = request.getSession().getAttribute("event");
        System.out.println(event);
    }
    @Test
    public void hello() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/events")
                        .param("name","boa")
                        .param("limit","-10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
        ;
        ModelAndView mav = resultActions.andReturn().getModelAndView();
        Map<String, Object> model = mav.getModel();
        System.out.println(model.size());
    }

    @Test
    public void getEvents() throws Exception {
        Event newEvent = new Event();
        newEvent.setName("Winter is coming");
        newEvent.setLimit(10000);

        mockMvc.perform(get("/events/list")
                .sessionAttr("visitTime", LocalDateTime.now())
                .flashAttr("newEvent", newEvent)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//p").nodeCount(2)); //p 노드가 2개 있는지 확인 (html 본문도 테스트 가능)
    }
}