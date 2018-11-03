import com.halle.java.base.algorithm.simpleSort.BubbleSort;
import org.junit.Test;

import java.util.Arrays;

public class BubbleSortTest {

    @Test
    public void testBubbleSort(){

        int[] testInput = {124,3,2,1,1,2,3,123,43,2,52,1,3,4,5,2,435,1232,53,5412,345,23,435};
        int[] sortInput = BubbleSort.swap(testInput);
        System.out.println(sortInput);
    }
}
