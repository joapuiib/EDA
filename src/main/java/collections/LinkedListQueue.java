package collections;

public class LinkedListQueue<E> implements Queue<E> {
    private Node<E> first;
    private Node<E> last;
    private int length;


    public LinkedListQueue() {
        first = null;
        last = null;
        length = 0;
    }

    @Override
    public void queue(E item) {
        Node<E> node = new Node<>(item);
        if (isEmpty()){
            first = node;
            last = node;
        } else {
            last.setSiguiente(node);
            last = node;
        }
        length++;
    }

    public void queue(E item, int index) {
        Node<E> node = new Node<>(item);
        if (index >= length){
            queue(item);
        } else {
            Node<E> anterior = null;
            Node<E> seguent = first;
            for (int i = 1; i <= index; i++){
                anterior = seguent;
                seguent = seguent.next();
            }

            if(anterior != null)
                anterior.setSiguiente(node);

            node.setSiguiente(seguent);
            length++;
        }
    }

    @Override
    public E dequeue() {
        if(isEmpty())
            return null;

        E object = first.getObjecto();

        this.first = first.next();
        length--;

        return object;
    }

    @Override
    public E first() {
        return this.first.getObjecto();
    }

    @Override
    public boolean isEmpty() {
        // return first == null;
        return length == 0;
    }

    public static void main(String[] args) {
        LinkedListQueue<String> linkedListQueue = new LinkedListQueue<>();
        linkedListQueue.queue("Item1");
        linkedListQueue.queue("Item2");
        linkedListQueue.queue("Item3");
        linkedListQueue.queue("Item4");
        linkedListQueue.queue("Item5", 2);
        linkedListQueue.queue("Item6", 100);

        while (!linkedListQueue.isEmpty()) {
            String item = linkedListQueue.dequeue();
            System.out.println(item);
        }
    }
}
