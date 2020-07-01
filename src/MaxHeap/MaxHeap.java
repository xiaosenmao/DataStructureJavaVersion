public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    // 将任意数组整理成堆的形状 heapify
    public MaxHeap(E[] arr) {  // O(n)

        data = new Array<>(arr);

        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    // 返回堆中元素的个数
    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父节点的索引
    private int parent(int index) {
        if (index == 0) throw new IllegalArgumentException("index-0 doesn't have parent.");

        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    public int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    public int rightChild(int index) {
        return index * 2 + 2;
    }

    // 向堆中添加元素（上浮）
    public void add(E e) {  // O(logn)
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) { // 需要上浮的元素的索引
        // 不是根节点， 需要上浮的元素节点的索引的父节点的元素 小于 需要上浮的元素
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    // 查看堆中的最大元素
    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return data.get(0);
    }

    // 向堆中取出元素 （下沉）
    public E extractMax() {  // O(logn)

        E ret = findMax();

        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    private void siftDown(int k) {

        while (leftChild(k) < data.getSize()) {  // 索引为k的元素的节点有左孩子
            int j = leftChild(k);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) { // 有右孩子，并且有孩子的元素的的值大于左孩子
                j = rightChild(k);
            }
            // data[j] 是 leftChild 和 rightChild 中的最大值

            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }

            data.swap(k, j);
            k = j;
        }
    }

    // 取出最大元素, 将堆顶元素替换成新的元素，在进行下沉，保证最大堆的性质， 一次 O(logn)的操作
    public E replace(E e) {
        E ret = findMax();

        data.set(0, e);
        siftDown(0);

        return ret;
    }
}



// 使用数组存储二叉堆
/*

                                                         62 (0)
                                              /                          \
                                41 (1)                                             30 (2)
                            /            \                                      /           \
                   28 (3)                       16 (4)                    22 (5)          13 (6)
                    /       \                   /
           19 (7)           17 (8)       15 (9)




i:    0   1   2   3   4   5   6   7   8   9
      62  41  30  28  16  22  13  19  17  15


    parent (i - 1) = i/2  (整除)

    leftChild (i)  =  2*i + 1
    rightChild (i) =  2*i + 2

*/
