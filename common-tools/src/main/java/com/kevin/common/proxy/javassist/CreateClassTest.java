package com.kevin.common.proxy.javassist;

import java.lang.reflect.Method;

import javassist.*;

/**
 * @author kevin
 * @date 2019-08-19 14:56
 */
public class CreateClassTest {

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();

        // 1. 创建一个空类
        CtClass cc = pool.makeClass("com.kevin.common.javassist.Car");

        // 2. 新增一个字段 private String name = "init";
        CtField param = new CtField(pool.get("java.lang.String"), "name", cc); // 字段名为name
        param.setModifiers(Modifier.PRIVATE); // 访问级别是 private
        cc.addField(param, CtField.Initializer.constant("init")); // 初始值是 "init"

        // 3. 生成 getter、setter 方法
        cc.addMethod(CtNewMethod.setter("setName", param));
        cc.addMethod(CtNewMethod.getter("getName", param));

        // 4. 添加无参的构造函数
        CtConstructor cons = new CtConstructor(new CtClass[] {}, cc);

        cons.setBody("{name = \"Kail\";}");
        cc.addConstructor(cons);

        // 5. 添加有参的构造函数
        // http://jboss-javassist.github.io/javassist/tutorial/tutorial2.html#before
        cons = new CtConstructor(new CtClass[] {pool.get("java.lang.String")}, cc);
        cons.setBody("{$0.name = $1;}"); // $0=this / $1,$2,$3... 代表方法参数
        cc.addConstructor(cons);

        // 6. 创建一个名为execute方法，无参数，无返回值，输出name值
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "execute", new CtClass[] {}, cc);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(name);}");
        cc.addMethod(ctMethod);

        // 写入文件
        // cc.writeFile();
        // //读取文件调用
        // ClassPool pool2 = ClassPool.getDefault();
        // pool2.appendClassPath("/Users/kail/_test"); // 设置类路径
        // CtClass ctClass = pool2.get("xyz.kail.blog.CodeClass");
        // Object codeClass = ctClass.toClass().newInstance();
        Object codeClass = cc.toClass().newInstance(); // 这里不写入文件，直接实例化
        // 直接用 设置值
        Method setName = codeClass.getClass().getMethod("setName", String.class);
        setName.invoke(codeClass, "Mr.Kail");

        // 输出值
        Method execute = codeClass.getClass().getMethod("execute");
        execute.invoke(codeClass);

    }
}