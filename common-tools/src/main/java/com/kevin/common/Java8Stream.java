package com.kevin.common;

import com.google.common.collect.Lists;
import com.kevin.common.domain.People;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by kevin on 2017/11/29.
 */
@Data
public class Java8Stream {

    public List<String> getPeopleNames(List<People> peopleList){
        return peopleList.stream().map(People::getName).collect(Collectors.toList());
    }

    public Map<String,List<People>> groupBySex(List<People> peoples){
        return peoples.stream().collect(Collectors.groupingBy(People::getSex));
    }

    public List<People> chooseSexPeoples(String sex,List<People> peoples){
        return peoples.stream().filter(o -> StringUtils.equals(sex,o.getSex())).collect(Collectors.toList());
    }

    public Map<String,Optional<People>> getMaxAgeGroupBySex(List<People> peoples){
        return peoples.parallelStream().collect(Collectors.groupingBy(People::getSex, Collectors.maxBy(Comparator.comparing(People::getAge))));
    }

    /**
     *  option's simple use
     * @param people
     * @return
     */
    public String getSex(People people){
        return Optional.ofNullable(people).map(p ->p.getSex()).orElse(null);
    }

    /**
     * 先分组再累加某些属性
     * @param list
     * @return
     */
    public List<People> getSumAmountGroupBySex(List<People> list){
         List<People> peoples = Lists.newArrayList();
         list.stream().collect(Collectors.groupingBy(People::getSex))
                    .forEach((name,fooListByName)->{
                        People bar = new People();
                        bar = fooListByName
                                .stream()
                                .reduce(bar,(u,t)->u.sum(t),(u,t)->u);
                        System.out.println(bar.toString());
                        peoples.add(bar);
                    });
         return peoples;
    }

    public static void main(String[] args) {
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
            people.setAge(4-i);
            people.setMoney(i *2);
            list.add(people);
        }
        System.out.println(list);
        list.sort(Comparator.comparing(People::getAge));

        System.out.println(list);

//        System.out.println(j8.getPeopleNames(list));
//
//        System.out.println(j8.chooseSexPeoples("1",list));
//
//        System.out.println(j8.groupBySex(list));
//
//        System.out.println(j8.getMaxAgeGroupBySex(list));
    }
}
