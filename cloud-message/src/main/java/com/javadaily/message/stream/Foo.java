package com.javadaily.message.stream;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * <p>
 * <code>Foo</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/3/31 9:52
 */
@Data
@Builder
public class Foo {
    private String code;
    private String name;

    /**
     * 加入了Builder后会生产全属性的构造方法
     * 使用Tolerate会生成无参构造器
     */
    @Tolerate
    Foo(){}
}
