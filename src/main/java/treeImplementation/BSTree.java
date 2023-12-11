/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package treeImplementation;

import java.util.NoSuchElementException;
import java.util.Stack;
import utilities.BSTreeADT;
import utilities.Iterator;

/**
 * Implementation of the BSTreeADT interface.
 *
 * @param <E> the type of elements stored in the binary search tree
 */
public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {

    private BSTreeNode<E> root;
    
    public BSTree() {
        this.root = null;
    }

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
        } else {
            int leftHeight = calculateHeight(node.getLeft());
            int rightHeight = calculateHeight(node.getRight());
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    @Override
    public int size() {
        return countNodes(root);
    }

    private int countNodes(BSTreeNode<E> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
        }
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
    public boolean contains(E entry) throws NullPointerException {
        if (entry == null) {
            throw new NullPointerException("Cannot search for null in the tree");
        }
        return containsNode(root, entry);
    }

    private boolean containsNode(BSTreeNode<E> node, E entry) {
        if (node == null) {
            return false;
        } else {
            int comparison = entry.compareTo(node.getData());
            if (comparison < 0) {
                return containsNode(node.getLeft(), entry);
            } else if (comparison > 0) {
                return containsNode(node.getRight(), entry);
            } else {
                return true; // Found
            }
        }
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
    public boolean add(E newEntry) throws NullPointerException {
        if (newEntry == null) {
            // Cannot add null to the tree
            return false;
        }

        return addNode(root, newEntry);
    }

    private boolean addNode(BSTreeNode<E> node, E newEntry) {
        if (node == null) {
            // Create a new node and add it to the tree
            root = new BSTreeNode<>(newEntry);
            return true; // Addition successful
        }

        int compareResult = newEntry.compareTo(node.getData());

        if (compareResult < 0) {
            // Add to the left subtree
            if (node.getLeft() == null) {
                node.setLeft(new BSTreeNode<>(newEntry));
                return true; // Addition successful
            } else {
                return addNode(node.getLeft(), newEntry);
            }
        } else if (compareResult > 0) {
            // Add to the right subtree
            if (node.getRight() == null) {
                node.setRight(new BSTreeNode<>(newEntry));
                return true; // Addition successful
            } else {
                return addNode(node.getRight(), newEntry);
            }
        } else {
            // Duplicate entry, not added
            return false;
        }
    }

    @Override
    public BSTreeNode<E> removeMin() {
        // Check if the tree is empty
        if (root == null) {
            return null; // Return null if the tree is empty
        }

        // Find the minimum node (leftmost node) in the tree
        BSTreeNode<E> minNode = findMin(root);

        // Remove the minimum node and update the root
        root = removeMinNode(root);

        // Return the removed minimum node
        return minNode;
    }

    // Helper method to find the minimum node in a subtree
    private BSTreeNode<E> findMin(BSTreeNode<E> node) {
        // Keep traversing to the left until the left child is null
        while (node.getLeft() != null) {
            node = node.getLeft();
        }

        // Return the leftmost node (minimum node)
        return node;
    }

    // Helper method to remove the minimum node from a subtree
    private BSTreeNode<E> removeMinNode(BSTreeNode<E> node) {
        // If the left child is null, return the right child
        if (node.getLeft() == null) {
            return node.getRight();
        }

        // Recursively remove the minimum node from the left subtree
        node.setLeft(removeMinNode(node.getLeft()));

        // Return the updated node
        return node;
    }

    @Override
    public BSTreeNode<E> removeMax() {
        // Check if the tree is empty
        if (root == null) {
            return null; // Return null if the tree is empty
        }

        // Find the maximum node (rightmost node) in the tree
        BSTreeNode<E> maxNode = findMax(root);

        // Remove the maximum node and update the root
        root = removeMaxNode(root);

        // Return the removed maximum node
        return maxNode;    // Helper method to find the maximum node in a subtree

    }

    private BSTreeNode<E> findMax(BSTreeNode<E> node) {
        // Keep traversing to the right until the right child is null
        while (node.getRight() != null) {
            node = node.getRight();
        }

        // Return the rightmost node (maximum node)
        return node;
    }

    // Helper method to remove the maximum node from a subtree
    private BSTreeNode<E> removeMaxNode(BSTreeNode<E> node) {
        // If the right child is null, return the left child
        if (node.getRight() == null) {
            return node.getLeft();
        }

        // Recursively remove the maximum node from the right subtree
        node.setRight(removeMaxNode(node.getRight()));

        // Return the updated node
        return node;
    }

    @Override
    public Iterator<E> inorderIterator() {
        return new InorderIterator<>(root);
    }

    // Inorder iterator class
    private static class InorderIterator<E extends Comparable<? super E>> implements Iterator<E> {
        private BSTreeNode<E> current;
        private Stack<BSTreeNode<E>> stack;

        public InorderIterator(BSTreeNode<E> root) {
            current = root;
            stack = new Stack<>();
        }

        @Override
        public boolean hasNext() {
            return current != null || !stack.isEmpty();
        }

        @Override
        public E next() {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }

            BSTreeNode<E> node = stack.pop();
            current = node.getRight();

            return node.getData();
        }
    }

    @Override
    public Iterator<E> preorderIterator() {
        return new PreorderIterator<>(root);
    }

    // Preorder iterator class
    private static class PreorderIterator<E extends Comparable<? super E>> implements Iterator<E> {
        private Stack<BSTreeNode<E>> stack;

        public PreorderIterator(BSTreeNode<E> root) {
            stack = new Stack<>();
            if (root != null) {
                stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the iteration");
            }

            BSTreeNode<E> node = stack.pop();

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }

            return node.getData();
        }
    }


    @Override
    public Iterator<E> postorderIterator() {
        return new PostorderIterator<>(root);
    }

    // Postorder iterator class
    private static class PostorderIterator<E extends Comparable<? super E>> implements Iterator<E> {
        private Stack<BSTreeNode<E>> stack1;
        private Stack<BSTreeNode<E>> stack2;

        public PostorderIterator(BSTreeNode<E> root) {
            stack1 = new Stack<>();
            stack2 = new Stack<>();

            if (root != null) {
                stack1.push(root);
                while (!stack1.isEmpty()) {
                    BSTreeNode<E> node = stack1.pop();
                    stack2.push(node);

                    if (node.getLeft() != null) {
                        stack1.push(node.getLeft());
                    }
                    if (node.getRight() != null) {
                        stack1.push(node.getRight());
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !stack2.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the iteration");
            }

            return stack2.pop().getData();
        }
    }
}
