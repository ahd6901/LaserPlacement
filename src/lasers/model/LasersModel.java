package lasers.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * The model of the lasers safe.  You are free to change this class however
 * you wish, but it should still follow the MVC architecture.
 *
 * @author RIT CS
 * @author Amy DO
 * @author Shubhang Mehrotra
 */
public class LasersModel {
    /**
     * the observers who are registered with this model
     */
    private List<Observer<LasersModel, ModelData>> observers;
    private Safe s;

    public LasersModel(String filename) throws IOException {
        this.observers = new LinkedList<>();
        // TODO: create the safe from safeFile
        readOneArgument(filename);

    }


    /**
     * Create the Safe, and process commands from user input
     *
     * @param safeFile to create the safe from
     * @throws IOException when file can't be found.
     */
    public void readOneArgument(String safeFile) throws IOException {
        //construct the safe
        readSafeFile(safeFile);

        //get user input.
        System.out.print(">");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            processUserCommand(in.nextLine());  //process input
            System.out.print(">");              //prompt input
        }
    }

    /**
     * //     * reads safe file to construct the safe object
     * //     * @param safeFile-contains information to construct safe object
     * //     * @throws IOException- when file is not found
     * //
     */
    public void readSafeFile(String safeFile) throws IOException {
        BufferedReader buff = new BufferedReader(new FileReader(safeFile));
        String firstLine = buff.readLine();
        String test;
        String[] list = firstLine.split(" ");

        //construct the safe from (row, column)
        int row = Integer.parseInt(list[0]);
        int column = Integer.parseInt(list[1]);
        s = new Safe(row, column);


        //process each row of the safeFile
        for (int i = 0; i <= row; i++) {
            test = buff.readLine();
            processSafeLine(test, i);
        }
        s.displaySafe();
    }

    /**
     * construct 2d array representation of the safe
     *
     * @param commandLine : file with shelf
     * @param row         : the row to construct
     */
    public void processSafeLine(String commandLine, int row) {
        String[] ls = commandLine.split(" ");
        int column = 0;
        for (String str : ls) {
            switch (str) {
                case ".":
                    s.addTile(".", row, column);
                    column++;
                    break;
                case "X":
                    s.addTile("X", row, column);
                    column++;
                    break;
                case "0":
                    s.addTile("0", row, column);
                    column++;
                    break;
                case "1":
                    s.addTile("1", row, column);
                    column++;
                    break;
                case "2":
                    s.addTile("2", row, column);
                    column++;
                    break;
                case "3":
                    s.addTile("3", row, column);
                    column++;
                    break;
                case "4":
                    s.addTile("4", row, column);
                    column++;
                    break;
            }
        }
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
                } else {
                    s.removeLaser(row, column);
                }
            } else {
                System.out.println("Incorrect coordinates: (" + command + ")");
            }
        } else {
            System.out.println("Unrecognized command: " + command);
        }
    }

    public Safe getSafe(){
        return this.s;
    }

    /**
     * Add a new observer.
     *
     * @param observer the new observer
     */
    public void addObserver(Observer<LasersModel, ModelData> observer) {
        this.observers.add(observer);
    }

    /**
     * Notify observers the model has changed.
     *
     * @param data optional data the model can send to the view
     */
    private void notifyObservers(ModelData data) {
        for (Observer<LasersModel, ModelData> observer : observers) {
            observer.update(this, data);
        }
    }

    /**
     * remove the observer from the list.
     *
     * @param observer to be removed.
     */
    public void removeObserver(Observer<LasersModel, ModelData> observer) {

        this.observers.remove(observer);
    }
}
