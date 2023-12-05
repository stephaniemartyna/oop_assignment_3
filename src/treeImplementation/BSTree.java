package treeImplementation;
import utilities.BSTreeADT;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {

    private BSTreeNode<E> root;

    // Getters and Setters
    @Override
    public BSTreeNode<E> getRoot() {
        return root;
    }

    @Override
    public int getHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(BSTreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = calculateHeight(node.getLeft());
        int rightHeight = calculateHeight(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    @Override
    public int size() {
        return countNodes(root);
    }

    private int countNodes(BSTreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean contains(E entry) {
        return search(entry) != null;
    }

    @Override
    public boolean add(E newEntry) throws NullPointerException {
        if (newEntry == null) {
            throw new NullPointerException("Cannot add null to the tree");
        }

        root = addNode(root, newEntry);
        return true; // Assuming the add operation always succeeds
    }

    private BSTreeNode<E> addNode(BSTreeNode<E> node, E newEntry) {
        if (node == null) {
            return new BSTreeNode<>(newEntry);
        }

        int compareResult = newEntry.compareTo(node.getData());

        if (compareResult < 0) {
            node.setLeft(addNode(node.getLeft(), newEntry));
        } else if (compareResult > 0) {
            node.setRight(addNode(node.getRight(), newEntry));
        }

        return node;
    }

    @Override
    public BSTreeNode<E> search(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException("Cannot search for null in the tree");
        }

        return searchNode(root, entry);
    }

    private BSTreeNode<E> searchNode(BSTreeNode<E> node, E entry) {
        if (node == null) {
            return null;
        }

        int compareResult = entry.compareTo(node.getData());

        if (compareResult == 0) {
            return node;
        } else if (compareResult < 0) {
            return searchNode(node.getLeft(), entry);
        } else {
            return searchNode(node.getRight(), entry);
        }
    }

    @Override
    public BSTreeNode<E> removeMin() {
        if (root == null) {
            return null; // Tree is empty
        }

        BSTreeNode<E> minParent = null;
        BSTreeNode<E> minNode = root;

        while (minNode.getLeft() != null) {
            minParent = minNode;
            minNode = minNode.getLeft();
        }

        if (minParent == null) {
            // The minimum node is the root
            root = root.getRight();
        } else {
            // Remove the leftmost node
            minParent.setLeft(minNode.getRight());
        }

        return minNode;
    }

    @Override
    public BSTreeNode<E> removeMax() {
        if (root == null) {
            return null; // Tree is empty
        }

        BSTreeNode<E> maxParent = null;
        BSTreeNode<E> maxNode = root;

        while (maxNode.getRight() != null) {
            maxParent = maxNode;
            maxNode = maxNode.getRight();
        }

        if (maxParent == null) {
            // The maximum node is the root
            root = root.getLeft();
        } else {
            // Remove the rightmost node
            maxParent.setRight(maxNode.getLeft());
        }

        return maxNode;
    }

    @Override
    public utilities.Iterator<E> inorderIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inorderIterator'");
    }

    @Override
    public utilities.Iterator<E> preorderIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'preorderIterator'");
    }

    @Override
    public utilities.Iterator<E> postorderIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postorderIterator'");
    }

}
