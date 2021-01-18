public class ArrayList<T extends Comparable<T>> implements List<T>{
    private boolean isSorted;
    private int size;//size means number of elements, not length of array.
    private T[] array;

    public ArrayList(){
        this.size = 0;
        this.array = (T[]) new Comparable[2];
        this.isSorted = true;
    }

    public void expandArray(){//double the size of the array
        T newArray[] = (T[]) new Comparable[array.length*2];
        for(int i = 0; i<array.length; i++){
            newArray[i] = array[i];
        }
        this.array=newArray;
    }

    public boolean add(T element){
        if(element == null) {//if element is null, shouldn't add anything
            return false;
        }
        if(array.length==size){//array is full, expand the array.
            expandArray();
        }
        array[size] = element;//add at end of list.
        size++;

        isSorted=false;
        return true;
    }

    public boolean add(int index, T element){
        if(element == null){//if element is null, shouldn't add anything
            return false;
        }
        if(index<0 || index >= size){//if index out of bounds, doesn't do anything.
            return false;
        }
        if(size==0){//if empty, don't do anything.
            return false;
        }

        if(array.length==size) {//if array is full, expand the array.
            expandArray();
        }
        for(int i = size-1; i >= 0 ; i--) {//shift elements to the right.
            array[i + 1] = array[i];
        }
        array[index] = element;//adds element to that index.
        size++;
        isSorted=false;
        return true;


    }

    public void clear() {
        this.size = 0;//updates size to 0 meaning 0 elements
        array = (T[]) new Comparable[2];//defaults the list
    }

    public boolean contains(T element){
        if(element == null){//checks if element is null
            return false;
        }
        if(isSorted == true){//runs if list is already sorted
            for(int i = 0; i<array.length; i++){
                if(element.equals(array[i])){
                    return true;
                }
            }
        }
        else{
            for(int i = 0; i<array.length; i++){
                if(element.equals(array[i])){
                    return true;
                }
            }
        }
        return false;
    }

    public T get(int index){
        if(index<0 || index>array.length){//checks out of bounds
            return null;
        }
        return (T) array[index];//returns element at index
    }

    public int indexOf(T element){
        if(element == null){//checks if element is null
            return -1;
        }
        if(isSorted == true){//runs if list is sorted
            for(int i = 0; i<array.length; i++){
                if(element.equals(array[i])){
                    return i;
                }
            }
        }
        else{//run if it isn't sorted
            for(int i = 0; i<array.length; i++){
                if(element.equals(array[i])){
                    return i;
                }
            }
        }

        return -1;
    }

    public boolean isEmpty(){
        for(int i = 0; i<array.length; i++){
            if(array[i] != null){//checks if there are any elements.
                return false;
            }
        }

        return true;
    }

    public int lastIndexOf(T element){
        if(element == null){//checks if element is null.
            return -1;
        }
        if(isSorted == true){//if it's sorted
            int reverseIndex = array.length-1;//index through array backwards
            for(int i = 0; i<array.length; i++){
                if(array[reverseIndex] == element){
                    return reverseIndex;
                }
                reverseIndex--;
            }
        }
        else{
            int reverseIndex = array.length-1;
            for(int i = 0; i<array.length; i++){
                if(element.equals(array[reverseIndex])){
                    return reverseIndex;
                }
                reverseIndex--;
            }
        }
        return -1;
    }

    public T set(int index, T element){
        if(index<0 || index>array.length || element == null) {//checks if element is null or index is element is out of bounds.
            return null;
        }
        T valueAtIndex = array[index];//changes element at given index
        array[index] = element;
        return valueAtIndex;
    }

    public int size(){
        return size;//prints number of elements.
    }

    public void sort(boolean order){//uses bubble sort
        if(isSorted == true){
            return;
        }
            if(order==true){//increasing alphabetical order
            for(int i = 0; i<array.length; i++){
                for(int j = 1; j<array.length-i; j++){
                    if(array[j].compareTo(array[j-1])<0){//if one before is greater than one after.
                        T temp = array[j];//holder for previous element
                        array[j] = array[j-1];//sets current index to one before
                        array[j-1] = temp;//sets one before to current index
                    }
                }
            }
            isSorted = true;
        }
        if(order==false){//decreasing alphabetical order
            for(int i = 0; i<array.length; i++){
                for(int j = 1; j<array.length-i; j++){
                    if(array[j].compareTo(array[j-1])>0){//if one after is greater than one before.
                        T temp = array[j];//holder for previous element
                        array[j] = array[j-1];//sets current index to one before
                        array[j-1] = temp;//sets one before to current index
                    }
                }
            }
        }
    }

    public boolean remove(T element){
        int index = 0;
        for(int i = 0; i<array.length; i++){

            if(element.equals(array[i])){//checks if element is found
                array[i] = null;
                for(int j = index; j<size; j++){
                    array[j]=array[j+1];//shifts element
                }
                size--;
                array[array.length-1] = null;//sets last index to null since elements have shifted
                return true;
            }
            index++;
        }
        return false;
    }

    public T remove(int index){
        if(index<0 || index>=size){//checks bounds
            return null;
        }
        T valueAtIndex = array[index];//holder for value at index
        array[index] = null;
        for(int j = index; j<size; j++){
            array[j]=array[j+1];
        }
        array[array.length-1] = null;//sets last element to null after shifted
        size--;
        return  valueAtIndex;//returns value at index
    }

    public String toString(){
        int count = 1;
        String string = "";//string variable to add to
        for(int i=0; i<array.length; i++){
            string += ("Element" + count + ": " + array[i]);//adds to string variable
            count++;
        }
        return string;//returns string variable
    }
}
