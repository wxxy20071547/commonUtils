package com.kevin.common.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.Method;

/**
 * @author kevin
 * @date 2019-08-19 15:22
 */
public class ModifyClassTest {

    public static void main(String[] args) throws Exception {

        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.kevin.common.javassist.Point");
        CtMethod m = cc.getDeclaredMethod("move");
        m.insertBefore("org.slf4j.LoggerFactory.getLogger(com.kevin.common.javassist.Point.class).info(\"--开始打印\");");
        m.insertAfter("org.slf4j.LoggerFactory.getLogger(com.kevin.common.javassist.Point.class).info(\"--打印完成\");");
        // cc.writeFile();
        Object codeClass = cc.toClass().newInstance(); // 这里不写入文件，直接实例化
        Method setName = codeClass.getClass().getMethod("move", int.class, int.class);
        setName.invoke(codeClass, 1, 2);
    }


}
