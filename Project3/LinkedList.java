//written by NGUY3969 and LEI00007
public class LinkedList<T extends Comparable<T>> implements List<T> {
    private Node head;
    private int length = 0;
    private boolean isSorted = true;
    public static void main(String[] args){  }

    public void LinkedLists(){//intializes the mode
        this.head = null;
    } //done

    public boolean add(T element) {
        if (element == null){//false if element is null
            return false;
        }
        if (head == null){ //if the list is empty
            head = new Node(element, null);
        } else{ //if there's elements
            if (head != null) {//if the list isn't empty
                Node runner = head;
                while (runner.getNext() != null) {//goes until end of list
                    runner = runner.getNext();
                }
                runner.setNext(new Node<>(element));
            }else{
                head = new Node<>(element);
            }
        }
        length++;
        isSorted = false;
        return true;
    }

    public boolean add(int index, T element) { //fixing
        if(index < 0 || index>= length || element == null) {//checks element if null and index if out of bounds
            return false;
        }
        if (index == 0){//case for if element wants to be added at first index
            Node<T> newHead = this.head;
            this.head = new Node<>(element, newHead);
            this.isSorted = false;
        }
        else{//case if other than first index
            Node<T> cur = this.head;
            Node<T> prev = cur; // variable used trails head
            int i = 0;
            while(i != index){
                prev = cur;
                cur = cur.getNext();
                i++;
            }
            prev.setNext(new Node<>(element, cur));//sets elements after one that trails
            this.isSorted = false;
        }
        length++;
        isSorted = false;
        return true;
    }

    public void clear() {
        this.head = null;//clears the node
        this.isSorted = true;
    }

    public boolean contains(T element) {
        if (head != null) { //if head is not null
            if (this.head.getData().equals(element)) {//if the head is the element
                return true;
            } else {//if element isn't the head
                Node <T> runner = head;
                while (runner != null) {
                    if (runner.getData().equals(element)) {
                        return true;
                    } else {
                        runner = runner.getNext();
                    }
                }
            }
        }
        return false; //if head is null
    }
    public T get(int index) {
        if (index < 0 || index >= length) {//checks bounds of index input
            return null;
        }
        if(this.head == null){//if the list is empty
            return null;
        }
        int count = 0;
        Node runner = head;
        while (count != index) {//indexes through the list
            runner = runner.getNext();
            count++;
        }
        return (T) runner.getData();
    }

    public int indexOf(T element) {
        int index = 0;
        Node <T> runner = head;
        if (runner == null) {//if the list is empty
            return -1;
        }else {
            while (runner != null && !(runner.getData().equals(element))) {//if list isn't empty and when the runner isn't the element
                runner = runner.getNext();
                index++;//updates the index until at element
                if (runner == null){
                    return -1;
                }
            }
        }
        return index;
    }
    public boolean isEmpty() {
        if (this.head == null ){//if head is null then list is empty
            return true;
        } else {
            return false;
        }
    }

    public int lastIndexOf(T element) {
        Node runner = head;
        int index = -1;
        int counter = 0;
        if (isSorted == true) {
            while (runner != null) {//goes until end of list
                if (runner.getData().equals(element)) {//updates the counter when element is found
                    index = counter;
                }
                runner = runner.getNext();
                counter++;
            }
        } else {
            while (runner != null) {//goes until end of list
                if (runner.getData().equals(element)) {//updates the counter when element is found
                    index = counter;
                }
                runner = runner.getNext();
                counter++;
            }
        }
        return index;
    }



    public T set(int index, T element) {
        if(index < 0 || index>= length || element == null) {
            return null;
        } else {
            T temp; //variable used to hold previous element
            Node <T> runner = head;
            int count = 0;
            while (count != index) {
                runner = runner.getNext();
                count++;
            }
            temp = runner.getData(); //gets data before changing element
            runner.setData(element); //sets new element at index
            return temp;
        }
    }


    public int size() {
        int count = 0;
        Node <T> runner = head;
        while (runner != null){//updates count of list, count used to count elements
            runner = runner.getNext();
            count++;
        }
        return count;
    }


    public void sort(boolean order) {//uses bubble sort
        Node<T> runner = head;
        if(isSorted == true){ return; }
        if (order == true) { //increasing alphabetical order
            for (int i = 0; i < length; i++) {
                runner = head;
                for (int j = 1; j < length; j++) {
                    if (runner.getData().compareTo(runner.getNext().getData()) > 0) { //0 means the one before is greater than the one after
                        T tempData = runner.getData(); //current data
                        runner.setData(runner.getNext().getData());
                        runner.getNext().setData(tempData);
                    }
                    runner = runner.getNext();
                }
            }
        }
        if (order == false) { //decreasing alphabetical order
            for (int i = 0; i < length; i++) {
                runner = head;
                for (int j = 1; j < length; j++) {
                    if (runner.getData().compareTo(runner.getNext().getData()) < 0) { //0 means the one after is greater
                        T tempData = runner.getData();
                        runner.setData(runner.getNext().getData());
                        runner.getNext().setData(tempData);
                    }
                    runner = runner.getNext();
                }
            }
        }
    }

    public boolean remove(T element) {
        Node prev = head;
        Node runner = head;
        if (element == null) {//checks if element is null
            return false;
        } if(prev.getData().equals(element)){ //if the head is the element wanting to be removed
            head = head.getNext();
            length--;
            return true;
        }
        if (head != null) {//if head isn't element wanting to be removed
            while (runner != null) {
                if (runner.getData().equals(element)) {
                    prev.setNext(runner.getNext());
                    length--;
                    return true;
                }
                prev = runner;
                runner = runner.getNext();
            }
            if (prev.getData().equals(element)) {
                length--;
                return true;
            }
        }
        return false;
    }

    public T remove(int index) {
        Node <T> prev = head;
        Node <T> runner = head;
        T temp = null;
        int count = 0;
        if (index < 0 || index >= length) { //checks if index is out of bounds
            return null;
        }
        if (head != null) { //if the list isn't empty
            if(index == 0){ //if index is first element
                temp = (T) head.getData();
                head = head.getNext();
                length--;
                return temp;
            }
            while (runner != null && count != index) { //goes until counter equals index
                prev = runner;
                runner = runner.getNext();
                count++;
            }
            temp = runner.getData();//holder for the runner
            prev.setNext(runner.getNext());//sets one after prev to one after runner
            length--;
        }
        return temp;
    }
    public String toString() {
        Node runner = head;
        int count = 1;
        String string = ""; //string varible
        while (runner != null) {
            string += ("Element" + count + ": " + runner.getData()); //updates string variable
            count++;//goes used to give element number
            runner = runner.getNext();
        }
        return string;
    }

}
