package DataStructures;

import SignUp.*;

public class SinglyLinkedList {
    public class Node {
        CustomerDetails data;
        Node next;

        Node(CustomerDetails data) {
            this.data = data;
            next = null;
        }
    }

    Node first = null;

    public void insertFirst(CustomerDetails data) {
        Node n = new Node(data);
        if(first == null) {
            first = n;
        }
        else {
            n.next = first;
            first = n;
        }
    }

    public void insertLast(CustomerDetails data) {
        if(first == null) {
            insertFirst(data);
        }
        else {
            Node n = new Node(data);
            Node current = first;
            while(current.next != null) {
                current = current.next;
            }
            current.next = n;
        }
    }

    public CustomerDetails getFirst() {
        if(first == null) {
            return null;
        }
        return first.data;
    }

}