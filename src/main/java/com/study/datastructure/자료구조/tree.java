package com.study.datastructure.자료구조;

import org.w3c.dom.Node;

public class tree {
    static class Node{
        int data;
        Node left;
        Node right;
    }

    static class Tree {
        public Node root;

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
    }
}
