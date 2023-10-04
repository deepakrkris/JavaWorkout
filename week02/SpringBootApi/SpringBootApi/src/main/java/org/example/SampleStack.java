package org.example;

class Stack {
    int[] arr = null;
    int curr;

    public Stack(int size) {
        this.arr = new int[size];
        curr = 0;
    }

    public void push(int val) {
        arr[curr] = val;
        curr++;
    }

    public int pop() {
        curr--;
        return arr[curr];
    }
}

public class SampleStack {
    public static void main(String[] args) {
        Stack s = new Stack(10);
        s.push(2);
        s.push(4);
        s.push(1);

        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
    }
}
