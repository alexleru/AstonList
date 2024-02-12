import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.alexleru.aston.AstonLinkedList;
import ru.alexleru.aston.AstonList;

import java.util.Comparator;

public class AstonLinkedListTest {

    AstonList<String> stringAstonLinkedList;
    AstonList<Integer> integerAstonLinkedList;
    AstonList<Number> numberAstonLinkedList;
    AstonList<Car> carAstonList;

    @BeforeEach
    public void beforeEach() {
        stringAstonLinkedList = new AstonLinkedList<>();
        integerAstonLinkedList = new AstonLinkedList<>();
        numberAstonLinkedList = new AstonLinkedList<>();
        carAstonList = new AstonLinkedList<>();
        carAstonList.add(new Car(1, "Red"));
        carAstonList.add(new Car(2, "Green"));
        carAstonList.add(new Car(3, "Blue"));
    }

    @Test
    @DisplayName("Check size for empty list")
    public void checkEmptySize() {

        var expected = 0;
        var actual = stringAstonLinkedList.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check size for list")
    public void checkSize() {

        stringAstonLinkedList.add("S");
        var expected = 1;
        var actual = stringAstonLinkedList.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check size for list")
    public void checkSize_add() {

        stringAstonLinkedList.add("S");
        stringAstonLinkedList.add("S");
        stringAstonLinkedList.add("S");
        var expected = 3;
        var actual = stringAstonLinkedList.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check size for list")
    public void checkSizeConstructor() {

        integerAstonLinkedList.add(100);
        integerAstonLinkedList.add(200);
        integerAstonLinkedList.add(300);

        numberAstonLinkedList = new AstonLinkedList<>(integerAstonLinkedList);
        var expected = 3;
        var actual = numberAstonLinkedList.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check method get(index) main way")
    public void checkMethodGetMainWay() {
        for (int i = 0; i < 6; i++) {
            integerAstonLinkedList.add(i + 100);
        }
        int countActual = integerAstonLinkedList.get(2);
        Assertions.assertEquals(102, countActual);
    }

    @Test
    @DisplayName("Check method get(index) alternative way")
    public void checkMethodGetAlternativeWay() {
        for (int i = 0; i < 6; i++) {
            integerAstonLinkedList.add(i + 100);
        }
        var wrongIndex = 6;
        Exception exception =
                Assertions.assertThrows(IndexOutOfBoundsException.class,
                        () -> integerAstonLinkedList.get(wrongIndex));
        var expectedMessage = "Element with index " + wrongIndex + " does not exist";
        var actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Check method remove(index) main way")
    public void checkMethodRemoveMainWay() {
        for (int i = 0; i < 10; i++) {
            integerAstonLinkedList.add(i + 100);
        }
        int countActual = integerAstonLinkedList.remove(2);
        Assertions.assertEquals(102, countActual);
    }

    @Test
    @DisplayName("Check method remove(index) main way compare answer")
    public void checkMethodRemoveMainWay_2() {
        for (int i = 0; i < 10; i++) {
            integerAstonLinkedList.add(i + 100);
        }

        integerAstonLinkedList.remove(6);

        StringBuilder actualMessage = new StringBuilder();
        for (int i = 0; i < integerAstonLinkedList.size(); i++) {
            actualMessage.append(integerAstonLinkedList.get(i)).append(" ");
        }
        String expectedMessage = "100 101 102 103 104 105 107 108 109 ";
        Assertions.assertEquals(expectedMessage, actualMessage.toString());
    }

    @Test
    @DisplayName("Check method remove(index) alternative way")
    public void checkMethodRemoveAlternativeWay() {
        for (int i = 0; i < 6; i++) {
            integerAstonLinkedList.add(i + 100);
        }
        var wrongIndex = 6;
        Exception exception =
                Assertions.assertThrows(IndexOutOfBoundsException.class,
                        () -> integerAstonLinkedList.remove(wrongIndex));
        var expectedMessage = "Element with index " + wrongIndex + " does not exist";
        var actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Check method set(index) main way")
    public void checkMethodSetMainWay() {
        for (int i = 0; i < 6; i++) {
            integerAstonLinkedList.add(i + 100);
        }
        var expected = 999;
        integerAstonLinkedList.set(2, expected);
        var actual = integerAstonLinkedList.get(2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check method set(index) alternative way")
    public void checkMethodSetAlternativeWay() {
        for (int i = 0; i < 6; i++) {
            integerAstonLinkedList.add(i + 100);
        }
        var wrongIndex = 6;
        Exception exception =
                Assertions.assertThrows(IndexOutOfBoundsException.class,
                        () -> integerAstonLinkedList.set(wrongIndex, 999));
        var expectedMessage = "Element with index " + wrongIndex + " does not exist";
        var actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Check method sortBubble()")
    public void checkMethodSortBubble() {

        stringAstonLinkedList.add("b");
        stringAstonLinkedList.add("abc");
        stringAstonLinkedList.add("abc");
        stringAstonLinkedList.add("x");
        stringAstonLinkedList.add("c");
        stringAstonLinkedList.add("a");
        stringAstonLinkedList.add("e");
        stringAstonLinkedList.add("x");

        stringAstonLinkedList.sortBubble();
        var expected = "AstonLinkedList{\n" +
                "innerArray=[a, abc, abc, b, c, e, x, x]\n" +
                ", size=8\n" +
                "}";

        var actual = stringAstonLinkedList.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check method sortBubble() class Car")
    public void checkMethodSortBubbleCar() {

        carAstonList.sortBubble(Comparator.comparing(Car::getColor));
        var expected = "AstonLinkedList{\n" +
                "innerArray=[Car{id=3, color='Blue'}, Car{id=2, color='Green'}, Car{id=1, color='Red'}]\n" +
                ", size=3\n" +
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
}
