package lasers.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


//grid, 2d array of modelData
// observer observes the model, MVC model..addObserver(this), have a list

/**
 * The model of the lasers safe.  You are free to change this class however
 * you wish, but it should still follow the MVC architecture.
 *
 *
 * @author RIT CS
 * @author Amy DO
 */
public class LasersModel {

    ModelData[][] model;                 //2d arr of ModelData
    int totalColumn, totalRow;
    private Integer[][] laserBeamCount;     // keeps count of the numbers of laser-beams on a tile
    private String safeFile;

    /**
     * the observers who are registered with this model
     */
    private List<Observer<LasersModel, ModelData>> observers;

    public LasersModel(String filename) throws IOException {
        this.observers = new LinkedList<>();
        this.safeFile= filename;
        this.laserBeamCount = new Integer[totalRow][totalColumn];

        for (int i = 0; i < this.totalColumn; i++) {    //initiallize number of beam at each tile
            for (int j = 0; j < this.totalRow; j++) {
                this.laserBeamCount[i][j] = 0;
            }
        }
        // TODO: read safeFile and create the model
        readSafeFile(filename);


    }

    public void addTile(ModelData tile, int row, int column ){
        model[row][column]= tile;
    }


    /**
     * //     * reads safe file to construct the safe object
     * //     * @param safeFile-contains information to construct safe object
     * //     * @throws IOException- when file is not found
     * //
     */
    public void readSafeFile(String safeFile) throws IOException {
        //read the safeFile for row, collum
        BufferedReader buff = new BufferedReader(new FileReader(safeFile));
        String firstLine = buff.readLine();
        String[] list = firstLine.split(" ");

        //construct the model from (row, column)
        int totalRow = Integer.parseInt(list[0]);
        int totalColumn = Integer.parseInt(list[1]);
        model= new ModelData[totalRow][totalColumn];

        //process each each in the safeFile
        String line;
        for (int i = 0; i <= totalRow; i++) {
            line = buff.readLine();
            processSafeLine(line, i);
        }
    }



    /**
     * construct 2d array representation of the safe
     *
     * @param commandLine : file with shelf
     * @param row         : the row to construct
     */
    public void processSafeLine(String commandLine, int row) {
        //TODO: figure out how ad
        String[] ls = commandLine.split(" ");
        int column = 0;
        for (String str : ls) {
            switch (str) {
                case ".":
                    model[row][column].setContent(".");
                    column++;
                    break;
                case "X":
                    model[row][column].setContent("X");
                    column++;
                    break;
                case "0":
                    model[row][column].setContent("0");
                    column++;
                    break;
                case "1":
                    model[row][column].setContent("1");
                    column++;
                    break;
                case "2":
                    model[row][column].setContent("2");
                    column++;
                    break;
                case "3":
                    model[row][column].setContent("3");
                    column++;
                    break;
                case "4":
                    model[row][column].setContent("4");
                    column++;
                    break;
            }
        }
    }

    public String getSafeFile() {
        return safeFile;
    }

    public int getTotalColumn() {
        return totalColumn;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public Integer[][] getLaserBeamCount() {
        return laserBeamCount;
    }

    public List<Observer<LasersModel, ModelData>> getObservers() {
        return observers;
    }

    /**
     * get the 2d array of tiles.
     * @return model
     */
    public ModelData[][] getModel() {
        return model;
    }





    /**
     * add a Laser at the required coordinates and make the laser beam
     * @param r - row
     * @param c - column
     */
    public void addLaser(int r, int c) {

        if (r < this.totalRow && c < this.totalColumn) {
            ModelData target = model[r][c];
            if (target.equals(".") || target.equals("*")) {
                model[r][c].setContent("L");

                //loop to draw east laser-beam
                for (int i = c + 1; i < this.totalColumn; i++) {
                    if ((isPillar(model[r][i]) || model[r][i].getContent().equals("L"))) {
                        break;
                    } else {
                        model[r][i].setContent("*");
                        laserBeamCount[r][i]++;
                    }
                }

                //loop to draw west laser-beam
                for (int i = c - 1; i >= 0; i--) {
                    if ((isPillar(model[r][i]) || model[r][i].getContent().equals("L"))) {
                        break;
                    } else {
                        model[r][i].setContent("*");
                        laserBeamCount[r][i]++;
                    }
                }

                //loop to draw north laser-beam
                for (int i = r - 1; i >= 0; i--) {
                    if ((isPillar(model[i][c]) || model[i][c].getContent().equals("L"))) {
                        break;
                    } else {
                        model[i][c].setContent("*");
                        laserBeamCount[i][c]++;
                    }
                }

                //loop to draw south laser-beam
                for (int i = r + 1; i < this.totalColumn; i++) {
                    if ((isPillar(model[i][c]) || model[i][c].getContent().equals("L"))) {
                        break;
                    } else {
                        model[i][c].setContent("*");
                        laserBeamCount[i][c]++;
                    }
                }
                System.out.println(String.format("Laser added at: (%d, %d)", r, c));
            } else {
                System.out.println(String.format("Error adding laser at: (%d, %d)", r, c));
            }
        } else {
            System.out.println(String.format("Error adding laser at: (%d, %d)", r, c));
        }
    }




    /**
     * remove a laser at given coordinates
     *
     * @param r row
     * @param c column
     */
    public void removeLaser(int r, int c) {
        if (r < this.totalRow && c < this.totalColumn) {
            ModelData target = model[r][c];
            if (target.equals("L")) {
                if(laserBeamCount[r][c] > 0) {
                    model[r][c].setContent("*");
                }
                else {
                    model[r][c].setContent(".");
                }

                //loop to remove east laser-beam
                for (int i = c + 1; i < this.totalColumn; i++) {
                    if ((isPillar(model[r][i]) || model[r][i].getContent().equals("L"))) {
                        break;
                    } else {
                        laserBeamCount[r][i]--;
                        if (laserBeamCount[r][i] == 0) {
                            model[r][i].setContent(".");
                        }
                    }
                }

                //loop to remove west laser-beam
                for (int i = c - 1; i >= 0; i--) {
                    if ((isPillar(model[r][i]) || model[r][i].getContent().equals("L"))) {
                        break;
                    } else {
                        laserBeamCount[r][i]--;
                        if (laserBeamCount[r][i] == 0) {
                            model[r][i].setContent(".");
                        }
                    }
                }

                //loop to remove north laser-beam
                for (int i = r - 1; i >= 0; i--) {
                    if ((isPillar(model[i][c]) || model[i][c].getContent().equals("L"))) {
                        break;
                    } else {
                        laserBeamCount[i][c]--;
                        if (laserBeamCount[i][c] == 0) {
                            model[i][c].setContent(".");
                        }
                    }
                }

                //loop to remove south laser-beam
                for (int i = r + 1; i < this.totalColumn; i++) {
                    if ((isPillar(model[i][c]) || model[i][c].getContent().equals("L"))) {
                        break;
                    } else {
                        laserBeamCount[i][c]--;
                        if (laserBeamCount[i][c] == 0) {
                            model[i][c].setContent( ".");
                        }
                    }
                }
                System.out.println(String.format("Laser removed at: (%d, %d)", r, c));
            } else {
                System.out.println(String.format("Error removing laser at: (%d, %d)", r, c));
            }
        } else {
            System.out.println(String.format("Error removing laser at: (%d, %d)", r, c));
        }
        displaySafe();
    }

    /**
     * method to display the safe to standard output
     */
    public void displaySafe() {
        StringBuilder str = new StringBuilder();
        str.append(" ");
        // build the column numbers
        for (int col = 0; col < this.totalColumn; ++col) {
            str.append(" ").append(col);
        }
        str.append("\n").append(" ");
        str.append(" _".repeat(Math.max(0, this.totalColumn)));
        str.append("\n");


        // build the rows with number and values
        for (int row = 0; row < this.totalRow; ++row) {
            str.append(row).append("|");

            //print each line [row fixed, col++]
            for (int col = 0; col < this.totalRow; ++col) {
                str.append(this.model[row][col]).append(" ");
            }
            str.append("\n");
        }
        System.out.println(str);
    }


    /**
     * method to quit the program
     */
    public void quit() {
        System.exit(0);
    }


    public void help() {
        System.out.println("        a|add r c: Add laser to (r,c)\n" +
                "        d|display: Display safe\n" +
                "        h|help: Print this help message\n" +
                "        q|quit: Exit program\n" +
                "        r|remove r c: Remove laser from (r,c)\n" +
                "        v|verify: Verify safe correctness ");
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

    /**
     * displays the status message that indicates whether the safe is valid or not
     *  + each tile must either be a pillar, beam or laser beam
     *  + Number of Lasers surrounding a Pillar must equal the number that Pillar's specified.
     *  + 2 lasers cannot be in the same row/column without a pillar between them
     */
    public void verify() {
        boolean allCorrect = true;
        for (int r = 0; r < this.totalRow; r++) {
            for (int c = 0; c < this.totalColumn; c++) {         //loop to iterate over the safe
                ModelData tile = model[r][c];

                //check if's the tile is covered or not
                if (isTile(tile)) {

                    //if tile is a pillar, do the check of  surrounding lasers.
                    boolean PillarNum = true;
                    if (isPillar(tile)) {
                        PillarNum = checkPillarLasers(r, c);
                        if (!PillarNum) {
                            System.out.println(String.format("Error verifying at: (%d, %d)", r, c));
                            allCorrect = false;
                        }
                    }

                    //if is a laser, do the collision check (N-E-S-W)
                    if (tile.equals("L")) {
                        if (!LaserCollisonNorthCheck(r, c) || !LaserCollisonEastCheck(r, c) ||
                                !LaserCollisonSouthCheck(r, c) || !LaserCollisonWestCheck(r, c)) {
                            allCorrect = false;
                            System.out.println(String.format("Error verifying at: (%d, %d)", r, c));
                        }
                    }
                } else {
                    System.out.println(String.format("Error verifying at: (%d, %d)", r, c));
                    allCorrect = false;
                }
            }
        }
        if (allCorrect) {
            System.out.println("Safe is fully verified!");
        }
    }
    /**
     * checks if the number of Lasers surrounding a Pillar equal the number that Pillar's specified or not
     * @param r- row coordinate of the pillar
     * @param c- column coordinate of the pillar
     * @return true if match, otherwise false.
     */
    public boolean checkPillarLasers(int r, int c) {
        boolean result = true;
        ArrayList<ModelData> LaserList = new ArrayList<>();

        ModelData pillar = model[r][c];
        // if (pillar.equals("L")
        //only add inbound tiles to the list.
        if (inBound(r, c - 1)) {
            LaserList.add(model[r][c - 1]);
        }
        if (inBound(r, c + 1)) {
            LaserList.add(model[r][c + 1]);
        }
        if (inBound(r - 1, c)) {
            LaserList.add(model[r - 1][c]);
        }
        if (inBound(r + 1, c)) {
            LaserList.add(model[r + 1][c]);
        }

        int count = 0;
        for (ModelData i : LaserList) { //
            if (i.getContent().equals("L")) {
                count++;
            }
        }
        //no need to check for "X"
        if (pillar.getContent().equals("X")) {
            result = true;
        } else {
            int test = Integer.parseInt(pillar.getContent());
            if (test != count) {
                result = false;
            }
        }
        return result;
    }

    /**
     * checks if the given coordinates are within the bound or not
     *
     * @param r-row to coordinate be checked
     * @param c-column coordinate to be checked
     * @return true of inbound, false if out of bound
     */
    public boolean inBound(int r, int c) {
        return r >= 0 && r < totalRow && c >= 0 && c < totalColumn;
    }

    /**
     * Check if collision occurred on the East side of the Laser
     * @param r- row of the Laser
     * @param c- column of the laser
     * @return true of no collision, otherwise false.
     */
    public boolean LaserCollisonEastCheck(int r, int c) {
        for (int i = c + 1; i < this.totalColumn; i++) {

            if (isPillar(model[r][i])) {
                return true;
            }
            //if encounter another Laser
            if (model[r][i].equals("L")) {
                return false;
            }
        }
        return true;
    }


    /**
     * Check if collision occurred on the East side of the Laser
     * @param r- row of the Laser
     * @param c- column of the laser
     * @return true of no collision, otherwise false.
     */
    public boolean LaserCollisonWestCheck(int r, int c) {
        for (int i = c - 1; i >= 0; i--) {
            if (isPillar(model[r][i])) {
                return true;
            }

            //if encounter another Laser
            if (model[r][i].getContent().equals("L")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if collision occurred on the East side of the Laser
     * @param r- row of the Laser
     * @param c- column of the laser
     * @return true of no collision, otherwise false.
     */
    public boolean LaserCollisonNorthCheck(int r, int c) {

        for (int i = r - 1; i >= 0; i--) {
            if (isPillar(model[i][c])) {
                return true;
            }
            //if encounter another Laser
            if (model[i][c].getContent().equals("L")) {
                return false;
            }
        }
        return true;
    }


    /**
     * Check if collision occurred on the East side of the Laser
     * @param r- row of the Laser
     * @param c- column of the laser
     * @return true of no collision, otherwise false.
     */
    public boolean LaserCollisonSouthCheck(int r, int c) {
        for (int i = r + 1; i < this.totalColumn; i++) {
            if (isPillar(model[i][c])) {
                return true;
            }


            if (model[i][c].getContent().equals("L")) {
                return false;
            }
        }
        return true;
    }
    /**
     * method to check the validity of the given pillar
     * @param tile : a tile in a safe
     * @return : true : if tile is valid
     * false: otherwise
     */
    private boolean isPillar(ModelData tile) {
        switch (tile.getContent()) {
            case "X":
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
                return true;
            default:
                return false;
        }
    }

    /**
     * method to check the validity of a tile
     *
     * @param tile : a tile in a safe
     * @return : true : if tile is valid
     * false: otherwise
     */
    private boolean isTile(ModelData tile) {
        switch (tile.getContent()) {
            case "*":
            case "L":
            case "X":
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
                return true;
            default:
                return false;
        }
    }



//    /**
//     * the observers who are registered with this model
//     */
//    private List<Observer<LasersModel, ModelData>> observers;
//    private Safe s;
//
//
//    public LasersModel(String filename) throws IOException {
//        this.observers = new LinkedList<>();
//        // TODO: initialzie a the view (board)
//        readSafeFile(filename);
//
//    }
//
//
//
//
//    /**
//     * //     * reads safe file to construct the safe object
//     * //     * @param safeFile-contains information to construct safe object
//     * //     * @throws IOException- when file is not found
//     * //
//     */
//    public void readSafeFile(String safeFile) throws IOException {
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
//    }
//
//    public Safe getS() {
//        return s;
//    }
//
//    /**
//     * construct 2d array representation of the safe
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
//    public void addLaser(){
//
//    }
//
//    public Safe getSafe(){
//        return s;
//    }
//
//    /**
//     * Add a new observer.
//     *
//     * @param observer the new observer
//     */
//    public void addObserver(Observer<LasersModel, ModelData> observer) {
//        this.observers.add(observer);
//    }
//
//    /**
//     * Notify observers the model has changed.
//     *
//     * @param data optional data the model can send to the view
//     */
//    private void notifyObservers(ModelData data) {
//        for (Observer<LasersModel, ModelData> observer : observers) {
//            observer.update(this, data);
//        }
//    }
//
//    /**
//     * remove the observer from the list.
//     *
//     * @param observer to be removed.
//     */
//    public void removeObserver(Observer<LasersModel, ModelData> observer) {
//        this.observers.remove(observer);
//    }





}
