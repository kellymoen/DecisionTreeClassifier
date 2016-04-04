import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Instance {
	private HashMap<String, Boolean> attributes;
	String[] outcomes;
	boolean outcome;

	public Instance(String nextLine, String[] outcomes, String[] attribs) {
		this.outcomes = outcomes;
		attributes = new HashMap<String, Boolean>();
		String[] data = nextLine.split("\t");

		if (data[0].equals(outcomes[0])) { // First element of line against
											// defined outcome names.
			outcome = true;
		} else {
			outcome = false;
		}

		for (int i = 1; i < data.length; i++) {
			if (data[i].equals("true")) {
				attributes.put(attribs[i - 1], true);
			} else {
				attributes.put(attribs[i - 1], false);
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

}
