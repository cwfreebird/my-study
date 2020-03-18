package com.cw.validator;

import lombok.Data;

import java.util.List;

/**
 * @author david.cai
 * @date 2019/5/30
 */
@Data
@ValidPassengerCount(message = "人数不能超过座位数")
public class Car {
    private int seat;

    private List<Person> persons;
}
