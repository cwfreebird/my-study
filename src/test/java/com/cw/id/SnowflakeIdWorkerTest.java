package com.cw.id;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author david.cai
 * @date 2018/7/16
 **/
@Slf4j
public class SnowflakeIdWorkerTest {
    private Long bit1 = 5L;
    private Long bit1Shift = 0L;

    private Long bit2 = 10L;
    private Long bit2Shift = bit1;

    private Long bit3 = 10L;
    private Long bit3Shift = bit2Shift + bit2;

    @Test
    public void test2(){
        Long a = 100L;
        Long b = 30L;

        log.info("a = {}, binary = {}", a, Long.toBinaryString(a));
        log.info("b = {}, binary = {}", b, Long.toBinaryString(b));

        Long c = a << bit1;
        log.info("a << bit1 = {}, binary = {}", c, Long.toBinaryString(c));

        Long d = c | b;
        log.info("a | b = {}, binary = {}", d, Long.toBinaryString(d));

        Long d0 = d & (-1L ^ (-1L << bit1));
        log.info("d0 = {}, binary = {}", d0, Long.toBinaryString(d0));

        Long d1 = d >>> bit1;
        log.info("d >>> bit1 = {}, binary = {}", d1, Long.toBinaryString(d1));

        Long d2 = d1 & getBitsMask();
        log.info("d1 & bitsMask = {}, binary = {}", d2, Long.toBinaryString(d2));

    }

    /**
     * ----------------
     * | 10 | 10 | 5 |
     * ----------------
     */
    @Test
    public void test3(){
        Long a = 30L;
        Long b = 100L;
        Long c = 280L;

        log.info("a = {}, binary = {}", a, Long.toBinaryString(a));
        log.info("b = {}, binary = {}", b, Long.toBinaryString(b));
        log.info("c = {}, binary = {}", c, Long.toBinaryString(c));

        Long t1 = b << bit1;
        log.info("b << bit1 = {}, binary = {}", t1, Long.toBinaryString(t1));

        Long t2 = c << bit3Shift;
        log.info("c << bit2Shift = {}, binary = {}", t2, Long.toBinaryString(t2));

        Long t3 = a | t1 | t2;
        log.info("a | b | c = {}, binary = {}", t3, Long.toBinaryString(t3));

        log.info("======================还原=================================");

        Long recoverA = t3 & getBitsMaskA();
        log.info("******recoverA = {}, binary = {}", recoverA, Long.toBinaryString(recoverA));

        Long x2 = t3 >>> bit2Shift;
        Long recoverB = x2 & getBitsMaskB();
        log.info("******recoverB = {}, binary = {}", recoverB, Long.toBinaryString(recoverB));

        Long x3 = t3 >>> bit3Shift;
        Long recoverC = x3 & getBitsMaskC();
        log.info("******recoverC = {}, binary = {}", recoverC, Long.toBinaryString(recoverC));
    }

    private Long getBitsMaskA(){
        Long bitsMask = -1L ^ -1L << bit1;
        log.info("bitsMaskA = {}, binary = {}", bitsMask, Long.toBinaryString(bitsMask));
        return bitsMask;
    }

    private Long getBitsMaskB(){
        Long bitsMask = -1L ^ -1L << (bit2);
        log.info("bitsMaskB = {}, binary = {}", bitsMask, Long.toBinaryString(bitsMask));
        return bitsMask;
    }

    private Long getBitsMaskC(){
        Long bitsMask = -1L ^ -1L << (bit3);
        log.info("bitsMaskC = {}, binary = {}", bitsMask, Long.toBinaryString(bitsMask));
        return bitsMask;
    }

    private Long getBitsMask(){
        Long bitsMask = -1L ^ (-1L << (bit2));
        log.info("bitsMask = {}, binary = {}", bitsMask, Long.toBinaryString(bitsMask));
        return bitsMask;
    }
}
