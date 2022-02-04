package me.whiteship.demobootweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.oxm.Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import java.io.StringWriter;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ObjectMapper objectMapper;
    /*
        @Autowired
        Marshaller marshaller;

        @Test
        public void xmlMessage() throws Exception {
            Person person = new Person();
            person.setId(2019l);
            person.setName("keesun");

            StringWriter stringWriter = new StringWriter();
            Result result = new StreamResult(stringWriter);
            marshaller.marshal(person, result);
            String xmlString = stringWriter.toString();

            this.mockMvc.perform(get("/jsonMessage")
                    .contentType(MediaType.APPLICATION_XML)
                    .accept(MediaType.APPLICATION_XML)
                    .content(xmlString))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(xpath("person/name").string("keesun"))
                    .andExpect(xpath("person/id").string("2019"))
            ;
        }
    */
    @Test
    public void hello() throws Exception {
        Person person = new Person();
        person.setName("boa");
        Person savedPerson = personRepository.save(person);

        this.mockMvc.perform(get("/hello")
                    .param("id", savedPerson.getId().toString()))
                .andDo(print())
                .andExpect(content().string("hello boa"));
    }

   @Test
    public void helloStatic() throws Exception {
        this.mockMvc.perform(get("/index.html"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(Matchers.containsString("hello static/index")))
        ;
    }

    @Test
    public void helloMobile() throws Exception {
        this.mockMvc.perform(get("/mobile/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Hello mobile/index")))
                .andExpect(header().exists(HttpHeaders.CACHE_CONTROL))
        ;
    }

    @Test
    public void stringMessage() throws Exception {
        this.mockMvc.perform(get("/message")
                .content("hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"))
        ;
    }

    @Test
    public void jsonMessage() throws Exception {
        Person person = new Person();
        person.setId(2022l); //long 이라서 뒤에 l 을 붙였음.
        person.setName("myname");

        String jsonString = objectMapper.writeValueAsString(person); //잭슨이 제공. person 객체를 json 으로 바꾸고 싶을 때 사용.

        this.mockMvc.perform(get("/jsonMessage")
                //여러 가지 컨버터 중 어떤 컨버터를 사용해야 할지 판단할 때, 컨텐츠 타입과 헤더 정보를 참고
                        .contentType(MediaType.APPLICATION_JSON_UTF8) //내가 보내는 데이터.
                        .accept(MediaType.APPLICATION_JSON_UTF8) //응답으로 어떤 타입의 데이터를 원한다.
                        .content(jsonString))
    //                    .content("{\"id\":0,\"name\"")) //ObjectMapper 없이 그냥 이렇게 사용해도 돼.
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2022))
                .andExpect(jsonPath("$.name").value("myname"))
        ;
    }

}