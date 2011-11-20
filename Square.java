public class Square {

    private int state  = 0; //1=robot, 2=space, 3=wall, 4=goal, 5=visited
    private double weight = 1;
    private int stepNumber = -1;

    public String toString(){
	return state+"";
    }

    public Square(char c){
	if(c=='r')state = 1;
	if(c=='-')state = 2;
	if(c=='X')state = 3;
    }
    
    public Square(){}


    public Square(int state){
	this.state = state;
    }
    
    public Square(int state, int step){
	if(state==5){
	    this.state = state;
	    this.stepNumber = step;
	}
	else{
	    System.out.println("only visited(5) can have step #");
	    System.exit(0);
	}
    }
    
    public int getState(){
	return state;
    }
    public void setState(int i){
	state = i;
    }
    public int getStep(){return stepNumber;}
	
    public void setStep(int i){
	if(state==5){
	    stepNumber = i;
	}
	else{
	    System.out.println("only visited(5) can have step #");
	    System.exit(0);
	}
    }

}
