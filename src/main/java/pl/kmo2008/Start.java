package pl.kmo2008;

/**
 * Starting class for App
 */
public class Start {
    /**
     * Initiate logic for app
     */
    private static Logic logic = new Logic();

    /**
     * Main method using for handling arguments
     * @param args Arguments
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            if (args[0].equals("-add")) {
                logic.add();
            }
            else if(args[0].equals("-update")){
                if(!args[1].isEmpty()){
                    logic.update(args[1]);
                }
                else
                {
                    System.out.println("Niepoprawne dane wejściowe. Przykład: java -jar phonebook.jar -update Jan");
                }
            }
            else if(args[0].equals("-delete")){
                if(!args[1].isEmpty()){
                    logic.delete(args[1]);
                }
                else
                {
                    System.out.println("Niepoprawne dane wejściowe. Przykład: java -jar phonebook.jar -delete Jan");
                }
            }
            else{
                logic.show(args[0]);
            }
        }
        else
        {
            logic.sort();
            logic.show();
        }
    }
}
