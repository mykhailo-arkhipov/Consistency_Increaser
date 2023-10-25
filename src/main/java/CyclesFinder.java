import java.util.ArrayList;
import java.util.List;

public class CyclesFinder {
    /**
     * This algorithm finds cycles in the matrix of pairwise comparisons.
     * @see <a href="https://www.researchgate.net/publication/313235180_ORCON_
     * -_a_tool_for_analysis_of_ordinal_consistency_in_a_pairwise_comparison_
     * matrix">Official research | ResearchGate</a>
     * @return List<int[]> cycles
     */
    public static ArrayList<int[]> findCycles(int[][] matrix) {

        ArrayList<int[]> cycles = new ArrayList<>();

        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (i < k) {
                        int m = matrix[i][k] * matrix[k][j] * matrix[j][i];
                        if (m != 0 && m != 8) {

                            int c = (matrix[i][k]*100)+(matrix[k][j]*10) + matrix[j][i];
                            cycles.add(new int[]{i, k, j, c});
                        }
                    }
                }
            }
        }

        return cycles;
    }
}