import java.nio.file.Paths;
import java.util.ArrayList; // import java.util.ArrayList
import java.util.Scanner; // import java.util.Scanner
import java.io.BufferedOutputStream; // import java.io.BufferedOutputStream;
import java.io.BufferedWriter; // import java.io.BufferedWriter;
import java.io.File; // import java.io.File;
import java.io.IOException; // import java.io.IOException;
import java.io.OutputStream; // import java.io.OutputStream;
import java.io.OutputStreamWriter; // import java.io.OutputStreamWriter;
import java.nio.file.Files; // import java.nio.file.Files;
import java.nio.file.Path; // import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE; // import static java.nio.file.StandardOpenOption.CREATE;
import java.io.FileNotFoundException; // import java.io.FileNotFoundException;
import javax.swing.JFileChooser; // import javax.swing.JFileChooser;

public class FileListMaker { // class FileListMaker
    static ArrayList<String> list = new ArrayList<>(); // the list array

    public static void main(String[] args) { // main
        JFileChooser chooser = new JFileChooser(); // JFileChooser chooser = new JFileChooser();
        Scanner inFile; // Scanner inFile;
        Scanner console = new Scanner(System.in); // Scanner console = new Scanner(System.in)
        Scanner in = new Scanner(System.in); // Scanner in = new Scanner(System.in)

        ArrayList<String> recs = new ArrayList<>(); // the recs array

        final String menu =  "A - Add D - Delete V - View O – Open a list file from disk S – Save the current list file to disk C – Clear removes all the elements from the current list Q - Quit"; // menu choices of add delete view open save clear or quit

        boolean done = false; // boolean done = false repeats loop until break
        String item; // String item = "" item needed to add
        String cmd; // String cmd = "" prints out what menu choice the user chose
        boolean answer = false; // boolean answer = false y or n answer loops until y
        int deleteItem; // num deleteItem item to delete
        int deleteItem2; // num deleteItem2 subtract 1 to convert to index

        boolean answerSave = false; // boolean answerSave = false loops if the user wants to save their list
        boolean needsToBeSaved = true; // boolean needsToBeSaved = true returns false if there are changes

        File workingDirectory = new File(System.getProperty("user.dir")); // File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "src"); // Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt")
        Path target = new File(System.getProperty("user.dir")).toPath(); // Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src"); // target = target.resolve("src")
        do { // do
            do{ // do
                do { // do
                    // display the list
                    displayList(); // displayList();
                    // display menu
                    // get the menu choice
                    cmd = SafeInput.getRegExString(console, menu, "[AaDdVvOoSsCcQq]"); // cmd = SafeInput.getRegExString(console, menu, "[AaDdPpQq]")
                    cmd = cmd.toUpperCase(); // cmd = cmd.toUpperCase()

                    // execute the menu choice
                    switch (cmd) { // switch (cmd)
                        case "A": // if A is chosen
                            // prompt the user for item to add to list
                            item = SafeInput.getNonZeroLenString(in, "Add an item to the list"); // item to add item = SafeInput.getNonZeroLenString(in, "Add an item to the list")
                            // add item
                            list.add(item); // list.add(item) adds item to list
                            recs.add(item); // recs.add(item) add item to file list
                            System.out.println("Make sure to save your changes!"); // output "Make sure to save your changes!"
                            needsToBeSaved = false; // needsToBeSaved = false because there are changes it is false
                            break; // break
                        case "D": // if d is chosen
                            // prompt the user for the number of item to delete
                            deleteItem = SafeInput.getRangedInt(in, "Enter which number of the item to delete", 1, list.size()); // deleteItem = SafeInput.getRangedInt(in, "Enter which number of the item to delete", 1, list.size())
                            // translate item to index by subtracting one
                            deleteItem2 = deleteItem - 1; // deleteItem2 = deleteItem - 1 translates to index
                            // remove item from list
                            list.set(deleteItem2, ""); // list.set(deleteItem2, "") replaces item with nothing; removes
                            try // try
                            {
                                OutputStream out = // OutputStream out =
                                        new BufferedOutputStream(Files.newOutputStream(file, CREATE)); // new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                                BufferedWriter writer = // BufferedWriter writer =
                                        new BufferedWriter(new OutputStreamWriter(out)); // new BufferedWriter(new OutputStreamWriter(out));

                                for (int i = 0; i < recs.size(); i++) // for (int i = 0; i < recs.size(); i++)
                                {
                                    writer.write(recs.set(deleteItem2, "                                       ")); // writer.write(recs.set(deleteItem2, "                                       ")) clears only selected item
                                } // endfor
                                writer.close(); // close the file and reduce buffer
                            }
                            catch (IOException e) // catch (IOException e)
                            {
                                e.printStackTrace(); //  e.printStackTrace();
                            }
                            System.out.println("Make sure to save your changes!"); // output "Make sure to save your changes!"
                            needsToBeSaved = false; // needsToBeSaved = false because there are changes it is false

                            break; // break
                        case "V": // if v is chosen
                            System.out.println("This is your current list: "); // "This is your current list: "
                            break; // break, any ways shows the display
                        case "O": // if o is chosen
                            try // try
                            {
                                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) // if a file is chosen (they did not click cancel or exit) then
                                {
                                    target = chooser.getSelectedFile().toPath();  // this is a File object not a String filename

                                    inFile = new Scanner(target); // inFile = new Scanner(target)
                                    OutputStream out = // OutputStream out =
                                            new BufferedOutputStream(Files.newOutputStream(target, CREATE)); // new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                                    BufferedWriter writer = // BufferedWriter writer =
                                            new BufferedWriter(new OutputStreamWriter(out)); // new BufferedWriter(new OutputStreamWriter(out));
                                    if (!needsToBeSaved) // if (needsToBeSaved == false) then
                                    {
                                        System.out.println("Make sure to save you list before starting a new one!"); // "Make sure to save you list before starting a new one!"
                                        break;
                                    }
                                    for (String rec : recs) // for (String rec : recs)
                                    {
                                        writer.write(rec, 0, rec.length());  // starts from 0 and goes the length of the string to write the item
                                        writer.newLine(); // adds the new line
                                    } // end for
                                    writer.close(); // close the file and reduce buffer
                                    System.out.println("Data file written!"); // output "Data file written!"
                                }
                                else   // else the user did not pick a file, close the chooser
                                {
                                    System.out.println("Sorry, you must select a file! Terminating, please run the program again!"); // output "Sorry, you must select a file! Terminating, please run the program again!"
                                    System.exit(0); // exit
                                }
                            }
                            catch (FileNotFoundException e) // catch (FileNotFoundException e)
                            {
                                System.out.println("File Not Found Error"); // output File Not Found Error
                                e.printStackTrace(); // e.printStackTrace()
                            }
                            catch (IOException e) // catch (IOException e)
                            {
                                System.out.println("IOException Error"); // output IOException Error
                                e.printStackTrace(); // e.printStackTrace();
                            }
                            break; // break
                        case "S": // if S is chosen
                            System.out.println("Please do ctrl + s to save");
                            needsToBeSaved = true; // needsToBeSaved is true now that the file is saved
                            break; // break
                        case "C": // if C is chosen
                            list.clear(); // list.clear(); clear the list
                            try // try
                            {
                                OutputStream out = // OutputStream out =
                                        new BufferedOutputStream(Files.newOutputStream(file, CREATE)); // new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                                BufferedWriter writer = // BufferedWriter writer =
                                        new BufferedWriter(new OutputStreamWriter(out)); // new BufferedWriter(new OutputStreamWriter(out));

                                for (int i = 0; i < recs.size(); i++) // for (int i = 0; i < recs.size(); i++)
                                {
                                    writer.write(recs.set(i, "                                       ")); // writer.write(recs.set(i, "                                       ")) clears each item
                                } // endfor
                                writer.close(); // close the file and reduce buffer
                                System.out.println("Clearing the list..."); // output "Clearing the list..."
                            }
                            catch (IOException e) // catch (IOException e)
                            {
                                e.printStackTrace(); // e.printStackTrace();
                            }
                            needsToBeSaved = false; // needsToBeSaved = false now that there is a change
                            break;
                        case "Q": // if q is chosen
                            if (!needsToBeSaved) // if (needsToBeSaved == false) if changes were made and not saved then
                            {
                                answerSave = SafeInput.getYNConfirm(in, "Are you sure you want to exit without saving?"); // answerSave = SafeInput.getYNConfirm(in, "Are you sure you want to exit without saving?")
                                if (!answerSave) // if (!answerSave) then
                                    System.exit(0); // exit
                            }

                            answer = SafeInput.getYNConfirm(in, "Are you sure you want to quit?"); // answer = SafeInput.getYNConfirm(in, "Are you sure you want to quit?")
                            if (!answer) // if !answer then
                                System.exit(0); // exit the whole loop
                            break; // break
                    } // end of switch
                } while (answerSave); // while answerSave is true
                System.out.println("The cmd is " + cmd); // output "The cmd is " + cmd
            } while(answer); // while answer is true repeat

        } while(!done); // while done is false repeat
    } // return
    private static void displayList() // displayList Method
    {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"); // border
        if(list.size() != 0) // if list size is not equal to zero then
        {
            for (int i = 0; i < list.size(); i++) // for (int i = 0; i < list.size(); i++) makes space in the center for the list
            {
                System.out.printf("%3d%35s", i + 1,  list.get(i)); // output "%3d%35s", i + 1,  list.get(i)
                System.out.println("\n"); // output new line
            } // end for
        }
        else // else
        {
            System.out.println("+++                        List is empty                       +++"); // output +++                        List is empty                       +++
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"); // border
    }
} // end class