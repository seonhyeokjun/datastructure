package com.study.datastructure.자료구조;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;

public class tree {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
        }
        Node() {}
    }

    static class Tree {
        public Node root;

        public Tree() {

        }

        Tree(int size){
            root = makeBST(0, size - 1);
            root.right.right.right.right = new Node(10);
            //root.right.right.left = new Node(11);
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
    }
}
