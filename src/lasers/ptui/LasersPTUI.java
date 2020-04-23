package lasers.ptui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import lasers.model.LasersModel;
import lasers.model.ModelData;
import lasers.model.Observer;
import lasers.model.Safe;

/**
 * This class represents the view portion of the plain text UI.  It
 * is initialized first, followed by the controller (ControllerPTUI).
 * You should create the model here, and then implement the update method.
 *
 * @author Sean Strout @ RIT CS
 * @author Amy Do
 * @author Shubhang Mehrotra
 */
public class LasersPTUI implements Observer<LasersModel, ModelData> {
    /** The UI's connection to the model */
    private LasersModel model;

    /**
     * Construct the PTUI.  Create the lasers.lasers.model and initialize the view.
     * @param filename the safe file name
     * @throws FileNotFoundException if file not found
     */
    public LasersPTUI(String filename) throws IOException {
        try {
            this.model = new LasersModel(filename);
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
            System.exit(-1);
        }
        this.model.addObserver(this);
    }

    /**
     * Accessor for the model the PTUI create.
     * @return the model
     */
    public LasersModel getModel() {
        return this.model; }


    @Override
    public void update(LasersModel model, ModelData data) {
        // TODO: update the view with the new changes from the model
        //check for any changes happended in the matrix
        checkForUpdate(model.getSafe());





    }

    public boolean checkForUpdate(Safe safe){

        return true;

    }
}

//Your plain text UI from part one will need to be re-designed so that it follows the Model-View-Controller
// pattern discussed in lecture and lab. All the state and behaviors for your safe need to go into the
// lasers.model package. The main class should be called LasersModel, and you are encouraged to create other
// supporting classes in here. Because you will find passing data to the UI's useful, like status messages,
// an empty ModelData class is provided to you that you can customize however you want.
//
//The plain text UI's view class, LasersPTUI.java in the lasers.ptui package must implement the provided
// Observer interface. It is responsible for creating the model and implementing the update method so that when
// the model changes and the view is notified, the user sees the change.
//
//The plain text UI's controller class, ControllerPTUI, is responsible for reading input and performing the
// appropriate operation on the model.



//package lasers.ptui;
//
//        import lasers.model.Safe;
//
//        import java.io.BufferedReader;
//        import java.io.FileNotFoundException;
//        import java.io.FileReader;
//        import java.io.IOException;
//        import java.util.Scanner;
//
///**
// * Class to represent the view and controller of the project
// * This program reads the user arguments for the safe file, and input file. After reading the safe file it constructs
// * the safe, and allow users to input their commands which will then be processed and display
// * the result to the console.
// *
// * @author : Amy Do
// * @author : Shubhang Mehrotra
// *
// * last modified : 04/17/2020
// */
//public class LasersPTUI {
//    private Safe s;
//
//    /**
//     * constructor, handling arguments from the command line.
//     * @param args command line
//     * @throws IOException when file not found
//     */
//    public LasersPTUI(String[] args) throws IOException {
//        try {
//            if (args.length == 1) {
//                readOneArgument(args[0]);   //safeFile
//            } else if (args.length == 2) {
//                readTwoArguments(args[0], args[1]); //safeFile, inputFile
//            }
//        } catch (IOException io) {
//            System.out.println("File cannot found exception");
//        }
//    }
//
//
//    /**
//     * Create the Safe, and process commands from user input
//     * @param safeFile to create the safe from
//     * @throws IOException when file can't be found.
//     */
//    public void readOneArgument(String safeFile) throws IOException {
//        //construct the safe
//        readSafeFile(safeFile);
//
//        //get user input.
//        System.out.print(">");
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextLine()) {
//            processUserCommand(in.nextLine());  //process input
//            System.out.print(">");              //prompt input
//        }
//    }
//
//    /**
//     * reads safe file to construct the safe object
//     * @param safeFile-contains information to construct safe object
//     * @throws IOException- when file is not found
//     */
//    public void readSafeFile(String safeFile) throws IOException {
//
//        BufferedReader buff = new BufferedReader(new FileReader(safeFile));
//        String firstLine = buff.readLine();
//        String test;
//        String[] list = firstLine.split(" ");
//
//        //construct the safe from (row, column)
//        int row = Integer.parseInt(list[0]);
//        int column = Integer.parseInt(list[1]);
//        s = new Safe(row, column);
//
//
//        //process each row of the safeFile
//        for (int i = 0; i <= row; i++) {
//            test = buff.readLine();
//            processSafeLine(test, i);
//        }
//        s.displaySafe();
//
//    }
//
//    /**
//     * construct 2d array representation of the safe
//     *
//     * @param commandLine : file with shelf
//     * @param row         : the row to construct
//     */
//    public void processSafeLine(String commandLine, int row) {
//        String[] ls = commandLine.split(" ");
//        int column = 0;
//        for (String str : ls) {
//            switch (str) {
//                case ".":
//                    s.addTile(".", row, column);
//                    column++;
//                    break;
//                case "X":
//                    s.addTile("X", row, column);
//                    column++;
//                    break;
//                case "0":
//                    s.addTile("0", row, column);
//                    column++;
//                    break;
//                case "1":
//                    s.addTile("1", row, column);
//                    column++;
//                    break;
//                case "2":
//                    s.addTile("2", row, column);
//                    column++;
//                    break;
//                case "3":
//                    s.addTile("3", row, column);
//                    column++;
//                    break;
//                case "4":
//                    s.addTile("4", row, column);
//                    column++;
//                    break;
//            }
//        }
//    }
//
//    /**
//     * Processes each user command, and produce the action accordingly.
//     * @param command to follow.
//     */
//    public void processUserCommand(String command) {
//        String[] list = command.split(" ");
//        char cmd = list[0].charAt(0);
//        if (cmd == 'a') {
//            if ((list.length == 3)) {
//                int row = Integer.parseInt(list[1]);
//                int column = Integer.parseInt(list[2]);
//                if (list[1].isEmpty() || list[2].isEmpty() || row < 0 || column < 0) {
//                    System.out.println("Incorrect coordinates: " + command);
//                }else{
//                    s.addLaser(row, column);
//                }
//            } else {
//                System.out.println("Incorrect coordinates: " + command);
//            }
//
//        } else if (cmd == 'd') {
//            s.displaySafe();
//        } else if (cmd == 'h') {
//            s.help();
//        } else if (cmd == 'q') {
//            s.quit();
//        } else if (cmd == 'v') {
//            s.verify();
//        } else if (cmd == 'r') {
//            if ((list.length == 3)) {
//                int row = Integer.parseInt(list[1]);
//                int column = Integer.parseInt(list[2]);
//                if (list[1].isEmpty() || list[2].isEmpty() || row < 0 || column < 0) {
//                    System.out.println("Incorrect coordinates: (" + command + ")");
//                }else{
//                    s.removeLaser(row, column);
//                }
//            } else {
//                System.out.println("Incorrect coordinates: (" + command+ ")");
//            }
//        } else {
//            System.out.println("Unrecognized command: " + command);
//        }
//    }
//
//    /**
//     * creates the board from the safe file, then handles commands from the input file
//     * @param safeFile- to create the board from
//     * @param inputFile- to read the commands from
//     * @throws IOException when file is not found
//     */
//    public void readTwoArguments(String safeFile, String inputFile) throws IOException {
//        //construct the safe
//        readSafeFile(safeFile);
//        //process inputFile
//        readInputFile(inputFile);
//        //get user input.
//        System.out.print(">");
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextLine()) {
//            processUserCommand(in.nextLine());  //process input
//            System.out.print(">");              //prompt input
//        }
//    }
//
//
//    /**
//     * reads input-file line by line until none is left.
//     * @param inputFile to read input from
//     * @throws IOException when file cannot be found.
//     */
//    public void readInputFile(String inputFile) throws IOException {
//        try {
//            BufferedReader buff = new BufferedReader(new FileReader(inputFile));
//            String line;
//
//            //read each line, until none is left.
//            while ((line = buff.readLine()) != null) {
//                processUserCommand(line);
//            }
//        } catch (FileNotFoundException fnf) {
//            System.out.println("File cannot be found!");
//        } catch (IOException e) {
//            System.out.println("IOException");
//        }
//    }
//
//
//    /**
//     * The main method to handle user input
//     *
//     * @param args command line arguments
//     */
//    public static void main(String[] args) throws FileNotFoundException, IOException {
//        // check sanity of input
//        if (args.length < 1 || args.length > 2) {
//            System.out.println("Usage: java LasersPTUI safe-file [input]");
//        } else {
//            new LasersPTUI(args);
//        }
//    }
//
//
//}
