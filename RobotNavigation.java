import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RobotNavigation implements StateSpace {

	String start;
	String goal;
	
	public RobotNavigation(String start, String goal) {
		this.start = start;
		this.goal = goal;
	}

	public Vector<String> getKids(String current) {
		Vector<String> kids = new Vector<String>();
		String s1 = "r";
		String s2 = "-";
		
		for(int i=1;i< current.indexOf("/")+1;i++){//nextline
		    s1+=".";//anything
		    s2+=".";
		}
		s1+="-";
		s2+="r";

		//System.out.println(s1+"/n"+s2);
		
		doOne(kids,s1,current);
		doOne(kids,s2,current);
		doOne(kids,"r-",current);
		doOne(kids,"-r",current);
		//System.out.println(kids);
		return kids;
	}

private void doOne(Vector<String> kids, String string, String current) {
		
		Pattern p = Pattern.compile(string);
		Matcher matcher = p.matcher(current);
		StringBuffer buf = new StringBuffer();
		if (matcher.find()) {
			String rep = matcher.group();
			if (rep.length() == 2) 
			    rep = ""+rep.charAt(1)+rep.charAt(0);
			else if (rep.length() == current.indexOf("/")+2){
			    String trep = ""+rep.charAt(current.indexOf("/")+1);
			    for(int i = 1;i< current.indexOf("/")+1;i++) trep+=rep.charAt(i);
			    trep+=rep.charAt(0);
			    rep=trep;
			}
			matcher.appendReplacement(buf, rep);
		
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
