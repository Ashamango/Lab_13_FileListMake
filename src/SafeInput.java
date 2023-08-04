import java.util.Scanner;
public class SafeInput {
    // public static String getNonZeroLenString(Scanner pipe, String prompt)

    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        // declare variables
        String retString = ""; // Set this to zero length. Loop runs until it isn’t

        do {
            System.out.print("/n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();

        } while (retString.length() == 0);
        return retString;
    }
    // public static int getInt(Scanner pipe, String prompt)

    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user to input integer
     * @return a num response that is an integer
     */
    public static int getInt(Scanner pipe, String prompt) { // getInt
        // declare variables
        int num = 0;  // num = 0
        boolean done = false; // boolean done = false

        do { // do
            System.out.print(prompt + ": "); // output prompt + ": "
            if (pipe.hasNextInt()) // if pipe.hasNextInt() then
            {
                num = pipe.nextInt(); // num = pipe.nextInt();
                pipe.nextLine(); // clear buffer
                done = true; // done = true
            }
            else // else
            {
                System.out.println("This is an invalid input. Please input an integer!"); // output "This is an invalid input. Please input an integer!"
                pipe.nextLine(); // clear buffer
            }
        } while (!done); // while done is false
        return num; // return num
    }

    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user to input double
     * @return a num response that is a double
     */
    public static double getDouble(Scanner pipe, String prompt) { // getDouble
        // declare variables
        double doubleNum = 0;  // num = 0
        boolean done = false; // boolean done = false

        do { // do
            System.out.print(prompt + ": "); // output prompt + ": "
            if (pipe.hasNextDouble()) // if pipe.hasNextDouble() then
            {
                doubleNum = pipe.nextDouble(); // doubleNum = pipe.nextDouble()
                pipe.nextLine(); // clear buffer
                done = true; // done = true
            }
            else // else
            {
                System.out.println("This is an invalid input. Please input a number!"); // output "This is an invalid input. Please input a number!"
                pipe.nextLine(); // clear buffer
            }

        } while (!done); // while done is false
        return doubleNum; // return doubleNum
    }

    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user to input integer in range
     * @param low int for the user
     * @param high int for the user
     * @return a num response that is a result
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) { // getRangedInt
        // declare variables
        int result = 0; // result = 0
        boolean done = false; // boolean done = false


        do { // do

            System.out.print(prompt + "[" + low + " - " + high + "]: "); // output prompt + "[" + low + " - " + high + "]: "
            if (pipe.hasNextInt()) // if pipe.hasNextInt() then
            {
                result = pipe.nextInt(); // result = pipe.nextInt()
                pipe.nextLine(); // clear buffer
                if (result >= low && result <= high) // if (result >= low && result <= high) then
                {
                    done = true; // done = true
                }
                else // else
                {
                    System.out.println("You must enter a value in range [" + low + " - " + high + "]"); // output "You must enter a value in range [" + low + " - " + high + "]
                }
            }
            else // else
            {
                pipe.nextLine(); // clear buffer
                System.out.println("You must enter a valid number [" + low + " - " + high + "]"); // output "You must enter a valid number [" + low + " - " + high + "]"
            }

        } while (!done); // while done is false
        return result; // return result
    }
    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low double for the user
     * @param high double for the user
     * @return a num response that is a double
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) { // getRangedDouble
        // declare variables
        double result = 0; // num result = 0
        boolean done = false; // boolean done = false

        do { // do

            System.out.print(prompt + "[" + low + " - " + high + "]: "); // output prompt + "[" + low + " - " + high + "]: "
            if (pipe.hasNextDouble()) { // if pipe.hasNextDouble() then
                result = pipe.nextDouble(); // result = pipe.nextDouble()
                pipe.nextLine(); // clear buffer
                if (result >= low && result <= high) // if (result >= low && result <= high) then
                {
                    done = true; // done = true
                }
                else // else
                {
                    System.out.println("You must enter a value in range [" + low + " - " + high + "]"); // "You must enter a value in range [" + low + " - " + high + "]"
                }
            }
            else // else
            {
                pipe.nextLine(); // clear buffer
                System.out.println("You must enter a valid number [" + low + " - " + high + "]"); // "You must enter a valid number [" + low + " - " + high + "]"
            }

        } while (!done); // while done is false
        return result; // return result
    }
    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a response that is either true or false
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt) { // getYNConfirm
        // declare variables
        String answer = ""; // String answer = ""
        boolean done = false; // boolean done = false
        String trash = ""; // String trash = ""

        do { // do

            System.out.print(prompt + "[Y or N]: "); // output prompt + "[Y or N]: "
            if (pipe.hasNext("Y|y|N|n")) // if (pipe.hasNext("Y|y|N|n")) then
            {
                {
                    answer = pipe.nextLine(); // answer = pipe.nextLine()
                }
                if (answer.equalsIgnoreCase("Y")) // if (answer.equalsIgnoreCase("Y")) then
                {
                    return false; // return false
                }
                else // else
                {
                    return true; // return true
                }
            }
            else // else
            {
                System.out.println("This is invalid. Please enter Y or N"); // output "This is invalid. Please enter Y or N"
                pipe.nextLine(); // clear buffer
            }

        } while (!done); // while done is false
        return done; // return done
    }
    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param regEx String
     * @return a value from the user
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx) { // getRegExString
        // declare variables
        String value = ""; // String value = ""
        boolean gotValue = false; // boolean gotValue = false

        do { // do

            System.out.print(prompt + ": "); // output prompt + ": "
            value = pipe.nextLine(); // value = pipe.nextLine()
            if (value.matches(regEx)) // if (value.matches(regEx)) then
            {
                gotValue = true; // gotValue = true
            }
            else // else
            {
                System.out.println("This is an invalid input. Please try again"); // output "This is an invalid input. Please try again"
            }

        } while (!gotValue); // while gotValue is false
        return value; // return value
    }

    /**
     * @param pipe a Scanner opened to read from System.in
     * @param msg prompt for the user
     */
    public static void prettyHeader(Scanner pipe, String msg) { // prettyHeader
        // declare variables
        boolean done = false; // boolean done = false
        int whiteSpace = 0; // num whiteSpace = 0

        System.out.print(msg + ": "); // output msg + ": "
        msg = pipe.nextLine(); // msg = pipe.nextLine()

        for (int top = 0; top <= 60; top++) // for top = 0 to 60 step 1
        {
            System.out.print("*"); // "*"
        }
        System.out.print("\n"); // output new line
        for (int middle = 0; middle <= 2; middle++) // for middle = 0 to 2 step 1
        {
            System.out.print("*"); // "*"
        }
        whiteSpace = ((54 - (msg.length())) / 2); // whiteSpace = ((54 - (msg.length())) / 2)
        if (msg.length() % 2 == 0) // if (msg.length() % 2 == 0) then
        {
            for (int middle1 = 1; middle1 <= whiteSpace; middle1++) // for middle1 = 1 to whiteSpace step 1
            {
                System.out.print(" "); // output " "
            }
        }
        else // else
        {
            for (int middle1 = 0; middle1 <= whiteSpace; middle1++) // for middle1 = 0 to whiteSpace step 1
            {
                System.out.print(" "); // output " "
            }
        }
        for (int middle2 = 0; middle2 <= 0; middle2++) // for middle2 = 0 to 0 step 1
        {
            System.out.printf(msg); // output msg
        }
        whiteSpace = ((54 - (msg.length())) / 2); // whiteSpace = ((54 - (msg.length())) / 2)
        for (int middle3 = 0; middle3 <= whiteSpace; middle3++) // for middle3 = 0 to whiteSpace step 1
        {
            System.out.print(" "); // output " "
        }
        for (int middle4 = 0; middle4 <= 2; middle4++) // for middle4 = 0 to 2 step 1
        {
            System.out.print("*"); // output "*"
        }
        System.out.print("\n"); // output new line
        for (int bottom = 0; bottom <= 60; bottom++) // for bottom = 0 to 60 step 1
        {
            System.out.print("*"); // output "*"
        }

    } // return
} // end class

