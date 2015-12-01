import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Created by medwar40 on 11/26/15.
 */
public class Main {

    public static void main(String[] args) {
        for(int i = 1; i < 6; i++) {
            System.out.println("=======file" + i + "=======");
            readFile("file" + i + ".txt", "");
        }
//        readFile(inFile, outFile);
    }

    public static void readFile(String filePath, String outPath) {
        BinarySearchTree<String> table = new BinarySearchTree<>();
        Path path = FileSystems.getDefault().getPath(".", filePath);
        InputStream in = null;
        int wordCount = 0;
        long beginningTime = System.currentTimeMillis();
        try {
            in = Files.newInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                for (String string : line.split("\\s+")) {
                    table.add(string);
                    wordCount++;
                }
            }
            double averageTimeRuntime = (System.currentTimeMillis() * 1.0) / beginningTime;
            double averageNodeRuntime = (table.getProbes() * 1.0) / wordCount;
            System.out.println("Probes: " + table.getProbes());
            System.out.println("Words: " + wordCount);
            System.out.println("Node based average runtime: " + averageNodeRuntime);
            System.out.println("Time based average runtime: " + averageTimeRuntime + "ms per node");
//            table.upload(outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
