package io.github.biezhi.java8.lambda.lesson3;

import java.util.function.Supplier;

/**
 * 构造器引用
 * <p>
 * 对于一个现有构造函数， 你可以利用它的名称和关键字 new 来创建它的一个引用： ClassName::new。
 * 它的功能与指向静态方法的引用类似。
 *
 * @author biezhi
 * @date 2018/2/10
 */
public class ConstructorReference {

    public static void main(String[] args) {
        //构造器引用
        //根据参数列表自动匹配构造器
        Supplier<ConstructorReference> sup = ConstructorReference::new;
        ConstructorReference constructorReference = sup.get();
    }

}
