package com.kevin.common;

import com.google.common.collect.Lists;
import com.kevin.common.domain.People;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;
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


    public static void main(String[] args) {
        Java8Stream j8 = new Java8Stream();
        List list = Lists.newArrayList();
        for (int i=0;i<10;i++){
            People people = new People();
            people.setName("joy"+i);
            people.setSex(""+i);
            list.add(people);
        }

        System.out.println(j8.getPeopleNames(list));

        System.out.println(j8.chooseSexPeoples("1",list));

    }
}
