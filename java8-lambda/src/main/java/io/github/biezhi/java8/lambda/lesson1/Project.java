package io.github.biezhi.java8.lambda.lesson1;

import lombok.Builder;
import lombok.Data;

/**
 * 项目
 *
 * @author biezhi
 * @date 2018/2/9
 */
@Data
@Builder
public class Project {

    /**
     * 项目名称
     */
    private String  name;

    /**
     * 编程语言
     */
    private String  language;

    /**
     * star 数
     */
    private Integer stars;

    /**
     * 描述
     */
    private String  description;

    /**
     * 作者
     */
    private String  author;

}
