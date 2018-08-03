package com.cw.job;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author david.cai
 * @date 2018/7/25
 **/
public class App {
    public static void main(String[] args) throws IOException {
        //new JobScheduler(createRegistryCenter(), createJobConfiguration()).init();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        context.start();
        System.in.read();
    }

    /**
     * 作业配置
     * @return
     */
    private static LiteJobConfiguration createJobConfiguration() {
        //作业核心配置
        JobCoreConfiguration coreConfiguration = JobCoreConfiguration.newBuilder("myElasticJob", "0/10 * * * * ?", 1)
                .build();
        //Simple类型对象
        SimpleJobConfiguration jobConfiguration = new SimpleJobConfiguration(coreConfiguration, MyElasticJob.class.getCanonicalName());
        //Lite作业对象
        LiteJobConfiguration rootConfig = LiteJobConfiguration.newBuilder(jobConfiguration).build();
        return rootConfig;
    }

    /**
     * 注册中心
     * @return
     */
    private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter registryCenter = new ZookeeperRegistryCenter(
                new ZookeeperConfiguration("10.199.197.224:2181", "MyElasticJob")
        );
        registryCenter.init();
        return registryCenter;
    }
}
