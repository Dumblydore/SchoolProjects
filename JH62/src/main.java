public class main {
    public static void main(String[] args) {
        for(String str : args)
            new IndentChecker(str).readFile();
    }

}
