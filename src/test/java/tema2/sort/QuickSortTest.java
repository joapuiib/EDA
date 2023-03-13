package tema2.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tema2.sort.QuickSort.quickSort;

class QuickSortTest {

    @ParameterizedTest
    @MethodSource("provideArguments")
    void mergeSortTest(int[] v, int[] expected) {
        quickSort(v);
        String expectedString = Arrays.toString(expected);
        String sortedString = Arrays.toString(v);
        assertEquals(expectedString, sortedString);
    }

    static Stream<Arguments> provideArguments(){
        return Stream.of(
                Arguments.arguments(new int[]{3, 5, -2, 1, 8, 5, -3}, new int[]{-3, -2, 1, 3, 5, 5, 8}),
                Arguments.arguments(new int[]{-2, 3, 4, -3, 5, 6, -2}, new int[]{-3, -2, -2, 3, 4, 5, 6}),
                Arguments.arguments(new int[]{}, new int[]{}),
                Arguments.arguments(new int[]{1}, new int[]{1}),
                Arguments.arguments(new int[]{2, 1}, new int[]{1, 2})
        );
    }
}