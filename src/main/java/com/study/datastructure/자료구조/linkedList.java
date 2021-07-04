package com.study.datastructure.자료구조;

public class linkedList {
    Node header;

    static class Node {
        int data;
        Node next = null;

        public Node(int data) {
            this.data = data;
        }

        public Node() {

        }

        /**
         * 길이를 잘라주는 함수
         * @param index
         * @return
         */
        public Node get(int index) {
            Node header = this;
            for (int i = 0; i < index; i++){
                header = header.next;
            }
            return header;
        }

        /**
         * 연결 리스트에 값을 더함
         * @param d
         * @return
         */
        public Node addNext(int d) {
            Node end = new Node();
            end.data = d;
            Node header = this;
            while (header.next != null){
                header = header.next;
            }
            header.next = end;
            return header;
        }

        /**
         * 두 연결리스트를 연결
         * @param node
         * @return
         */
        public Node addNext(Node node) {
            Node header = this;
            while (header.next != null){
                header = header.next;
            }
            header.next = node.next;
            return header;
        }

        /**
         * 연결리스트를 출력
         */
        public void print() {
            Node header = this;
            while (header.next != null){
                System.out.print(header.data + "->");
                header = header.next;
            }
            System.out.println(header.data);
        }
    }

    linkedList(){
        header = new Node();
    }

    /**
     * 해당 인덱스 값 반환
     */
    public Node get(int index){
        Node n = header;
        for (int i = 0; i < index; i++){
            n = n.next;
        }
        return n;
    }

    /**
     * 연결 리스트 값 추가
     */
    void append(int d){
        Node end = new Node();
        end.data = d;
        Node n = header;
        while (n.next != null){
            n = n.next;
        }
        n.next = end;
    }

    /**
     * 연결 리스트 특정 값 삭제
     */
    void delete(int d){
        Node n = header;
        while (n.next != null){
            if (n.next.data == d){
                n.next = n.next.next;
            } else {
                n = n.next;
            }
        }
    }

    /**
     * 연결 리스트 출력
     */
    void retrieve(){
        Node n = header.next;
        while (n.next != null){
            System.out.print(n.data + "->");
            n = n.next;
        }
        System.out.println(n.data);
    }

    /**
     * 연결 리스트 중복값 제거
     */
    void removeDups() {
        Node n = header;
        while (n != null && n.next != null) {
            Node r = n;
            while (r.next != null) {
                if (n.data == r.next.data) {
                    r.next = r.next.next;
                } else {
                    r = r.next;
                }
            }
            n = n.next;
        }
    }

    /**
     *  단 방향 Linked List 뒤부터 세기
     */
    private static Node KthToLast(Node first, int k){
        Node n = first;
        int total = 1;
        while (n.next != null){
            total++;
            n = n.next;
        }
        n = first;
        for (int i = 1; i < total - k + 1; i++){
            n = n.next;
        }
        return n;
    }

    /**
     * 재귀호출을 통한 단 방향 연결 리스트 뒤부터 세기
     */
    static class Reference{
        public int count = 0;
    }
    private static Node KthToLast2(Node n, int k, Reference r){
        if (n == null){
            return null;
            // return 0;
        }

        // int count = KthToLast2(n.next, k) + 1;
        Node found = KthToLast2(n.next, k, r);
        r.count++;
        if (r.count == k) {
            // System.out.println(k + "th to last node is " + n.data);
            return n;
        }
        return found;
    }

    /**
     * 포인터를 이용한 단 방향 연결 리스트 뒤부터 세기
     */
    private static Node KthToLast3(Node first, int k){
        Node p1 = first;
        Node p2 = first;

        for (int i = 0; i < k; i++){
            if (p1 == null) return null;
            p1 = p1.next;
        }

        while (p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }

    /**
     * 중간 노드 삭제 (첫번째, 마지막 노드는 삭제 불가능)
     */
    private static boolean deleteNode(Node n){
        if (n == null || n.next == null){
            return false;
        }
        Node next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }

    /**
     * Linked List Digit합산 알고리즘
     */
    private static Node sumLists(Node l1, Node l2, int carry){
        if (l1 == null && l2 == null && carry == 0){
            return null;
        }

        Node result = new Node();
        int value = carry;

        if (l1 != null){
            value += l1.data;
        }
        if (l2 != null){
            value += l2.data;
        }
        result.data = value % 10;

        if (l1 != null || l2 != null){
            Node next = sumLists(l1 == null? null : l1.next, l2 == null? null : l2.next, value >= 10? 1 : 0);
            result.next = next;
        }
        return result;
    }

    static class Storage{
        int carry = 0;
        Node result = null;
    }

    private static Node sumLists2(Node l1, Node l2){
        int len1 = getListLength(l1);
        int len2 = getListLength(l2);

        if (len1 < len2){
            l1 = LpadList(l1, len2 - len1);
        } else {
            l2 = LpadList(l2, len1 - len2);
        }

        Storage storage = addLists(l1, l2);
        if (storage.carry != 0){
            storage.result = insertBefore(storage.result, storage.carry);
        }
        return storage.result;
    }

    /**
     * 두 연결 리스트 더하기
     */
    private static Storage addLists(Node l1, Node l2) {
        if (l1 == null && l2 == null){
            Storage storage = new Storage();
            return storage;
        }
        Storage stroage = addLists(l1.next, l2.next);
        int value = stroage.carry + l1.data + l2.data;
        int data = value % 10;
        stroage.result = insertBefore(stroage.result, data);
        stroage.carry = value / 10;
        return stroage;
    }

    /**
     * 연결리스트 길이
     */
    private static int getListLength(Node l){
        int total = 0;
        while (l != null){
            total++;
            l = l.next;
        }
        return total;
    }

    /**
     * 앞에 노드를 채워주는 함수
     */
    private static Node insertBefore(Node node, int data){
        Node before = new Node(data);
        if (node != null){
            before.next = node;
        }
        return before;
    }

    /**
     * 얼마나 채워야 하는지 알아내는 함수
     */
    private static Node LpadList(Node l, int length){
        Node head = l;
        for (int i = 0; i < length; i++){
            head = insertBefore(head, 0);
        }
        return head;
    }

    private static Node getIntersection (Node l1, Node l2){
        //지난번에 만든 LinkedList의 길이를 반환하는 함수를 호출합니다.
        int len1 = getListLength(l1);
        int len2 = getListLength(l2);

        //길이가 맞게끔 잘라주는 작업을 합니다.
        if(len1 > len2){
            l1 = l1.get(len1-len2);
            //이제 자른 후 첫 번째 노드를 변경합니다.
        } else if(len2 > len1){
            l2 = l2.get(len2-len1);
        }
        //여기 이후 길이가 맞춰졌으니, 비교를 해주면 됩니다.
        while(l1 != null && l2 != null){ //두 리스트 모두 마지막 노드까지 돌립니다.
            if(l1 == l2){
                return l1; //같은 노드를 만나면 둘 중 아무 노드나 반환합니다.
            }
            //아직 찾지 못했을 경우, 다음 노드로 넘어갑니다.
            l1 = l1.next;
            l2 = l2.next;
        }
        return null; //Loop가 다 돌 때까지 못찾으면
    }

    /**
     * 루프 찾는 함수
     * @param head
     * @return
     */
    private static Node findLoop(Node head){
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow){
                break;
            }
        }

        if (fast == null || slow == null){
            return null;
        }

        slow = head;

        while (fast != slow){
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    public static void main(String[] args) {
        linkedList ll = new linkedList();
        ll.append(1);
        ll.append(2);
        ll.append(3);
        ll.append(4);
        ll.retrieve();

        deleteNode(ll.get(2));
        ll.retrieve();

        linkedList l1 = new linkedList();
        l1.append(9);
        l1.append(1);
        l1.retrieve();

        linkedList l2 = new linkedList();
        l2.append(1);
        l2.append(1);
        l2.retrieve();

        // Node n = sumLists(l1.get(1), l2.get(1), 0);
        Node n = sumLists2(l1.get(1), l2.get(1));
        while (n.next != null){
            System.out.print(n.data + "->");
            n = n.next;
        }
        System.out.println(n.data);

        int k = 3;
        Node kth = KthToLast(ll.header, k);
        System.out.println("Last k(" + k + ")th data is " + kth.data);
        Reference r = new Reference();
        Node found = KthToLast2(ll.header, k, r);
        System.out.println(found.data);
        Node found2 = KthToLast3(ll.header, k);
        System.out.println(found2.data);

        Node n1 = new Node(5);
        Node n2 = n1.addNext(7);
        Node n3 = n2.addNext(9);
        Node n4 = n3.addNext(10);
        Node n5 = n4.addNext(7);
        Node n6 = n5.addNext(6);

        Node m1 = new Node(6);
        Node m2 = m1.addNext(8);
//        Node m3 = m2.addNext(3);
//        Node m4 = m3.addNext(4);
//        Node m5 = m4.addNext(5);
//        Node m6 = m5.addNext(6);
        Node m3 = m2.addNext(n4);

        n1.print();
        m1.print();

        Node m = getIntersection(n1, m1);

        if (m != null){
            System.out.println("Interserction: " + m.data);
        } else {
            System.out.println("Not found");
        }

        Node t1 = new Node(1);
        Node t2 = t1.addNext(2);
        Node t3 = t2.addNext(3);
        Node t4 = t3.addNext(4);
        Node t5 = t4.addNext(5);
        Node t6 = t5.addNext(6);
        Node t7 = t6.addNext(7);
        Node t8 = t7.addNext(8);
        t8.addNext(t2);

        Node t = findLoop(t1);

        if (t != null){
            System.out.println("Start of loop: " + t.data);
        } else {
            System.out.println("Loop not found");
        }
    }
}