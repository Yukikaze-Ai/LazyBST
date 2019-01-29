public class LazyBinarySearchTree {
	private static class TreeNode {
		int key;
		TreeNode leftChild;
		TreeNode rightChile;
		boolean deleted;

		TreeNode(int key) {
			this.key = key;
		}

	}

	TreeNode root;
	private int leftHeight = -1;
	private int rightHeight = -1;
	private int size = 0;

	public LazyBinarySearchTree() {
		root = null;
	}

	public boolean insert(int key) throws IllegalArgumentException {
		// the argument should between 1 to 99 (inclusive)
		if (key < 1 || key > 99)
			throw new IllegalArgumentException("Invalid data");
		int counter = -1;
		boolean lOr = false;// false means Right, true means Left
		TreeNode parent = null;
		TreeNode current = root;

		// if the root is empty, then initialize it.
		if (root == null) {
			root = new TreeNode(key);
			size++;
			return true;
		} else {
			while (current != null) {
				// find the location that the new node should be
				if (current.key == key) {
					if (current.deleted == true) {
						current.deleted = false;
						return true;
					}
					return false;
				} else if (current.key > key) {
					parent = current;
					current = parent.leftChild;
					counter++;
				} else {
					if (current.hashCode() == root.hashCode())
						lOr = true;
					parent = current;
					current = parent.rightChile;
					counter++;
				}
			}
			// depends on the Key value to create the left or right node.
			if (current == null) {
				if (parent.key > key) {
					parent.leftChild = new TreeNode(key);
					size++;
					if (lOr == true) {
						if (counter > leftHeight)
							leftHeight = counter;
					} else {
						if (counter > rightHeight)
							rightHeight = counter;
					}
					return true;
				} else {
					parent.rightChile = new TreeNode(key);
					size++;
					if (lOr == true) {
						if (counter > leftHeight)
							leftHeight = counter;
					} else {
						if (counter > rightHeight)
							rightHeight = counter;
					}
					return true;
				}

			}
		}
		return false;
	}

	public boolean delete(int key) throws IllegalArgumentException {
		// this is lazy deletion
		// the argument should between 1 to 99 (inclusive)
		if (key < 1 || key > 99)
			throw new IllegalArgumentException("Invalid data");
		TreeNode parent = null;
		TreeNode current = root;
		while (current != null) {
			if (key == current.key) {
				if (current.deleted == true)
					return false;
				current.deleted = true;
				return true;
			} else if (key > current.key) {
				parent = current;
				current = parent.rightChile;
			} else {
				parent = current;
				current = parent.leftChild;
			}

		}
		return false;
	}

	public int findMin() {
		// if this tree is a empty tree, it will return 0
		TreeNode temp = findMin(root);
		if (temp == null)
			return 0;
		else
			return temp.key;
		// if returns 0 means where is no MIN
	}

	private TreeNode findMin(TreeNode current) {
		// this will travel post-order
		if (current == null)
			return null;
		TreeNode temp;
		if ((temp = findMin(current.leftChild)) == null)
			;
		else {
			if (temp.deleted == false)
				return temp;
			else
				return null;
		}
		if (current.deleted == false)
			return current;
		else {
			if ((temp = findMin(current.rightChile)) == null)
				;
			else {
				if (temp.deleted == false)
					return temp;
				else
					return null;
			}
		}
		return null;
	}

	public int findMax() {
		// if this tree is a empty tree, it will return 0

		TreeNode temp = findMax(root);
		if (temp == null)
			return 0;
		else
			return temp.key;
		// if returns 0 means where is no MAX
	}

	private TreeNode findMax(TreeNode current) {
		// this will travel right,current,left
		if (current == null)
			return null;
		TreeNode temp;
		if ((temp = findMax(current.rightChile)) != null) {
			if (temp.deleted == false)
				return temp;
			else
				return null;
		}

		if (current.deleted == false)
			return current;
		else {
			if ((temp = findMax(current.leftChild)) != null) {
				if (temp.deleted == false)
					return temp;
				else
					return null;
			}
		}
		return null;
	}

	public boolean contains(int key) throws IllegalArgumentException {
		// the argument should between 1 to 99 (inclusive)
		if (key < 1 || key > 99)
			throw new IllegalArgumentException("Invalid data");
		TreeNode temp = root;
		while (temp != null) {
			if (key == temp.key) {
				if (temp.deleted == false)
					return true;
				else
					return false;
			} else if (key > temp.key)
				temp = temp.rightChile;
			else if (key < temp.key)
				temp = temp.leftChild;
		}
		return false;
	}

	public String toString() {
		TreeNode temp = root;
		return preTraver(temp);

	}

	private String preTraver(TreeNode parent) {
		// this function will print the tree with pre-order.
		String result = "";
		if (parent == null)
			;
		else {
			if (parent.deleted == true)
				result = result + "*";
			result = result + parent.key + " ";
			result = result + preTraver(parent.leftChild);
			result = result + preTraver(parent.rightChile);
		}
		return result;

	}

	public int height() {
		// return the height of the tree
		if (root == null)
			return -1;
		int rst = Math.max(leftHeight, rightHeight);
		return rst + 1;
	}

	public int size() {
		// return the size of the tree
		return size;
	}
}
