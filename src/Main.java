import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long income = persons.stream()
                .filter(a -> a.getAge() < 18)
                .count();
        System.out.println(income);


        Stream<Person> numStream = persons.stream();
        List<String> conscripts =
                numStream.filter(a -> a.getAge() > 17)
                        .filter(a -> a.getAge() < 28)
                        .filter(a -> a.getSex().equals(Sex.MAN))
                        .map(n -> n.getFamily())
                        .collect(Collectors.toList());
        System.out.println(conscripts);


        Stream<Person> numStream1 = persons.stream();
        Predicate<Person> predicatePeople = x -> (x.getSex().equals(Sex.MAN) && x.getAge() < 65) ||
                (x.getSex().equals(Sex.WOMAN) && x.getAge() < 60);
        List<Person> workPeople =
                numStream1.filter(a -> a.getAge() > 17)
                        .filter(predicatePeople)
                        .filter(a -> a.getEducation().equals(Education.HIGHER))
                        .sorted(Comparator.comparing(Person::getFamily))
                        .collect(Collectors.toList());
        for (int i = 0; i < workPeople.size(); i++) {
            System.out.println(workPeople.get(i));
        }
    }
}