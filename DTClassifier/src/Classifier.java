import java.util.ArrayList;
import java.util.HashMap;

public class Classifier {
	Dataset training;
	Dataset test;

	public Classifier(String trainingString, String testString) {
		// Parse the dataset
		training = Parser.scan(trainingString);
		test = Parser.scan(testString);
		// For each attribute:
		// Create list of instances where attrib is true
		// Create list of instances where attrib is false
		// Calculate purity
		DTreeNode classifierRoot = buildTree(training.getInstances(), training.getAttribs());
		classifierRoot.report("");
		classify(classifierRoot);
	}

	public void classify(DTreeNode root) {
		// for each instance in the test set
		// if currentnode is leaf report class, check against class outcome
		// else get attribute value and traverse tree accordingly
		// keep track of total class count, correctly classified count
		HashMap<String, Integer> classCount = new HashMap<String, Integer>();
		HashMap<String, Integer> correctCount = new HashMap<String, Integer>();
		for (Instance i : test.getInstances()) {
			DTreeNode currentNode = root;
			String correctOutcomeString = i.getOutcomeString();
			if (!classCount.containsKey(correctOutcomeString)){
				classCount.put(correctOutcomeString, 1);
			} else {
				int thisClassCount =  classCount.get(correctOutcomeString);
				thisClassCount++;
				classCount.put(correctOutcomeString, thisClassCount);
			}
			// find which node to go down
			while (!currentNode.isLeaf()) {
				if (i.attribIsTrue(currentNode.getAttrib())) {
					currentNode = currentNode.getLeft();
				} else {
					currentNode = currentNode.getRight();
				}
			}
			if (currentNode.getClassName().equals(correctOutcomeString)){
				if (!correctCount.containsKey(correctOutcomeString)){
					correctCount.put(correctOutcomeString, 1);
				} else {
					int thisCount =  correctCount.get(correctOutcomeString);
					thisCount++;
					correctCount.put(correctOutcomeString, thisCount);
				}
			}
		}
		System.out.println();
		System.out.printf("%s: %d/%d correct\n", test.getOutcomes()[0], correctCount.get(test.getOutcomes()[0]), classCount.get(test.getOutcomes()[0]));
		System.out.printf("%s: %d/%d correct\n", test.getOutcomes()[1], correctCount.get(test.getOutcomes()[1]), classCount.get(test.getOutcomes()[1]));
		int baselineCount = Integer.MIN_VALUE;

		int treeCorrect = 0;
		for (String attrib : correctCount.keySet()){
			treeCorrect += correctCount.get(attrib);
		}
		double treeAccuracy = (double) treeCorrect/(double) test.getInstances().size() * 100;

		System.out.printf("Tree accuracy: %4.2f%%\n", treeAccuracy);


		String baseline = "";
		for (String attrib : classCount.keySet()){
			if (classCount.get(attrib) > baselineCount) {
				baseline = attrib;
				baselineCount = classCount.get(attrib);
			}
		}
		double baselineAccuracy = (double)correctCount.get(baseline)/(double)classCount.get(baseline) * 100;
		System.out.printf("Baseline (%s) accuracy: %4.2f%%\n", baseline,baselineAccuracy);


	}

	public DTreeNode buildTree(ArrayList<Instance> instances, ArrayList<String> attributes) {
		if (instances.isEmpty()) {
			return null;

		}
		boolean purityCheck = instances.get(0).getOutcome();
		boolean isPure = true;
		if (instances.size() > 1) {
			for (int i = 1; i < instances.size(); i++) {
				if (purityCheck != instances.get(i).getOutcome()) {
					isPure = false;
				}
			}

		}
		if (isPure) {
			return new DTreeNode(instances.get(0).getOutcomeString(), 1.0);
		}
		if (attributes.isEmpty()) {
			return null;
		}

		String bestAttrib = "";
		double bestPurity = Double.MAX_VALUE;
		for (String attrib : attributes) {
			double thisPurity = calculatePurity(attrib, instances);
			if (thisPurity < bestPurity) {
				bestPurity = thisPurity;
				bestAttrib = attrib;
			}
		}
		ArrayList<String> childAttributes = new ArrayList<String>();
		for (String attrib : attributes) {
			if (!attrib.equals(bestAttrib)) {
				childAttributes.add(attrib);
			}
		}
		DTreeNode left = buildTree(getAttributeTrueInstances(bestAttrib, instances), childAttributes);
		DTreeNode right = buildTree(getAttributeFalseInstances(bestAttrib, instances), childAttributes);
		return new DTreeNode(bestAttrib, left, right);
	}

	public ArrayList<Instance> getAttributeTrueInstances(String attribute, ArrayList<Instance> instances) {
		ArrayList<Instance> trueInstances = new ArrayList<Instance>();
		for (Instance i : instances) {
			if (i.attribIsTrue(attribute)) {
				trueInstances.add(i);
			}
		}
		return trueInstances;
	}

	public ArrayList<Instance> getAttributeFalseInstances(String attribute, ArrayList<Instance> instances) {
		ArrayList<Instance> falseInstances = new ArrayList<Instance>();
		for (Instance i : instances) {
			if (!i.attribIsTrue(attribute)) {
				falseInstances.add(i);
			}
		}
		return falseInstances;
	}

	public double calculatePurity(String attribute, ArrayList<Instance> instances) {
		ArrayList<Instance> trueInstances = getAttributeTrueInstances(attribute, instances);
		ArrayList<Instance> falseInstances = getAttributeFalseInstances(attribute, instances);
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
		double trueProbability = ((double) trueInstances.size() / (double) instances.size());
		double falseProbability = ((double) falseInstances.size() / (double) instances.size());
		double weightedPurity = (trueNodePurity * trueProbability) + (falseNodePurity * falseProbability);
		return weightedPurity;
	}

	public static void main(String[] args) {
		if (args.length != 2){
			System.out.println("Error: incorrect number of arguments\ndtclassify training test");
		} else {
			new Classifier(args[0], args[1]);
		}
	}
}