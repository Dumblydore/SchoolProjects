public class Main {
    public static void main(String[] args){
        Dictionary dictionary = new Dictionary();
        for(String str : args)
            dictionary.spellCheck(str);
    }
}
