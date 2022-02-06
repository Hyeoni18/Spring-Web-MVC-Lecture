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
    public void helloTest() throws Exception {
        mockMvc.perform(options("/hello")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.ALLOW)) //헤더가 있는지 확인
        //        .andExpect(header().stringValues(HttpHeaders.ALLOW,"GET,HEAD,POST,OPTIONS")) //순서가 다르면 error 남.
                //순서 상관없이 찾고 싶다면.
                .andExpect(header().stringValues(HttpHeaders.ALLOW,
                        hasItems(containsString("GET"),
                                containsString("POST"),
                                containsString("HEAD"),
                                containsString("OPTIONS")
                        )
                ))
        ;
    }

}