package treeImplementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class WordTracker implements Serializable {
    private static final long serialVersionUID = 1L;
    private static BSTree<String> wordTree;

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println(
                    "Invalid command. Usage: java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>]");
            return;
        }

        String filePath = args[0];
        String option = args[1];
        String outputFilePath = "";

        processTextFile(filePath);
        deserializeBinarySearchTree();

    }

    private static void processTextFile(String filePath) {
        try (FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            int lineNumber = 1;

            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        BSTreeNode<String> node = wordTree.search(word);
                        if (node == null) {
                            node = new BSTreeNode<>(word);
                            wordTree.add(node.getData());
                        }
                        node.addLineNumber(filePath, lineNumber);
                    }
                }
                lineNumber++;
            }

            FileOutputStream fileOutputStream = new FileOutputStream("repository.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(wordTree);

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file or serializing the binary search tree: "
                    + e.getMessage());
        }
    }

    private static void deserializeBinarySearchTree() {
        try {
            File repositoryFile = new File("repository.ser");
            if (repositoryFile.exists()) {
                FileInputStream fileInputStream = new FileInputStream(repositoryFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                wordTree = (BSTree<String>) objectInputStream.readObject();

                objectInputStream.close();
                fileInputStream.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("The repository.ser file was not found.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while deserializing the binary search tree: " + e.getMessage());
        }
    }
}