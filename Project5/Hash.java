//WRITTEN BY LEI00007 and NGUY3969
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Hash <T> {
    private int size;
    private NGen<T>[] array;

    public Hash(int size){
        this.size = size;
        array = new NGen[this.size];
    }
    private int hash(T key) { //used to get index
        String string = key.toString();
        int sum = 0;
        for(int i = 0; i<string.length(); i++){
            sum += string.charAt(i);
        }
        int index = sum % array.length;
        return index;
    }
    private int hash2(T key) { //used to get index
        String string = key.toString();
        int sum = 0;
        for(int i = 0; i<2; i++){
            if(string.length() < 2){
                sum += string.charAt(0);
            }
            else if(string.length() >= 2){
                sum += string.charAt(i);
            }
        }
        int index = sum % array.length;
        return index;
    }
    private int hash3(T key) { //used to get index
        String string = key.toString();
        int sum = 0;
        for(int i = 0; i<string.length(); i++){
            sum += string.charAt(i);
        }
        int index = sum % array.length;
        while(array[index] != null){
            if(index == array.length-1){
                index = 0;
            }
            else {
                index++;
            }
        }
        return index;
    }
    private int hash4(T key) { //used to get index
        String string = key.toString();
        int sum = 0;
        int count = 0;
        for(int i = 0; i<string.length(); i++){
            sum += string.charAt(i);
        }
        int index = sum % array.length;
        while(array[index] != null){
            count++;
            array[index] = array[index].getNext();
            if(count>=3){
                index++;
                count = 0;
            }
        }

        return index;
    }


    public void add(T item){
        int index = hash(item); //gets index from hash function
        NGen<T> doubleCheck = array[index];
        while(doubleCheck != null){//checks to see if there are duplicates.
            if(doubleCheck.getData().equals(item)){
                return;
            }
            doubleCheck = doubleCheck.getNext();
        }
        if(array[index] == null){ //if the spot in the array is empty
            array[index] = new NGen(item,null);
        }else{ //not empty
            NGen<T> ptr = array[index];
            while(ptr.getNext() != null){ //traverses the linked list
                ptr = ptr.getNext();
            }
            ptr.setNext(new NGen(item,null)); //sets the next of final element to the item
        }
    }
    public void add2(T item){//for keywords specific hash table
        int index = hash3(item); //gets index from hash3 function
        NGen<T> doubleCheck = array[index];
        while(doubleCheck != null){//checks to see if there are duplicates.
            if(doubleCheck.getData().equals(item)){
                return;
            }
            doubleCheck = doubleCheck.getNext();
        }
        if(array[index] == null){ //if the spot in the array is empty
            array[index] = new NGen(item,null);
        }else{ //not empty
            NGen<T> ptr = array[index];
            while(ptr.getNext() != null){ //traverses the linked list
                ptr = ptr.getNext();
            }
            ptr.setNext(new NGen(item,null)); //sets the next of final element to the item
        }
    }
    public void display(){
        int shortChainLength = 0;
        int longChainLength = 0;
        int longIndex = 0;
        int shortIndex = 0;
        int nullCount = 0;
        for(int i = 0; i<array.length; i++){
            int count = 0;
            int templength = 0;
            if(array[i] == null){ //if array is empty
                System.out.println(i +":" + "null");
            } else if(array[i] != null && array[i].getNext() == null) { //if one thing array
                count = 1;
                System.out.println(i + ":" + array[i].getData());
                if(count<longChainLength){
                    templength = count;
                    if(templength<shortChainLength) { //every other time when something is shorter than shortChainLength
                        shortChainLength = templength;
                        shortIndex = i;
                    }else if(shortChainLength ==0){//first time when something is shorter than longChainLength
                        shortChainLength = templength;
                        shortIndex = i;
                    }
                } else if(count>longChainLength){
                    longChainLength = count;
                    longIndex = i;
                }
            }
            else{ //more than one in array
                String str = "";
                while(array[i].getNext() != null){
                    str += (String) array[i].getData();
                    str += ", ";
                    array[i] = array[i].getNext();
                    count++;
                }
                str += (String) array[i].getData();
                str += ", ";
                count++;
                if(count<longChainLength) {
                    templength = count;
                    if (templength < shortChainLength) { //every other time when something is shorter than shortChainLength
                        shortChainLength = templength;
                        shortIndex = i;
                    } else if (shortChainLength == 0) {//first time when something is shorter than longChainLength
                        shortChainLength = templength;
                        shortIndex = i;
                    }
                }else if(count>longChainLength){
                    longChainLength = count;
                    longIndex = i;
                }
                System.out.println(i + ":" + str);
            }

        }
        System.out.println("Longest Chain at " + longIndex + " with length of " + longChainLength);
        System.out.println("Shortest Chain at " + shortIndex + " with length of " + shortChainLength);
        for(int a = 0; a<array.length; a++){//shows how many null spaces
            if(array[a] == null){
                nullCount++;
            }
        }
        System.out.println("Number of empty spots: " + nullCount);

    }

    public static void main(String[] args){ //taken from TextScan.java from 1933 Project 5 page.
        Scanner readFile = null;
        String s;
        int count=0;
        System.out.println();

        //Canterbury.txt...%13 null space
            System.out.println("Attempting to read from file: " + "canterbury.txt");
            try {
                readFile = new Scanner(new File("canterbury.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("File: " + "canterbury.txt" + " not found");
                System.exit(1);
            }

            System.out.println("Connection to file: " + "canterbury.txt" + " successful");
            Hash hashtable = new Hash(103);
            while (readFile.hasNext()) {
                s = readFile.next();
                hashtable.add(s);
                count++;
            }

            System.out.println();
            System.out.println(count + " Tokens found");
            System.out.println();
            hashtable.display();
        //Gettysburg.txt...7% null space
        System.out.println("Attempting to read from file: " + "gettysburg.txt");
        try {
            readFile = new Scanner(new File("gettysburg.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File: " + "gettysburg.txt" + " not found");
            System.exit(1);
        }

        System.out.println("Connection to file: " + "gettysburg.txt" + " successful");
        Hash hashtable2 = new Hash(83);
        while (readFile.hasNext()) {
            s = readFile.next();
            hashtable2.add(s);
            count++;
        }

        System.out.println();
        System.out.println(count + " Tokens found");
        System.out.println();
        hashtable2.display();
        //Proverbs.txt...1.3% null space
        System.out.println("Attempting to read from file: " + "proverbs.txt");
        try {
            readFile = new Scanner(new File("proverbs.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File: " +"proverbs.txt" + " not found");
            System.exit(1);
        }

        System.out.println("Connection to file: " + "proverbs.txt" + " successful");
        Hash hashtable3 = new Hash(79);
        while (readFile.hasNext()) {
            s = readFile.next();
            hashtable3.add(s);
            count++;
        }

        System.out.println();
        System.out.println(count + " Tokens found");
        System.out.println();
        hashtable3.display();
        //That_bad.txt...5.4%
        System.out.println("Attempting to read from file: " + "that_bad.txt");
        try {
            readFile = new Scanner(new File("that_bad.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File: " + "that_bad.txt" + " not found");
            System.exit(1);
        }

        System.out.println("Connection to file: " + "that_bad.txt" + " successful");
        Hash hashtable4 = new Hash(93);
        while (readFile.hasNext()) {
            s = readFile.next();
            hashtable4.add(s);
            count++;
        }

        System.out.println();
        System.out.println(count + " Tokens found");
        System.out.println();
        hashtable4.display();
        //Keywords.txt... no null space.
        System.out.println("Attempting to read from file: " + "keywords.txt");
        try {
            readFile = new Scanner(new File("keywords.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File: " + "keywords.txt" + " not found");
            System.exit(1);
        }

        System.out.println("Connection to file: " + "keywords.txt" + " successful");
        Hash hashtable5 = new Hash(50);
        while (readFile.hasNext()) {
            s = readFile.next();
            hashtable5.add2(s);
            count++;
        }

        System.out.println();
        System.out.println(count + " Tokens found");
        System.out.println();
        hashtable5.display();
    }

}



