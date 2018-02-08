package io.github.biezhi.java8.growing.jdk7;

import io.github.biezhi.java8.growing.jdk5.EnumDemo;

/**
 * switch对String的支持
 *
 * @author biezhi
 * @date 2018/2/8
 */
public class SwitchString {

    public static void main(String[] args) {
        String bis = "java";
        switch (bis) {
            case "java":
                break;
            case "python":
                break;
            case "ruby":
                break;
            default:
                break;
        }

        EnumDemo enumDemo = EnumDemo.GREEN;

        switch (enumDemo) {
            case RED:
                break;
            case YELLOW:
                break;
            default:
                break;
        }
        
    }

}
