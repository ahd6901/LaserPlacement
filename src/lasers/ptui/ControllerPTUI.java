package lasers.ptui;

import lasers.model.LasersModel;
import lasers.model.Safe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class represents the controller portion of the plain text UI.
 * It takes the model from the view (LasersPTUI) so that it can perform
 * the operations that are input in the run method.
 *
 * @author RIT CS
 * @author Amy Do
 * @author Shubhang Mehrotra
 */
public class ControllerPTUI  {
    /** The UI's connection to the lasers.lasers.model */
    private LasersModel model;
    private Safe s;


    /**
     * Construct the PTUI.  Create the model and initialize the view.
     * @param model The laser model
     */
    public ControllerPTUI(LasersModel model) {
        this.model = model;
        this.s= this.model.getSafe();          //get the current safe.
    }

     /**
     * reads input-file line by line until none is left.
     * @param inputFile to read input from
     * @throws IOException when file cannot be found.
     */
    public void readInputFile(String inputFile) throws IOException {
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
    }
    /**
     * Processes each user command, and produce the action accordingly.
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
                }else{
                    s.addLaser(row, column);
                }
            } else {
                System.out.println("Incorrect coordinates: " + command);
            }

        } else if (cmd == 'd') {
            s.displaySafe();
        } else if (cmd == 'h') {
            s.help();
        } else if (cmd == 'q') {
            s.quit();
        } else if (cmd == 'v') {
            s.verify();
        } else if (cmd == 'r') {
            if ((list.length == 3)) {
                int row = Integer.parseInt(list[1]);
                int column = Integer.parseInt(list[2]);
                if (list[1].isEmpty() || list[2].isEmpty() || row < 0 || column < 0) {
                    System.out.println("Incorrect coordinates: (" + command + ")");
                }else{
                    s.removeLaser(row, column);
                }
            } else {
                System.out.println("Incorrect coordinates: (" + command+ ")");
            }
        } else {
            System.out.println("Unrecognized command: " + command);
        }
    }

    /**
     * Run the main loop.  This is the entry point for the controller
     * @param inputFile The name of the input command file, if specified
     */
    public void run(String inputFile) {
        // TODO: proccess inpuFile if it's specified
        if(!inputFile.equals(null)){

        }

    }
}
