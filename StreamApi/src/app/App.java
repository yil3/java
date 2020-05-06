package app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class App {
    public static void main(String[] args) throws Exception {
        // List<String> aList = Arrays.asList("Hello","World");
        // aList.stream()
        //     .flatMap(s -> Arrays.stream(s.split("")))
        //     .forEach(e -> System.out.println(e));

        // List<String> bList = Arrays.asList("Hello","Hello","World","Lion");
        // List<String> a1 = bList.stream()
        // .distinct() //去重
        // .filter(e -> e.length() > 4)
        // .collect(Collectors.toList());
        // System.out.println(a1);

        List<String> cList = Arrays.asList("Hello","Hello","World","Lion");
        boolean b1 = cList.stream().anyMatch(e -> e.equals("Hello"));
        System.out.println(b1);


    }
}