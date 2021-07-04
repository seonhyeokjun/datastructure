package com.study.datastructure.linkedList;

import java.util.EmptyStackException;
import java.util.Stack;

public class stack {
    static class Stack<T>{
        class Node<T>{
            private T data;
            private Node<T> next;

            public Node(T data){
                this.data = data;
            }
        }

        private Node<T> top;

        public T pop(){
            if (top == null){
                throw new EmptyStackException();
            }

            T item = top.data;
            top = top.next;
            return item;
        }

        public void push(T item){
            Node<T> t = new Node<T>(item);
            t.next = top;
            top = t;
        }

        public T peek(){
            if (top == null){
                throw new EmptyStackException();
            }
            return  top.data;
        }

        public boolean isEmpty(){
            return top == null;
        }
    }

    /**
     * 스택 정렬하기
     * @param s1
     */
    private static void sort(Stack<Integer> s1){
        Stack<Integer> s2 = new Stack<Integer>();
        while (!s1.isEmpty()){
            int tmp = s1.pop();
            while (!s2.isEmpty() && s2.peek() > tmp){
                s1.push(s2.pop());
            }
            s2.push(tmp);
        }

        while (!s2.isEmpty()){
            s1.push(s2.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        s.push(3);
        s.push(5);
        s.push(1);
        s.push(6);
        sort(s);
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());

//        System.out.println(s.pop());
//        System.out.println(s.pop());
//        System.out.println(s.peek());
//        System.out.println(s.pop());
//        System.out.println(s.isEmpty());
//        System.out.println(s.pop());
//        System.out.println(s.isEmpty());
    }
}
