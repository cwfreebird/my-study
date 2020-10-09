package com.cw;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author david.cai
 * @date 2018/8/3
 **/
@Slf4j
public class TestAll {
    @Test
    public void byteTest(){
        int n = 2;
        System.out.println(n >>> 1);
        System.out.println(n |= n >>> 1);
        System.out.println(n |= n >>> 2);
        System.out.println(n |= n >>> 4);
        System.out.println(n |= n >>> 8);
        System.out.println(n |= n >>> 16);
    }

    @Test
    public void map(){
        Map<String, String> map = new HashMap<>();

        for (int i = 1; i < 17; i ++){
            if (i == 12){
                System.out.println("=====" + i);
            }
            map.put("k_" + i, "v" + i);
        }
    }

    @Test
    public void linkedHashMap(){
        Map<String, String> map = new LinkedHashMap<>();

        for (int i = 1; i < 17; i ++){
            if (i == 12){
                System.out.println("=====" + i);
            }
            map.put("k_" + i, "v" + i);
        }
    }

    @Test
    public void string(){
        String s = "{\"fieldCode\":\"建昌镇县委\\县政府接待办\"}";
        Gson gson = new Gson();
        Str s1 = gson.fromJson(s, Str.class);
        Object parse = JSON.parse(s);
        parse.toString();
    }

    @Data
    class Str {
        String fieldCode;
    }
}
