package lasers.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import lasers.model.*;

/**
 * The main class that implements the JavaFX UI.   This class represents
 * the view/controller portion of the UI.  It is connected to the lasers.lasers.model
 * and receives updates from it.
 *
 * @author RIT CS
 * @author Amy Do
 * @author Shubhang Mehrotra
 */
public class LasersGUI extends Application implements Observer<LasersModel, ModelData> {
    /** The UI's connection to the lasers.lasers.model */
    private LasersModel model;

    /** this can be removed - it is used to demonstrates the button toggle */
    private static boolean status = true;

    //The JavaFX GUI lives in the lasers.gui package. The main class is LasersGUI. This class acts as both view and
    // controller. Just like the plain text UI, it must implement the provided Observer interface and handle displaying
    // changes to the user via the update method. Additionally, it will also act like a controller and provide handlers
    // for the UI components so that the model can be informed of them.

    // Pay special attention to how the plain text UI is structured and launched in the main method.
    // There is a main class for the view, LasersPTUI, and a separate controller class, ControllerPTUI, in the lasers.ptui package.

    //Functionality:
    //
    //  Message Outputs: current status of the safe, updated as operations are performed, could go in the particular methods, or have a separate method to handle the message label.
    //      1. The name of the safe file when initially loaded or restarted. - Read from file, and display txt
    //      2. The result of attempting to add or remove a laser from the safe. - goes in add/remove
    //      3. The status of the safe when checked for correctness. - goes in verify
    //      4. The result of requesting a hint for the next laser to place. - hint
    //      5. The result of attempting to fully solve the laser placements. - result when pressed solve, from verify??
    //
    //  Graphical Display: From safe file in command line
    //
    //  Controls: All Buttons
    //           Add/Remove: A mouse click on a tile - if the cell is empty, or a laser beam, a new laser is added.
    //            If the cell already contains a laser, it should be removed. If a cell contains a pillar,
    //            it is an error. In all cases, a message should be displayed to the message
    //            area that includes the coordinate of the tile that was selected.
    //
    //           Check: button for verify method : If the check fails, the first tile that is invalid should be visibly
    //             marked in a way that stands out to the user and its coordinates should be indicated in the message area.
    //
    //           Hint: attempts to add a new laser to the safe,  the message area should be updated to indicate success
    //             or, if the safe is not valid, or the current laser placement can't yield a solution.
    //
    //           Solve: button to solve the puzzle : Solving should ignore the current placement of lasers and start
    //             from the initial configuration. Then display message.
    //
    //           Restart: Reset to initial, then display message.
    //
    //           Load: lets a user load another safe file : Selecting this should bring up a
    //           file chooser window that lets the user browse to a safe file





    @Override
    public void init() throws Exception {
        // the init method is run before start.  the file name is extracted
        // here and then the model is created.
        try {
            Parameters params = getParameters();
            String filename = params.getRaw().get(0);
            this.model = new LasersModel(filename);
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
            System.exit(-1);
        }
        this.model.addObserver(this);
    }

    /**
     * A private utility function for setting the background of a button to
     * an image in the resources subdirectory.
     *
     * @param button the button control
     * @param bgImgName the name of the image file
     */
    private void setButtonBackground(Button button, String bgImgName) {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image( getClass().getResource("resources/" + bgImgName).toExternalForm()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        button.setBackground(background);
    }

    /**
     * This is a private demo method that shows how to create a button
     * and attach a foreground image with a background image that
     * toggles from yellow to red each time it is pressed.
     *
     * @param stage the stage to add components into
     */
    private void buttonDemo(Stage stage) {
        // this demonstrates how to create a button and attach a foreground and
        // background image to it.
        Button button = new Button();
        Image laserImg = new Image(getClass().getResourceAsStream("resources/laser.png"));
        ImageView laserIcon = new ImageView(laserImg);
        button.setGraphic(laserIcon);
        setButtonBackground(button, "yellow.png");
        button.setOnAction(e -> {
            // toggles background between yellow and red
            if (!status) {
                setButtonBackground(button, "yellow.png");
            } else {
                setButtonBackground(button, "red.png");
            }
            status = !status;
        });

        Scene scene = new Scene(button);
        stage.setScene(scene);
    }

    /**
     * The initialization of all GUI component happens here.
     *
     * @param stage the stage to add UI components into
     */
    private void init(Stage stage) {
        // TODO
        buttonDemo(stage);  // this can be removed/altered
    }

    @Override
    public void start(Stage stage) throws Exception {
        // TODO
        init(stage);  // do all your UI initialization here

        stage.setTitle("Lasers GUI");
        stage.show();
    }

    @Override
    public void update(LasersModel model, ModelData data) {
        // TODO
    }
}
