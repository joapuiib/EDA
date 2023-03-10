package tema2.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static tema2.sort.MergeSort.mergeSort;

class MergeSortTest {

    @ParameterizedTest
    @MethodSource("provideArguments")
    void mergeSortTest(int[] v, int[] expected) {
        int[] sorted = mergeSort(v);
        String expectedString = Arrays.toString(expected);
        String sortedString = Arrays.toString(sorted);
        assertEquals(expectedString, sortedString);
    }

    static Stream<Arguments> provideArguments(){
        return Stream.of(
                Arguments.arguments(new int[]{-2, 3, 4, -3, 5, 6, -2}, new int[]{-3, -2, -2, 3, 4, 5, 6}),
                Arguments.arguments(new int[]{}, new int[]{}),
                Arguments.arguments(new int[]{1}, new int[]{1}),
                Arguments.arguments(new int[]{2, 1}, new int[]{1, 2})
        );
    }
}