package collections;

public class LinkedList<E> {
    private Nodo<E> first;
    private Nodo<E> last;
    private Nodo<E> previous;
    private int length;

    public LinkedList(){
        first = last = previous = new Nodo<>(null);
        length = 0;
    }

    public void insert(E item){
        Nodo<E> newNodo = new Nodo<>(item);

        if(isEnd())
            last = newNodo;

        newNodo.setSiguiente(previous.next());
        previous.setSiguiente(newNodo);
        previous = newNodo;

        length++;
    }

    public E delete(){
        E item = get();
        if(isEmpty())
            return item;
        else if(isEnd()){
            previous.setSiguiente(null);
            this.last = previous;
        } else {
            Nodo<E> nextCurrent = this.previous.next().next();
            previous.setSiguiente(nextCurrent);
        }
        length--;
        return item;
    }

    public void start(){
        this.previous = first;
    }

    public void next(){
        if(!isEmpty()) {
            this.previous = previous.next();
        }
    }

    public void end(){
        this.previous = last;
    }

    public E get(){
        return this.previous.next().getObjecto();
    }

    public boolean isEnd(){
        return previous == last;
    }

    public boolean isEmpty(){
        return length == 0;
    }

    public int length(){
        return length;
    }

    public static void main(String[] args) {
        LinkedList<String> linkedList= new LinkedList<>();
        linkedList.insert("Item1");
        linkedList.insert("Item2");
        linkedList.insert("Item3");
        linkedList.insert("Item4");
        linkedList.insert("Item5");

        linkedList.start();
        linkedList.next();
        linkedList.next();
        linkedList.insert("Item6");

        linkedList.start();
        linkedList.insert("Item7");

        linkedList.end();
        String deleted = linkedList.delete();
        System.out.println("Deleted: " + deleted);

        linkedList.start();
        while (!linkedList.isEnd()){
            String item = linkedList.get();
            System.out.println(item);
            linkedList.next();
        }
    }
}