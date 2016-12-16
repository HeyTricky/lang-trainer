package com.dsr.javaschool.langtrainer.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

public class TextParser {

    private static final List<String> articles = Arrays.asList("a", "an", "the");  //артикли
    private static final List<String> prepositions = Arrays.asList("at", "into", "for", "after", "up", "around", "back", "out", "off", "about", "on", "across", "in");  //предлоги для фразовых глаголов

    public static List<String> parse(String text) {
        text = text.toLowerCase();
        List<String> list = new ArrayList<>(Arrays.asList(text.split("[^а-яА-Яa-zA-Z]")));
        list.removeIf(articles::contains); //убираем артикли
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i), "")) continue;
            if (!newList.contains(list.get(i)))
                newList.add(list.get(i));
            if (list.size() == 1) break;
            //добавляем возможные фразовые глаголы
            if (i > 0 && prepositions.contains(list.get(i)) && !prepositions.contains(list.get(i - 1))) {
                newList.add(format("%s %s", list.get(i - 1), list.get(i)));
            }
        }
        return newList;
    }
}
