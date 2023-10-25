public class Encoder {
    /**
     * Method contains encoding algorithm which makes matrix correct.
     * Rules of these matrices: DOI:10.1504/IJMDM.2017.10002944
     * @param matrix matrix to encode
     * @return encoded matrix
     */
    public static int[][] Encode(int[][] matrix){

        System.out.println("\nIt appears that you entered a matrix of 0s and 1s or " +
                "one with contradictions. The recoded matrix, where equivalence values " +
                "are replaced with 2, pairs with corrected preferences as Ai > Aj (1) " +
                "and Aj < Ai (0), and diagonal elements are set to 0, looks like this:");
        for (int i = 0; i < matrix[0].length; i ++){
            for (int j = 0; j < matrix[0].length; j++ ){
                if (i == j) {
                    matrix[i][j] = 0;
                }
                else if ((matrix[i][j] == 1) & (matrix[j][i] == 2)){
                    matrix[i][j] = 2;
                    matrix[j][i] = 2;
                }
                else if ((matrix[i][j] == 2) & (matrix[j][i] == 1)){
                    matrix[i][j] = 2;
                    matrix[j][i] = 2;
                }
                else if (matrix[i][j] == 2 & matrix[j][i] == 0){
                    matrix[i][j] = 2;
                    matrix[j][i] = 2;
                }
                else if (matrix[i][j] == 0 & matrix[j][i] == 2) {
                    matrix[i][j] = 2;
                    matrix[j][i] = 2;
                }
                else if (matrix[i][j] == 1 & matrix[j][i] == 1){
                    matrix[i][j] = 2;
                    matrix[j][i] = 2;
                }
            }
        }

        System.out.println();
        Main.printMatrix(matrix);
        System.out.println();
        return matrix;
    }
}
