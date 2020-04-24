package lasers.ptui;

import lasers.model.LasersModel;
import lasers.model.ModelData;
import lasers.model.Safe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class represents the controller portion of the plain text UI.
 * It takes the model from the view (LasersPTUI) so that it can perform
 * the operations that are input in the run method.
 *
 * @author RIT CS
 * @author Amy Do
 * @author Shubhang Mehrotra
 */
public class ControllerPTUI {
    /**
     * The UI's connection to the lasers.lasers.model
     */
    private LasersModel model;   //matrix of ModelData



    /**
     * Construct the PTUI.  Create the model and initialize the view.
     *
     * @param model The laser model
     */
    public ControllerPTUI(LasersModel model) throws IOException {
        this.model = model;


    }



    /**
     * Processes each user command, and produce the action accordingly.
     *
     * @param command to follow.
     */
    public void processUserCommand(String command) {
        String[] list = command.split(" ");
        char cmd = list[0].charAt(0);
        if (cmd == 'a') {
            if ((list.length == 3)) {
                int row = Integer.parseInt(list[1]);
                int column = Integer.parseInt(list[2]);
                if (list[1].isEmpty() || list[2].isEmpty() || row < 0 || column < 0) {
                    System.out.println("Incorrect coordinates: " + command);
                } else {
                    model.addLaser(row, column);
                }
            } else {
                System.out.println("Incorrect coordinates: " + command);
            }

        } else if (cmd == 'd') {
            model.displaySafe();
        } else if (cmd == 'h') {
            model.help();
        } else if (cmd == 'q') {
            model.quit();
        } else if (cmd == 'v') {
            model.verify();
        } else if (cmd == 'r') {
            if ((list.length == 3)) {
                int row = Integer.parseInt(list[1]);
                int column = Integer.parseInt(list[2]);
                if (list[1].isEmpty() || list[2].isEmpty() || row < 0 || column < 0) {
                    System.out.println("Incorrect coordinates: (" + command + ")");
                } else {
                    model.removeLaser(row, column);
                }
            } else {
                System.out.println("Incorrect coordinates: (" + command + ")");
            }
        } else {
            System.out.println("Unrecognized command: " + command);
        }
    }

    /**
     * Run the main loop.This is the entry point for the controller
     *
     * @param inputFile The name of the input command file, if specified
     */
    public void run(String inputFile) {
        //if the inputFile is specified,
        if (inputFile != null) {
            try {
                BufferedReader buff = new BufferedReader(new FileReader(inputFile));
                String line;

                //read each line, until none is left.
                while ((line = buff.readLine()) != null) {
                    processUserCommand(line);
                }
            } catch (FileNotFoundException fnf) {
                System.out.println("File cannot be found!");
            } catch (IOException e) {
                System.out.println("IOException");
            }

        } else {
            System.out.print(">");
            Scanner in = new Scanner(System.in);
            while (in.hasNextLine()) {
                processUserCommand(in.nextLine());  //process input
                System.out.print(">");              //prompt input
            }
        }
    }
}
