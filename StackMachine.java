import java.util.Deque;
import java.util.ArrayDeque;

class StackMachine {
    Deque<Integer> stack;

    public StackMachine () {
        stack = new ArrayDeque<Integer>();
    }

    public void push(int a) {
        this.stack.push(a);
    }

    public void add() {
        int b = this.stack.poll();
        int a = this.stack.poll();
        this.stack.push(a+b);
    }

    public void mul() {
        int b = this.stack.poll();
        int a = this.stack.poll();
        this.stack.push(a*b);
    }

    public void sub() {
        int b = this.stack.poll();
        int a = this.stack.poll();
        this.stack.push(a-b);
    }

    public void div() {
        int b = this.stack.poll();
        int a = this.stack.poll();
        this.stack.push(a/b);
    }

    public void wrt() {
        int a = this.stack.poll();
        System.out.println(a);
    }
}