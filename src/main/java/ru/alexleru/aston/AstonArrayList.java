package ru.alexleru.aston;

import java.util.Arrays;
import java.util.Objects;

public class AstonArrayList<T> implements AstonList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] innerArray;
    private int size;


    public AstonArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public AstonArrayList(int capacity) {
        size = 0;
        innerArray = (T[]) new Object[capacity];
    }

    public AstonArrayList(AstonList<? extends T> astonList) {
        this(astonList.size());
        addAll(astonList);
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return innerArray.length;
    }

    /**
     * Добавляет элемент в массив
     * @param element элемент, который добавляем
     * @return если элемент добавлен выводит true, в противном случае false
     */
    @Override
    public boolean add(T element) {
        if (innerArray.length > size) {
            innerArray[size] = element;
            size++;
        } else {
            if (extendCapacity(1)) {
                innerArray[size] = element;
                size++;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Метод добавляет все элементы из коллекции имплементирующих AstonList
     * @param array коллекции имплементирующие AstonList с учетом Upper Bounded Wildcards
     * @return если элементы добавлены выводит true, в противном случае false
     */
    @Override
    public boolean addAll(AstonList<? extends T> array) {
        if (innerArray.length >= size + array.size()) {
            helperForAddAll(array);
        } else {
            if (extendCapacity(array.size())) {
                helperForAddAll(array);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Внутренний метод - добавляет все элементы в конец уже существующей внутренней коллекции
     * @param array коллекции имплементирующие AstonList с учетом Upper Bounded Wildcards
     * @return если элементы добавлены выводит true, в противном случае false
     */
    private void helperForAddAll(AstonList<? extends T> array) {
        for (int i = 0; i < array.size(); i++) {
            innerArray[size] = array.get(i);
            size++;
        }
    }


    /**
     * Вспомогательный метод  - увеличивает внутренний размер массива по следующей формуле
     * - внутренний размер * 1.5 + добавляемый размер
     * Это происходит за счет создания нового массива и копирования в него элементов из старого
     * с указанием размера нового внутреннего массива
     *
     * @param addSize число, на которое увеличивается массив
     * @return возращает true, если удалось расширить массив и false, если размер массива превысил Integer.MAX_VALUE
     */
    private boolean extendCapacity(int addSize) {
        int checkSize = innerArray.length * 3 / 2 + addSize;
        boolean check = (checkSize) < innerArray.length;
        if (check) return false;
        innerArray = Arrays.copyOf(innerArray, checkSize);
        return true;
    }

    /**
     * Возвращет элемент из массив
     * @param index номер элемента, который требуется вернуть
     * @return возращает элемент массива
     * @throws IndexOutOfBoundsException если номер элемента больше массива
     */
    @Override
    public T get(int index) {
        if (index < size) {
            return innerArray[index];
        } else {
            throw new IndexOutOfBoundsException
                    ("Element with index " + index + " does not exist");
        }
    }

    /**
     * Удаляет элемент из массив
     * @param index номер элемента, который требуется удалить
     * @return удаляет элемент массива
     * @throws IndexOutOfBoundsException если номер элемента больше массива
     */
    @Override
    public T remove(int index) {
        if (index < size) {
            T element = innerArray[index];
            removeHelper(index);
            size--;
            return element;
        } else {
            throw new IndexOutOfBoundsException
                    ("Element with index " + index + " does not exist");
        }
    }

    /**
     * Перезаписывает элемент
     *
     * @param index   номер элемента, значение, которого будет переписано
     * @param element элемент, который будет присвоен
     * @throws IndexOutOfBoundsException если номер элемента больше массива
     */
    @Override
    public void set(int index, T element) {
        if (index < size) {
            innerArray[index] = element;
        } else {
            throw new IndexOutOfBoundsException
                    ("Element with index " + index + " does not exist");
        }
    }

    /**
     * Вспомогательный метод - проходит поэлементно переставляя элементы на предыдущие позиции
     * @param index номер элемента, с которого требуется начать перестановку элементов
     */
    private void removeHelper(int index) {
        for (int i = index; i < size - 1; i++) {
            innerArray[i] = innerArray[i + 1];
        }
        innerArray[size - 1] = null;
    }


    @Override
    public String toString() {
        return "AstonArrayList{" +
                "\ninnerArray=" + Arrays.toString(Arrays.stream(innerArray).filter(Objects::nonNull).toArray()) +
                "\n, size=" + size() +
                "\n, capacity=" + capacity() +
                "\n}";
    }
}
