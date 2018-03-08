package pl.kmo2008;


import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class is responsible for read and write data from/to file.
 */
public class IO {
    Charset charset = Charset.forName("UTF-8");

    private ArrayList<Person> persons = new ArrayList<>();
    private Path path = Paths.get("./PhoneBookBase.txt");

    /**
     * This method save an data to file from list.
     *
     * @param person - ArrayList with person objects.
     */
    public void save(ArrayList<Person> person) {

        try {
            Files.delete(path);
            Files.createFile(path);
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
            for (Person p : person) {
                writer.write(p.toString(), 0, p.toString().length());

            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    /**
     * This method read all data from file.
     *
     * @return Arraylist of Person objects.
     */
    public ArrayList<Person> load() {
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {

            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] tab = StringUtils.split(line, ";");
                for (String t : tab) {
                    persons.add(new Person(t));
                }
            }


        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return persons;
    }

    /**
     * Constructor without parameters.
     * This constructor check if database file exist. If not create file.
     */
    public IO() {
        try {

            if (!Files.exists(path)) {
                System.out.println("Baza danych nie istnieje. Tworzenie nowej bazy. ");
                Files.createFile(path);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}
