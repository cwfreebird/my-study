package com.cw.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author david.cai
 * @date 2018/8/3
 **/
public class MyLogger implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?>
            classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
        System.err.println("================className:" + className);
        return null;
    }
}
