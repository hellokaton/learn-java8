package io.github.biezhi.java8.growing.jdk6;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;

/**
 * 对脚本语言的支持
 *
 * @author biezhi
 * @date 2018/2/8
 */
public class ScriptEngineDemo {

    public static void main(String[] args) {

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine        engine  = manager.getEngineByName("ECMAScript");
        try {
            String jsPath = ScriptEngineDemo.class.getResource("/test.js").getPath();

            engine.eval(new FileReader(jsPath));

            Invocable invokableEngine = (Invocable) engine;

            Object ret = invokableEngine.invokeFunction("test", null);

            System.out.println("The result is : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}  