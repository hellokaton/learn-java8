package io.github.biezhi.java8.growing.jdk8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Nashorn JavaScript引擎
 *
 * @author biezhi
 * @date 2018/2/8
 */
public class NashornDemo {

    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine        engine  = manager.getEngineByName("JavaScript");

        System.out.println(engine.getClass().getName());
        System.out.println("Result:" + engine.eval("function f() { return 1; }; f() + 1;"));
    }
}
