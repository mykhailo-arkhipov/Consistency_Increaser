import static org.junit.Assert.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import org.junit.Test;

public class MatrixInputTest {


    @Test
    public void testGenerateMatrix() {
        int[][] matrix = MatrixInput.generateMatrix(3);
        assertNotNull(matrix);
        assertEquals(3, matrix.length);
        assertEquals(3, matrix[0].length);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    assertEquals(0, matrix[i][j]);
                } else {
                    assertTrue(matrix[i][j] >= 0 && matrix[i][j] <= 2);
                }
            }
        }
    }

    @Test
    public void testGenerateMatrixWithZeroSize() {
        int[][] matrix = MatrixInput.generateMatrix(0);
        assertNotNull(matrix);
        assertEquals(0, matrix.length);
    }

    @Test
    public void testGenerateMatrixWith100Size() {

        int[][] matrix = MatrixInput.generateMatrix(100);
        assertNotNull(matrix);
        assertEquals(100, matrix.length);
        assertEquals(100, matrix[0].length);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (i == j) {
                    assertEquals(0, matrix[i][j]);
                } else {
                    assertTrue(matrix[i][j] >= 0 && matrix[i][j] <= 2);
                }
            }
        }


    }

    @Test
    public void testGenerateMatrixWith1000Size() {

        Runtime runtime = Runtime.getRuntime();

        long beforeMemory = runtime.freeMemory();

        int[][] matrix = MatrixInput.generateMatrix(1000);
        assertNotNull(matrix);
        assertEquals(1000, matrix.length);
        assertEquals(1000, matrix[0].length);
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (i == j) {
                    assertEquals(0, matrix[i][j]);
                } else {
                    assertTrue(matrix[i][j] >= 0 && matrix[i][j] <= 2);
                }
            }
        }

        long afterMemory = runtime.freeMemory();
        long usedMemory = beforeMemory - afterMemory;

        System.out.println("(1000 elements) Storage used - " + usedMemory + " bytes");
    }

    @Test
    public void testGenerateMatrixWith5000Size() {

        Runtime runtime = Runtime.getRuntime();

        long beforeMemory = runtime.freeMemory();

        int[][] matrix = MatrixInput.generateMatrix(5000);
        assertNotNull(matrix);
        assertEquals(5000, matrix.length);
        assertEquals(5000, matrix[0].length);
        for (int i = 0; i < 5000; i++) {
            for (int j = 0; j < 5000; j++) {
                if (i == j) {
                    assertEquals(0, matrix[i][j]);
                } else {
                    assertTrue(matrix[i][j] >= 0 && matrix[i][j] <= 2);
                }
            }
        }

        long afterMemory = runtime.freeMemory();
        long usedMemory = beforeMemory - afterMemory;

        System.out.println("(5000 elements) Storage used - " + usedMemory + " bytes");

    }

    @Test
    public void testGenerateMatrixWith10000Size() {

        MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage beforeMemory = mxBean.getHeapMemoryUsage();

        int[][] matrix = MatrixInput.generateMatrix(10000);
        assertNotNull(matrix);
        assertEquals(10000, matrix.length);
        assertEquals(10000, matrix[0].length);
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++) {
                if (i == j) {
                    assertEquals(0, matrix[i][j]);
                } else {
                    assertTrue(matrix[i][j] >= 0 && matrix[i][j] <= 2);
                }
            }
        }

        MemoryUsage afterMemory = mxBean.getHeapMemoryUsage();
        long usedMemory = afterMemory.getUsed() - beforeMemory.getUsed();

        System.out.println("(10000 elements) Storage used - " + usedMemory + " bytes");

    }

    @Test
    public void testGenerateMatrixWith15000Size() {

        MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage beforeMemory = mxBean.getHeapMemoryUsage();

        int[][] matrix = MatrixInput.generateMatrix(15000);
        assertNotNull(matrix);
        assertEquals(15000, matrix.length);
        assertEquals(15000, matrix[0].length);
        for (int i = 0; i < 15000; i++) {
            for (int j = 0; j < 15000; j++) {
                if (i == j) {
                    assertEquals(0, matrix[i][j]);
                } else {
                    assertTrue(matrix[i][j] >= 0 && matrix[i][j] <= 2);
                }
            }
        }

        MemoryUsage afterMemory = mxBean.getHeapMemoryUsage();
        long usedMemory = afterMemory.getUsed() - beforeMemory.getUsed();

        System.out.println("(15000 elements) Storage used - " + usedMemory + " bytes");

    }

    @Test
    public void testGenerateMatrixWith20000Size() {

        MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage beforeMemory = mxBean.getHeapMemoryUsage();

        int[][] matrix = MatrixInput.generateMatrix(20000);
        assertNotNull(matrix);
        assertEquals(20000, matrix.length);
        assertEquals(20000, matrix[0].length);
        for (int i = 0; i < 20000; i++) {
            for (int j = 0; j < 20000; j++) {
                if (i == j) {
                    assertEquals(0, matrix[i][j]);
                } else {
                    assertTrue(matrix[i][j] >= 0 && matrix[i][j] <= 2);
                }
            }
        }

        MemoryUsage afterMemory = mxBean.getHeapMemoryUsage();
        long usedMemory = afterMemory.getUsed() - beforeMemory.getUsed();

        System.out.println("(20000 elements) Storage used - " + usedMemory + " bytes");

    }

}
