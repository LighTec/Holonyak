package frontEnd_ViewController;

import java.awt.*;
import java.io.File;
import javax.swing.*;

/**
 * This Class is repsonsible for what you see on or in the GUI, this class acts as the GUI setup 
 * and content updater. 
 * 
 * has 3 uses in total: create and initialize the main GUI window, update any of the components as instructed
 * by the controller, and creates additional windows (like a save dialouge).
 */
public class MainViewDisplay extends JFrame {

    /*
     *
     * MainViewDisplay needs to have a instance variable to reference the
     * backend's models because the frontend's MainViewDisplay is responsible
     * for displaying data from the backend.
     *
     * Since the backend models is initially set up by an instance of the
     * BackendModelSetup class, we just need this one instance variable here:
     */
    BackendModelSetup theBackendModel;

    /*
     *
     * Step 1 of 4 for creating GUI widgets: declare them
     * --------------------------------------------------
     *
     * GUI widgets to be displayed to the user on the screen is declared here
     * (but will be constructed and initialized in the initComponents method).
     * The user will see data and can later interact with these widgets.
     */
    JButton encryptButton;
    JLabel textLabel;
    JButton openButton;
    JTextArea textArea;
    JButton saveButton;
    JButton decryptButton;              //sample code
    JScrollPane textField;
    JButton probDistButton;
    JButton probDistSortedButton;
    JButton approxDecryptButton;

    /*
     *
     * Constructor. Probably nothing for students to change.
     */
    public MainViewDisplay(BackendModelSetup aBackend) {
        this.theBackendModel = aBackend;
        this.initComponents();
    }

    /*
     *
     * initComponents is all about fulfilling Responsibility #1 of this class:
     * (1) Construct the graphical user interface (GUI) on the screen
     */
    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //this.setMinimumSize(new Dimension(600, 200));

        /*
         *
         * Step 2 of 4 for creating GUI widgets: construct them
         * ----------------------------------------------------
         *
         * Construct GUI widget components here, and add them into the
         * mainDisplayPane later
         */
        this.textLabel = new JLabel();
        this.textLabel.setText("Text content:");

        this.saveButton = new JButton();
        this.saveButton.setText("Save File");

        this.openButton = new JButton();
        this.openButton.setText("Open File");

        this.textArea = new JTextArea();
        this.textArea.setLineWrap(true);
        this.textArea.setEditable(false);
        this.textArea.setWrapStyleWord(true);
        this.textArea.setSize(400,800);
        this.textField = new JScrollPane(this.textArea);

        this.encryptButton = new JButton();
        this.encryptButton.setText("Encrypt");

        this.decryptButton = new JButton();
        this.decryptButton.setText("Decrypt");
        
        this.probDistButton = new JButton();
        this.probDistButton.setText("Probability Distribution");
        
        this.probDistSortedButton = new JButton();
        this.probDistSortedButton.setText("Sorted Prob. Distribution");
        
        this.approxDecryptButton = new JButton();
        this.approxDecryptButton.setText("Approx. Decrypt");
        


        /*
         * Choose your LayoutManager for the mainDisplayPane here. See:
         * http://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
         *
         * I suggest GridBagLayout. For more details, see:
         * http://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
         */
        Container mainDisplayPane = this.getContentPane();
        mainDisplayPane.setLayout(new GridBagLayout());


        /*
         * you should construct a new GridBagConstraints object each time you
         * use it, in order to avoid subtle bugs...
         */
        GridBagConstraints c;


        /*
         *
         * Step 3 of 4 for creating GUI widgets: add them to the pane
         * ----------------------------------------------------------
         *
         * For each GUI widget you constructed earlier, you will now specify a
         * GridBagConstraints for it, then add the widget into the
         * mainDisplayPane
         */
        
        
        //c.fill = GridBagConstraints.BOTH; // many options! See online tutorial
        //c.ipady = 0;
        //c.weightx = 0;
        //c.weighty = 0;
        //c.anchor = GridBagConstraints.CENTER;
        //c.insets = new Insets(0, 0, 0, 0);
        
        
        c = new GridBagConstraints(); // construct a new GridBagConstraints each time you use it, to avoid subtle bugs...
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.SOUTHWEST;
        c.insets = new Insets(0, 50, 0, 0);
        mainDisplayPane.add(this.textLabel, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.ipadx = 500;
        c.ipady = 300;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(0, 50, 0, 0);
        mainDisplayPane.add(this.textField, c);

        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 0, 0, 50);
        mainDisplayPane.add(this.encryptButton, c);

        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 0, 0, 50);
        mainDisplayPane.add(this.decryptButton, c);

        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.SOUTHWEST;
        //c.insets = new Insets(0, 0, 0, 50);
        mainDisplayPane.add(this.openButton, c);

        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 3;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(0, 0, 0, 50);
        mainDisplayPane.add(this.saveButton, c);
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(0, 50, 0, 0);
        mainDisplayPane.add(this.probDistButton, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        mainDisplayPane.add(this.probDistSortedButton, c);
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 3;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        mainDisplayPane.add(this.approxDecryptButton, c);

        this.pack(); // leave this line last in this method.
        // must pack this JFrame before it can be displayed on screen
    }

    /*
     *
     * Step 4 of 4 for creating GUI widgets: write methods to update them
     * -------------------------------------------------------------------
     *
     * The methods below are all about fulfilling Responsibility #2 of this
     * class: (2) Pull data from the backend to display in the GUI
     *
     * Write below all the methods for displaying data into the GUI using this
     * MainViewDisplay object
     */
    void updateTextContentField() {

        //example of content update. Set new stuff, like different text in a box, here. Make extra methods for 
        //extra updaters.
        }
    
    
        String showSaveDialog() {
        JFileChooser kfc = new JFileChooser();
        int status = kfc.showSaveDialog(this);
        if (status == JFileChooser.APPROVE_OPTION) {
            File theFile = kfc.getSelectedFile();                       // some other examples of updaters
            String thePath = theFile.getAbsolutePath();
            return thePath;
        }

        return null;
    }

    String showOpenDialog() {
        JFileChooser kfc = new JFileChooser();
        int status = kfc.showOpenDialog(this);
        if (status == JFileChooser.APPROVE_OPTION) {
            File theFile = kfc.getSelectedFile();
            String thePath = theFile.getAbsolutePath();
            return thePath;
        }

        return null;
    }
    }



