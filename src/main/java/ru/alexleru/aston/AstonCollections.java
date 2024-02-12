package ru.alexleru.aston;

import java.util.Comparator;

public class AstonCollections {
    public static <T> void sortBubble(AstonList<? extends T> astonList) {
        sortBubble(astonList, null);
    }

    public static <T> void sortBubble(AstonList<T> astonList, Comparator<? super T> comparator) {
        if (astonList.size() == 0) return;
        var element = astonList.get(0);
        if(comparator == null) {
            if (!(element instanceof Comparable<?>)){
                throw new ClassCastException("Class "
                        + element.getClass().getName()
                        + " does not have interface "
                        + Comparable.class.getName());
            }
        }

        for (int i = astonList.size() - 1; i > 0; i--) {
            boolean isOrdered = true;
            for (int j = 0; j < i; j++) {

                var elementN = astonList.get(j);
                var elementNPlus = astonList.get(j + 1);

                int compare = 0;
                if(comparator != null) {
                    compare = comparator.compare(elementN, elementNPlus);
                } else {
                    compare = ((Comparable<T>)elementN).compareTo(elementNPlus);
                }

                if (compare > 0) {
                    isOrdered = false;
                    var temp = astonList.get(j);
                    astonList.set(j, elementNPlus);
                    astonList.set(j + 1, temp);
                }
            }
            if (isOrdered) break;
        }
    }
}
