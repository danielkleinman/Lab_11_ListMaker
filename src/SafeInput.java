import java.util.Scanner;
public class SafeInput {
    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do
        {
            System.out.print("\n" +prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;

    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a valid integer
     */
    public static int getInt(Scanner pipe, String prompt){
        int retInt = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print(prompt + ": ");
            if(pipe.hasNextInt()){
                retInt = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("Invalid input (" + trash + ")");

            }
        }while(!done);
        return retInt;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a valid double
     */
    public static double getDouble(Scanner pipe, String prompt){
        double retDouble = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print(prompt + ": ");
            if(pipe.hasNextDouble()){
                retDouble = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("Invalid input (" + trash + ")");
            }
        }while(!done);
        return retDouble;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user + range
     * @param low minimum value for the output
     * @param high maximum value for the output
     * @return an integer from low to high
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high){
        int retInt = 0;
        boolean done = false;
        String trash = "";
        do{
            System.out.print(prompt + " [" + low + " - " + high + "]:");
            if(pipe.hasNextInt()){
                retInt = pipe.nextInt();
                if(retInt >= low && retInt <= high){
                    pipe.nextLine();
                    done = true;
                } else {
                    System.out.println("Invalid input (" + retInt + ")");
                    retInt = 0;
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("Invalid input (" + trash + ")");
                retInt = 0;
            }
        }while(!done);
        return retInt;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user + range
     * @param low minimum value for the output
     * @param high maximum value for the output
     * @return a double from low to high
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high){
        double retDouble = 0;
        boolean done = false;
        String trash = "";
        do{
            System.out.print(prompt + " [" + low + " - " + high + "]:");
            if (pipe.hasNextDouble()) {
                retDouble = pipe.nextDouble();
                if (retDouble >= low && retDouble <= high){
                    pipe.nextLine();
                    done = true;
                } else {
                    System.out.println("Invalid input (" + retDouble + ")");
                    retDouble = 0;
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("Invalid input (" + trash + ")");
                retDouble = 0;
            }
        }while(!done);
        return retDouble;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user + (y/n)
     * @return boolean representing y or n
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt){
        boolean retConf = false;
        boolean done = false;
        String input = "";
        do{
            System.out.print(prompt + " [Y/N]: ");
            input = pipe.nextLine();
            if(input.equalsIgnoreCase("Y")){
                retConf = true;
                done = true;
            } else if(input.equalsIgnoreCase("N")){
                done = true;
            } else{
                System.out.println("Invalid response (" + input + ")");
            }
        }while(!done);
        return retConf;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param regEx regular expression to be checked
     * @return an integer from low to high
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx){
        String retStr = "";
        boolean done = false;
        do{
            System.out.print(prompt + ": ");
            retStr = pipe.nextLine();
            if(retStr.matches(regEx)) {
                done = true;
            } else {
                System.out.println("Invalid input (" + retStr + ")");
            }
        }while(!done);
        return retStr;
    }

    public static void prettyHeader(String msg) {
        int size = 60;
        for (int i = 0; i < size; i++){
            System.out.print('*');
        }
        System.out.println();
        System.out.print("***");
        for (int i = 0; i < size - 6; i++) {
            if(i < (((size / 2) - 3) - (msg.length()/2))) {
                System.out.print(" ");
            } else if(i < (((size / 2)- 3 - (msg.length()/2))) + msg.length()) {
                System.out.print(msg.charAt(i - (((size/2) - 3) - (msg.length()/2))));
            } else {System.out.print(" ");}
//            System.out.print(msg.charAt(i));
        }
        System.out.println("***");
        for (int i = 0; i < size; i++){
            System.out.print('*');
        }
    }

}
