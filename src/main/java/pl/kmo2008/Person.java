package pl.kmo2008;

import org.apache.commons.lang3.StringUtils;

/**
 * This class definied an Person object.
 */
public class Person {
    /**
     * Name of contact
     */
    private String name;
    /**
     * Telephone number
     */
    private String telNumber;

    /**
     * Empty constructor
     */
    public Person() {

    }

    /**
     * Constructor for creating and object from 2 parameters.
     *
     * @param name      Name of contact
     * @param telNumber Telephone number
     */
    public Person(String name, String telNumber) {
        this.name = name;
        this.telNumber = telNumber;
    }

    /**
     * Constructor for creating object from String
     * This method split strig with ",' separator.
     * Ex. "John,145236584""
     * Result Person("John", 145236584)
     *
     * @param record String with number and telephone separated with ","
     */
    public Person(String record) {
        if (record.contains(",")) {
            String[] tab = StringUtils.split(record, ",");
            this.name = tab[0];
            this.telNumber = tab[1];
        }
    }

    /**
     * This method update both fields
     * @param name String name of Person
     * @param tel String telephone of person
     * @return always true
     */
    public boolean update(String name, String tel){
        this.setName(name);
        this.setTelNumber(tel);
        return true;
    }

    /**
     * Getter of name
     * @return String of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     * @param name String of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of telephone number
     * @return Integer of telephone number
     */
    public String getTelNumber() {
        return telNumber;
    }

    /**
     * Setter of telephone number
     * @param telNumber Integer of number
     */

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    /**
     * Overrided method toString for create String of object to save in file.
     * @return String of Person "name,telephone"
     */
    @Override
    public String toString() {
        return name + ',' + telNumber + ";" + System.lineSeparator();
    }

    /**
     * Special view for console view
     * @return String of Person "name -> telephone"
     */
    public String toStringView() {
        return name + " -> " + telNumber;
    }
}
