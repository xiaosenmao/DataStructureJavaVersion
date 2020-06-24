public class Array<E> {
    private static final int defaultCapacity = 10;

    private E[] data;
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(Array.defaultCapacity);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public Boolean isEmpty() {
        return size == 0;
    }

    public void addLast(E e) {  // O(1)  // 特殊的操作引起复杂度震荡 O(n)
        add(size, e);
    }

    public void addFirst(E e) {  // O(n)
        add(0, e);
    }

    public void add(int index, E e) {  // index越小，需要移动的元素越多，平均来看可以认为是 n/2;  ==> O(n/2) 忽略常数1/2 就约等于 O(n)

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size");
        }

        if (size == data.length) {
            resize(2 * data.length);  // expansion
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public E get(int index) {  // O(1) 数组的最大优势，支持随机访问
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }

        return data[index];
    }

    public void set(int index, E e) {  // O(1)
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }

        data[index] = e;
    }

    public Boolean contains(E e) {  // O(n)
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {  // O(n)
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i; // Find the first equals.
            }
        }
        return -1;
    }

    public E remove(int index) {  // O(n)
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }

        E ret = data[index];
        for (int i = index + 1; i < size ; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;  // optional. Because data[size] is loitering object. (!= memory leak);

        if (size == data.length / 4 && data.length / 2 != 0) {  // 当剩下1/4的元素时，缩容到容积的1/2，消除复杂度震荡
            resize(data.length / 2);  // reduce capacity.
        }
        return ret;
    }

    public E removeFirst() {  // O(n)
        return remove(0);
    }

    public E removeLast() {  // O(1)
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) remove(index);
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }

    private void resize(int newCapacity) {  // O(n) 均摊复杂度
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}

/*
  简单分析动态数组的时间复杂度
    1. 增：O(n)
    2. 删：O(n)
    对于 增 和 删，如果只是对最后一个元素操作的话依然是 O(1); 但是考虑 resize的情况下是 O(n)

    3. 改：已知索引 O(1);  未知索引 O(n)
    4. 查：已知索引 O(1);  未知索引 O(n)
*/

/*
  常见的时间复杂度所消耗时间的大小排列
    O(1) < O(logn) < O(n) < O(nlogn) < O(n^2) < O(n^3) < O(2^n) < O(n!) < O(n^n)
*/