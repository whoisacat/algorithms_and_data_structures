package com.whoisacat;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeWay {

    public static void main(String[] args) {
        ArrayList<List<String>> flights1 = new ArrayList<>();
        LinkedList<String> e = new LinkedList<>();
        e.add("Москва");
        e.add("Белград");
        flights1.add(e);
        System.out.println(findPath(flights1));
        ArrayList<List<String>> flights2 = new ArrayList<>();
        LinkedList<String> e1 = new LinkedList<>();
        e1.add("Москва");
        e1.add("Белград");
        LinkedList<String> e2 = new LinkedList<>();
        e2.add("Москва");
        e2.add("Ереван");
        flights2.add(e1);
        flights2.add(e2);
        System.out.println(findPath(flights2));
    }

    /**
     Вам задан набор пар городов: между каждой парой городов сотрудник компании совершил прямой перелёт,
     но информация, в каком направлении был совершен перелёт, утеряна.

     Известно, что все указанные перелёты относятся к одному путешествию,
     и что каждый следующий перелёт сотрудник начинал из того города, в котором закончил предыдущий.

     Также вы знаете, что никакой город не был посещён сотрудником дважды,
     в том числе город в котором путешествие началось отличается от города, в котором оно закончилось.

     Выведите города в порядке следования по маршруту. Очевидно, что есть два возможных ответа, подойдёт любой.

     Примеры:
     [("Москва", "Белград")] -> ["Москва", "Белград"]
     [("Москва", "Белград"), ("Москва", "Ереван")] -> ["Ереван", "Москва", "Белград"]
     */


    static List<String> findPath(List<List<String>> flights) {
        Map<String, List<List<String>>> map = new HashMap<>();
        for (List<String> pair : flights) {
            for (String city : pair) {
                List<List<String>> listOfLists = map.get(city);
                if (listOfLists == null) {
                    listOfLists = new ArrayList<>();
                    map.put(city, listOfLists);
                }
                listOfLists.add(pair);
            }
        }
        List<String> startStop = map.entrySet().stream()
                .filter(entry -> entry.getValue().size() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Set<String> employeeWay = new LinkedHashSet<>();
        String start = startStop.get(0);
        employeeWay.add(start);
        String stop = startStop.get(1);

        String key = start;
        while(!key.equals(stop)) {
            List<List<String>> upperList = map.get(key);
            if (upperList.size() == 1) {
                String finalKey = key;
                key = upperList.get(0).stream()
                        .filter(it -> !it.equals(finalKey))
                        .findAny()
                        .orElseThrow(RuntimeException::new);
                employeeWay.add(key);
            } else {
                for (List<String> list : upperList) {
                    for (String city : list) {
                        if (!employeeWay.contains(city)) {
                            key = city;
                            employeeWay.add(key);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(employeeWay);
    }
}
