package com.lecheng.protectAnimals.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    public static String getRegexTextResult (String input,String regex,int groupNumber){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()){
            return matcher.group(groupNumber);
        }
        return null;
    }
    public static List<String> getRegexTextResults (String input, String regex, int groupNumber){
        ArrayList<String> results  = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()){
            results.add(matcher.group(groupNumber));
        }
        return results;
    }

    public static Matcher getMatcher (String input,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

}
