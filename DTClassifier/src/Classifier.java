import java.util.ArrayList;

public class Classifier {
	Dataset dataset;
	public Classifier() {
		//Parse the dataset
		dataset = Parser.scan("golf-training.dat");
		//For each attribute:
		//Create list of instances where attrib is true
		//Create list of instances where attrib is false
		//Calculate purity
		String[] attribs = dataset.getAttribs();
		ArrayList<Instance> instances = dataset.getInstances();
		String attrib = attribs[0];
		ArrayList<Instance> trueInstances = new ArrayList<Instance>();
		ArrayList<Instance> falseInstances = new ArrayList<Instance>();
		for (Instance i : instances){
			if (i.attribIsTrue(attrib)){
				trueInstances.add(i);
			} else {
				falseInstances.add(i);
			}
		}
		System.out.println("TRUE SIZE: " + trueInstances.size());
		System.out.println("FALSE SIZE: " + falseInstances.size());
		//true instances outcome = true/size * outcome = false/size


	}

	public static void main(String[] args) {
		new Classifier();
	}
}