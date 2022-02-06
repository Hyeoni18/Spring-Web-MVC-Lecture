package me.whiteship.demowebmvc;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasItems;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest extends TestCase {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void eventForm() throws Exception {
        mockMvc.perform(get("/events/form"))
                .andDo(print())
                .andExpect(view().name("/events/form"))
                .andExpect(model().attributeExists("event"));
    }
    @Test
    public void hello() throws Exception {
        mockMvc.perform(post("/events/name/nnnnn")
                        .param("limit","-10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("nnnnn"))
        ;
    }

}