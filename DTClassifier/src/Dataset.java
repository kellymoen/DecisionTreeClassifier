import java.util.ArrayList;

public class Dataset {
	String[] attribs;
	public String[] getAttribs() {
		return attribs;
	}

	public String[] getOutcomes() {
		return outcomes;
	}

	public ArrayList<Instance> getInstances() {
		return instances;
	}

	String[] outcomes;
	ArrayList<Instance> instances;
	
	public Dataset(String[] attribs, String[] outcomes, ArrayList<Instance> instances){
		this.attribs = attribs;
		this.outcomes = outcomes;
		this.instances = instances;
	}
	

}
