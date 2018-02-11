package io.github.biezhi.java8.optional;

import java.util.Optional;
import java.util.Properties;

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
//        userOptional.map(user -> user.getOptAddress())
        Optional<String> stringOptional = userOptional.flatMap(User::getOptAddress).map(Address::getStreet);

    }

    /**
     * 4. 默认行为及解引用 Optional 对象
     */
    public void defaultValue() {
        Optional<Address> addressOptional = Optional.ofNullable(null);
        String            street          = addressOptional.map(Address::getStreet).orElse("北京二环");
        System.out.println(street);
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("biezhi");
        user.setPassword("123456");
        user.setOptAddress(Optional.of(new Address("达尔文路", "88号")));
        user.setAge(30);

//        Address address1 = null;
//        try {
//            address1 = user.getOptAddress().filter(address -> address.getDoor().contains("878"))
//                    .orElseThrow(new Supplier<Throwable>() {
//                        @Override
//                        public Throwable get() {
//                            return new Exception("挂了");
//                        }
//                    });
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        System.out.println(address1);

        System.out.println(getStreet(Optional.of(user), 50));
    }

    public static String getStreet(Optional<User> user, int minAge) {
        return user.filter(u -> u.getAge() >= minAge)
                .flatMap(User::getOptAddress)
                .map(Address::getStreet)
                .orElse("没有");
    }

    public static Optional<Integer> parseInt(String value) {
        try {
            return Optional.ofNullable(Integer.parseInt(value));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public int readPoint(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalDemo::parseInt)
                .filter(i -> i > 0)
                .orElse(0);
    }

}
