package io.github.biezhi.java8.optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 住址对象
 *
 * @author biezhi
 * @date 2018/2/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    /**
     * 街道
     */
    private String street;

    /**
     * 门牌
     */
    private String door;

}
