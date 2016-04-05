import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Instance {
	private HashMap<String, Boolean> attributes;
	String[] outcomes;
	boolean outcome;

	public Instance(String nextLine, String[] outcomes, ArrayList<String> attribs) {
		this.outcomes = outcomes;
		attributes = new HashMap<String, Boolean>();
		ArrayList<String> data = new ArrayList<String>();
		Scanner sc = new Scanner(nextLine);
		while (sc.hasNext()){
			data.add(sc.next());
		}

		if (data.get(0).equals(outcomes[0])) { // First element of line against
											// defined outcome names.
			outcome = true;
		} else {
			outcome = false;
		}

		for (int i = 1; i < data.size(); i++) {
			if (data.get(i).equals("true")) {
				attributes.put(attribs.get(i - 1), true);
			} else {
				attributes.put(attribs.get(i-1) , false);
			}
		}
	}

	public void print(){
		if (outcome){
			System.out.println(outcomes[0]);
		} else {
			System.out.println(outcomes[1]);
		}
	}

	public boolean attribIsTrue(String attrib){
		return attributes.get(attrib);
	}

	public boolean getOutcome(){
		return outcome;
	}

}
