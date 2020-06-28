public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 像二分搜索树中添加新的元素e
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 向以node为根节点的二分搜索树中插入元素（key, value），递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            node = new Node(key, value);
            size++;
            return node;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        }
        else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        }
        else {  // key.compareTo(node.key) == 0
            node.value = value;
        }

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(key) == 0) {
            return node;
        }
        else if (key.compareTo(key) < 0) {
            return getNode(node.left, key);
        }
        else {
            return getNode(node.right, key);
        }
    }

    // 是否包含 key的节点， 使用递归
    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    // 是否包含 key的节点， 使用非递归
    public boolean has(K key) {
        Node cur = root;
        while (cur != null) {
            if (cur.key.compareTo(key) == 0) {
                return true;
            }
            else if (cur.key.compareTo(key) < 0) {
                cur = cur.left;
            }
            else {
                cur = cur.right;
            }
        }

        return false;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            return node.value;
        } else {
            return null;
        }
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exists!");
        }

        node.value = newValue;
    }

    // 删除二分搜索树中key的节点， 返回此节点的值
    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }

        return null;
    }

    // 删除以node为根的二分搜索树中键为key的节点，递归算法
    // 返回删除节点后的新的二分搜索树的根
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) < 0) {
            node.left = remove(node.left, key);
            return node;
        }
        else if (node.key.compareTo(key) > 0) {
            node.right = remove(node.right, key);
            return node;
        }
        else { // node.key.compareTo(key) > 0

            // 待删除节点左子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 待删除节点右子树为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左子树，右子树均不为空
            // 找到 以待删除节点为根的二分搜索树的最小值所在的节点(后继) 用来顶替掉待删除节点。
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 删除以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }
}
