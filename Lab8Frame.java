import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Lab8Frame extends JFrame implements ActionListener{

    public Lab8Canvas myCanvas;  // These are instance variables
    //private JTextField squares;    // variables that are part of the frame
    private JLabel sLabel;         // object and are not local to a method.
    private JComboBox choice;
    //private JButton run;
    private int rows;
    public final int canvasWidth = 700;
    private SearchBase searchBase;

    Lab8Frame (int i, SearchBase s) {
	super();
	rows=i;
	searchBase = s;
	setLocation(100, 100);
	addWindowListener(new MyWindowAdapter());

	myCanvas = new Lab8Canvas(canvasWidth, rows, searchBase);
	sLabel   = new JLabel (s.which+" --Description of search goes here.");
	//squares  = new JTextField(rows+"");
	//run      = new JButton("New Maze");

	String[] choices = {"dfsRobot","bfsRobot","aStarRobot2","idaStarRobot2"};
	choice = new JComboBox(choices);
	choice.setSelectedIndex(0);
	choice.addActionListener(this);
	//squares.addActionListener(this);
	//run.addActionListener(this);           
						 /* hitting enter in the text
						field will cause
						actionPerformed to be called */

	setLayout(new FlowLayout());   /* this says how to arrange things */
      
	add(myCanvas);                 /* we add components to the frame */  
	add(sLabel);
	//add(squares);
	add(choice);
	//add(run);

	setVisible(true);
	setSize (canvasWidth + 500, canvasWidth + 100);

	JComboBox cb = (JComboBox)choice;
	//myCanvas.setChoice((String)cb.getSelectedItem());
	myCanvas.repaint();

    }

    public void actionPerformed (ActionEvent e) {
	/*if (e.getSource() == choice) {
	    JComboBox cb = (JComboBox)e.getSource();
	    myCanvas.setChoice((String)cb.getSelectedItem());
	    myCanvas.repaint();
	}
	else if (e.getSource() == run) {
	    JComboBox cb = (JComboBox)choice;
	    myCanvas.setChoice((String)cb.getSelectedItem());
	    myCanvas.repaint();
	}
	else if (e.getSource() ==  squares) {
	    try {
		int r = Integer.parseInt(squares.getText());
		rows = r;
	    }
	    catch (NumberFormatException ex) {
		squares.setText(rows+"");
	    }
	    myCanvas.setRows(rows);
	    myCanvas.repaint();
	}*/
    }

    private class MyWindowAdapter extends WindowAdapter {

	public void windowClosing (WindowEvent e) {
	    dispose();    // this method is called if the window is closed
	    System.exit(0);
	}
    }
}





