package DataStructures;


public class Stack {
    int capacity = 200;
    public static int top;
    String stackArray[];

    public Stack() {
        top = -1;
        stackArray = new String[capacity];
    }

    public void PUSH(String push) {
        if(top >= capacity-1) {
            System.out.println("Stack is overflow");
        }
        else {
            top++;
            stackArray[top] = push;
        }
    }

    public String POP(int pos) {
        return stackArray[pos];
    }

    public int isEmpty() {
        if(top == -1) {
            return -1;
        }
        return 0;
    }

}
