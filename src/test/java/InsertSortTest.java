import com.halle.java.base.algorithm.simpleSort.BubbleSort;
import com.halle.java.base.algorithm.simpleSort.InsertSort;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class InsertSortTest {

    int[] datas = null;
    int size = 100000;
    @Test
    public void testInsertSort(){

        int[] testInput1 = {124,3,2,1,1,2,3,123,43,2,52,1,3,4,5,2,435,1232,53,5412,345,23,435};
        int[] testInput = {124,3,2,1,1232};

        int[] sortInput = InsertSort.swap(testInput);
        System.out.println(sortInput);
    }

    @Before
    public void initData(){
        datas = new int[size];

        for (int i = 0; i < size; i++) {
            datas[i] = new Random().nextInt();
        }
    }


    @Test
    public void testBetweenInsertSortAndBubbleSortTime(){
        int[] sortInput = new int[size];
        System.arraycopy(datas,0,sortInput,0,size);
        long time = System.currentTimeMillis();
        int[] sortInput2 = new int[size];
        System.arraycopy(datas,0,sortInput2,0,size);
        InsertSort.swap(sortInput2);
        System.out.println(System.currentTimeMillis()-time);
        long time1 = System.currentTimeMillis();
        BubbleSort.swap(sortInput);
        System.out.println(System.currentTimeMillis()-time1);
    }




}
