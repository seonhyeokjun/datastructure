package com.study.datastructure.자료구조;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class queue {
    static class Queue<T>{
        class Node<T>{
            private T data;
            private Node<T> next;

            public Node(T data){
                this.data = data;
            }
        }

        private Node<T> first;
        private Node<T> last;

        public void add(T item){
            Node<T> t = new Node<T>(item);

            if (last != null){
                last.next = t;
            }
            last = t;
            if (first == null){
                first = last;
            }
        }

        public T remove(){
            if (first == null){
                throw new NoSuchElementException();
            }

            T data = first.data;
            first = first.next;

            if (first == null){
                last = null;
            }
            return data;
        }

        public T peek(){
            if (first == null){
                throw new NoSuchElementException();
            }
            return first.data;
        }

        public boolean isEmpty(){
            return first == null;
        }
    }

    enum AnimalType{
        DOG, CAT
    }

    /**
     * LinkenList로 Queue 구현하기
     * 동물로 구현
     */
    abstract static class Animal{
        AnimalType type;
        String name;
        int order;
        Animal (AnimalType type, String name){
            this.type = type;
            this.name = name;
        }

        void setOrder(int order){
            this.order = order;
        }
        int getOrder(){
            return order;
        }
        String info(){
            return order + ") type: " + type + ", name: " + name;
        }
    }

    static class Dog extends Animal{

        Dog(String name) {
            super(AnimalType.DOG, name);
        }
    }
    static class Cat extends Animal{

        Cat(String name) {
            super(AnimalType.CAT, name);
        }
    }

    static class AnimalShelter{
        LinkedList<Dog> dogs = new LinkedList<Dog>();
        LinkedList<Cat> cats = new LinkedList<Cat>();
        int order;

        /**
         * 동물 번호 부여
         */
        AnimalShelter(){
            order = 1;
        }
        void enqueue(Animal animal){
            animal.setOrder(order);
            order++;
            if (animal.type == AnimalType.DOG){
                dogs.addLast((Dog) animal);
            } else if (animal.type == AnimalType.CAT){
                cats.addLast((Cat) animal);
            }
        }
        Animal dequeueDog(){
            return dogs.poll();
        }
        Animal dequeueCat(){
            return cats.poll();
        }
        Animal dequeue(){
            if (dogs.size() == 0 && cats.size() == 0){
                return null;
            } else if (dogs.size() == 0){
                return cats.poll();
            } else if (cats.size() == 0){
                return dogs.poll();
            }

            Animal dog = dogs.peek();
            Animal cat = cats.peek();
            if (cat.order < dog.order){
                return cats.poll();
            } else {
                return dogs.poll();
            }
        }
    }

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<Integer>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.peek());
        System.out.println(q.remove());
        System.out.println(q.isEmpty());
        System.out.println(q.remove());
        System.out.println(q.isEmpty());

        Dog d1 = new Dog("puppy");
        Dog d2 = new Dog("chichi");
        Dog d3 = new Dog("choco");
        Cat c1 = new Cat("shasha");
        Cat c2 = new Cat("miumiu");
        Cat c3 = new Cat("gaga");

        AnimalShelter animalShelter = new AnimalShelter();
        animalShelter.enqueue(d1);
        animalShelter.enqueue(c1);
        animalShelter.enqueue(d2);
        animalShelter.enqueue(c2);
        animalShelter.enqueue(d3);
        animalShelter.enqueue(c3);

        System.out.println(animalShelter.dequeueCat().info());
        System.out.println(animalShelter.dequeueDog().info());
        System.out.println(animalShelter.dequeue().info());
        System.out.println(animalShelter.dequeue().info());
        System.out.println(animalShelter.dequeue().info());
        System.out.println(animalShelter.dequeue().info());
    }
}
