import org.junit.Test;

import java.util.List;

public class CyclesToAlternatives {

    @Test
    public void CyclesToAlternatives10Test() {

        int[] cyclesCount = new int[10];

        for (int k = 1; k < 11; k++) {
            System.out.print(k + ". First test (10 alternatives): ");
            for (int i = 1; i < 11; i++) {
                int[][] matrix = MatrixInput.generateMatrix(i * 10);
                List<int[]> cycles = CyclesFinder.findCycles(matrix);
                System.out.print(cycles.size() + " ");
                cyclesCount[i - 1] += cycles.size();
            }
            System.out.println();

        }

        for (int c = 0; c < cyclesCount.length; c++){
            cyclesCount[c] /= 10;
        }

        System.out.println("Middle count of cycles");
        for (int qwed = 0; qwed < cyclesCount.length; qwed++){
             System.out.print(cyclesCount[qwed] + " ");
        }

    }


}
