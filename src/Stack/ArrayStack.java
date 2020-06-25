public class ArrayStack<E> implements Stack<E> {

    Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack(){
        array = new Array<>();
    }

    @Override
    public int getSize() {  // O(1)
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {  // O(1)
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public void push(E e) {  // O(1)
        array.addLast(e);
    }

    @Override
    public E pop() {  // O(1) Average complexity
        return array.removeLast();
    }

    @Override
    public E peek() {  // O(1) Average complexity
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");

        return res.toString();
    }
}

// 所有操作都是 O(1), 但是使用了 Array 作为容器，可能会触发 resize