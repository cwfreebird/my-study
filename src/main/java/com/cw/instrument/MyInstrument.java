package com.cw.instrument;

import java.lang.instrument.Instrumentation;

/**
 * @author david.cai
 * @date 2018/8/3
 **/
public class MyInstrument {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new MyLogger());
        //inst.addTransformer(new MyClassFileTransformer());
        System.out.println("============premain==============");
    }
}
