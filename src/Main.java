import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );
        Map<String,Double> result = students.parallelStream()
                .flatMap(student -> student.getGrades()
                        .entrySet().stream()
                        .map(m -> new AbstractMap.SimpleEntry<>(m.getKey(),m.getValue())))
                .collect(Collectors
                        .groupingBy(Map.Entry::getKey,Collectors.averagingInt(Map.Entry::getValue)
                        ));
        for(Map.Entry<String,Double> entry:result.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}