import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
// Current implementation doesn't contains repeat element. We do nothing if the same element is existed.

public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            right = null;
            left = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    // 向以node为根的二分搜索树中出插入元素e，使用递归。
    // 返回插入新节点的根
    public Node add(Node node, E e) {

        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        }
        else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    //  向二分搜索树中出插入元素e，使用循环。
    public void insert(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
            return;
        }

        Node current = root;
        Node parent;
        while (true) {
            parent = current;

            if (e.compareTo(current.e) < 0) {
                current = current.left;
                if (current == null) {
                    size++;
                    parent.left = new Node(e);
                    break;
                }
            }
            else if (e.compareTo(current.e) > 0) {
                current = current.right;
                if (current == null) {
                    size++;
                    parent.right = new Node(e);
                    break;
                }
            } else {
                break;
            }
        }
    }

    //  看二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    //  看以node为根的二分搜索树中是否包含元素e, 递归算法
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        }
        else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        }
        else {
            return contains(node.right, e);
        }
    }

    //  看二分搜索树中是否包含元素e,使用循环
    public boolean has(E e) {
        Node cur = root;
        while (cur != null) {
            if (e.compareTo(cur.e) == 0) {
                return true;
            }
            else if (e.compareTo(cur.e) < 0) {
                cur = cur.left;
            }
            else if (e.compareTo(cur.e) > 0) {
                cur = cur.right;
            }
        }

        return false;
    }

    //  二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }

    //  前序遍历以node为根的二分搜索树，递归算法
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        // do stuff.
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 二分搜索树的前序遍历,非递归，借助栈实现
    public void preOrderNR() {

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // do stuff;
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    //  二分搜索树的中序遍历
    public void inOrder() {
        inOrder(root);
    }

    //  中序遍历以node为根的二分搜索树，递归算法
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        // do stuff.
        System.out.println(node.e);
        inOrder(node.right);
    }

    // 二分搜索树的中序遍历,非递归，借助栈实现
    public void inOrderNR() {
        Stack<Node> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            if (!stack.isEmpty()) {
                Node cur = stack.pop();
                System.out.println(cur.e);
                root = cur.right;
            }
        }
    }

    //  二分搜索树的后序遍历
    public void postOrder() {
        postOrder(root);
    }

    //  后序遍历以node为根的二分搜索树，递归算法
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        // do stuff.
        System.out.println(node.e);
    }

    // 二分搜索树的后序遍历,非递归，借助栈实现
    public void postOrderNR() {
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        Node pre = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                Node top = stack.peek();
                if (top.right == null || top.right == pre) {
                    System.out.println(top.e);
                    pre = top;
                    stack.pop();
                } else {
                    cur = top.right;
                }
            }
        }
    }

    // 二分搜索树的层序遍历（广度优先）
    public void levelOrder() {

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node cur = q.remove();
            // do stuff.
            System.out.println(cur.e);

            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }

    // 查找二分搜索树中的最小值, 使用递归
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }

        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 查找二分搜索树中的最大值, 使用递归
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }

        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    // 查找二分搜索树中的最小值, 非递归
    public E findMin() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }

        Node cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur.e;
    }

    // 查找二分搜索树中的最大值, 非递归
    public E findMax() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }

        Node cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.e;
    }

    // 从二分搜索树中删除最小值所在的节点，返回最小值
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 删除以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    public Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除最大值所在的节点，返回最大值
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    public Node removeMax(Node node)  {

        if (node.right == null) {
            Node leftNode = node.left;
            node.right = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    // 从二分搜索树中删除元素为e的节点
    public void remove(E e) {
        root = remove(root, e);
    }

    // 删除以node为根节点的二分搜索树中值为e的节点，递归算法
    // 返回删除节点后的二分搜索树的根
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        }
        else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else { // e.equals(node.e)

            // 待删除的节点左子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除的节点右子树为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除的节点左，右子树均不为空
            // 找到比待删除节点大的所有节点中最小的节点，即 待删除节点右子树的最小节点(待删除节点的后继)
            // 使用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;

            return successor;
        }

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

//    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//            size++;
//        } else {
//            add(root, e);
//        }
//    }

    // 向以node为根的二分搜索树中出插入元素e，使用递归。
    // Insert element e into the binary search tree with node as root. Use recursion.
//    private void add(Node node, E e) {
//
//        if (e.compareTo(node.e) == 0) {
//            return;
//        }
//        else if (e.compareTo(node.e) < 0 && node.left == null) {
//            node.left = new Node(e);
//            size++;
//            return;
//        }
//        else if (e.compareTo(node.e) > 0 && node.right == null) {
//            node.right = new Node(e);
//            size++;
//            return;
//        }
//
//        if (e.compareTo(node.e) < 0) {
//            add(node.left, e);
//        } else {
//            add(node.right, e);
//        }
//    }
}

// 前序，中序，后序 都是深度优先， 可以使用递归完成

// 层序遍历是广度优先，使用非递归实现，借助队列完成;
// 广度优先遍历的意义：1.更快的找到问题的解  2.常用于算法设计中——最短路径
