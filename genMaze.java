public class genMaze{

    //TODO make sure solvable
    //TODO call from search base
    //TODO add start goal robot positions

	//not used in Runner
    public static void main(String[] args){
	String goal="";
	String start="";
	int length = 11;
	for (int i =0; i<length; i++){
	    for (int j =0;j<length; j++){
		if(Math.random()>.2){//density of obstacles
		    goal=goal+"-";
		    start=start+"-";
		}
		else {
		    goal=goal+"X";
		    start=start+"X";
		}
	    }
	    goal=goal+"/";
	    start=start+"/";
	}
	System.out.println(start);
    }
}
	    
