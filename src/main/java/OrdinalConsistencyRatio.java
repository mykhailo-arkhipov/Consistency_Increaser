import java.util.List;

public class OrdinalConsistencyRatio {
    /**
     * @see <a href="https://en.wikipedia.org/wiki/Kendall%27s_W">
     *     Wiki - <b>Kendall's W</b></a>
     * @param cycles List of found cycles
     * @param matrix Main matrix encoded by {@link Encoder ()}
     */
    public static void getCR (List<int[]> cycles, int[][] matrix){
        // Calculating the Kendall's Coefficient of Concordance
        int a = cycles.size();
        int n = matrix[0].length;

        double CR = (double) a /( (n - 1)*(3*n)+1);

        System.out.print("Coefficient of Concordance: " + CR);
        if (CR > 0.1){
            System.out.println(" - the matrix is insufficiently consistent.\n");
        }
        else if (CR == 0.0){
            System.out.println(" - the matrix is fully consistent.\n");
        }
        else System.out.println(" - the matrix is sufficiently consistent.\n");
    }
}
