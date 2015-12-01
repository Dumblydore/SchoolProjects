import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by medwar40 on 11/26/15.
 */

public class LinkedHashTable<K, V> {

    private class Entry {
        private K key;
        private V value;
        private Entry next;

        public Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }


    }

    private final int CAPACITY = 10000;
    private Entry[] table;
    private int size;
    private int count;
    private int probes;

    public LinkedHashTable() {
        size = CAPACITY;
        count = 0;
        probes = 0;
        this.table = (Entry[]) Array.newInstance(Entry.class, size);
    }

    public void add(K key, V value) {
        if (size == CAPACITY) //returns if array is full
            return;

        int initial;
        initial = Math.abs(key.hashCode()) % size; // start probing
        Entry tmp = table[initial];

        while (tmp != null) {
            if (value.equals(tmp.value)) { // if value already exists in array
                probes++; // count as probe
                return; //exit function
            } else {
                tmp = tmp.next; // go to next Entry
            }
        }

        // Add new entry
        table[initial] = new Entry(key, value, table[initial]);
        count++;
        probes++;
    }

    public void upload(String filePath) {
        try {
            Path path = FileSystems.getDefault().getPath(".", filePath);
            OutputStream in = Files.newOutputStream(path);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(in));
            for (Entry entry : table) {
                upload(entry, writer);
            }
            writer.flush();
            writer.close();
        } catch (IOException ignored) {

        }
    }

    private void upload(Entry entry, BufferedWriter writer) throws IOException {
        if (entry != null) {
            writer.write(entry.value.toString() + '\n'); // write current node
            upload(entry.next, writer);
        }
    }

    public int size() {
        return count;
    }

    public int getProbes() {
        return probes;
    }

    public int nodeHeight() {
//        return count;
        return 0;
    }
}
