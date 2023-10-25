import static org.junit.Assert.*;

import org.junit.Test;

public class Matrix_EncoderTest {

    @Test
    public void testEncodeWithValidMatrix() {
        int[][] matrix = {{0, 1, 2}, {0, 0, 1}, {2, 0, 0}};
        int[][] encoded = Encoder.Encode(matrix);
        assertNotNull(encoded);
        assertEquals(3, encoded.length);
        assertEquals(3, encoded[0].length);
        for (int i = 0; i < encoded.length; i++) {
            for (int j = 0; j < encoded[i].length; j++) {
                if (i == j) {
                    assertEquals(0, encoded[i][j]);
                } else {
                    assertTrue(encoded[i][j] >= 0 && encoded[i][j] <= 2);
                }
            }
        }
    }

    @Test
    public void testEncodeWithInvalidMatrix() {
        int[][] matrix = {{1, 1, 2}, {1, 0, 2}, {0, 1, 0}};
        int[][] truematrix = {{0, 2, 2}, {2, 0, 2}, {2, 2, 0}};
        int[][] encoded = Encoder.Encode(matrix);
        assertNotNull(encoded);
        assertEquals(3, encoded.length);
        assertEquals(3, encoded[0].length);
        for (int i = 0; i < encoded.length; i++) {
            for (int j = 0; j < encoded[i].length; j++) {
                if (i == j) {
                    assertEquals(0, encoded[i][j]);
                } else {
                    assertTrue(encoded[i][j] >= 0 && encoded[i][j] <= 2);
                }
            }
        }

        assertArrayEquals (matrix, truematrix);
    }
}