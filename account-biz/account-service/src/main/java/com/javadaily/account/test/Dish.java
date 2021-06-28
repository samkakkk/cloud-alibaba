package com.javadaily.account.test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * <code>Dish</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/3/16 11:28
 */
@Data
@AllArgsConstructor
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;


    public enum Type {
        MEAT,
        FISH,
        OTHER
    }
}



