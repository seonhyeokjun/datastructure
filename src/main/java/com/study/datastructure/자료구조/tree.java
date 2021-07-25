package com.study.datastructure.자료구조;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;

public class tree {
    static class Node{
        int data;
        Node left;
        Node right;
        Node parent;
        Node(int data){
            this.data = data;
        }
        Node() {}
    }

    static class Tree {
        Node root;
        HashMap<Integer, Node> rootMap;
        int size;
        public Tree() {}

        Tree(int size){
            this.size = size;
            rootMap = new HashMap<Integer, Node>();
            //root = makeBST(0, size - 1);
            root = makeBST(0, size - 1, null);
            //root.right.right.right.right = new Node(10);
            //root.right.right.left = new Node(11);
            //root.right.right.right.left = new Node(10);
            //this.size++;
        }

        public void setRoot(Node node){
            this.root = node;
        }
        public Node getRoot(){
            return root;
        }
        public Node makeNode(Node left, int data, Node right){
            Node node = new Node();
            node.data = data;
            node.left = left;
            node.right = right;
            return node;
        }
        public void inorder(Node node){
            if (node != null){
                inorder(node.left);
                System.out.println(node.data);
                inorder(node.right);
            }
        }
        public void preorder(Node node){
            if (node != null){
                System.out.println(node.data);
                preorder(node.left);
                preorder(node.right);
            }
        }
        public void postorder(Node node){
            if (node != null){
                postorder(node.left);
                postorder(node.right);
                System.out.println(node.data);
            }
        }
        public void makeTree(int[] a){
            root = makeTreeR(a, 0, a.length - 1);
        }

        /**
         * 정렬된 배열을 이진검색트리로 만들기
         * @param a
         * @param start
         * @param end
         * @return
         */
        public Node makeTreeR(int[] a, int start, int end) {
            if (start > end) return null;
            int mid = (start + end) / 2;
            Node node = new Node(a[mid]);
            node.left = makeTreeR(a, start, mid - 1);
            node.right = makeTreeR(a, mid + 1, end);
            return node;
        }
        public void searchBTree(Node n, int find){
            if (find < n.data){
                System.out.println("Data is smaller than " + n.data);
                searchBTree(n.left, find);
            } else if (find > n.data){
                System.out.println("Data is bigger than " + n.data);
                searchBTree(n.right, find);
            } else {
                System.out.println("Data found!");
            }
        }
        /**
         * 이진트리를 레벨단위 리스트로 변형하기
         * @param start
         * @param end
         * @return
         */
        Node makeBST(int start, int end) {
            if (start > end) return null;
            int mid = (start + end) / 2;
            Node node = new Node(mid);
            node.left = makeBST(start, mid - 1);
            node.right = makeBST(mid + 1, end);
            return node;
        }

        Node makeBST(int start, int end, Node parent) {
            if (start > end) return null;
            int mid = (start + end) / 2;
            Node node = new Node(mid);
            node.left = makeBST(start, mid - 1, node);
            node.right = makeBST(mid + 1, end, node);
            node.parent = parent;
            rootMap.put(mid, node);
            return node;
        }

        /**
         * tree의 balance 확인하기
         * @param root
         * @return
         */
        boolean isBalanced(Node root){
            if (root == null) return true;
            int hightDiff = getHeight(root.left) - getHeight(root.right);
            if (Math.abs(hightDiff) > 1){
                return false;
            } else {
                return isBalanced(root.left) && isBalanced(root.right);
            }
        }
        int getHeight(Node root) {
            if (root == null) return -1;
            return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        }
        int checkHeight(Node root){
            if (root == null) return -1;
            int legtHeight = checkHeight(root.left);
            if (legtHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
            int rightHeight = checkHeight(root.right);
            if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
            int heightDiff = legtHeight - rightHeight;
            if (Math.abs(heightDiff) > 1){
                return Integer.MIN_VALUE;
            } else {
                return Math.max(legtHeight, rightHeight) + 1;
            }
        }
        boolean isBalanced2(Node root){
            return checkHeight(root) != Integer.MIN_VALUE;
        }
        class Level{
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
        }
        boolean isBalanced3(Node root){
            Level obj = new Level();
            checkBalanced(root, obj, 0);
            if (Math.abs(obj.min - obj.max) > 1) return false;
            else return true;
        }
        void checkBalanced(Node node, Level obj, int level){
            if (node == null){
                if (level < obj.min) obj.min = level;
                else if (level > obj.max) obj.max = level;
                return;
            }
            checkBalanced(node.left, obj, level+1);
            checkBalanced(node.right, obj, level+1);
        }
        ArrayList<LinkedList<Node>> BSTtoList(){
            ArrayList<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();
            BSTtoList(root, lists, 0);
            return lists;
        }

        /**
         * 이진트리를 레벨단위 리스트로 변형하기
         * @param root
         * @param lists
         * @param level
         */
        void BSTtoList(Node root, ArrayList<LinkedList<Node>> lists, int level) {
            if (root == null) return;
            LinkedList<Node> list = null;
            if (lists.size() == level){
                list = new LinkedList<Node>();
                lists.add(list);
            } else {
                list = lists.get(level);
            }
            list.add(root);
            BSTtoList(root.left, lists, level + 1);
            BSTtoList(root.right, lists, level + 1);
        }

        /**
         * 주어진 트리가 이진검색트리인지 확인하기
         * @return
         */
        boolean isValidateBST1(){
            int[] array = new int[size];
            inorder(root, array);
            for (int i = 1; i < array.length; i++){
                if (array[i] < array[i - 1]){
                    return false;
                }
            }
            return true;
        }
        int index = 0;
        void inorder(Node root, int[] array){
            if (root != null){
                inorder(root.left, array);
                array[index] = root.data;
                index++;
                inorder(root.right, array);
            }
        }
        Integer last_printed = null;
        boolean isValidateBST2(){
            return isValidateBST2(root);
        }
        boolean isValidateBST2(Node n){
            if (n == null) return true;
            if (!isValidateBST2(n.left)) return false;
            if (last_printed != null && n.data < last_printed){
                return false;
            }
            last_printed = n.data;
            if (!isValidateBST2(n.right)) return false;
            return true;
        }
        boolean isValidateBST3(){
            return isValidateBST3(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        boolean isValidateBST3(Node n, int min, int max){
            if (n == null){
                return true;
            }
            if (n.data < min || n.data > max){
                return false;
            }
            if (!isValidateBST3(n.left, min, n.data) || !isValidateBST3(n.right, n.data, max)){
                return false;
            }
            return true;
        }

        /**
         * 이진검색트리에서 다음노드 찾기
         * @param node
         */
        void findNext(Node node){
            if (node.right == null){
                System.out.println(findAbove(node.parent, node).data + " is " + node.data + "'s next");
            } else {
                System.out.println(findBelow(node.right).data + " is " + node.data + "'s next");
            }
        }
        Node findAbove(Node root, Node child){
            if (root == null) return null;
            if (root.left == child) return root;
            return findAbove(root.parent, root);
        }
        Node findBelow(Node root){
            if (root.left == null) return root;
            return findBelow(root.left);
        }
        ArrayList<LinkedList<Node>> BSTtoList2(){
            ArrayList<LinkedList<Node>> result = new ArrayList<LinkedList<Node>>();
            LinkedList<Node> current = new LinkedList<Node>();
            if (root != null){
                current.add(root);
            }
            while (current.size() > 0){
                result.add(current);
                LinkedList<Node> parents = current;
                current = new LinkedList<Node>();
                for (Node parent : parents){
                    if (parent.left != null) current.add(parent.left);
                    if (parent.right != null) current.add(parent.right);
                }
            }
            return result;
        }
        void printList(ArrayList<LinkedList<Node>> arr){
            for (LinkedList<Node> list : arr){
                for (Node node : list){
                    System.out.print(node.data + " ");
                }
                System.out.println();
            }
        }
        /**
         * Tree에서 두노드의 첫번째 공통부모 찾기
         */
        Node getNode(int data){
            return rootMap.get(data);
        }
        boolean covers(Node root, Node node){
            if (root == null) return false;
            if (root == node) return true;
            return covers(root.left, node) || covers(root.right, node);
        }
        Node commonAncestor(int d1, int d2){
            Node p = getNode(d1);
            Node q = getNode(d2);
            int diff = depth(p) - depth(q);
            Node first = diff > 0 ? q : p;
            Node second = diff > 0 ? p : q;
            second = goUpBy(second, Math.abs(diff));
            while (first != second && first != null && second != null){
                first = first.parent;
                second = second.parent;
            }
            return first == null || second == null ? null : first;
        }
        Node commonAncestor2(int d1, int d2){
            Node p = getNode(d1);
            Node q = getNode(d2);
            if (!covers(root, p) || !covers(root, q)){
                return null;
            } else if (covers(p, q)){
                return p;
            } else if (covers(q, p)){
                return q;
            }
            Node sibling = getSibling(p);
            Node parent = p.parent;
            while (!covers(sibling, q)){
                sibling = getSibling(parent);
                parent = parent.parent;
            }
            return parent;
        }
        Node commonAncestor3(int d1, int d2){
            Node p = getNode(d1);
            Node q = getNode(d2);
            if (!covers(root, p) || !covers(root, q)){
                return null;
            }
            return ancestorHelper(root, p , q);
        }
        class Result{
            Node node;
            boolean isAncestor;
            Result (Node n, boolean isAnc){
                node = n;
                isAncestor = isAnc;
            }
        }
        Node commonAncestor4(int d1, int d2){
            Node p = getNode(d1);
            Node q = getNode(d2);
            Result r = commonAncestor(root, p, q);
            if (r.isAncestor){
                return r.node;
            }
            return null;
        }
        Result commonAncestor(Node root, Node p, Node q){
            if (root == null) return new Result(null, false);
            if (root == p && root == q) return new Result(null, true);
            Result rx = commonAncestor(root.left, p, q);
            if (rx.isAncestor) return rx;
            Result ry = commonAncestor(root.right, p, q);
            if (ry.isAncestor) return ry;

            if (rx.node != null && ry.node != null) {
                return new Result(root, true);
            } else if (root == p || root == q){
                boolean isAncestor = rx.node != null || ry.node != null;
                return new Result(root, isAncestor);
            } else {
                return new Result(rx.node != null ? rx.node : ry.node, false);
            }
        }
        Node ancestorHelper(Node root, Node p, Node q){
            if (root == null || root == p || root == q){
                return root;
            }
            boolean pIsOnLeft = covers(root.left, p);
            boolean qIsOnLeft = covers(root.left, q);
            if (pIsOnLeft != qIsOnLeft){
                return root;
            }
            Node childSide = pIsOnLeft ? root.left : root.right;
            return ancestorHelper(childSide, p, q);
        }
        Node getSibling(Node node){
            if (node == null || node.parent == null){
                return null;
            }
            Node parent = node.parent;
            return parent.left == node ? parent.right : parent.left;
        }
        Node goUpBy(Node node, int diff){
            while (diff > 0 && node != null){
                node = node.parent;
                diff--;
            }
            return node;
        }
        int depth(Node node){
            int depth = 0;
            while(node != null){
                node = node.parent;
                depth++;
            }
            return depth;
        }
    }
    /*
                (1)
             ↙     ↘
          (2)       (3)
         ↙    ↘
       (4)     (5)
    Inorder (Left, Root, Right): 4 2 5 1 3
    Preorder (Root, Left, Right): 1 2 4 5 3
    Postorder (Left, Right, Root): 4 5 2 3 1
     */
    public static void main(String[] args) {
        Tree tree = new Tree();
        Node n4 = tree.makeNode(null, 4, null);
        Node n5 = tree.makeNode(null, 5, null);
        Node n2 = tree.makeNode(n4, 2, n5);
        Node n3 = tree.makeNode(null, 3, null);
        Node n1 = tree.makeNode(n2, 1, n3);
        tree.setRoot(n1);
        tree.inorder(tree.getRoot());
        //tree.preorder(tree.getRoot());
        //tree.postorder(tree.getRoot());

        int[] a = new int[10];
        for (int i = 0; i < a.length; i++){
            a[i] = i;
        }
        /*
                    (4)
                  /     \
                /        \
              /           \
            (1)           (7)
           /   \         /   \
         (0)   (2)     (5)   (8)
                 \       \     \
                  (3)    (6)   (9)
         */
        Tree t = new Tree();
        t.makeTree(a);
        t.searchBTree(t.root, 2);

        Tree newt = new Tree(10);
        newt.printList(t.BSTtoList2());

        /*
                    (4)
                  /     \
                /        \
              /           \
            (1)           (7)
           /   \         /   \
         (0)   (2)     (5)   (8)
                 \       \     \
                 (3)     (6)   (9)
                                 \
                                 (10)
         */
        Tree t1 = new Tree(10);
        System.out.println(t1.isBalanced(t1.root));
        System.out.println(t1.isBalanced2(t1.root));
        System.out.println(t1.isBalanced3(t1.root));

        /*
                    (4)
                  /     \
                /        \
              /           \
            (1)           (7)
           /   \         /   \
         (0)   (2)     (5)   (8)
                 \       \     \
                 (3)     (6)   (9)
                                 \
                                 (10)
         */
        Tree t2 = new Tree(10);
        System.out.println("Solution 1 - using inorder: " + t2.isValidateBST1());
        System.out.println("Solution 2 - without array: " + t2.isValidateBST2());
        System.out.println("Solution 3 - min/max: " + t2.isValidateBST3());

        Tree t3 = new Tree(10);
        /*
                    (4)
                  /     \
                /        \
              /           \
            (1)           (7)
           /   \         /   \
         (0)   (2)     (5)   (8)
                 \       \     \
                  (3)    (6)   (9)
         */
        t3.findNext(t3.root.left.right.right);
        t3.findNext(t3.root.left);
        t3.findNext(t3.root);
        t3.findNext(t3.root.left.left);
        t3.findNext(t3.root.right.left.right);

        /*
                    (4)
                  /     \
                /        \
              /           \
            (1)           (7)
           /   \         /   \
         (0)   (2)     (5)   (8)
                 \       \     \
                  (3)    (6)   (9)
         */
        Tree t4 = new Tree(10);
        Node fa = t4.commonAncestor(3, 5);
        Node fa2 = t4.commonAncestor2(0, 3);
        Node fa3 = t4.commonAncestor3(2, 8);
        Node fa4 = t4.commonAncestor4(0, 3);
        System.out.println("The first common ancestor is " + fa.data);
        System.out.println("The first common ancestor is " + fa2.data);
        System.out.println("The first common ancestor is " + fa3.data);
        System.out.println("The first common ancestor is " + fa4.data);
    }
}
