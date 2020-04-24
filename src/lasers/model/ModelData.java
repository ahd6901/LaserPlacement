package lasers.model;

import java.util.ArrayList;

//tile

/**
 * Use this class to customize the data you wish to send from the model
 * to the view when the model changes state.
 * //basically a Tile representation
 *
 * @author RIT CS
 * @author Amy Do
 * @author Shubhang Mehrotra
 */
public class ModelData {
    private int row,column;
    private String content;
  //  private int beamCount;   //number of beam in this tile



    public ModelData( String content){
        this.content= content;
    }


//
//    public void addBeam(int num){
//        beamCount+=num;
//    }

//    public int getRow() {
//        return row;
//    }
//
//    public void setRow(int row) {
//        this.row = row;
//    }
//
//    public int getColumn() {
//        return column;
//    }
//
//    public void setColumn(int column) {
//        this.column = column;
//    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }















    //    private Integer[][] laserBeamCount;     // keeps count of the numbers of laser-beams on a tile
//
//    int row, column;  //cordinates of the laser
//
//    //update a Laser information.
//    public ModelData(int row, int collumn){
//        super();
//        this.laserBeamCount = new Integer[row][column];
//        for (int i = 0; i < this.column; i++) {    //initiallize number of beam at each tile
//            for (int j = 0; j < this.row; j++) {
//                this.laserBeamCount[i][j] = 0;
//            }
//        }
//    }
//
//    //
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
//            String target = safe[r][c];
//            if (target.equals(".") || target.equals("*")) {
//                safe[r][c] = "L";
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
//        //displaySafe();
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
//        //displaySafe();
//    }

}
