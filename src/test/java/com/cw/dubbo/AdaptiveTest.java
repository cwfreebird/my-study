package com.cw.dubbo;

import com.cw.dubbo.adaptive.IAdaptive;
import com.cw.dubbo.spi.Animal;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.util.ServiceLoader;
import java.util.Spliterator;

import static java.util.ServiceLoader.load;
import static java.util.stream.StreamSupport.stream;

/**
 * @author david.cai
 * @date 2020/8/3
 */
@Slf4j
public class AdaptiveTest {
    @Test
    public void test1(){
        ExtensionLoader<IAdaptive> loader = ExtensionLoader.getExtensionLoader(IAdaptive.class);
        IAdaptive ext2 = loader.getAdaptiveExtension();
        URL url = URL.valueOf("http://localhost/test");
        log.info("{}", ext2.hello("hello", url));
    }

    @Test
    public void test2(){
        ExtensionLoader<IAdaptive> loader = ExtensionLoader.getExtensionLoader(IAdaptive.class);
        IAdaptive ext2 = loader.getAdaptiveExtension();
        URL url = URL.valueOf("http://localhost/test?i.adaptive=spring");
        log.info("{}", ext2.hello("hello", url));
    }

    @Test
    public void load(){
        ServiceLoader<Animal> load = ServiceLoader.load(Animal.class);

        for (Animal animal : load){
            log.info("===={}", animal.yield("hello"));
        }
    }
}
