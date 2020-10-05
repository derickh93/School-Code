/**
 * Title: csc130.hansrajd.lab9
 * Filename: BinarySearchTree.java
 * Date Written: December 16, 2017 
 * Due Date: December 16, 2017
 * Description: Create methods to utilize and test a Binary Tree using preorder,
 * postorder, inorder, and level order. There are also methods to get the height of the tree,
 * insert a node, buildheap, and heapify. 
 * @author Derick Hansraj
 **/

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> {
	/**
	 * Title: csc130.hansrajd.lab9
	 * Filename: BinarySearchTree.java
	 * Date Written: December 16, 2017 
	 * Due Date: December 16, 2017
	 * Description: Creates a BinaryNode object for use with the BinarySearchTree class. 
	 * @author Derick Hansraj
	 **/
	class BinaryNode<E extends Comparable<E>> {
		//instance variables
		protected E data;
		protected BinaryNode<E> left, right;

		/**
		 * Parameterized Constructor -- Creates a Binary Node with the desired parameters
		 * @param d - data that will be stored in the node
		 */
		BinaryNode(E d) {
			data = d;
			left = right = null;
		}
		
		/**
		 * toString method -- returns the Binary Node as a string
		 * @return a string representing the Binary Node
		 */
		public String toString() {
			return "" + data;
		}
	}

	//instance variable
	private BinaryNode<T> root;

	/**
	 * insert method -- Inserts a node into the Binary Tree
	 * @param - a node that will be inserted into the list
	 */
	public void insert(T d) {
		root = insert(d, root);
	}

	/**
	 * preOrder method -- counts the amount of nodes in the list

	 */
	public void preOrder() {
		System.out.println("\nPreorder:");
		preOrder(root);
	}

	/**
	 * inOrder method -- counts the amount of nodes in the list

	 */
	public void inOrder() {
		System.out.println("\nInorder:");
		inOrder(root);
	}

	/**
	 * postOrder method -- counts the amount of nodes in the list

	 */
	public void postOrder() {
		System.out.println("\nPostorder:");
		postOrder(root);
	}

	/**
	 * levelOrderPrint method -- counts the amount of nodes in the list

	 */
	public void levelOrderPrint() {
		System.out.println("\nPostorder:");
		levelOrderPrint(root);
	}

	/**
	 * countNodes method -- counts the amount of nodes in the list
	 * @param - a node that will traverse a list
	 * @return an int representing the amount of nodes in the list
	 */
	private BinaryNode<T> insert(T d, BinaryNode<T> root) {
		if (root == null)
			root = new BinaryNode<T>(d);
		else if (root.data.compareTo(d) > 0)
			root.left = insert(d, root.left);
		else
			root.right = insert(d, root.right);
		return root;
	}

	/**
	 * countNodes method -- counts the amount of nodes in the list
	 * @param - a node that will traverse a list
	 * @return an int representing the amount of nodes in the list
	 */
	private void preOrder(BinaryNode<T> root) {
		if (root != null) {
			System.out.print(root.data + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	/**
	 * countNodes method -- counts the amount of nodes in the list
	 * @param - a node that will traverse a list
	 * @return an int representing the amount of nodes in the list
	 */
	private void inOrder(BinaryNode<T> root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.data + " ");
			inOrder(root.right);
		}
	}

	/**
	 * countNodes method -- counts the amount of nodes in the list
	 * @param - a node that will traverse a list
	 * @return an int representing the amount of nodes in the list
	 */
	private void postOrder(BinaryNode<T> root) {
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.data + " ");
		}
	}

	/**
	 * countNodes method -- counts the amount of nodes in the list
	 * @param - a node that will traverse a list
	 * @return an int representing the amount of nodes in the list
	 */
	private void levelOrderPrint(BinaryNode<T> root) {
		Queue<BinaryNode<T>> queue = new LinkedList<BinaryNode<T>>();
		LinkedList<BinaryNode<T>> list = new LinkedList<BinaryNode<T>>();
		queue.add(root);
		while (!queue.isEmpty()) {
			BinaryNode<T> item = queue.remove();
			if (item != null) {
				list.add(item);
				if (item.left != null)
					queue.add(item.left);
				if (item.right != null)
					queue.add(item.right);
			}
		}
		System.out.println(list);
	}

	/**
	 * countNodes method -- counts the amount of nodes in the list
	 * @param - a node that will traverse a list
	 * @return an int representing the amount of nodes in the list
	 */
	public static void buildHeap(int[] a,int size) {
		// start from last parent to first parent
		for(int i = size / 2 - 1; i >= 0; i--)
			heapify(a,i,size);
	}

	/**
	 * countNodes method -- counts the amount of nodes in the list
	 * @param - a node that will traverse a list
	 * @return an int representing the amount of nodes in the list
	 */
	public static void heapify(int[] a, int i, int size) {
		int l = 2 * i + 1; // left child
		int r = 2 * i + 2; // right child
		int largest=i; // parent
		// find the larger of parent and left child
		if(l <= size-1 && a[l] > a[i])
			largest = l;
		else
			largest = i;
		// find the larger of parent and right child
		if(r <= size-1 && a[r] > a[largest])
			largest = r;
		// swap parent and larger child if necessary
		if(largest != i) {
			int temp = a[i];
			a[i] = a[largest];
			a[largest] = temp;
			// repeat heapify until all children are in a heap
			heapify(a, largest, size);
		}
	}

	/**
	 * countNodes method -- counts the amount of nodes in the list
	 * @param - a node that will traverse a list
	 * @return an int representing the amount of nodes in the list
	 */
	private int height(BinaryNode<T> node) {
		if(node == null)
			return -1;
		return(Math.max(height(node.left), height(node.right)) + 1);
	}

}