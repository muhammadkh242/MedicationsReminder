package com.example.medicalreminder.constant;

import java.util.Arrays;
import java.util.List;

public class Constant {

    public static final String ANSWER_YES = "Yes";
    public static final String ANSWER_NO = "No";
    public static final List<String> ANSWER = Arrays.asList(ANSWER_YES, ANSWER_NO);
    public static  final String  ONE_DAY = "Once day";
    public static  final String  TWICE_DAY = "Twice day";
    public static  final String  THREE_TIMES_IN_DAY = "3 times in day";
    public static  final String  FOUR_TIMES_IN_DAY = "4 times in day";
    public  static  final List<String> TIMES_IN_DAYS =
            Arrays.asList(ONE_DAY,TWICE_DAY,THREE_TIMES_IN_DAY,FOUR_TIMES_IN_DAY);

    public static  final String  ONE_WEEK = "Once week";
    public static  final String  TWICE_WEEk = "Twice week";
    public static  final String  THREE_TIMES_IN_WEEK = "3 times in week";
    public static  final String  FOUR_TIMES_IN_WEEK = "4 times in week";
    public static  final  List<String> TIMES_IN_WEEK =
            Arrays.asList(ONE_WEEK,TWICE_WEEk,THREE_TIMES_IN_WEEK,FOUR_TIMES_IN_WEEK);
    public static final List<String> DURATION = Arrays.asList("30 days", "1 week", "10 days", "5 days");

}