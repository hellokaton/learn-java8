package io.github.biezhi.java8.optional;

import java.util.Optional;

/**
 * @author biezhi
 * @date 2018/2/11
 */
public class OptionalDemo {

    /**
     * 1. 创建 Optional
     */
    public void createOptional() {
        // 声明一个空的Optional
        Optional<Address> optionalAddress = Optional.empty();

        // 依据一个非空值创建Optional
        Optional<Address> optionalAddress2 = Optional.of(new Address());

        // 可接受null的Optional
        Optional<Address> optionalAddress3 = Optional.ofNullable(new Address());
    }

    /**
     * 2. 使用 map 从 Optional 对象中提取和转换值
     */
    public void map() {
        Optional<Address> addressOptional = Optional.ofNullable(new Address("达尔文路", "88号"));
        Optional<String>  street          = addressOptional.map(Address::getStreet);
    }

    /**
     * 3. 使用 flatMap 链接 Optional 对象
     */
    public void flatMap() {
        User           user         = new User();
        Optional<User> userOptional = Optional.of(user);
        // userOptional.map(User::getOptAddress).map(Address::getStreet);
        userOptional.flatMap(User::getOptAddress).map(Address::getStreet);
    }

    /**
     * 4. 默认行为及解引用 Optional 对象
     */

}
