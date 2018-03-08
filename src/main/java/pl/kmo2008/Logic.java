package pl.kmo2008;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class support logic of app
 */

public class Logic {
    /**
     * Initialize io module
     */
    private IO io = new IO();

    /**
     * Initialize scanner for getting strings from console
     */
    private Scanner scanner = new Scanner(System.in);
    /**
     * Create Arraylist of Persons and fill it with data from database
     */
    private ArrayList<Person> persons = io.load();

    /**
     * This method reload ArrayList od Persons
     */
    public void reload() {
        persons.clear();
        persons = io.load();
    }

    /**
     * This method save full list to database
     */
    public void save() {
        io.save(persons);
    }

    /**
     * This method delete record from database with declared name
     *
     * @param name Name of Person
     */
    public void delete(String name) {
        reload();
        persons.stream()
                .filter(person -> person.getName().equals(name))
                .allMatch(person -> persons.remove(person));
        System.out.println("Usunięto rekordy o wskazanej nazwie.");
        save();

    }

    /**
     * This method add record to database
     */
    public void add() {
        reload();
        String name = inputName();
        String number = inputNumber();
        if (!number.equals("-1")) {
            persons.add(new Person(name, number));
            System.out.println("Dodano " + name + " -> " + number);
        }
        else System.out.println("Wprowadz dane jescze raz.");
        save();
    }

    /**
     * This method takes name from user.
     *
     * @return String of name
     */
    public String inputName() {
        System.out.print("Podaj personalia: ");
        String name = scanner.next();
        return name;
    }

    /**
     * This method takes telephone number from user.
     * Working formats:
     * +48 123 456 789
     * 123456789
     * 123 456 789
     * 123-456-789
     * (+48) 123 456 789
     * +48123456789
     * 0048123456789
     * <p>
     * Things not to capture:
     * 12 345 67 89
     * 1234567899876543211
     * 654564654654654654
     * spam
     * 1231312asdasdf1231231
     * 123321
     * +4863227124
     *
     * @return String of number or "-1" when wrong input
     */
    public String inputNumber() {

        String pattern = "(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)";
        System.out.print("Podaj numer: ");
        try {
            String number = scanner.next(pattern);
            return number;
        } catch (InputMismatchException e) {
            System.out.println("Podane dane nie są numerem telefonu.");
        }
        return "-1";
    }

    /**
     * This method show arrayList of Persons
     */
    public void show() {
        reload();
        persons.stream()
                .forEach(p -> System.out.println(p.toStringView()));
    }

    /**
     * This method show list of Persons where name it's the same or it's part of name.
     * @param args String of name or part of name
     */
    public void show(String args) {
        reload();
        persons.stream()
                .filter(person -> person.getName().contains(args))
                .forEach(p -> System.out.println(p.toStringView()));
    }

    /**
     * This method update record in database where name is the same as input
     * @param arg String of name
     */
    public void update(String arg) {
        reload();
        String name = inputName();
        String number = inputNumber();
        if (!number.equals("-1"))
            persons.stream()
                    .findFirst()
                    .filter(person -> person.getName().equals(arg))
                    .filter(person -> person.update(name, number));
        else System.out.println("Wprowadz dane jescze raz.");
        System.out.println("Zaktualizowano rekord o wskazanej nazwie.");
        save();
    }

    /**
     * Sorting method to arraylist on name field
     */
    public void sort() {
        reload();
        persons.sort(Comparator.comparing(Person::getName));
        save();
    }
}
