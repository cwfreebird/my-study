package com.cw;

import com.cw.id.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author david.cai
 * @date 2018/7/18
 **/
@Slf4j
public class WorkIdTest {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");

    @Test
    public void genId() throws ParseException {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(11, 1);
        long id = idWorker.nextId(10000L);
        log.info("id {}, binary {}", id, Long.toBinaryString(id));

        doPareseId(id);
    }


    @Test
    public void genId10000(){
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(3, 1);
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            long id = idWorker.nextId(9);
            //log.info("id {}, binary {}", id, Long.toBinaryString(id));
        }
        Long end = System.currentTimeMillis();
        log.info("spend {} ms", end - start);//42ms
    }

    @Test
    public void currentTimeMillis() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-mm HH:mm:ss");
        Date date = sdf.parse("2038-01-01 00:00:00");
        log.info("currentTimeMillis binary {}", Long.toBinaryString(date.getTime()));
    }

    @Test
    public void and(){
        log.info("{}", Long.toBinaryString(377433879335731209L));
        log.debug("{}, {}", 0xFF & 5, Long.toBinaryString(5));
        log.error("{}, {}", 0xFF & 9989, Long.toBinaryString(9989));
        log.warn("{}, {}", 0xFF & 10000, Long.toBinaryString(10000));
    }

    /**
     * --------------------------------
     * | 1 | 39 | 1 | 7 | 8 | 8 |
     * --------------------------------
     */
    @Test
    public void parseId(){
        long id = 377547936839630864L;

        this.doPareseId(id);
    }

    private void doPareseId(Long id){
        log.info("id {}, binary {}", id, Long.toBinaryString(id));
        log.info(" ");
        log.info("======================recover start=====================================");
        log.info(" ");

        long recoverPartnerId = id & getBitMask(SnowflakeIdWorker.placeHoldBits);
        log.info("recoverPartnerId       : {}, binary {}", recoverPartnerId, Long.toBinaryString(recoverPartnerId));

        long sequence = id >>> SnowflakeIdWorker.sequenceIdShift;
        long recoverSequence = sequence & getBitMask(SnowflakeIdWorker.placeHoldBits);
        log.info("recoverSequence        : {}, binary {}", recoverSequence, Long.toBinaryString(recoverSequence));

        long workIdShift = id >>> SnowflakeIdWorker.workerIdShift;
        long recoverWorkId = workIdShift & getBitMask(SnowflakeIdWorker.workerIdBits);
        log.info("recoverWorkId          : {}, binary {}", recoverWorkId, Long.toBinaryString(recoverWorkId));

        long datacenterShift = id >>> SnowflakeIdWorker.dataCenterIdShift;
        long recoverDatacenterId = datacenterShift & getBitMask(SnowflakeIdWorker.datacenterIdBits);
        log.info("recoverDatacenterId    : {}, binary {}", recoverDatacenterId, Long.toBinaryString(recoverDatacenterId));

        long recoverTime = id >>> SnowflakeIdWorker.timestampShift;
        log.info("recoverTime            : {}, binary {}", recoverTime, Long.toBinaryString(recoverTime));

        long timestamp = SnowflakeIdWorker.twepoch + recoverTime;
        log.info("timestamp              : {}", sdf.format(new Date(timestamp)));
    }

    private long getBitMask(Long bit){
        return -1L ^ -1L << bit;
    }
}
