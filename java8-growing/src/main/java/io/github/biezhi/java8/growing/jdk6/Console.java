package io.github.biezhi.java8.growing.jdk6;

/**
 * 用Console开发控制台程序
 *
 * @author biezhi
 * @date 2018/2/8
 */
public class Console {

    public static void main(String[] args) {

        java.io.Console console = System.console();

        if (console != null) {
            String user = new String(console.readLine(" Enter User: ", new Object[0]));
            String pwd  = new String(console.readPassword(" Enter Password: ", new Object[0]));
            console.printf(" User name is:%s ", new Object[]{user});
            console.printf(" Password is:%s ", new Object[]{pwd});
        } else {
            System.out.println(" No Console! ");
        }

    }
}