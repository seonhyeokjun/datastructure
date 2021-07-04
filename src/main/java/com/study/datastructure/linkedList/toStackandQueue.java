package com.study.datastructure.linkedList;

import java.util.Stack;

/**
 * 두개의 Stack으로 Queue 구현하기
 */
public class toStackandQueue {
    static class MyQueue<T>{
        Stack<T> stackNewStack, stackOldStack;

        MyQueue(){
            stackNewStack = new Stack<T>();
            stackOldStack = new Stack<T>();
        }
        public int size(){
            return stackNewStack.size() + stackOldStack.size();
        }
        public void add(T value){
            stackNewStack.push(value);
        }
        private void shiftStacks(){
            if (stackOldStack.isEmpty()){
                while (!stackNewStack.isEmpty()){
                    stackOldStack.push(stackNewStack.pop());
                }
            }
        }
        public T peek(){
            shiftStacks();
            return stackOldStack.peek();
        }
        public T remove(){
            shiftStacks();
            return stackOldStack.pop();
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> q = new MyQueue<Integer>();
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
    }
}
