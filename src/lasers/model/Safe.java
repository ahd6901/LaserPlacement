package lasers.model;

import java.util.ArrayList;


/**
 * The Model of the LasersPTUI project to represent the "safe" component of the game.
 * A safe whose floors are represented by a 2d array. Each floor tile is either covered by
 * a Pillar, Laser, or a laser beam.
 *
 * @author : Amy Do
 * @author : Shubhang Mehrotra
 *
 * last modified : 04/17/2020
 */
public class Safe {
//    private int row, column;                // co-ordinates of a floor tile
//    private ModelData[][] safe;                //a 2D array to represent the safe floor
//    private Integer[][] laserBeamCount;     // keeps count of the numbers of laser-beams on a tile
//
//
//    /**
//     * create the safe object from given dimensions.
//     * @param row- of the safe
//     * @param column- of the safe
//     */
//    public Safe(int row, int column) {
//        this.row = row;
//        this.column = column;
//        this.safe = new ModelData[row][column];
//        this.laserBeamCount = new Integer[row][column];
//
//        for (int i = 0; i < this.column; i++) {    //initiallize number of beam at each tile
//            for (int j = 0; j < this.row; j++) {
//            this.laserBeamCount[i][j] = 0;
//           }
//      }
//    }
//
//    public int getRow() {
//        return row;
//    }
//
//    public int getColumn() {
//        return column;
//    }
//
//    /**
//     * method to check the validity of the given pillar
//     * @param tile : a tile in a safe
//     * @return : true : if tile is valid
//     * false: otherwise
//     */
//    private boolean isPillar(String tile) {
//        switch (tile) {
//            case "X":
//            case "0":
//            case "1":
//            case "2":
//            case "3":
//            case "4":
//                return true;
//            default:
//                return false;
//        }
//    }
//
//    /**
//     * add a Laser at the required coordinates and make the laser beam
//     * @param r - row
//     * @param c - column
//     */
//    public void addLaser(int r, int c) {
//
//        if (r < this.row && c < this.column) {
//            ModelData target = safe[r][c];
//            if (target.getContent().equals(".") || target.getContent().equals("*")) {
//                safe[r][c].setContent("L");
//
//                //loop to draw east laser-beam
//                for (int i = c + 1; i < this.column; i++) {
//                    if ((isPillar(safe[r][i]) || safe[r][i].equals("L"))) {
//                        break;
//                    } else {
//                        safe[r][i] = "*";
//                        laserBeamCount[r][i]++;
//                    }
//                }
//
//                //loop to draw west laser-beam
//                for (int i = c - 1; i >= 0; i--) {
//                    if ((isPillar(safe[r][i]) || safe[r][i].equals("L"))) {
//                        break;
//                    } else {
//                        safe[r][i] = "*";
//                        laserBeamCount[r][i]++;
//                    }
//                }
//
//                //loop to draw north laser-beam
//                for (int i = r - 1; i >= 0; i--) {
//                    if ((isPillar(safe[i][c]) || safe[i][c].equals("L"))) {
//                        break;
//                    } else {
//                        safe[i][c] = "*";
//                        laserBeamCount[i][c]++;
//                    }
//                }
//
//                //loop to draw south laser-beam
//                for (int i = r + 1; i < this.column; i++) {
//                    if ((isPillar(safe[i][c]) || safe[i][c].equals("L"))) {
//                        break;
//                    } else {
//                        safe[i][c] = "*";
//                        laserBeamCount[i][c]++;
//                    }
//                }
//                System.out.println(String.format("Laser added at: (%d, %d)", r, c));
//            } else {
//                System.out.println(String.format("Error adding laser at: (%d, %d)", r, c));
//            }
//        } else {
//            System.out.println(String.format("Error adding laser at: (%d, %d)", r, c));
//        }
//        displaySafe();
//    }
//
//
//
//
//    /**
//     * remove a laser at given coordinates
//     *
//     * @param r row
//     * @param c column
//     */
//    public void removeLaser(int r, int c) {
//        if (r < this.row && c < this.column) {
//            String target = safe[r][c];
//            if (target.equals("L")) {
//                if(laserBeamCount[r][c] > 0) {
//                    safe[r][c] = "*";
//                }
//                else {
//                    safe[r][c] = ".";
//                }
//
//                //loop to remove east laser-beam
//                for (int i = c + 1; i < this.column; i++) {
//                    if ((isPillar(safe[r][i]) || safe[r][i].equals("L"))) {
//                        break;
//                    } else {
//                        laserBeamCount[r][i]--;
//                        if (laserBeamCount[r][i] == 0) {
//                            safe[r][i] = ".";
//                        }
//                    }
//                }
//
//                //loop to remove west laser-beam
//                for (int i = c - 1; i >= 0; i--) {
//                    if ((isPillar(safe[r][i]) || safe[r][i].equals("L"))) {
//                        break;
//                    } else {
//                        laserBeamCount[r][i]--;
//                        if (laserBeamCount[r][i] == 0) {
//                            safe[r][i] = ".";
//                        }
//                    }
//                }
//
//                //loop to remove north laser-beam
//                for (int i = r - 1; i >= 0; i--) {
//                    if ((isPillar(safe[i][c]) || safe[i][c].equals("L"))) {
//                        break;
//                    } else {
//                        laserBeamCount[i][c]--;
//                        if (laserBeamCount[i][c] == 0) {
//                            safe[i][c] = ".";
//                        }
//                    }
//                }
//
//                //loop to remove south laser-beam
//                for (int i = r + 1; i < this.column; i++) {
//                    if ((isPillar(safe[i][c]) || safe[i][c].equals("L"))) {
//                        break;
//                    } else {
//                        laserBeamCount[i][c]--;
//                        if (laserBeamCount[i][c] == 0) {
//                            safe[i][c] = ".";
//                        }
//                    }
//                }
//                System.out.println(String.format("Laser removed at: (%d, %d)", r, c));
//            } else {
//                System.out.println(String.format("Error removing laser at: (%d, %d)", r, c));
//            }
//        } else {
//            System.out.println(String.format("Error removing laser at: (%d, %d)", r, c));
//        }
//        displaySafe();
//    }
//
//    /**
//     * method to display the safe to standard output
//     */
//    public void displaySafe() {
//        StringBuilder str = new StringBuilder();
//        str.append(" ");
//        // build the column numbers
//        for (int col = 0; col < this.column; ++col) {
//            str.append(" ").append(col);
//        }
//        str.append("\n").append(" ");
//        str.append(" _".repeat(Math.max(0, this.column)));
//        str.append("\n");
//
//
//        // build the rows with number and values
//        for (int row = 0; row < this.row; ++row) {
//            str.append(row).append("|");
//
//            //print each line [row fixed, col++]
//            for (int col = 0; col < this.row; ++col) {
//                str.append(this.safe[row][col]).append(" ");
//            }
//            str.append("\n");
//        }
//        System.out.println(str);
//    }
//
//    /**
//     * method to add a tile in the safe
//     *
//     * @param str    : tile
//     * @param row    : row
//     * @param column : column
//     */
//    public void addTile(String str, int row, int column) {
//        this.safe[row][column] = str;
//    }
//
//    /**
//     * method to check the validity of a tile
//     *
//     * @param tile : a tile in a safe
//     * @return : true : if tile is valid
//     * false: otherwise
//     */
//    private boolean isTile(String tile) {
//        switch (tile) {
//            case "*":
//            case "L":
//            case "X":
//            case "0":
//            case "1":
//            case "2":
//            case "3":
//            case "4":
//                return true;
//            default:
//                return false;
//        }
//    }
//
//
//    /**
//     * displays the status message that indicates whether the safe is valid or not
//     *  + each tile must either be a pillar, beam or laser beam
//     *  + Number of Lasers surrounding a Pillar must equal the number that Pillar's specified.
//     *  + 2 lasers cannot be in the same row/column without a pillar between them
//     */
//    public void verify() {
//        boolean allCorrect = true;
//        for (int r = 0; r < this.row; r++) {
//            for (int c = 0; c < this.column; c++) {         //loop to iterate over the safe
//                String tile = safe[r][c];
//
//                //check if's the tile is covered or not
//                if (isTile(tile)) {
//
//                    //if tile is a pillar, do the check of  surrounding lasers.
//                    boolean PillarNum = true;
//                    if (isPillar(tile)) {
//                        PillarNum = checkPillarLasers(r, c);
//                        if (!PillarNum) {
//                            System.out.println(String.format("Error verifying at: (%d, %d)", r, c));
//                            allCorrect = false;
//                        }
//                    }
//
//                    //if is a laser, do the collision check (N-E-S-W)
//                    if (tile.equals("L")) {
//                        if (!LaserCollisonNorthCheck(r, c) || !LaserCollisonEastCheck(r, c) ||
//                                !LaserCollisonSouthCheck(r, c) || !LaserCollisonWestCheck(r, c)) {
//                            allCorrect = false;
//                            System.out.println(String.format("Error verifying at: (%d, %d)", r, c));
//                        }
//                    }
//                } else {
//                    System.out.println(String.format("Error verifying at: (%d, %d)", r, c));
//                    allCorrect = false;
//                }
//            }
//        }
//        if (allCorrect) {
//            System.out.println("Safe is fully verified!");
//        }
//    }
//
//    /**
//     * checks if the number of Lasers surrounding a Pillar equal the number that Pillar's specified or not
//     * @param r- row coordinate of the pillar
//     * @param c- column coordinate of the pillar
//     * @return true if match, otherwise false.
//     */
//    public boolean checkPillarLasers(int r, int c) {
//        boolean result = true;
//        ArrayList<String> LaserList = new ArrayList<>();
//
//        String pillar = safe[r][c];
//        // if (pillar.equals("L")
//        //only add inbound tiles to the list.
//        if (inBound(r, c - 1)) {
//            LaserList.add(safe[r][c - 1]);
//        }
//        if (inBound(r, c + 1)) {
//            LaserList.add(safe[r][c + 1]);
//        }
//        if (inBound(r - 1, c)) {
//            LaserList.add(safe[r - 1][c]);
//        }
//        if (inBound(r + 1, c)) {
//            LaserList.add(safe[r + 1][c]);
//        }
//
//        int count = 0;
//        for (String i : LaserList) { //
//            if (i.equals("L")) {
//                count++;
//            }
//        }
//        //no need to check for "X"
//        if (pillar.equals("X")) {
//            result = true;
//        } else {
//            int test = Integer.parseInt(pillar);
//            if (test != count) {
//                result = false;
//            }
//        }
//        return result;
//    }
//
//    /**
//     * checks if the given coordinates are within the bound or not
//     *
//     * @param r-row to coordinate be checked
//     * @param c-column coordinate to be checked
//     * @return true of inbound, false if out of bound
//     */
//    public boolean inBound(int r, int c) {
//        return r >= 0 && r < row && c >= 0 && c < column;
//    }
//
//    /**
//     * Check if collision occurred on the East side of the Laser
//     * @param r- row of the Laser
//     * @param c- column of the laser
//     * @return true of no collision, otherwise false.
//     */
//    public boolean LaserCollisonEastCheck(int r, int c) {
//        for (int i = c + 1; i < this.column; i++) {
//
//            if (isPillar(safe[r][i])) {
//                return true;
//            }
//            //if encounter another Laser
//            if (safe[r][i].equals("L")) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//
//    /**
//     * Check if collision occurred on the East side of the Laser
//     * @param r- row of the Laser
//     * @param c- column of the laser
//     * @return true of no collision, otherwise false.
//     */
//    public boolean LaserCollisonWestCheck(int r, int c) {
//        for (int i = c - 1; i >= 0; i--) {
//            if (isPillar(safe[r][i])) {
//                return true;
//            }
//
//            //if encounter another Laser
//            if (safe[r][i].equals("L")) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * Check if collision occurred on the East side of the Laser
//     * @param r- row of the Laser
//     * @param c- column of the laser
//     * @return true of no collision, otherwise false.
//     */
//    public boolean LaserCollisonNorthCheck(int r, int c) {
//
//        for (int i = r - 1; i >= 0; i--) {
//            if (isPillar(safe[i][c])) {
//                return true;
//            }
//            //if encounter another Laser
//            if (safe[i][c].equals("L")) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//
//    /**
//     * Check if collision occurred on the East side of the Laser
//     * @param r- row of the Laser
//     * @param c- column of the laser
//     * @return true of no collision, otherwise false.
//     */
//    public boolean LaserCollisonSouthCheck(int r, int c) {
//        for (int i = r + 1; i < this.column; i++) {
//            if (isPillar(safe[i][c])) {
//                return true;
//            }
//
//
//            if (safe[i][c].equals("L")) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//
//    /**
//     * method to print out the valid user commands for the functioning of the PTUI.
//     */
//    public void help() {
//        System.out.println("        a|add r c: Add laser to (r,c)\n" +
//                "        d|display: Display safe\n" +
//                "        h|help: Print this help message\n" +
//                "        q|quit: Exit program\n" +
//                "        r|remove r c: Remove laser from (r,c)\n" +
//                "        v|verify: Verify safe correctness ");
//    }
//
//    /**
//     * method to quit the program
//     */
//    public void quit() {
//        System.exit(0);
//    }

    private int row, column;                // co-ordinates of a floor tile
    private String[][] safe;                //a 2D array to represent the safe floor
    private Integer[][] laserBeamCount;     // keeps count of the numbers of laser-beams on a tile


    /**
     * create the safe object from given dimensions.
     * @param row- of the safe
     * @param column- of the safe
     */
    public Safe(int row, int column) {
        this.row = row;
        this.column = column;
        this.safe = new String[row][column];
        this.laserBeamCount = new Integer[row][column];

        for (int i = 0; i < this.column; i++) {    //initiallize number of beam at each tile
            for (int j = 0; j < this.row; j++) {
            this.laserBeamCount[i][j] = 0;
           }
      }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    /**
     * method to check the validity of the given pillar
     * @param tile : a tile in a safe
     * @return : true : if tile is valid
     * false: otherwise
     */
    private boolean isPillar(String tile) {
        switch (tile) {
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
     * add a Laser at the required coordinates and make the laser beam
     * @param r - row
     * @param c - column
     */
    public void addLaser(int r, int c) {

        if (r < this.row && c < this.column) {
            String target = safe[r][c];
            if (target.equals(".") || target.equals("*")) {
                safe[r][c] = "L";

                //loop to draw east laser-beam
                for (int i = c + 1; i < this.column; i++) {
                    if ((isPillar(safe[r][i]) || safe[r][i].equals("L"))) {
                        break;
                    } else {
                        safe[r][i] = "*";
                        laserBeamCount[r][i]++;
                    }
                }

                //loop to draw west laser-beam
                for (int i = c - 1; i >= 0; i--) {
                    if ((isPillar(safe[r][i]) || safe[r][i].equals("L"))) {
                        break;
                    } else {
                        safe[r][i] = "*";
                        laserBeamCount[r][i]++;
                    }
                }

                //loop to draw north laser-beam
                for (int i = r - 1; i >= 0; i--) {
                    if ((isPillar(safe[i][c]) || safe[i][c].equals("L"))) {
                        break;
                    } else {
                        safe[i][c] = "*";
                        laserBeamCount[i][c]++;
                    }
                }

                //loop to draw south laser-beam
                for (int i = r + 1; i < this.column; i++) {
                    if ((isPillar(safe[i][c]) || safe[i][c].equals("L"))) {
                        break;
                    } else {
                        safe[i][c] = "*";
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
        displaySafe();
    }




    /**
     * remove a laser at given coordinates
     *
     * @param r row
     * @param c column
     */
    public void removeLaser(int r, int c) {
        if (r < this.row && c < this.column) {
            String target = safe[r][c];
            if (target.equals("L")) {
                if(laserBeamCount[r][c] > 0) {
                    safe[r][c] = "*";
                }
                else {
                    safe[r][c] = ".";
                }

                //loop to remove east laser-beam
                for (int i = c + 1; i < this.column; i++) {
                    if ((isPillar(safe[r][i]) || safe[r][i].equals("L"))) {
                        break;
                    } else {
                        laserBeamCount[r][i]--;
                        if (laserBeamCount[r][i] == 0) {
                            safe[r][i] = ".";
                        }
                    }
                }

                //loop to remove west laser-beam
                for (int i = c - 1; i >= 0; i--) {
                    if ((isPillar(safe[r][i]) || safe[r][i].equals("L"))) {
                        break;
                    } else {
                        laserBeamCount[r][i]--;
                        if (laserBeamCount[r][i] == 0) {
                            safe[r][i] = ".";
                        }
                    }
                }

                //loop to remove north laser-beam
                for (int i = r - 1; i >= 0; i--) {
                    if ((isPillar(safe[i][c]) || safe[i][c].equals("L"))) {
                        break;
                    } else {
                        laserBeamCount[i][c]--;
                        if (laserBeamCount[i][c] == 0) {
                            safe[i][c] = ".";
                        }
                    }
                }

                //loop to remove south laser-beam
                for (int i = r + 1; i < this.column; i++) {
                    if ((isPillar(safe[i][c]) || safe[i][c].equals("L"))) {
                        break;
                    } else {
                        laserBeamCount[i][c]--;
                        if (laserBeamCount[i][c] == 0) {
                            safe[i][c] = ".";
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
        for (int col = 0; col < this.column; ++col) {
            str.append(" ").append(col);
        }
        str.append("\n").append(" ");
        str.append(" _".repeat(Math.max(0, this.column)));
        str.append("\n");


        // build the rows with number and values
        for (int row = 0; row < this.row; ++row) {
            str.append(row).append("|");

            //print each line [row fixed, col++]
            for (int col = 0; col < this.row; ++col) {
                str.append(this.safe[row][col]).append(" ");
            }
            str.append("\n");
        }
        System.out.println(str);
    }

    /**
     * method to add a tile in the safe
     *
     * @param str    : tile
     * @param row    : row
     * @param column : column
     */
    public void addTile(String str, int row, int column) {
        this.safe[row][column] = str;
    }

    /**
     * method to check the validity of a tile
     *
     * @param tile : a tile in a safe
     * @return : true : if tile is valid
     * false: otherwise
     */
    private boolean isTile(String tile) {
        switch (tile) {
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


    /**
     * displays the status message that indicates whether the safe is valid or not
     *  + each tile must either be a pillar, beam or laser beam
     *  + Number of Lasers surrounding a Pillar must equal the number that Pillar's specified.
     *  + 2 lasers cannot be in the same row/column without a pillar between them
     */
    public void verify() {
        boolean allCorrect = true;
        for (int r = 0; r < this.row; r++) {
            for (int c = 0; c < this.column; c++) {         //loop to iterate over the safe
                String tile = safe[r][c];

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
        ArrayList<String> LaserList = new ArrayList<>();

        String pillar = safe[r][c];
        // if (pillar.equals("L")
        //only add inbound tiles to the list.
        if (inBound(r, c - 1)) {
            LaserList.add(safe[r][c - 1]);
        }
        if (inBound(r, c + 1)) {
            LaserList.add(safe[r][c + 1]);
        }
        if (inBound(r - 1, c)) {
            LaserList.add(safe[r - 1][c]);
        }
        if (inBound(r + 1, c)) {
            LaserList.add(safe[r + 1][c]);
        }

        int count = 0;
        for (String i : LaserList) { //
            if (i.equals("L")) {
                count++;
            }
        }
        //no need to check for "X"
        if (pillar.equals("X")) {
            result = true;
        } else {
            int test = Integer.parseInt(pillar);
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
        return r >= 0 && r < row && c >= 0 && c < column;
    }

    /**
     * Check if collision occurred on the East side of the Laser
     * @param r- row of the Laser
     * @param c- column of the laser
     * @return true of no collision, otherwise false.
     */
    public boolean LaserCollisonEastCheck(int r, int c) {
        for (int i = c + 1; i < this.column; i++) {

            if (isPillar(safe[r][i])) {
                return true;
            }
            //if encounter another Laser
            if (safe[r][i].equals("L")) {
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
            if (isPillar(safe[r][i])) {
                return true;
            }

            //if encounter another Laser
            if (safe[r][i].equals("L")) {
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
            if (isPillar(safe[i][c])) {
                return true;
            }
            //if encounter another Laser
            if (safe[i][c].equals("L")) {
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
        for (int i = r + 1; i < this.column; i++) {
            if (isPillar(safe[i][c])) {
                return true;
            }


            if (safe[i][c].equals("L")) {
                return false;
            }
        }
        return true;
    }


    /**
     * method to print out the valid user commands for the functioning of the PTUI.
     */
    public void help() {
        System.out.println("        a|add r c: Add laser to (r,c)\n" +
                "        d|display: Display safe\n" +
                "        h|help: Print this help message\n" +
                "        q|quit: Exit program\n" +
                "        r|remove r c: Remove laser from (r,c)\n" +
                "        v|verify: Verify safe correctness ");
    }

    /**
     * method to quit the program
     */
    public void quit() {
        System.exit(0);
    }

}
