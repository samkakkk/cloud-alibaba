package com.javadaily.account.test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * <p>
 * <code>StreamTest</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/3/16 11:30
 */
public class StreamTest {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

        Map<Boolean, List<Dish>> collect = menu.stream().collect(groupingBy(Dish::isVegetarian));
        System.out.println(collect);


        Map<Boolean, Dish> collect1 = menu.stream().collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("=============");
        System.out.println(collect1);

        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("cpu:  "+i);


        //循环合并
        //短路技巧
        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(dish -> {
                    System.out.println("filting:"+dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping:"+dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(threeHighCaloricDishNames);

        Integer reduce = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println(reduce);


//        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
//        List<Integer> collect = words.stream().map(String::length).collect(Collectors.toList());
//        System.out.println(collect);

        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);

    }



}
