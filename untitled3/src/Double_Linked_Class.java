import java.util.Scanner;
interface Container{
    public int size();
    public void clear();
    public boolean empty();
    public void delete_index(int pos);
}

class Node{
    public int data;
    public Node next, prev;
}

interface Iterator{
    public void insert();
    public boolean hasNext();
    public Node newIterator();

    public int find(int elem);
}

interface AbstractList{
    public void push_back(int elem);
    public void push_front(int elem);
    public void pop_back();
    public void pop_front();
    public void print();
}

class List implements Container, Iterator, AbstractList{
    private int count;
    private Node first;
    private Node last;
    private Node iter;
    public List(){
        first = last = null;
        iter = null;
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean empty() {
        if(first == null && last == null){
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public void delete_index(int pos){
        if(empty()){
            return;
        }
        if(pos == 0){
            System.out.println("enter the right position\n");
            Scanner in = new Scanner(System.in);
            System.out.print(" ");
            pos = in.nextInt();
        }
        if(pos < 1 || pos > count){
            return;
        }
        int i = 1;
        Node tmp = first;
        while(i < pos){
            tmp = tmp.next;
            i++;
        }
        Node prevDel = tmp.prev;
        Node afterDel = tmp.next;
        if(prevDel != null && count != 1){
            prevDel.next = afterDel;
        }
        if(afterDel != null && count != 1){
            afterDel.prev = prevDel;
        }
        if(pos == 1){
            first = afterDel;
        }
        if( pos == count){
            last = prevDel;
        }
        tmp = null;
        count = count -1;
    }
    @Override
    public void clear(){
        while(count != 0){
            delete_index(1);
        }
    }
    @Override
    public Node newIterator(){
        if(empty()){
            return null;
        }
        iter = first;
        return iter;
    }

    @Override
    public void insert() {
        if(first == null){
            int a;
            System.out.println("input elem\n");
            Scanner in = new Scanner(System.in);
            a = in.nextInt();
            push_front(a);
        }
        else if(last == null){
            int a;
            System.out.println("input elem\n");
            Scanner in = new Scanner(System.in);
            a = in.nextInt();
            push_back(a);
        }
        else{
            Node previter = iter.prev;
            Node tmp = new Node();
            System.out.println("input new elem\n");
            Scanner in = new Scanner(System.in);
            tmp.data = in.nextInt();
            if(previter != null & count != 0){
                previter.next = tmp;
            }
            tmp.next = iter;
            tmp.prev = previter;
            iter.prev = tmp;
            count++;
        }
    }

    @Override
    public boolean hasNext(){
        if(empty()){
            return false;
        }
        else{
            if(iter.next != null){
                return true;
            }
            else{
                return false;
            }
        }
    }
    @Override
    public int find(int elem) {
        if (empty()) {
            return 0;
        }
        int i = 0;
        Node tmp_iter = iter;
        while (i < count) {
            if (iter.data == elem) {
                return iter.data;
            } else {
                iter = iter.next;
                i++;
            }
        }
        iter = tmp_iter;
        return 0;
    }

    @Override
    public void push_back(int elem){
        Node tmp = new Node();
        tmp.next = null;
        tmp.prev =last;
        tmp.data = elem;
        if(!empty()){
            last.next = tmp;
        }
        if(count == 0){
            first = last = tmp;
        }
        else{
            last = tmp;
        }
        count++;
    }
    @Override
    public void push_front(int elem) {
        Node tmp = new Node();
        tmp.prev = null;
        tmp.next = first;
        tmp.data = elem;
        if (!empty()) {
            first.prev = tmp;
        }
        if (count == 0) {
            first = last = tmp;
        } else {
            first = tmp;
        }
        count++;
    }
    @Override
    public void pop_front(){
        if(empty()){
            return;
        }
        else{
            Node tmp = first;
            first = first.next;
            first.prev = null;
            tmp = null;
            count--;
        }
    }
    @Override
    public void pop_back(){
        if(empty()){
            return;
        }
        else{
            Node tmp = last;
            last = last.prev;
            last.next = null;
            tmp = null;
            count--;
        }
    }
    @Override
    public void print(){
        if(count != 0){
            Node tmp = first;
            while(tmp.next != null){
                System.out.println(tmp.data + " ");
                tmp = tmp.next;
            }
            System.out.println(tmp.data + "\n");
        }
    }

}




public class Double_Linked_Class {
    public static void main(String[] args) {
        List a = new List();
        a.push_back(1);
        a.push_back(2);
        a.push_back(3);
        a.clear();
        a.push_back(1);
        a.push_back(2);
        a.push_back(3);
        a.newIterator();
        System.out.println(a.find(2)+"\n");
        a.insert();
        a.pop_back();
        a.pop_front();
        a.print();
    }
}
