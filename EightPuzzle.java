import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EightPuzzle implements StateSpace {

	String start;
	String goal;
	
	public EightPuzzle(String start, String goal) {
		this.start = start;
		this.goal = goal;
	}

	/*public static void main (String[] args){
		
		String start = "216/4x8/753";
		String goal = "123/8x4/765";
		EightPuzzle EP = new EightPuzzle(start, goal);
		Vector<String> vec = new Vector<String>();
		EP.doOne(vec , "x[1-8]","216/4x8/753");
	}*/
	
	public Vector<String> getKids(String current) {
		Vector<String> kids = new Vector<String>();
		
		doOne(kids,"x[1-8]",current);
		doOne(kids,"[1-8]x",current);
		doOne(kids,"x...[1-8]",current);
		doOne(kids,"[1-8]...x",current);
		return kids;
	}

private void doOne(Vector<String> kids, String string, String current) {
		
		Pattern p = Pattern.compile(string);
		Matcher matcher = p.matcher(current);
		StringBuffer buf = new StringBuffer();
		if (matcher.find()) {
			String rep = matcher.group();
//			System.out.println(rep);
			if (rep.length() == 2) 
				rep = ""+rep.charAt(1)+rep.charAt(0);
			else if (rep.length() == 5)
				rep = ""+rep.charAt(4)+rep.charAt(1)+rep.charAt(2)+rep.charAt(3)+rep.charAt(0);
			matcher.appendReplacement(buf, rep);
	//		System.out.println(rep);
			matcher.appendTail(buf);
		
			kids.add(buf.toString());
		}
		
	}

public String getGoal() {
	return goal;
}

public String getStart() {
	return start;
}

public boolean isGoal(String rep) {
	return rep.equals(getGoal());
}
}
