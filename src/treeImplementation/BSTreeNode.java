package treeImplementation;

import java.util.ArrayList;
import java.util.List;

public class BSTreeNode<E> {

    private E data;
    private BSTreeNode<E> left;
    private BSTreeNode<E> right;
    private List<LineNumber> lineNumbers;

    public BSTreeNode(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
        // line numbers
        this.lineNumbers = new ArrayList<>();
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public BSTreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(BSTreeNode<E> left) {
        this.left = left;
    }

    public BSTreeNode<E> getRight() {
        return right;
    }

    public void setRight(BSTreeNode<E> right) {
        this.right = right;
    }

    public void addLineNumber(String fileName, int lineNumber) {
        LineNumber lineNumberObj = new LineNumber(fileName, lineNumber);
        lineNumbers.add(lineNumberObj);
    }

    public List<LineNumber> getLineNumbers() {
        return lineNumbers;
    }
}