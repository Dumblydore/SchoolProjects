/**
 * Created by medwar40 on 11/26/15.
 */
public class Word {
    String str;

    public Word(String str) {
        this.str = str;
    }

    /*@Override
    public int hashCode() {
        return str.hashCode();
    }*/

    /*@Override
    public int hashCode() {
        int hash = 0;
        int key = str.length();
        for (char aChar : str.toCharArray()) { // iterate through string's characters
            hash += aChar | key; // adds a bitmask of the character and key to the hash
        }
        return hash;
    }*/


    /*@Override
    public int hashCode() {
        int hash = 0;
        int key = str.length();
        char[] chars = str.toCharArray(); // gets array of characters from string
        for (char aChar : chars) { // loops through string
            hash += aChar * key + 27; // adds
        }
        return hash;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        int key = str.length();
        for (char ch : str.toCharArray()) { // iterate through string
            hash += ((ch % 2) == 0 ? ch : 0) * key;
        }
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return !(str != null ? !str.equals(word.str) : word.str != null);

    }

    @Override
    public String toString() {
        return str;
    }
}
