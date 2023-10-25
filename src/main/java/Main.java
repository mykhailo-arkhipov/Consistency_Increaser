import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    // protected volatile int decreaseCoefficient = 0.14;

    public static void main(String[] args) {
        // 1. Entering the matrix and encoding to binary pairwise comparisons format

        int[][] matrix = MatrixInput.inputTypeChoice();
        if (EncoderChecker.check(matrix)) Encoder.Encode(matrix);

        System.out.println("Enter 1 to manual increasing ordinal consistency \n" +
                "Or enter 2 to automated increasing");

        int manualAuto = 0;
        while (true) {
            try {
                manualAuto = Integer.parseInt(bufferedReader.readLine());
                break;
            } catch (IOException e) {
                System.out.println("Please type again");
                e.printStackTrace();
                throw new RuntimeException();
            }
        }


        // 2. Initial initialization of the cycle list and calculation of the order consistency coefficient
        ArrayList<int[]> cycles = CyclesFinder.findCycles(matrix);


        // sample graph
        GraphVisualizer.visualize(matrix, cycles);

        // 2.1. CR calculation
        OrdinalConsistencyRatio.getCR(cycles, matrix);

        if (!cycles.isEmpty()) {
            System.out.printf("\nCount of the found cycles: %d\n> signifies preference," +
                    " *= signifies equivalent preferences: %d\n", cycles.size(), cycles.size());
        }

        else {
            System.out.println("There are no cycles in the matrix; it is already consistent.");
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.exit(0);
        }

        System.out.println("Found cycles: \n");

        for (int i = 0; i < cycles.size(); i++){
                String n1 = cycles.get(i)[3] / 100 == 1 ? ">" : "*=";
                String n2 = (cycles.get(i)[3] / 10) % 10 == 1 ? ">" : "*=";
                String n3 = cycles.get(i)[3] % 10 == 1 ? ">" : "*=";
            System.out.println(i+1 + ". A" + (cycles.get(i)[0]+1) + " " + n1 + " A" +
                        (cycles.get(i)[1]+1) + " " + n2 + " A" + (cycles.get(i)[2]+1) +
                        " " + n3 + " A" + (cycles.get(i)[0]+1));
        }
        int step = 1;

        while (true){
            System.out.println("Step " + step);
            if (manualAuto == 1) {
                System.out.println();
                    matrix = ManualReducer.ReduceCycles(cycles, matrix);
                }
                else {
                    matrix = AutoReducer.ReduceCycles(cycles, matrix);
                }


                if (matrix == null){
                    System.out.println("Program is ending.");
                    break;
                }

                cycles = CyclesFinder.findCycles(matrix);
                // GraphVizualizer.VisualizeMatrix(matrix);

                OrdinalConsistencyRatio.getCR(cycles, matrix);


                if (cycles.size() == 0){

                    System.out.println("Here is no cycles anymore. Exiting.");
                    break;
                }
                else {

                    System.out.println("Found cycles: \n");
                    for (int i = 0; i < cycles.size(); i++) {
                        String n1 = cycles.get(i)[3] / 100 == 1 ? ">" : "*=";
                        String n2 = (cycles.get(i)[3] / 10) % 10 == 1 ? ">" : "*=";
                        String n3 = cycles.get(i)[3] % 10 == 1 ? ">" : "*=";
                        System.out.println(i + 1 + ". A" + (cycles.get(i)[0] + 1) + " " + n1 +
                                " A" + (cycles.get(i)[1] + 1) + " " + n2 + " A" + (cycles.get(i)[2] + 1)
                                + " " + n3 + " A" + (cycles.get(i)[0] + 1));
                    }
                }
                step++;
            }
        System.out.println();
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < ints.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}