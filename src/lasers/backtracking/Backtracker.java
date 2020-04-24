package lasers.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//two options, is valid function():
                            //decide a number, another laser
// create a map of the grid



/**
 * This class represents the classic recursive backtracking algorithm.
 * It has a solver that can take a valid configuration and return a
 * solution, if one exists.
 *
 * This file comes from the backtracking lecture. It should be useful
 * in this project. A second method has been added that you should
 * implement.
 *
 * @author RIT CS
 * @author Amy DO
 * @author Shubhang Mehrotra
 */
public class Backtracker {

    // Each tile that isn't a pillar can be populated with two successors - an empty tile, or a laser.
    // This is done in getSuccessors. You should not check the validity of the successors here.
    // If a laser was last placed, you must check that no other laser is in sight of it. This is done in isValid.
    // When the last tile in the safe is populated, you must check that all numbered pillars have the exact number of
    // lasers attached to them. There should also be no empty tiles remaining. This can be done in isValid or isGoal.

    //better pruning algorithms. They could start by focusing on the numbered pillars, and then working through the
    // empty tiles that remain and try to place lasers on them. If you implement this or something else that noticeably
    // speeds up your solver you will receive extra credit for the project.

    private boolean debug;

    /**
     * Initialize a new backtracker.
     *
     * @param debug Is debugging output enabled?
     */
    public Backtracker(boolean debug) {
        this.debug = debug;
        if (this.debug) {
            System.out.println("Backtracker debugging enabled...");
        }
    }

    /**
     * A utility routine for printing out various debug messages.
     *
     * @param msg    The type of config being looked at (current, goal,
     *               successor, e.g.)
     * @param config The config to display
     */
    private void debugPrint(String msg, Configuration config) {
        if (this.debug) {
            System.out.println(msg + ":\n" + config);
        }
    }

    /**
     * Try find a solution, if one exists, for a given configuration.
     *
     * @param config A valid configuration
     * @return A solution config, or Optional.empty() if no solution
     */
    public Optional<Configuration> solve(Configuration config) {
        debugPrint("Current config", config);
        if (config.isGoal()) {
            debugPrint("\tGoal config", config);
            return Optional.of(config);
        } else {
            for (Configuration child : config.getSuccessors()) {
                if (child.isValid()) {
                    debugPrint("\tValid successor", child);
                    Optional<Configuration> sol = solve(child);
                    if (sol.isPresent()) {
                        return sol;
                    }
                } else {
                    debugPrint("\tInvalid successor", child);
                }
            }
            // implicit backtracking happens here
        }
        return Optional.empty();
    }

    /**
     * Find a goal configuration if it exists, and how to get there.
     *
     * @param current the starting configuration
     * @return a list of configurations to get to a goal configuration.
     * If there are none, return null.
     */
    public List<Configuration> solveWithPath(Configuration current) {
        // TODO
        return new ArrayList<>();  // change this
    }
}
