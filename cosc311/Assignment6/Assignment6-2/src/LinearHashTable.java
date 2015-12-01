import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by medwar40 on 11/30/15.
 */
public class LinearHashTable<K, V> {

    private final int CAPACITY = 10000;
    private Entry[] table;
    private int count;
    private int probes;

    public LinearHashTable() {
        count = 0;
        probes = 0;
        this.table = (Entry[]) Array.newInstance(Entry.class, CAPACITY);
    }

    private class Entry {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void add(K key, V value) {

        if (CAPACITY <= count) // checks if table is at capacity
            return;

        int initial = Math.abs(key.hashCode()) % CAPACITY; //starts probing
        boolean hasIteratedOver = false;
        for (int i = 0; i < CAPACITY + 1; i++) {
            int current = (initial + 1) % CAPACITY; // gets current probing location
            if (table[current] == null) { // if empty entry is reached is reached, insert value
                table[current] = new Entry(key, value);
                count++; // add to count
                probes++; // add to probes
                break;
            } else if (value.equals(table[current].value)) { // otherwise just add to probes
                probes++;
                break;
            }
            // to iterate through the entire table at least once
            if(i == CAPACITY - 1) { // if the index is at the last index in array
                if(hasIteratedOver) // if the loop has already been iterated over just return
                    return;
                else { // otherwise try to iterate over array again
                    hasIteratedOver = true;
                    i = 0;
                }
            }
        }
    }

    public void upload(String filePath) {
        try {
            Path path = FileSystems.getDefault().getPath(".", filePath);
            OutputStream in = Files.newOutputStream(path);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(in));
            for (Entry entry : table) {
                if (entry != null)
                    writer.write(entry.value.toString() + '\n'); // write current node
            }
            writer.flush();
            writer.close();
        } catch (IOException ignored) {

        }
    }

    public int getProbes() {
        return probes;
    }

    public int size() {
        return count;
    }
}
