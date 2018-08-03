package com.cw.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;

/**
 * @author david.cai
 * @date 2018/7/25
 **/
@Slf4j
public class MyElasticJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("======shardingContext : {}", shardingContext);
    }
}
