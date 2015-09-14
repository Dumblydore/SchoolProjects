import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
    Scanner input = new Scanner(System.in);
    private HashSet<String> dictionary = new HashSet<>();

    public Dictionary() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("Dictionary.txt"));
            String line;
            while ((line = in.readLine()) != null) {
                this.dictionary.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void spellCheck(String fileName) {
        System.out.println("==========Reading " + fileName + "==========");
        TreeSet<String> misspelledWords = new TreeSet<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String line;
            int count = 0;
            while ((line = in.readLine()) != null) {
                boolean sentence = false;
                for (String word : line.split(" ")) {
                    String str = word.replaceAll("\\W", "").toLowerCase();
                    if (str.length() > 0) {
                        try {
                            Integer.parseInt(str.substring(0, 1));
                        } catch (Exception e) {
                            if (str.substring(str.length() - 1, str.length()).equals("s")) {
                                if (!dictionary.contains(str.substring(0, str.length())) && !misspelledWords.contains(str)) {
                                    String cutStr = str.substring(0, str.length()-1);
                                    if (!sentence)
                                        System.out.println(count + ": " + line);
                                    System.out.println("Add " + str + " to dictionary?(y/n)");
                                    while (true) {
                                        String choice = input.next().toLowerCase();
                                        if (choice.equals("y")) {
                                            this.dictionary.add(cutStr);
                                            System.out.println(str + " -added");
                                            break;
                                        } else if (choice.toLowerCase().equals("n")) {
                                            misspelledWords.add(cutStr);
                                            System.out.println(str + " not added.");
                                            break;
                                        }
                                        else
                                            System.out.println("Invalid input.");
                                    }
                                    sentence = true;
                                }
                            } else if (!dictionary.contains(str) && !misspelledWords.contains(str)) {
                                if (!sentence)
                                    System.out.println(count + ": " + line);
                                System.out.println("Add " + str + " to dictionary?(y/n)");
                                label:
                                while (true) {
                                    String choice = input.next().toLowerCase();
                                    switch (choice) {
                                        case "y":
                                            this.dictionary.add(str);
                                            System.out.println(str + " -added");
                                            break label;
                                        case "n":
                                            misspelledWords.add(str);
                                            System.out.println(str + " not added.");
                                            break label;
                                        default:
                                            System.out.println("Invalid input.");
                                            break;
                                    }
                                }
                                sentence = true;
                            }
                        }
                    }
                }
                count++;
            }
        } catch (IOException e) {

        }
        System.out.println("****Misspelled Words****");
        for (String lp : misspelledWords)
            System.out.println(lp);
        System.out.println("************************\n");
    }
}
