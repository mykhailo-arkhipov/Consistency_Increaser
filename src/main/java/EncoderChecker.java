public class EncoderChecker {

    /**
     * Method checks initial matrix
     * @param matrix
     * @return <b>true</b> if matrix is correct,<b>false</b> in other case
     */
    public static boolean check(int[][] matrix) {

        boolean isIncorrect = false;

        for (int counter_encode_rows = 0; counter_encode_rows < matrix[0].length; counter_encode_rows++){
            for (int counter_encode_col = 0; counter_encode_col < matrix[0].length; counter_encode_col++){
                if (counter_encode_col == counter_encode_rows){
                    if (matrix[counter_encode_col][counter_encode_rows] != 0) {
                        return false;
                    }
                }

                // if there are relationships Ai > Aj and Aj > Ai simultaneously in the matrix
                else if (matrix[counter_encode_col][counter_encode_rows] == 1 & matrix[counter_encode_rows][counter_encode_col] == 1){
                    return false;
                }

                // if there are relationships Ai = Aj and Aj < Ai simultaneously in the matrix
                else if ((matrix[counter_encode_col][counter_encode_rows] == 2 & matrix[counter_encode_rows][counter_encode_col] == 0) && (matrix[counter_encode_col][counter_encode_rows] == 0 & matrix[counter_encode_rows][counter_encode_col] == 2)){
                    return false;
                }

                // if there are relationships Ai = Aj and Aj > Ai simultaneously in the matrix
                else if ((matrix[counter_encode_col][counter_encode_rows] == 2 & matrix[counter_encode_rows][counter_encode_col] == 1) && (matrix[counter_encode_col][counter_encode_rows] == 1 & matrix[counter_encode_rows][counter_encode_col] == 2)){
                    return false;
                }

                // if there are values other than 1, 2, and 0 in the matrix
                else if (matrix[counter_encode_col][counter_encode_rows] != 1 & matrix[counter_encode_col][counter_encode_rows] != 2 & matrix[counter_encode_col][counter_encode_rows] != 0){
                    isIncorrect = true;
                    System.out.println("Please enter a new matrix containing only values of 0, 1, and 2.");
                    matrix = MatrixInput.inputTypeChoice();
                }
            }
        }
        return isIncorrect;
    }
}
