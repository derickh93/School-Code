
/**
 * Title: csc130.hansrajd.lab9
 * Filename: Lab9App.java
 * Date Written: December 16, 2017 
 * Due Date: December 16, 2017
 * Description: Test the methods from the BinarySearchTree class. 
 * @author Derick Hansraj
 **/
public class Lab9App {
	public static void main (String[] args ) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.insert(12);
		tree.insert(6);
		tree.insert(2);
		tree.insert(15);
		tree.insert(1);
		tree.insert(4);
		tree.inOrder();
		tree.postOrder();
		tree.preOrder();
		tree.levelOrderPrint();
	}

}
