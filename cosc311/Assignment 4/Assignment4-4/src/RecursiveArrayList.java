public class RecursiveArrayList {
    int[] container;

    public RecursiveArrayList() {
        container = new int[20];
        init(0);
    }

    private void init(int n) {
        if (n >= container.length)
            return;
        container[n] = n + 1;
        init(n + 1);
    }

    public void display() {
        display(0);
    }

    private void display(int index) {
        if (container.length <= index)
            return;
        System.out.println(container[index]);
        display(index + 1);
    }

    public void displayReverse() {
        displayReverse(0);
    }

    private void displayReverse(int index) {
        if (container.length - 1 <= index) {
            System.out.println(container[index]);
            return;
        }
        displayReverse(index + 1);
        System.out.println(container[index]);
    }

    public int sum() {
        return sum(0, 0);
    }

    private int sum(int index, int sum) {
        if (container.length <= index)
            return sum;
        return sum(index + 1, sum += container[index]);
    }

    public void containsZero() {
        System.out.println(contains(0, 0) ? "List contains zeros." : "List doesn't contain zeros.");
    }

    private boolean contains(int n, int index) {
        if (container.length <= index) {
            return container[index] != n;
        }
        return container[index] == n || contains(n, index);
    }

    public void printOdd() {
        printOdd(0);
    }

    private void printOdd(int index) {
        if (container.length <= index)
            return;
        if (container[index] % 2 > 0)
            System.out.println(container[index]);
        printOdd(index + 1);
    }

    public void skip() {
        skip(false, 0);
    }

    private void skip(boolean skip, int index) {
        if (container.length <= index)
            return;
        if (!skip)
            System.out.println(container[index]);
        skip(!skip, index + 1);
    }

}

