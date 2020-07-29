package com.cw.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author david.cai
 * @date 2020/5/3
 */
@Slf4j
public class JedisClusterTest {
    public static void main(String[] args) {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("10.199.197.224", 7000));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);

        for (int i = 1; i < 5000; i ++) {
            jc.set("key_" + i, "bar_" + i);
        }

        //String value = jc.get("foo");

        log.info("************value:{}", jc.get("key_" + 4999));
    }
}
