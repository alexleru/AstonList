import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.alexleru.aston.AstonArrayList;
import ru.alexleru.aston.AstonCollections;
import ru.alexleru.aston.AstonList;

import java.util.Comparator;

public class AstonArrayListTest {

    AstonList<String> stringAstonArrayList;
    AstonList<Integer> integerAstonArrayList;
    AstonList<Number> numberAstonArrayList;

    AstonList<Car> carAstonList;

    @BeforeEach
    public void beforeEach() {
        stringAstonArrayList = new AstonArrayList<>();
        integerAstonArrayList = new AstonArrayList<>();
        numberAstonArrayList = new AstonArrayList<>();
        carAstonList = new AstonArrayList<>();
        carAstonList.add(new Car(1, "Red"));
        carAstonList.add(new Car(2, "Green"));
        carAstonList.add(new Car(3, "Blue"));
    }

    @Test
    @DisplayName("Check capacity before grow add")
    public void whenSize7_thenCapacity10() {
        int countExpected = 10;
        for (int i = 0; i < 7; i++) {
            stringAstonArrayList.add("i");
        }
        int countActual =
                ((AstonArrayList<String>) stringAstonArrayList)
                        .capacity();
        Assertions.assertEquals(countExpected, countActual);
    }

    @Test
    @DisplayName("Check capacity after grow add")
    public void checkCapacityGrow() {
        int countExpected = 58;
        for (int i = 0; i < 40; i++) {
            stringAstonArrayList.add("i");
        }
        int countActual =
                ((AstonArrayList<String>) stringAstonArrayList)
                        .capacity();
        Assertions.assertEquals(countExpected, countActual);
    }

    @Test
    @DisplayName("Check capacity before grow addAll")

    public void checkCapacityAddAll() {
        int countExpected = 10;
        for (int i = 0; i < 4; i++) {
            stringAstonArrayList.add("i");
        }
        AstonList<String> addAllNewAstonArrayList = new AstonArrayList<>();
        for (int i = 0; i < 5; i++) {
            stringAstonArrayList.add("i");
        }
        stringAstonArrayList.addAll(addAllNewAstonArrayList);
        int countActual =
                ((AstonArrayList<String>) stringAstonArrayList)
                        .capacity();
        Assertions.assertEquals(countExpected, countActual);
    }

    @Test
    @DisplayName("Check capacity after grow addAll")
    public void checkCapacityAddAllGrow() {
        int countExpected = 58;
        for (int i = 0; i < 35; i++) {
            stringAstonArrayList.add("i");
        }
        AstonList<String> addAllNewAstonArrayList = new AstonArrayList<>();
        for (int i = 0; i < 5; i++) {
            stringAstonArrayList.add("i");
        }
        stringAstonArrayList.addAll(addAllNewAstonArrayList);

        int countActual =
                ((AstonArrayList<String>) stringAstonArrayList)
                        .capacity();
        Assertions.assertEquals(countExpected, countActual);
    }

    @Test
    @DisplayName("Check size before grow")
    public void checkSize() {
        int countExpected = 7;
        for (int i = 0; i < countExpected; i++) {
            stringAstonArrayList.add("i");
        }
        int countActual = stringAstonArrayList.size();
        Assertions.assertEquals(countExpected, countActual);
    }

    @Test
    @DisplayName("Check size after grow")
    public void checkSizeGrow() {
        int countExpected = 14;
        for (int i = 0; i < countExpected; i++) {
            stringAstonArrayList.add("i");
        }
        int countActual = stringAstonArrayList.size();
        Assertions.assertEquals(countExpected, countActual);
    }

    @Test
    @DisplayName("Check size before grow addAll")
    public void checkSizeAddAll() {
        int countExpected = 7;
        for (int i = 0; i < 2; i++) {
            stringAstonArrayList.add("i");
        }

        AstonList<String> addAllNewAstonArrayList = new AstonArrayList<>();
        for (int i = 0; i < 5; i++) {
            stringAstonArrayList.add("i");
        }
        stringAstonArrayList.addAll(addAllNewAstonArrayList);

        int countActual = stringAstonArrayList.size();
        Assertions.assertEquals(countExpected, countActual);
    }

    @Test
    @DisplayName("Check size after grow addAll")
    public void checkSizeGrowAddAll() {
        int countExpected = 14;
        for (int i = 0; i < 8; i++) {
            stringAstonArrayList.add("i");
        }

        AstonList<String> addAllNewAstonArrayList = new AstonArrayList<>();
        for (int i = 0; i < 6; i++) {
            stringAstonArrayList.add("i");
        }
        stringAstonArrayList.addAll(addAllNewAstonArrayList);

        int countActual = stringAstonArrayList.size();
        Assertions.assertEquals(countExpected, countActual);
    }

    @Test
    @DisplayName("Check Parent Number addAll Child Integer")
    public void checkChildElementAddAll() {
        int countExpected = 14;
        for (int i = 0; i < 8; i++) {
            numberAstonArrayList.add(i);
        }

        for (int i = 0; i < 6; i++) {
            integerAstonArrayList.add(i + 100);
        }
        numberAstonArrayList.addAll(integerAstonArrayList);

        int countActual = numberAstonArrayList.size();
        Assertions.assertEquals(countExpected, countActual);
    }

    @Test
    @DisplayName("Check method get(index) main way")
    public void checkMethodGetMainWay() {
        for (int i = 0; i < 6; i++) {
            integerAstonArrayList.add(i + 100);
        }
        int countActual = integerAstonArrayList.get(2);
        Assertions.assertEquals(102, countActual);
    }

    @Test
    @DisplayName("Check method get(index) alternative way")
    public void checkMethodGetAlternativeWay() {
        for (int i = 0; i < 6; i++) {
            integerAstonArrayList.add(i + 100);
        }
        var wrongIndex = 6;
        Exception exception =
                Assertions.assertThrows(IndexOutOfBoundsException.class,
                        () -> integerAstonArrayList.get(wrongIndex));
        var expectedMessage = "Element with index " + wrongIndex + " does not exist";
        var actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    @DisplayName("Check method remove(index) main way")
    public void checkMethodRemoveMainWay() {
        for (int i = 0; i < 10; i++) {
            integerAstonArrayList.add(i + 100);
        }
        int countActual = integerAstonArrayList.remove(2);
        Assertions.assertEquals(102, countActual);
    }

    @Test
    @DisplayName("Check method remove(index) main way compare answer")
    public void checkMethodRemoveMainWay_2() {
        for (int i = 0; i < 10; i++) {
            integerAstonArrayList.add(i + 100);
        }

        integerAstonArrayList.remove(6);

        StringBuilder actualMessage = new StringBuilder();
        for (int i = 0; i < integerAstonArrayList.size(); i++) {
            actualMessage.append(integerAstonArrayList.get(i)).append(" ");
        }
        String expectedMessage = "100 101 102 103 104 105 107 108 109 ";
        Assertions.assertEquals(expectedMessage, actualMessage.toString());
    }

    @Test
    @DisplayName("Check method remove(index) alternative way")
    public void checkMethodRemoveAlternativeWay() {
        for (int i = 0; i < 6; i++) {
            integerAstonArrayList.add(i + 100);
        }
        var wrongIndex = 6;
        Exception exception =
                Assertions.assertThrows(IndexOutOfBoundsException.class,
                        () -> integerAstonArrayList.remove(wrongIndex));
        var expectedMessage = "Element with index " + wrongIndex + " does not exist";
        var actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Check method set(index) main way")
    public void checkMethodSetMainWay() {
        for (int i = 0; i < 6; i++) {
            integerAstonArrayList.add(i + 100);
        }
        var expected = 999;
        integerAstonArrayList.set(2, expected);
        var actual = integerAstonArrayList.get(2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check method set(index) alternative way")
    public void checkMethodSetAlternativeWay() {
        for (int i = 0; i < 6; i++) {
            integerAstonArrayList.add(i + 100);
        }
        var wrongIndex = 6;
        Exception exception =
                Assertions.assertThrows(IndexOutOfBoundsException.class,
                        () -> integerAstonArrayList.set(wrongIndex, 999));
        var expectedMessage = "Element with index " + wrongIndex + " does not exist";
        var actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Check method sortBubble()")
    public void checkMethodSortBubble() {

        stringAstonArrayList.add("b");
        stringAstonArrayList.add("abc");
        stringAstonArrayList.add("abc");
        stringAstonArrayList.add("x");
        stringAstonArrayList.add("c");
        stringAstonArrayList.add("a");
        stringAstonArrayList.add("e");
        stringAstonArrayList.add("x");

        stringAstonArrayList.sortBubble();
        var expected = "AstonArrayList{\n" +
                "innerArray=[a, abc, abc, b, c, e, x, x]\n" +
                ", size=8\n" +
                ", capacity=10\n" +
                "}";

        var actual = stringAstonArrayList.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check method sortBubble() class Car")
    public void checkMethodSortBubbleCar() {

        carAstonList.sortBubble(Comparator.comparing(Car::getColor));
        var expected = "AstonArrayList{\n" +
                "innerArray=[Car{id=3, color='Blue'}, Car{id=2, color='Green'}, Car{id=1, color='Red'}]\n" +
                ", size=3\n" +
                ", capacity=10\n" +
                "}";

        var actual = carAstonList.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check method sortBubble() class Car alternative way")
    public void checkMethodSortBubbleCarAlternativeWay() {

        Exception exception =
                Assertions.assertThrows(ClassCastException.class,
                        () -> carAstonList.sortBubble());
        var expectedMessage = "Class Car does not have interface java.lang.Comparable";
        var actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Check method sortBubble() class Car use Collection")
    public void checkMethodSortBubbleCarCollection() {

        AstonCollections.sortBubble(carAstonList, Comparator.comparing(Car::getColor));
        var expected = "AstonArrayList{\n" +
                "innerArray=[Car{id=3, color='Blue'}, Car{id=2, color='Green'}, Car{id=1, color='Red'}]\n" +
                ", size=3\n" +
                ", capacity=10\n" +
                "}";

        var actual = carAstonList.toString();
        Assertions.assertEquals(expected, actual);
    }
}
