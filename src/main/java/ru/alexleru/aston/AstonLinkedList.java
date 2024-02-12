package ru.alexleru.aston;

public class AstonLinkedList<T> implements AstonList<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public AstonLinkedList() {
        size = 0;
    }

    public AstonLinkedList(AstonList<? extends T> astonList) {
        size = 0;
        addAll(astonList);
    }

    /**
     * @param element
     * @return
     */
    @Override
    public boolean add(T element) {
        Node<T> node;
        if (size == 0) {
            node = new Node<>(null, null, element);
            first = node;
        } else {
            node = new Node<>(last, null, element);
            last.next = node;
        }
        last = node;
        size++;
        return true;
    }

    /**
     * @param array
     * @return
     */
    @Override
    public boolean addAll(AstonList<? extends T> array) {
        for (int i = 0; i < array.size(); i++) {
            this.add(array.get(i));
        }
        return true;
    }

    /**
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if (index < size) {
            Node<T> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.value;
        } else {
            throw new IndexOutOfBoundsException
                    ("Element with index " + index + " does not exist");
        }
    }

    /**
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {
        if (index < size) {
            Node<T> node = first;
            if (index == 0) {
                Node<T> next = first.next;
                next.previous = null;
                first = next;
            } else if (index == size - 1) {
                node = last;
                Node<T> previous = last.previous;
                previous.next = null;
                last = previous;
            } else {
                for (int i = 0; i < index; i++) {
                    node = node.next;
                }
                Node<T> previous = node.previous;
                Node<T> next = node.next;
                previous.next = next;
                next.previous = previous;
            }
            size--;
            return node.value;
        } else {
            throw new IndexOutOfBoundsException
                    ("Element with index " + index + " does not exist");
        }
    }

    /**
     * @param index
     * @param element
     */
    @Override
    public void set(int index, T element) {
        if (index < size) {
            Node<T> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            node.value = element;
        } else {
            throw new IndexOutOfBoundsException
                    ("Element with index " + index + " does not exist");
        }
    }

    /**
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        if (size > 0) {
            Node<T> node = first;
            for (int i = 0; i < size - 1; i++) {
                stringBuilder.append(node.value.toString() + ", ");
                node = node.next;
            }
            stringBuilder.append(node.value.toString() + "]");
        }

        return "AstonLinkedList{" +
                "\ninnerArray=" + stringBuilder +
                "\n, size=" + size +
                "\n}";
    }

    private static class Node<T> {
        Node previous;
        Node next;
        T value;

        public Node(Node previous, Node next, T value) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }
    }
}
