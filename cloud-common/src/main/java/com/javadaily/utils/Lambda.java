package com.javadaily.utils;

/**
 * <p>
 * <code>Lambda</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/3/10 10:28
 */
public class Lambda {
    public static void main(String[] args) {
        process(() -> System.out.println("hello"));
    }

    public static void process(Runnable r){
        r.run();
    }
}
