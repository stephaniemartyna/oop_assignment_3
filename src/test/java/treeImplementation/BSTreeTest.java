/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package treeImplementation;

import org.junit.Test;
import static org.junit.Assert.*;
import utilities.Iterator;

/**
 *
 * @author oribe
 */
public class BSTreeTest {
    
    public BSTreeTest() {
    }

    /**
     * Test of getRoot method, of class BSTree.
     */
    @Test
    public void testGetRoot() {
        System.out.println("getRoot");

        // Create an instance of BSTree
        BSTree<Integer> instance = new BSTree<>();

        // Test getting the root of an empty tree
        assertNull(instance.getRoot());

        // Add elements to the tree (replace Integer with the actual type of your elements)
        instance.add(5);
        instance.add(3);
        instance.add(7);
        
        // Create an instance of BSTreeNode to hold the root
        BSTreeNode<Integer> root = instance.getRoot();
     
        // Test getting the root after adding elements
        assertNotNull(root);
        assertEquals(Integer.valueOf(5), root.getData());
    }

    /**
     * Test of getHeight method, of class BSTree.
     */
    @Test
    public void testGetHeight() {
         System.out.println("getHeight");

        // Create an instance of BSTree
        BSTree<Integer> instance = new BSTree<>();

        // Add elements to the tree (replace Integer with the actual type of your elements)
        instance.add(5);
        instance.add(3);
        instance.add(7);

        // Test the height after adding elements
        int expResult = 2; 
        int result = instance.getHeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class BSTree.
     */
    @Test
    public void testSize() {
        System.out.println("size");

        // Create an instance of your concrete class that implements BSTreeADT with the actual type
        BSTree<Integer> instance = new BSTree<>();

        // Test the size of an empty tree
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);

        // Add elements to the tree (replace Integer with the actual type of your elements)
        instance.add(5);
        instance.add(3);
        instance.add(7);

        // Test the size after adding elements
        expResult = 3;
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class BSTree.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        
        // Create an instance of BSTree
        BSTree<Integer> instance = new BSTree<>();

        // Test if the tree is empty initially
        assertTrue(instance.isEmpty());

        // Add elements to the tree (replace Integer with the actual type of your elements)
        instance.add(5);
        instance.add(3);
        instance.add(7);

        // Test if the tree is not empty after adding elements
        assertFalse(instance.isEmpty());
    }

    /**
     * Test of clear method, of class BSTree.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        
        // Create an instance of BSTree
        BSTree<Integer> instance = new BSTree<>();

        // Add elements to the tree (replace Integer with the actual type of your elements)
        instance.add(5);
        instance.add(3);
        instance.add(7);

        // Check if the tree is not empty before clearing
        assertFalse(instance.isEmpty());

        // Clear the tree
        instance.clear();

        // Test if the tree is empty after clearing
        assertTrue(instance.isEmpty());
    }

    /**
     * Test of contains method, of class BSTree.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        
        // Create an instance of BSTree
        BSTree<Integer> instance = new BSTree<>();

        // Test contains on an empty tree
        assertFalse(instance.contains(5));

        // Add elements to the tree (replace Integer with the actual type of your elements)
        instance.add(5);
        instance.add(3);
        instance.add(7);

        // Test contains on a non-empty tree
        assertTrue(instance.contains(5));
        assertFalse(instance.contains(10)); // Test with an element not in the tree
    }

    /**
     * Test of add method, of class BSTree.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        
        // Create an instance of BSTree
        BSTree<Integer> instance = new BSTree<>();

        // Test adding elements to the tree
        assertTrue(instance.add(5));
        assertTrue(instance.add(3));
        assertTrue(instance.add(7));

        // Test adding a duplicate element
        assertFalse(instance.add(5));

        // Test adding more elements
        assertTrue(instance.add(4));
        assertTrue(instance.add(6));
        assertTrue(instance.add(8));

        // Test adding null element
        assertFalse(instance.add(null));

        // Test the size of the tree after adding elements
        assertEquals(6, instance.size());
    }

    /**
     * Test of search method, of class BSTree.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
       // Create an instance of BSTree
        BSTree<Integer> instance = new BSTree<>();

        // Test search on an empty tree
        assertNull(instance.search(5));

        // Add elements to the tree
        instance.add(5);
        instance.add(3);
        instance.add(7);

        // Test search on a non-empty tree
        assertNotNull(instance.search(5));
        assertNull(instance.search(10)); // Test with an element not in the tree
    }

    /**
     * Test of removeMin method, of class BSTree.
     */
    @Test
    public void testRemoveMin() {
        System.out.println("removeMin");
        
        // Create an instance of BSTree
        BSTree<Integer> instance = new BSTree<>();

        // Test search on an empty tree
        assertNull(instance.search(5));

        // Add elements to the tree
        instance.add(5);
        instance.add(3);
        instance.add(7);

        // Test search on a non-empty tree
        assertNotNull(instance.search(5));
        assertNull(instance.search(10)); // Test with an element not in the tree
    }

    /**
     * Test of removeMax method, of class BSTree.
     */
    @Test
    public void testRemoveMax() {
        System.out.println("removeMax");
        
        // Create an instance of BSTree
        BSTree<Integer> instance = new BSTree<>();

        // Test removeMax on an empty tree
        assertNull(instance.removeMax());

        // Add elements to the tree
        instance.add(5);
        instance.add(3);
        instance.add(7);

        // Test removeMax on a non-empty tree
        assertEquals(Integer.valueOf(7), instance.removeMax().getData());
        assertEquals(Integer.valueOf(5), instance.removeMax().getData());
        assertEquals(Integer.valueOf(3), instance.removeMax().getData());
        assertNull(instance.removeMax());
    }

    /**
     * Test of inorderIterator method, of class BSTree.
     */
    @Test
    public void testInorderIterator() {
        System.out.println("inorderIterator");
        
        // Create an instance of BSTree
        BSTree<Integer> instance = new BSTree<>();

        // Test inorderIterator on an empty tree
        Iterator<Integer> iterator = instance.inorderIterator();
        assertFalse(iterator.hasNext());

        // Add elements to the tree
        instance.add(5);
        instance.add(3);
        instance.add(7);

        // Test inorderIterator on a non-empty tree
        iterator = instance.inorderIterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(7), iterator.next());
        assertFalse(iterator.hasNext());
    }

    /**
     * Test of preorderIterator method, of class BSTree.
     */
    @Test
    public void testPreorderIterator() {
        System.out.println("preorderIterator");
        
        // Create an instance of BSTree
        BSTree<Integer> instance = new BSTree<>();

        // Test preorderIterator on an empty tree
        Iterator<Integer> iterator = instance.preorderIterator();
        assertFalse(iterator.hasNext());

        // Add elements to the tree
        instance.add(5);
        instance.add(3);
        instance.add(7);

        // Test preorderIterator on a non-empty tree
        iterator = instance.preorderIterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(7), iterator.next());
        assertFalse(iterator.hasNext());
    }

    /**
     * Test of postorderIterator method, of class BSTree.
     */
    @Test
    public void testPostorderIterator() {
        System.out.println("postorderIterator");
        
        // Create an instance of BSTree
        BSTree<Integer> instance = new BSTree<>();

        // Test postorderIterator on an empty tree
        Iterator<Integer> iterator = instance.postorderIterator();
        assertFalse(iterator.hasNext());

        // Add elements to the tree
        instance.add(5);
        instance.add(3);
        instance.add(7);

        // Test postorderIterator on a non-empty tree
        iterator = instance.postorderIterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(7), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertFalse(iterator.hasNext());
    }
    
}
