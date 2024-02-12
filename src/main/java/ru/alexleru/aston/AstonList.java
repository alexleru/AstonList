package ru.alexleru.aston;

import java.util.Comparator;

public interface AstonList<T> {
    boolean add(T element);

    boolean addAll(AstonList<? extends T> array);

    T get(int index);

    T remove(int index);

    void set(int index, T element);

    int size();

    @Override
    String toString();

    default void sortBubble(){
        AstonCollections.sortBubble(this);
    }
    default void sortBubble(Comparator<? super T> comparator) {
        AstonCollections.sortBubble(this, comparator);
    }
}
