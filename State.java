import java.util.Vector;


public class State implements Comparable<State> {

    private String rep;
    private Vector<String> path;
    private int depth;
    private String goal;
    private String which;
    private int size;
	
    public State(String r, String goal, String which) {
	rep = r;
	path = new Vector<String>();
	path.add(r);
	depth = 0;
	this.goal = goal;
	this.which = which;
	this.size = r.indexOf("/"); //first occurence of line-break character = width of board
		
    }
    public State(State s, String r, String goal, String which) {
	rep = r;
	path = new Vector<String>(s.path);
	path.add(r);
	depth = s.depth+1;
	this.goal = goal;
	this.which = which;
	this.size = r.indexOf("/"); //first occurence of line-break character
    }
    public int printPath() {
	int count = 1;
	for (String step : path)
	    {
		System.out.println(count+"\n"+convert(step));
		count++;
	    }
	return 0;
    }
    
    public int fValue(State other){	  //for ida* to find the next f value limit to use. this uses a heuristic that only works for the 8-puzzle!
	int depth = ((State)other).getDepth();
	int heuristic = 0;
	//easy heuristic: count the number of tiles that are  out of place
	for (int i = 0; i < goal.length(); i++){
	    if (rep.charAt(i) != goal.charAt(i))
		heuristic++;
	    
	}
	
	return depth + heuristic-1;//-1 to not count blank space
	//admissable because it takes at least one move to put a tile in its correct position
    }

    public int maximization(State other){   //since both of the heuristics we use are admissable, this will also be admissable
	return Math.max(fValue2(other), fValue3(other));
    }
    
    public int convexCombination(State other){
	double a = 0.9;
	double b = 0.1;
	return (int) (a*fValue3(other) +  b*fValue2(other));
	
    }

    public int fValue2(State other){				//used by idaStarRobot1
	//first heuristic: horizontal distance to goal
	int horizontalHeur = Math.abs(goal.indexOf('r')%size - other.getRep().indexOf('r')%size);
	return horizontalHeur + ((State) other).getDepth();
	//admissable because it takes at least one vertical move to reach goal
    }
    
    public int fValue3(State other){				//used by idaStarRobot2
	//second heuristic: straight line distance to goal
	int vertDist = Math.abs((goal.indexOf('r')/size)+1 - (other.getRep().indexOf('r')/size)+1);
	int horizontalDist = Math.abs(goal.indexOf('r')%size - other.getRep().indexOf('r')%size);
	int Heur = ((int) Math.sqrt(vertDist*vertDist + horizontalDist*horizontalDist));		//straight line distance to goal
	
	return Heur + ((State) other).getDepth();
	//admissable because the hypotenuse of a right triangle is <= the sum of the sides
    }
    
    public int compareTo(State other) {
	
	if (!(other instanceof State)) {
	    System.out.println("State , compareTo ");
	    System.exit(3);
	    
	}
	if(which.equals("bfs")) return depth - ((State)other).getDepth();
	if(which.equals("bfsRobot")) return depth - ((State)other).getDepth();
	
	else if(which.equals("aStar")) return this.fValue(this) - other.fValue(other);
	else if(which.equals("aStarRobot1")) return this.fValue2(this) - other.fValue2(other);
	else if(which.equals("aStarRobot2")) return this.fValue3(this) - other.fValue3(other);
	else if(which.equals("aStarRobot3")) return this.convexCombination(this) - other.convexCombination(other);
	else if(which.equals("aStarRobot4")) return this.maximization(this) - other.maximization(other);
	
	System.out.println("State, compareTo error2");
	System.exit(0);	
	//for AStar, use heuristic + depth to order the list
	return -4981209; //dont get here 
	//change this method call to select the heuristic to use
	
    }
    
    private String convert(String step) {
	String[] pieces = step.split("/");
	String answer = "";
	for (String p : pieces)
	    answer = answer +p +"\n";
	return answer;
    }
    
    public int getDepth() {
	return depth;
    }
    public void setDepth(int depth) {
	this.depth = depth;
    }
    public Vector<String> getPath() {
	return path;
    }
    public void setPath(Vector<String> path) {
	this.path = path;
    }
    public String getRep() {
	return rep;
    }
    public void setRep(String rep) {
	this.rep = rep;
    }

    public String toString(){
	return rep;
    }
}
