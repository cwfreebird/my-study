package com.cw.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author david.cai
 * @date 2018/8/3
 **/
public class MyClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?>
            classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
        if (!className.endsWith("InstumentTest2")){
            return null;
        }

        for (int i = 0; i < classfileBuffer.length; i++) {
            System.out.print(Byte.toString(classfileBuffer[i]) + " ");
            if (i > 0 && i % 50 == 0){
                System.out.println("");
            }

            if (Byte.toString(classfileBuffer[i]).equals("120")){
                System.out.println("");
                System.out.println("==========data change=======");
                classfileBuffer[i] = (byte)'y';
            }
        }
        System.out.println("");
        System.out.println("The number of bytes in InstumentTest2:" + classfileBuffer.length);

        return (classfileBuffer);
    }
}
