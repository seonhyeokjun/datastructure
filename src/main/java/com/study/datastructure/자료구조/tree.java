package com.study.datastructure.자료구조;

import org.w3c.dom.Node;

import java.util.*;
import java.util.logging.Level;

import static com.study.datastructure.자료구조.tree.Tree.allSequences;

public class tree {
    static class Node{
        int data;
        Node left;
        Node right;
        Node parent;
        int size = 0;
        Node(int data){
            this.data = data;
            this.size = 1;
        }
        Node() {}
        void insert(int data){
            if (data <= this.data){
                if (left == null){
                    left = new Node(data);
                } else {
                    left.insert(data);
                }
            } else {
                if (right == null){
                    right = new Node(data);
                } else {
                    right.insert(data);
                }
            }
            size++;
        }
        int size() {
            return size;
        }
        Node find(int data){
            if (data == this.data){
                return this;
            } else if (data < this.data){
                return left != null ? left.find(data) : null;
            } else if (data > this.data){
                return right != null ? right.find(data) : null;
            } else {
                return null;
            }
        }
        Node getIthNode(int i){
            int leftSize = left == null ? 0 : left.size();
            if (i < leftSize){
                return left.getIthNode(i);
            } else if (i == leftSize){
                return this;
            } else {
                return right.getIthNode(i - (leftSize + 1));
            }
        }
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
            //root = makeBST(0, size - 1);
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
        /**
         * 이진검색트리를 만드는 모든 배열 찾기
         */
        static ArrayList<LinkedList<Integer>> allSequences(Node node){
            ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
            if (node == null){
                result.add(new LinkedList<Integer>());
                return result;
            }
            LinkedList<Integer> prefix = new LinkedList<Integer>();
            prefix.add(node.data);

            ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
            ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

            for (LinkedList<Integer> left : leftSeq) {
                for (LinkedList<Integer> right : rightSeq) {
                    /*
                                (2)
                               /   \
                             /      \
                           (0)      (4)
                             \      /  \
                             (1)  (3)  (5)
                        root: 2
                        left: 0->1
                        right: 4->3, 4->5

                        case1)
                        left: 0->1
                        right: 4->3

                        case2)
                        left: 0->1
                        right: 4->5
                     */
                    ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
                    weaveLists(left, right, weaved, prefix);
                    result.addAll(weaved);
                }
            }
            return result;
        }
        static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix){
            if (first.size() == 0 || second.size() == 0){
                LinkedList<Integer> result = new LinkedList<Integer>();
                for (int data : prefix) result.add(data);
                result.addAll(first);
                result.addAll(second);
                results.add(result);
                return;
            }
            int headFirst = first.removeFirst();
            prefix.addLast(headFirst);
            weaveLists(first, second, results, prefix);
            prefix.removeLast();
            first.addFirst(headFirst);

            int headSecond = second.removeFirst();
            prefix.addLast(headSecond);
            weaveLists(first, second, results, prefix);
            prefix.removeLast();
            second.addFirst(headSecond);
        }

        /**
         * 서브트리인지 확인하기
         */
        boolean containsTree(Node t1, Node t2){
            if (t2 == null) return true;
            return subTree(t1, t2);
        }
        boolean subTree(Node t1, Node t2){
            if (t1 == null){
                return false;
            } else if (t1.data == t2.data && matchTree(t1, t2)) {
                return true;
            }
            return subTree(t1.left, t2) || subTree(t1.right, t2);
        }
        boolean matchTree(Node t1, Node t2){
            if (t1 == null && t2 == null){
                return true;
            } else if (t1 == null || t2 == null){
                return false;
            } else if (t1.data != t2.data){
                return false;
            } else {
                return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);
            }
        }

        /**
         * 이진트리에서 랜덤노드가져오기
         */
        int size(){
            return root == null ? 0 : root.size();
        }

        void insert(int data){
            if (root == null) root = new Node(data);
            else root.insert(data);
        }
        Node getRandomNode(){
            if (root == null) return null;
            Random random = new Random();
            int i = random.nextInt(size());
            return root.getIthNode(i);
        }

        /**
         * 트리에서 주어진값을 합상으로가지는 경로의 개수찾기
         */
        int countPathsWithSum(int targetSum){
            return countPathsWithSum(root, targetSum);
        }
        int countPathsWithSum(Node root, int targetSum){
            if (root == null) return 0;
            int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);
            int pathsOnLeft = countPathsWithSum(root.left, targetSum);
            int pathsOnRight = countPathsWithSum(root.right, targetSum);
            return pathsFromRoot + pathsOnLeft + pathsOnRight;
        }
        int countPathsWithSumFromNode(Node node, int targetSum, int currSum){
            if (node == null) return 0;
            currSum += node.data;
            int totalPaths = 0;
            if (currSum == targetSum){
                totalPaths++;
            }
            totalPaths += countPathsWithSumFromNode(node.left, targetSum, currSum);
            totalPaths += countPathsWithSumFromNode(node.right, targetSum, currSum);
            return totalPaths;
        }
        int countPathsWithSum2(int targetSum){
            ArrayList<Integer> array = new ArrayList<Integer>();
            return countPathsWithSum2(root, targetSum, array);
        }
        int countPathsWithSum2(Node root, int targetSum, ArrayList<Integer> array){
            if (root == null) return 0;
            int totalPaths = 0;
            addValue(array, root.data);
            totalPaths = countPaths(array, targetSum);
            totalPaths += countPathsWithSum2(root.left, targetSum, array);
            totalPaths += countPathsWithSum2(root.right, targetSum, array);
            removeLast(array);
            return totalPaths;
        }
        void addValue(ArrayList<Integer> array, int value){
            for (int i = 0; i < array.size(); i++){
                array.set(i, array.get(i) + value);
            }
            array.add(value);
        }
        void removeLast(ArrayList<Integer> array){
            int value = array.remove(array.size() - 1);
            for (int i = 0; i < array.size(); i++){
                array.set(i, array.get(i) - value);
            }
        }
        int countPaths(ArrayList<Integer> array, int targetSum){
            int totalPaths = 0;
            for (int i = 0; i < array.size(); i++){
                if (array.get(i) == targetSum) totalPaths++;
            }
            return totalPaths;
        }
        int countPathsWithSum3(int targetSum){
            HashMap<Integer, Integer> hashTable = new HashMap<Integer, Integer>();
            hashTable.put(0, 1);
            return countPathsWithSum3(root, targetSum, 0, hashTable);
        }
        int countPathsWithSum3(Node node, int targetSum, int currSum, HashMap<Integer, Integer> hashTable){
            if (node == null) return 0;

            currSum += node.data;
            int sum = currSum - targetSum;
            int totalPaths = hashTable.getOrDefault(sum, 0);
            incrementHashTable(hashTable, currSum, 1);
            totalPaths += countPathsWithSum3(node.left, targetSum, currSum, hashTable);
            totalPaths += countPathsWithSum3(node.right, targetSum, currSum, hashTable);
            incrementHashTable(hashTable, currSum, -1);
            return totalPaths;
        }
        void incrementHashTable(HashMap<Integer, Integer> hashTable, int key, int val){
            int newCount = hashTable.getOrDefault(key, 0) + val;
            if (newCount == 0){
                hashTable.remove(key);
            } else {
                hashTable.put(key, newCount);
            }
        }

        /**
         * 순회결과로 원본트리 재현하기
         */
        static int pIndex = 0;
        public void buildTreeByInPre(int[] in, int[] pre){
            pIndex = 0;
            root = buildTreeByInPre(in, pre, 0, in.length - 1);
        }
        private Node buildTreeByInPre(int[] in, int[] pre, int start, int end){
            if (start > end) return null;
            Node node = new Node(pre[pIndex++]);
            if (start == end) return node;
            int mid = search(in, start, end, node.data);
            node.left = buildTreeByInPre(in, pre, start, mid - 1);
            node.right = buildTreeByInPre(in, pre, mid + 1, end);
            return node;
        }
        public void buildTreeByInPost(int[] in, int[] post){
            pIndex = post.length - 1;
            root = buildTreeByInPost(in, post, 0, in.length - 1);
        }
        private Node buildTreeByInPost(int[] in, int[] post, int start, int end){
            if (start > end) return null;
            Node node = new Node(post[pIndex--]);
            if (start == end) return node;
            int mid = search(in, start, end, node.data);
            node.right = buildTreeByInPost(in, post, mid + 1, end);
            node.left = buildTreeByInPost(in, post, start, mid - 1);
            return node;
        }
        private int search(int[] arr, int start, int end, int value){
            int i;
            for (i = start; i <= end; i++){
                if (arr[i] == value) return i;
            }
            return i;
        }
        void printInorder(Node node){
            if (node == null) return;
            printInorder(node.left);
            System.out.print(node.data + " ");
            printInorder(node.right);
        }

        /**
         * BST insertion/deletion
         */
        public Node search(Node root, int key){
            if (root == null || root.data == key) return root;
            if (root.data > key) return search(root.left, key);
            return search(root.right, key);
        }
        public void insert2(int data){
            root = insert(root, data);
        }
        private Node insert(Node root, int data){
            if (root == null) {
                root = new Node(data);
                return root;
            }
            if (data < root.data){
                root.left = insert(root.left, data);
            } else if (data > root.data){
                root.right = insert(root.right, data);
            }
            return root;
        }
        public void delete(int data){
            root = delete(root, data);
        }
        private Node delete(Node root, int data){
            if (root == null) return root;
            if (data < root.data){
                root.left = delete(root.left, data);
            } else if (data > root.data){
                root.right = delete(root.right, data);
            } else {
                if (root.left == null && root.right == null) {
                    return null;
                } else if (root.left == null){
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }
                root.data = findMin(root.right);
                root.right = delete(root.right, root.data);
            }
            return root;
        }
        int findMin(Node root){
            int min = root.data;
            while (root.left != null){
                min = root.left.data;
                root = root.left;
            }
            return min;
        }
        public void inorder2(){
            inorder2(root);
            System.out.println("");
        }
        private void inorder2(Node root){
            if (root != null){
                inorder2(root.left);
                System.out.print(root.data + " ");
                inorder2(root.right);
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

        /*
                    (2)
                   /   \
                 /      \
               (0)      (3)
                 \        \
                 (1)      (4)

           20134
           20314
           20341
           23014
           23041
           23401
        */
        Tree t5 = new Tree(6);
        ArrayList<LinkedList<Integer>> result = allSequences(t5.root);
        for (LinkedList<Integer> l : result){
            for (int data : l){
                System.out.print(data);
            }
            System.out.println();
        }

        Tree t6 = new Tree();
        Tree t7 = new Tree();
        boolean result2;
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
        t6.root = t6.makeBST(0,9);
        t7.root = t7.makeBST(5,9);
        result2 = t6.containsTree(t6.root, t7.root);
        System.out.println(result2);

        /*
            (8)
           /   \
         (7)   (9)
         */
        t7.root = t7.makeBST(7,9);
        result2 = t6.containsTree(t6.root, t7.root);
        System.out.println(result2);

        /*
                    (4)
                  /     \
                /        \
              /           \
            (0)           (5)
               \            \
               (1)          (7)
                 \         /  \
                  (2)    (6)  (8)
                    \           \
                    (3)         (9)

         */
        Tree t8 = new Tree();
        t8.insert(4);
        t8.insert(0);
        t8.insert(1);
        t8.insert(2);
        t8.insert(5);
        t8.insert(7);
        t8.insert(8);
        t8.insert(3);
        t8.insert(6);
        t8.insert(9);
        System.out.println(t8.getRandomNode().data);

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
        Tree t9 = new Tree(10);
        System.out.println(t9.countPathsWithSum(3));
        System.out.println(t9.countPathsWithSum2(5));
        System.out.println(t9.countPathsWithSum3(3));

        Tree t10 = new Tree();
        int[] pre = {4,2,1,3,6,5,7};
        int[] in = {1,2,3,4,5,6,7};
        int[] post = {1,3,2,5,7,6,4};
        t10.buildTreeByInPre(in, pre);
        t10.printInorder(t10.root);
        System.out.println(" ");
        t10.buildTreeByInPost(in, post);
        t10.printInorder(t10.root);

        /*
                 4
              /    \
             2       6
           /  \    /   \
          1    3  5     7

         */
        Tree t11 = new Tree();
        t11.insert2(4);
        t11.insert2(2);
        t11.insert2(1);
        t11.insert2(3);
        t11.insert2(6);
        t11.insert2(5);
        t11.insert2(7);

        System.out.println("");
        t11.inorder2();
        t11.delete(5);
        t11.delete(6);
        t11.delete(2);
        t11.inorder2();
    }
}
