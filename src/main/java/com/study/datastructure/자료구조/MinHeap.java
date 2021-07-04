package com.study.datastructure.자료구조;

import java.util.Arrays;

public class MinHeap {
    private int[] Heap;
    private int size;
    private int maxsize;

    public MinHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize];
    }

    // Function to return the position of
    // the parent for the node currently
    // at pos
    private int parent(int pos)
    {
        return (pos - 1) / 2;
    }

    // Function to return the position of the
    // left child for the node currently at pos
    private int leftChild(int pos)
    {
        return (pos * 2) + 1;
    }

    // Function to return the position of
    // the right child for the node currently
    // at pos
    private int rightChild(int pos)
    {
        return (pos * 2) + 2;
    }

    // Function that returns true if the passed
    // node is a leaf node
    private boolean isLeaf(int pos) {
        if (pos >= size/2 && pos <= size) {
            return true;
        }
        return false;
    }

    // Function to swap two nodes of the heap
    private void swap(int fpos, int spos)
    {
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    // Function to heapify the node at pos
    private void  minHeapify(int pos)
    {
        // If the node is a non-leaf node and greater
        // than any of its child
        if (!isLeaf(pos)) {
            if (Heap[pos] > Heap[leftChild(pos)]
                    || Heap[pos] > Heap[rightChild(pos)]) {

                // Swap with the left child and heapify
                // the left child
                if (Heap[leftChild(pos)] < Heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }
                // Swap with the right child and heapify
                // the right child
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }

    // Function to insert a node into the heap
    public void insert(int element) {
        if (size >= maxsize) {
            return;
        }
        Heap[size] = element;
        int current = size;

        while (Heap[current] < Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }

    // Function to print the contents of the heap
    public void print() {
        for (int i = 0; i < (size / 2); i++) {
            System.out.print(" PARENT : " + Heap[i]
                    + " LEFT CHILD : " + Heap[2 * i + 1]
                    + " RIGHT CHILD :" + Heap[2 * i + 2]);
            System.out.println();
        }
    }

    // Function to remove and return the minimum
    // element from the heap
    public int remove() {
        int popped = Heap[0];
        Heap[0] = Heap[--size];
        minHeapify(0);
        Heap[size] = 0;
        return popped;
    }

    // Driver code
    public static void main(String[] arg) {
        System.out.println("The Min Heap is ");
        MinHeap minHeap = new MinHeap(15);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);

        minHeap.print();
        System.out.println("\nThe Min Heap is :"+ Arrays.toString(minHeap.Heap));
        System.out.println("The Min val is " + minHeap.remove());
        minHeap.print();
        System.out.println("\nThe Min Heap is :"+ Arrays.toString(minHeap.Heap));
        System.out.println("The Min val is " + minHeap.remove());
        minHeap.print();
        System.out.println("\nThe Min Heap is :"+ Arrays.toString(minHeap.Heap));
        System.out.println("The Min val is " + minHeap.remove());
        minHeap.print();
        System.out.println("\nThe Min Heap is :"+ Arrays.toString(minHeap.Heap));
        System.out.println("The Min val is " + minHeap.remove());
        minHeap.print();
        System.out.println("\nThe Min Heap is :"+ Arrays.toString(minHeap.Heap));
        System.out.println("The Min val is " + minHeap.remove());
        minHeap.print();
        System.out.println("\nThe Min Heap is :"+ Arrays.toString(minHeap.Heap));
        System.out.println("The Min val is " + minHeap.remove());
        minHeap.print();
        System.out.println("\nThe Min Heap is :"+ Arrays.toString(minHeap.Heap));
        System.out.println("The Min val is " + minHeap.remove());
        minHeap.print();
        System.out.println("\nThe Min Heap is :"+ Arrays.toString(minHeap.Heap));
        System.out.println("The Min val is " + minHeap.remove());
        minHeap.print();
        System.out.println("\nThe Min Heap is :"+ Arrays.toString(minHeap.Heap));
        System.out.println("The Min val is " + minHeap.remove());
        minHeap.print();
    }
}
