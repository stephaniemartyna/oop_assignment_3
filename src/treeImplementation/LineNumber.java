package treeImplementation;

public class LineNumber {
    private String fileName;
    private int lineNumber;

    public LineNumber(String fileName, int lineNumber) {
        this.fileName = fileName;
        this.lineNumber = lineNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

}
