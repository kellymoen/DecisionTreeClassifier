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
			Scanner sc = new Scanner(new File(System.getProperty("user.dir") + "/part2/" + fileName));
			String outcomesLine = sc.nextLine();
			String attribsLine = sc.nextLine();
			String[] outcomes = outcomesLine.split("\t");
			String[] attribs = attribsLine.split("\t");

			while (sc.hasNext()) {
				instances.add(new Instance(sc.nextLine(), outcomes, attribs));
			}
			sc.close();
			for (Instance i : instances) {
				i.print();
				System.out.println("");
			}
			return new Dataset(attribs, outcomes, instances);

		} catch (FileNotFoundException e) {
		}
		return null;
	}

}
