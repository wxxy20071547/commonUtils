package com.kevin.common.enums;


import lombok.Getter;

/**
 * @author kevin
 * @date 2019-06-10 18:33
 */
public enum Day {
    MONDAY(1, "星期一", "各种不在状态") {
        @Override
        public Day getNext() {
            return TUESDAY;
        }
    },
    TUESDAY(2, "星期二", "依旧犯困") {
        @Override
        public Day getNext() {
            return WEDNESDAY;
        }
    },
    WEDNESDAY(3, "星期三", "感觉半周终于过去了") {
        @Override
        public Day getNext() {
            return THURSDAY;
        }
    },
    THURSDAY(4, "星期四", "期待这星期五") {
        @Override
        public Day getNext() {
            return FRIDAY;
        }
    },
    FRIDAY(5, "星期五", "感觉还不错") {
        @Override
        public Day getNext() {
            return SATURDAY;
        }
    },
    SATURDAY(6, "星期六", "感觉非常好") {
        @Override
        public Day getNext() {
            return SUNDAY;
        }
    },
    SUNDAY(7, "星期日", "感觉周末还没过够。。。") {
        @Override
        public Day getNext() {
            return MONDAY;
        }
    };

    Day(int index, String name, String value) {
        this.index = index;
        this.name = name;
        this.value = value;
    }


    @Getter
    private int index;
    @Getter
    private String name;
    @Getter
    private String value;

    public abstract Day getNext();


    public static void main(String[] args) {
        Day day = Day.FRIDAY;
        System.out.println(day.getNext().getValue());
    }


}
