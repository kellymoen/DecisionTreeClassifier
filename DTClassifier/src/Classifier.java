import java.util.ArrayList;

public class Classifier {
	Dataset dataset;

	public Classifier() {
		// Parse the dataset
		dataset = Parser.scan("golf-training.dat");
		// For each attribute:
		// Create list of instances where attrib is true
		// Create list of instances where attrib is false
		// Calculate purity
		String[] attribs = dataset.getAttribs();
		ArrayList<Instance> instances = dataset.getInstances();
		for (String attrib: attribs){
			calculatePurity(attrib, instances);
		}
	}

	public ArrayList<Instance> getAttributeTrueInstances(String attribute, ArrayList<Instance> instances){
		ArrayList<Instance> trueInstances = new ArrayList<Instance>();
		for (Instance i : instances) {
			if (i.attribIsTrue(attribute)) {
				trueInstances.add(i);
			}
		}
		return trueInstances;
	}

	public ArrayList<Instance> getAttributeFalseInstances(String attribute, ArrayList<Instance> instances){
		ArrayList<Instance> falseInstances = new ArrayList<Instance>();
		for (Instance i : instances) {
			if (!i.attribIsTrue(attribute)) {
				falseInstances.add(i);
			}
		}
		return falseInstances;
	}



	public double calculatePurity(String attribute, ArrayList<Instance> instances){
		ArrayList<Instance> trueInstances = getAttributeTrueInstances(attribute, instances);
		ArrayList<Instance> falseInstances = getAttributeFalseInstances(attribute, instances);
		System.out.println(attribute);
		System.out.println("TRUE SIZE: " + trueInstances.size());
		System.out.println("FALSE SIZE: " + falseInstances.size());
		int trueCount = 0;
		int falseCount = 0;
		for (Instance i : trueInstances) {
			if (i.getOutcome()) {
				trueCount++;
			}
		}
		for (Instance i : falseInstances) {
			if (i.getOutcome()) {
				falseCount++;
			}
		}
		double trueNodePurity = ((double) trueCount / (double) trueInstances.size())
				* ((double) (trueInstances.size() - trueCount) / (double) trueInstances.size());
		double falseNodePurity = ((double) falseCount / (double) falseInstances.size())
				* ((double) (falseInstances.size() - falseCount) / (double) falseInstances.size());
		double trueProbability = ((double)trueInstances.size()/(double)instances.size());
		double falseProbability = ((double)falseInstances.size()/(double)instances.size());

		System.out.println("TRUE PURITY: " + trueNodePurity);
		System.out.println("FALSE PURITY: " + falseNodePurity);
		System.out.println("TRUE PROBABILITY: " + trueProbability);
		System.out.println("FALSE PROBABILITY: " + falseProbability);
		double weightedPurity = (trueNodePurity * trueProbability) + (falseNodePurity * falseProbability);
		System.out.println("WEIGHTED PURITY: " + weightedPurity);
		return weightedPurity;
	}

	public static void main(String[] args) {
		new Classifier();
	}
}