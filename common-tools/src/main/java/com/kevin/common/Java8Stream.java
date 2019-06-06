package com.kevin.common;

import com.google.common.collect.Lists;
import com.kevin.common.domain.People;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by kevin on 2017/11/29.
 */
@Data
public class Java8Stream {

    public List<String> getPeopleNames(List<People> peopleList) {
        return peopleList.stream().map(People::getName).collect(Collectors.toList());
    }

    public Map<String, List<People>> groupBySex(List<People> peoples) {
        return peoples.stream().collect(Collectors.groupingBy(People::getSex));
    }

    public List<People> chooseSexPeoples(String sex, List<People> peoples) {
        return peoples.stream().filter(o -> StringUtils.equals(sex, o.getSex())).collect(Collectors.toList());
    }

    public Map<String, Optional<People>> getMaxAgeGroupBySex(List<People> peoples) {
        return peoples.parallelStream().collect(Collectors.groupingBy(People::getSex, Collectors.maxBy(Comparator.comparing(People::getAge))));
    }

    public Map<String, People> getPeopleMapByName(List<People> peoples) {
        return peoples.parallelStream().collect(Collectors.toMap(People::getName, Function.identity(), (newValue, oldValue) -> newValue));
        //return peoples.parallelStream().collect(Collectors.toMap(People::getName, Function.identity());

    }

    public int getTotalAge(List<People> peoples) {
        return peoples.stream().mapToInt(People::getAge).sum();
    }

    /**
     * option's simple use
     *
     * @param people
     * @return
     */
    public String getSex(People people) {
        return Optional.ofNullable(people).map(p -> p.getSex()).orElse(null);
    }


    /**
     * 先分组再累加某些属性
     *
     * @param list
     * @return
     */
    public List<People> getSumAmountGroupBySex(List<People> list) {
        List<People> peoples = Lists.newArrayList();
        list.stream().collect(Collectors.groupingBy(People::getSex))
                .forEach((name, fooListByName) -> {
                    People bar = new People();
                    bar = fooListByName
                            .stream()
                            .reduce(bar, (u, t) -> u.sum(t), (u, t) -> u);
                    System.out.println(bar.toString());
                    peoples.add(bar);
                });
        return peoples;
    }


    public static Map<String, List<People>> buildRecordMapBrief(List<People> records) {
        return records.stream().collect(Collectors.groupingBy(
                record -> buildRecordKey(record.getAge(), record.getSex())
        ));
    }

    public int addNumberArray(List<Integer> numbers) {
        int result = numbers.stream().reduce(0, (subtotal, element) -> subtotal + element);
        //int result = numbers.stream().reduce(0, Integer::sum);
        return result;
    }


    public String jointStringArray(List<String> letters) {
        String result = letters.stream().reduce("", (partialString, element) -> partialString + element);
        //  String result = letters.stream().reduce("", String::concat);
        //  String result = letters.stream().reduce("", (partialString, element) -> partialString.toUpperCase() + element.toUpperCase());
        //  String result = letters.parallelStream().reduce("", String::concat);
        return result;
    }


    public int sumAge(List<People> users) {
        int result = users.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
        //int result = numbers.stream().reduce(0, Integer::sum);
        return result;
    }

    private Map<Integer, String> filterMap(Map<Integer, String> map, String filterName) {
        Map<Integer, String> collect = map.entrySet().stream()
                .filter(x -> filterName.equals(x.getValue()))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        // or like this
        //  Map<Integer, String> collect = map.entrySet().stream()
        //                .filter(x -> filterName.equals(x.getValue()))
        //                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        //Map -> Stream -> Filter -> String
        String result = map.entrySet().stream()
                .filter(x -> filterName.equals(x.getValue()))
                .map(x -> x.getValue())
                .collect(Collectors.joining(","));

        System.out.println(result);

        return collect;
    }


    //多个集合合成一个
    private static void testFlatMap() {
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        //Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);

        //Stream<String>, GOOD!
        Stream<String> stringStream = temp.flatMap(x -> Arrays.stream(x));


        //Stream<String> stream = stringStream.filter(x -> "a".equals(x.toString()));

        stringStream.forEach(System.out::println);

    }


    private static String buildRecordKey(int age, String sex) {
        return age + "_" + sex;
    }

    public static void main(String[] args) {
        Runnable r1 = () -> {
            System.out.println("say i'm a runnable");
        };

        r1.run();
        Java8Stream j8 = new Java8Stream();
        List<People> list = Lists.newArrayList();
        for (int i = 0; i < 4; i++) {
            People people = new People();
            people.setName("joy" + i);
            if (i % 2 == 0) {
                people.setSex("" + 0);
            } else {
                people.setSex("" + 1);
            }
            people.setAge(4 - i);
            people.setMoney(i * 2);
            list.add(people);
        }
        //System.out.println(buildRecordMapBrief(list));

        System.out.println(j8.getPeopleMapByName(list));
        // System.out.println(list);
        //list.sort(Comparator.comparing(People::getAge));

        // System.out.println(list);
        //System.out.println(j8.getTotalAge(list));

//        System.out.println(j8.getPeopleNames(list));
//
//        System.out.println(j8.chooseSexPeoples("1",list));
//
//        System.out.println(j8.groupBySex(list));
//
//        System.out.println(j8.getMaxAgeGroupBySex(list));


//        Map<Integer, String> map = new HashMap<>();
//        map.put(1, "linode.com");
//        map.put(2, "heroku.com");
//        map.put(3, "heroku.com");
//        System.out.println(j8.filterMap(map, "heroku.com"));


    }
}
