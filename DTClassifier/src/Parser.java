import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
	// Static method
	// Create list of attributes
	// for each line
	// create map entry for instance
	// outcome
	// make list of instances
	public static Dataset scan(String fileName) {
		ArrayList<Instance> instances = new ArrayList<Instance>();
		try {
			Scanner sc = new Scanner(new File(System.getProperty("user.dir") + "/" + fileName));
			String outcomesLine = sc.nextLine();
			String attribsLine = sc.nextLine();
			String[] outcomes = outcomesLine.split("\t");
			String[] attribSplit = attribsLine.split("\t");
			ArrayList<String> attribs = new ArrayList<String>();
			for (int i =0; i< attribSplit.length; i++){
				attribs.add(attribSplit[i]);
			}

			while (sc.hasNext()) {
				instances.add(new Instance(sc.nextLine(), outcomes, attribs));
			}
			sc.close();
			return new Dataset(attribs, outcomes, instances);

		} catch (FileNotFoundException e) {
		}
		return null;
	}

}
