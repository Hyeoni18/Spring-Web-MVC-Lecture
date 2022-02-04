package me.whiteship.demobootweb;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PersonFormatter implements Formatter<Person> {
    //이 포메터는 사실상 두 가지의 인터페이스를 하나로 합친거임. parse 와 print. 어떻게 변환해서 보여줄거냐.
    @Override
    public Person parse(String name, Locale locale) throws ParseException {
        Person person = new Person();
        person.setName(name);
        return person;
    }

    @Override
    public String print(Person person, Locale locale) {
        return person.toString(); //person 에 toString이 구현되어 있어야 함.
    }
}
