import treeImplementation.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BSTreeTest {

    @Test
    public void testGetRoot() {
        BSTree<Integer> bst = new BSTree<>();

        // Initially, the tree is empty, so the root should be null
        assertNull(bst.getRoot());

        // Adding some elements to the tree
        bst.add(5);
        bst.add(3);
        bst.add(7);

        // After adding elements, the root should not be null
        assertNotNull(bst.getRoot());

        // Check if the root contains the correct value
        assertEquals(5, bst.getRoot().getData());
    }

    @Test
    public void testGetHeight() {
        BSTree<Integer> bst = new BSTree<>();

        // Initially, the tree is empty, so the height should be 0
        assertEquals(0, bst.getHeight());

        // Adding elements to the tree to create a balanced tree
        bst.add(5);
        bst.add(3);
        bst.add(7);

        // After adding elements, the height should be 2 (assuming a balanced tree)
        assertEquals(2, bst.getHeight());

        // Adding more elements to create an unbalanced tree
        bst.add(2);
        bst.add(4);

        // After adding more elements, the height should be 3 (due to the unbalanced
        // tree)
        assertEquals(3, bst.getHeight());
    }

    @Test
    public void testSize() {
        BSTree<Integer> bst = new BSTree<>();

        // For an empty tree, the size should be 0
        assertEquals(0, bst.size());

        // Add elements to the tree
        bst.add(5);
        bst.add(3);
        bst.add(7);

        // The size of this tree should be 3
        assertEquals(3, bst.size());

        // Add more elements
        bst.add(2);
        bst.add(4);

        // The size of this tree should be 5
        assertEquals(5, bst.size());
    }

    @Test
    public void testIsEmpty() {
        BSTree<Integer> bst = new BSTree<>();

        // The tree should be empty
        assertTrue(bst.isEmpty());

        // Add an element to the tree
        bst.add(5);

        // After adding an element, the tree should not be empty
        assertFalse(bst.isEmpty());

        // Add more elements
        bst.add(3);
        bst.add(7);

        // After adding more elements, the tree should still not be empty
        assertFalse(bst.isEmpty());
    }

    @Test
    public void testClear() {
        BSTree<Integer> bst = new BSTree<>();

        // Add elements to the tree
        bst.add(5);
        bst.add(3);
        bst.add(7);

        // Verify that the tree is not empty before clear
        assertFalse(bst.isEmpty());

        // Clear the tree
        bst.clear();

        // After clearing, the tree should be empty
        assertTrue(bst.isEmpty());
    }

    @Test
    public void testContains() {
        BSTree<Integer> bst = new BSTree<>();

        // Add elements to the tree
        bst.add(5);
        bst.add(3);
        bst.add(7);

        // Verify that the tree contains the added elements
        assertTrue(bst.contains(5));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(7));

        // Verify that the tree does not contain an element that was not added
        assertFalse(bst.contains(10));
    }

    @Test
    public void testAdd() {
        BSTree<Integer> bst = new BSTree<>();

        // Add elements to the tree
        assertTrue(bst.add(5));
        assertTrue(bst.add(3));
        assertTrue(bst.add(7));

        // Verify that the tree contains the added elements
        assertTrue(bst.contains(5));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(7));

        // Try adding a null element, it should throw NullPointerException
        assertThrows(NullPointerException.class, () -> bst.add(null));
    }

    @Test
    public void testSearch() {
        BSTree<Integer> bst = new BSTree<>();

        // Add elements to the tree
        bst.add(5);
        bst.add(3);
        bst.add(7);

        // Verify that the search method finds the added elements
        assertEquals(5, bst.search(5).getData());
        assertEquals(3, bst.search(3).getData());
        assertEquals(7, bst.search(7).getData());

        // Try searching for a non-existing element, it should return null
        assertNull(bst.search(10));

        // Try searching for null, it should throw NullPointerException
        assertThrows(NullPointerException.class, () -> bst.search(null));
    }

    @Test
    public void testRemoveMin() {
        BSTree<Integer> bst = new BSTree<>();

        // Try removing from an empty tree, it should return null
        assertNull(bst.removeMin());

        // Add elements to the tree
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(2);
        bst.add(4);

        // Remove the smallest element (2) and verify it
        BSTreeNode<Integer> removedMin = bst.removeMin();
        assertNotNull(removedMin);
        assertEquals(2, removedMin.getData());

        // Verify that the smallest element is no longer in the tree
        assertNull(bst.search(2));

        // Verify that the tree is still valid after removal
        assertEquals(5, bst.getRoot().getData()); // The new root after removal
    }

    @Test
    public void testRemoveMax() {
        BSTree<Integer> bst = new BSTree<>();

        // Try removing from an empty tree, it should return null
        assertNull(bst.removeMax());

        // Add elements to the tree
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(2);
        bst.add(4);

        // Remove the largest element (7) and verify it
        BSTreeNode<Integer> removedMax = bst.removeMax();
        assertNotNull(removedMax);
        assertEquals(7, removedMax.getData());

        // Verify that the largest element is no longer in the tree
        assertNull(bst.search(7));

        // Verify that the tree is still valid after removal
        assertEquals(5, bst.getRoot().getData()); // The new root after removal

    }
}