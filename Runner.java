import java.io.*;

public class Runner{

   

    public static void main (String [] args) {
	
	if (args.length != 3){
	    System.out.println("Usage: depthBound searchType boardSize");	
	    System.exit(0);
	}	

	Runner r = new Runner();
	r.go(args[0],args[1],args[2]);
}
    public void go(String one, String two, String three){

	//FileOutputStream p = new FileOutputStream("output.txt");
	//DataOutputStream pout = new DataOutputStream(p);
	//File f= new File ("output.txt");
	

	boolean success = true;
	int depth_limit = Integer.parseInt(one);
	String which = two;
	System.out.println("Depth Limit "+depth_limit);
	int counter = Integer.parseInt(three); //size of maze, and loop count
	SearchBase searchBase = new SearchBase();
	searchBase.setWhich(which);
	this.genMaze(counter, searchBase);
	Lab8Frame frame = new Lab8Frame(counter,searchBase,this);	

	//see below for multiple runs

	//one run
	searchBase.process(depth_limit, frame);
       
    }



    public void genMaze(int length, SearchBase s){
	String goal="";
	String start="";
	for (int i =0; i<length; i++){
	    for (int j =0;j<length; j++){
		if(i==0&&j==0){//start position
		    start=start+"r";
		    goal=goal+"-";
		}
		else if(i==length-1&&j==length-1){//goal position
		    start=start+"-";
		    goal=goal+"r";
		}
		else if((i==length-1 && j==length-2)
			||(i==length-2 && j==length-1)
			||(i==1 && j==0)
			||(i==0 && j==1)){
		    goal=goal+"-";
		    start=start+"-";
		}
		else if(Math.random()>.25){//density of obstacles
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
	
	s.setStart(start);
	s.setGoal(goal);
	//System.out.println(start);
    }


}





	//astrix
	/*while(true){//set to true for iteritive trials
	    counter+=1;
	    depth_limit= depth_limit*2;
	    genMaze(counter, searchBase);//genmaze will set start and goalRobot
	    success = searchBase.process(depth_limit);
	    
	    if(!success){
		for(int i=0;i<4;i++){
		    genMaze(counter, searchBase);//if its a unsolvable maze
		    depth_limit= depth_limit*2;//give the depthlimit a chance to catchup
		    success = searchBase.process(depth_limit);
		    if (success) break;
		    System.out.println("I give up, too big of a maze :(");
		    System.exit(0);
		}
	    }
	}
	*/
