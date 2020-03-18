package com.cw.validator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.inject.Named;
import javax.validation.constraints.*;

/**
 * @author david.cai
 * @date 2019/5/29
 */
@Data()
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person{
    @NotEmpty
    @Size(max = 10)
    private String name;

    @Min(1)
    @Max(60)
    private int age;

    public String say(@NotBlank @Size(min = 5) @Named("hello") String hello){
        return hello + "," + name;
    }
}
