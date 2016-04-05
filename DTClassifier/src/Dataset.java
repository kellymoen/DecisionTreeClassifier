import java.util.ArrayList;

public class Dataset {
	ArrayList<String> attribs;
	public ArrayList<String> getAttribs() {
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
	
	public Dataset(ArrayList<String> attribs, String[] outcomes, ArrayList<Instance> instances){
		this.attribs = attribs;
		this.outcomes = outcomes;
		this.instances = instances;
	}
	

}
