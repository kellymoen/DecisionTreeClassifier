
public class DTreeNode {
	boolean isLeaf = false;
	String attrib;
	String className;
	double probability;
	DTreeNode left;
	DTreeNode right;

	public DTreeNode(String attrib, DTreeNode left, DTreeNode right) {
		this.attrib = attrib;
		this.left = left;
		this.right = right;
	}

	public DTreeNode(String className, double probability){
		this.isLeaf = true;
		this.className = className;
		this.probability = probability;
	}

	public void report(String indent) {
		if (!isLeaf) {
			System.out.format("%s%s = True:\n", indent, attrib);
			if (left != null) left.report(indent + " ");
			System.out.format("%s%s = False:\n", indent, attrib);
			if (right != null) right.report(indent + " ");
		} else {
				System.out.format("%sClass %s, prob=%4.2f\n",
				indent, className, probability);
				}
	}

	public boolean isLeaf(){
		return isLeaf;
	}

	public String getAttrib(){
		return attrib;
	}

	public DTreeNode getLeft() {
		return left;
	}

	public DTreeNode getRight() {
		return right;
	}

	public String getClassName() {
		return className;
	}



}
