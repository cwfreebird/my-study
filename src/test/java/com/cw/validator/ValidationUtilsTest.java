package com.cw.validator;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author david.cai
 * @date 2019/5/29
 */
public class ValidationUtilsTest {
    @Test
    public void validEntity(){
        Person person = Person.builder()
                .age(0)
                .name("12345678901")
                .build();
        ValidationUtils.valid(person);
    }

    @Test
    public void validMethod() throws NoSuchMethodException {
        Person person = Person.builder()
                .age(1)
                .name("1234567890")
                .build();

        Class<Person> personClass = Person.class;
        Method say = personClass.getDeclaredMethod("say", String.class);

        ValidationUtils.validMethod(person, say, new Object[]{""});
    }

    @Test
    public void validEntityByClass(){
        Car car = new Car();
        car.setSeat(5);

        List<Person> persons = Stream.of(1,2,3,4,5,6).map(i -> {
            return Person.builder()
                    .age(i)
                    .name("name" + i)
                    .build();
        }).collect(Collectors.toList());
        car.setPersons(persons);

        ValidationUtils.valid(car);
    }
}
