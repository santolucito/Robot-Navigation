import java.awt.*;
import javax.swing.*;

class Lab8Canvas extends JPanel {

    private int canvasWidth;             
    private int rows;
    private String choice = "";          // The initial menu choice
    private SearchBase s;

    public Lab8Canvas(int w, int r, SearchBase s) {
	canvasWidth = w;
	rows = r;
	this.s = s;
	setPreferredSize (new Dimension(w+1, w+1));
	setBackground(Color.white);
	setOpaque(false);
    }

    public synchronized void setChoice (String c) {
	choice = c;
    }

    public synchronized void setRows (int r) {
	rows = r;
    }

    public synchronized void paintComponent (Graphics g){

	super.paintComponent(g);

	Dimension size = getSize();
	int canvasWidth = size.width;
	int canvasHeight = size.height;

	if (canvasHeight < canvasWidth) canvasWidth = canvasHeight;
	
	int columns = rows;             // same # rows as columns

	int width = canvasWidth / rows; // width of a cell

	g.setColor(Color.black);        // draw the grid
	for (int i=0; i<=rows; ++i)
	    g.drawLine(0,i*width,columns*width,i*width);
	for (int j=0; j<=columns; ++j)
	    g.drawLine(j*width,0,j*width,rows*width);


	Square[][] matrix = new Square[rows][columns];

	s.makeArray(matrix);//get picture rep
	


	for (int i=0; i<rows; ++i)//no brace b/c just one line (a for loop)
	  // draw it
	    for (int j=0; j<columns; ++j){
		g.setColor(new Color(14,14,244));
		if (matrix[i][j].getState()==3)//block
		    g.fillRect(j*width+1,i*width+1,width-1,width-1);
		else if(matrix[i][j].getState()==2){//space
		    g.setColor(new Color(24,194,4));
		    g.fillRect(j*width+1,i*width+1,width-1,width-1);
		}	
		else if(matrix[i][j].getState()==1){//robot
		    g.setColor(Color.black);
		    g.fillRect(j*width+1,i*width+1,width-1,width-1);
		    	   
		}
		else if(matrix[i][j].getState()==4){//goal
		    g.setColor(Color.yellow);
		    g.fillRect(j*width+1,i*width+1,width-1,width-1);
	
		}
		else if(matrix[i][j].getState()==5){//visited
		    g.setColor(Color.black);//new Color(164,244,4));
		    if((i<rows-1 && matrix[i][j].getStep()==matrix[i+1][j].getStep()-1)&&(i>0 && matrix[i][j].getStep()==matrix[i-1][j].getStep()+1)||//moving up
			(i<rows-1 && matrix[i][j].getStep()==matrix[i+1][j].getStep()+1)&&(i>0 && matrix[i][j].getStep()==matrix[i-1][j].getStep()-1))//moving down
			g.fillRect(j*width+1+(width/4),i*width+1,(width/2-1),(width-1));
		    else if((j<rows-1 && matrix[i][j].getStep()==matrix[i][j+1].getStep()-1)&&(j>0 && matrix[i][j].getStep()==matrix[i][j-1].getStep()+1)||//moving left
			(j<rows-1 && matrix[i][j].getStep()==matrix[i][j+1].getStep()+1)&&(j>0 && matrix[i][j].getStep()==matrix[i][j-1].getStep()-1))//moving right
			g.fillRect(j*width+1,i*width+1+(width/4),width-1,width/2-1);
		    
		    else if((i>0 && matrix[i][j].getStep()-1==matrix[i-1][j].getStep())&&(j>0 && matrix[i][j].getStep()+1==matrix[i][j-1].getStep())||	// -X
			    (j>0 && matrix[i][j].getStep()-1==matrix[i][j-1].getStep())&&(i>0 && matrix[i][j].getStep()+1==matrix[i-1][j].getStep()))   // XX
			g.fillRect(j*width+1,i*width+1,width/2-1,width/2-1);
 		    else if((i>0 && matrix[i][j].getStep()-1==matrix[i-1][j].getStep())&&(j<rows-1 && matrix[i][j].getStep()+1==matrix[i][j+1].getStep())|| 	// X-
			    (j<rows-1 && matrix[i][j].getStep()-1==matrix[i][j+1].getStep())&&(i>0 && matrix[i][j].getStep()+1==matrix[i-1][j].getStep()))      // XX
			g.fillRect(j*width+1+(width/2),i*width+1,width/2-1,width/2-1);
	  	    else if((i<rows-1 && matrix[i][j].getStep()-1==matrix[i+1][j].getStep())&&(j>0 && matrix[i][j].getStep()+1==matrix[i][j-1].getStep())||  // XX
			    (j>0 && matrix[i][j].getStep()-1==matrix[i][j-1].getStep())&&(i<rows-1 && matrix[i][j].getStep()+1==matrix[i+1][j].getStep()))   // -X
			g.fillRect(j*width+1,i*width+1+(width/2),width/2-1,width/2-1);
		    else if((i<rows-1 && matrix[i][j].getStep()-1==matrix[i+1][j].getStep())&&(j<rows-1 && matrix[i][j].getStep()+1==matrix[i][j+1].getStep())||  // XX 
			    (j<rows-1 && matrix[i][j].getStep()-1==matrix[i][j+1].getStep())&&(i<rows-1 && matrix[i][j].getStep()+1==matrix[i+1][j].getStep()))   // X- 
			g.fillRect(j*width+1+(width/2),i*width+1+(width/2),width/2-1,width/2-1);
		   
		    else g.fillRect(j*width+1+(width/4),i*width+1+(width/4),width/2-1,width/2-1);
		    
	
		}

	    }
    }
}
		
		
