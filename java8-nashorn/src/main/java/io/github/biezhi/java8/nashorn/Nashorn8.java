package io.github.biezhi.java8.nashorn;

import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Benjamin Winterberg
 */
public class Nashorn8 {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("load('java8-nashorn/src/main/resources/nashorn8.js')");

        engine.invokeFunction("evaluate1");                             // [object global]
        engine.invokeFunction("evaluate2");                             // [object Object]
        engine.invokeFunction("evaluate3", "Foobar");                   // Foobar
        engine.invokeFunction("evaluate3", new Person("John", "Doe"));  // [object global] <- ???????
    }

}