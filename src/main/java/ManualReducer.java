import java.io.IOException;
import java.util.*;

public class ManualReducer {

    // Last pair of the last iteration
    public static int[] lastPair = {0, 0};
    public static int globalCounter = 1;

    public static int[][] ReduceCycles(ArrayList<int[]> cycles, int[][] matrix) {

        // 1. Creating a list of unique pairs with their relationships
        ArrayList<int[]> originalPairs = getOriginalPairs(cycles);

        // originalPairs - a newly formed list of unique pairs with their relationship,
        // but still without the number of cycles when changing

        // 2. (optional) Filling in the number of cycles (forming a new array of pairs with new
        // relationships and calculating the number of cycles with writing them to the last cell)

        // 3. Output of unique pairs with their original relationships
        showUniquePairs(originalPairs);

        // 3.1. changing the array of original pairs by overwriting the first
        // half to the value 0 in relation and counting the number of cycles
        modifyOriginalPairs(originalPairs, matrix);

        // 4. Sorting by number of cycles
        originalPairs.sort(Comparator.comparingInt(a -> a[3]));

        // 5. Output of pairs with modified ratios and corresponding numbers of cycles
        displayOriginalPairs(originalPairs);

        // 6. Suggestions for improvement
        doSuggestions(originalPairs, matrix);

        return matrix;
    }

    private static void doSuggestions(ArrayList<int[]> originalPairs, int[][] matrix) {
        System.out.println("The way to effectively achieve ordinal consistency: ");

        int comparable_variable = originalPairs.get(0)[3];
        for (int k = 0; k < originalPairs.size(); k++) {

            // if the number of cycles is greater than the previously proposed, we exit the cycle
            if (originalPairs.get(k)[3] > comparable_variable) break;

            // if the program goes into recursion (the case where the cycle 1 = 2 > 3 = 1 changes to 1 = 3 > 2 = 1), skip this pair
            if (lastPair[0] == originalPairs.get(k)[0] & lastPair[1] == originalPairs.get(k)[1]) k++;

            String s = "";

            switch (originalPairs.get(k)[2]) {
                case 0:
                    s = "<";
                    break;
                case 1:
                    s = ">";
                    break;
                case 2:
                    s = "*=";
                    break;
            }

            System.out.println("New relationship A" + (originalPairs.get(k)[0] + 1) +
                    " " + s + " A" + (originalPairs.get(k)[1] + 1) + ", where will be " + originalPairs.get(k)[3] + " cycles.");

            lastPair = new int[] {originalPairs.get(k)[0], originalPairs.get(k)[1]};
        }

        System.out.println("\nEnter the change number (or 0 to exit): ");

        int ch = 0;
        try {
            ch = Integer.parseInt(Main.bufferedReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (ch == 0) {
            return;
        }
        ch--;

        // depending on the choice, we change the value in the original matrix and return it in a modified form

        int fir = originalPairs.get(ch)[0];
        int sec = originalPairs.get(ch)[1];

        // if the value of the matrix ...
        if (matrix[fir][sec] == 2){
            // ... need to change to ...
            if (originalPairs.get(ch)[2] == 0){
                // то
                matrix[fir][sec] = 0;
                // and necessarily a mirror element
                matrix[sec][fir] = 1;
            }
            else if(originalPairs.get(ch)[2] == 1){
                matrix[fir][sec] = 1;
                matrix[sec][fir] = 0;
            }
        }
        else if (matrix[fir][sec] == 1){
            if (originalPairs.get(ch)[2] == 0){
                matrix[fir][sec] = 0;
                matrix[sec][fir] = 1;
            }
            else if(originalPairs.get(ch)[2] == 2){
                matrix[fir][sec] = 2;
                matrix[sec][fir] = 2;
            }
        }
        else{
            System.out.println(" Attention! Algorithm error!");
        }

        globalCounter++;

        System.out.println();
        System.out.println("Modified matrix: ");
        Main.printMatrix(matrix);
        System.out.println();
    }

    private static void displayOriginalPairs(ArrayList<int[]> originalPairs) {
        int range = 0;
        int cyclesStepForRange;
        int cyclesStepLast = 0;

        for (int counter_changed = 0; counter_changed < originalPairs.size(); counter_changed++) {
            String s = "";

            switch (originalPairs.get(counter_changed)[2]) {
                case 0:
                    s = "<";
                    break;
                case 1:
                    s = ">";
                    break;
                case 2:
                    s = "*=";
                    break;
            }


            cyclesStepForRange = originalPairs.get(counter_changed)[3];
            if ((cyclesStepForRange > cyclesStepLast) || counter_changed == 0) {
                cyclesStepLast = cyclesStepForRange;
                range++;
                System.out.println();
                System.out.println("Range " + range + ": ");
            }



            System.out.println((counter_changed + 1) + ". New relationship А" + (originalPairs.get(counter_changed)[0] + 1)
                    + " " + s + " A" + (originalPairs.get(counter_changed)[1] + 1)
                    + ", will result in the following number of cycles: " + originalPairs.get(counter_changed)[3]);
        }

        System.out.println();
    }

    private static void modifyOriginalPairs(ArrayList<int[]> originalPairs, int[][] matrix) {
        int hiddenCounter = originalPairs.size();

        for (int i = 0; i < hiddenCounter; i++){
            // If we reach the first element with the relation <, it means that
            // we have passed all the pairs, and we need to exit the loop
            if (originalPairs.get(i)[2] == 0) break;

            // 3.1.1. Temporary replacement in the matrix of the relationship
            // from > to = and from = to >, change of the relationship in the pair itself and calculation of cycles

            // memorize the values to return the original matrix
            int a = matrix[originalPairs.get(i)[0]][originalPairs.get(i)[1]];
            int b = matrix[originalPairs.get(i)[1]][originalPairs.get(i)[0]];

            if (originalPairs.get(i)[2] == 1){
                matrix[originalPairs.get(i)[0]][originalPairs.get(i)[1]] = 2;
                matrix[originalPairs.get(i)[1]][originalPairs.get(i)[0]] = 2;
                originalPairs.get(i)[2] = 2;
            }
            else if (originalPairs.get(i)[2] == 2){
                matrix[originalPairs.get(i)[0]][originalPairs.get(i)[1]] = 1;
                matrix[originalPairs.get(i)[1]][originalPairs.get(i)[0]] = 0;
                originalPairs.get(i)[2] = 1;
            }

            List<int[]> temporaryCycles = CyclesFinder.findCycles(matrix);

            // adding the value of the number of cycles to the current pair
            originalPairs.get(i)[3] = temporaryCycles.size();
            temporaryCycles = null; // just for understanding

            // 3.1.2. calculation for the ratio < (that is, the value 0) and adding the pair to the end of the modified list
            matrix[originalPairs.get(i)[0]][originalPairs.get(i)[1]] = 0;
            matrix[originalPairs.get(i)[1]][originalPairs.get(i)[0]] = 1;
            temporaryCycles = CyclesFinder.findCycles(matrix);
            originalPairs.add(new int[] {originalPairs.get(i)[0], originalPairs.get(i)[1], 0, temporaryCycles.size()});
            temporaryCycles = null;

            // returning the old value in the matrix
            matrix[originalPairs.get(i)[0]][originalPairs.get(i)[1]] = a;
            matrix[originalPairs.get(i)[1]][originalPairs.get(i)[0]] = b;
        }
    }

    private static void showUniquePairs(ArrayList<int[]> originalPairs) {
        System.out.println("\nUnique pairs: ");
        for (int counter_unic = 0; counter_unic < originalPairs.size(); counter_unic++) {
            System.out.println((counter_unic + 1) + ". А" + (originalPairs.get(counter_unic)[0] + 1) + " " +
                    (originalPairs.get(counter_unic)[2] == 1 ? ">" : "*=") + " A" + (originalPairs.get(counter_unic)[1] + 1));
        }
        System.out.println();
    }

    private static ArrayList<int[]> getOriginalPairs(ArrayList<int[]> cycles) {
        ArrayList<int[]> originalPairs = new ArrayList<>();

        for (int[] cycle : cycles) {

            // splitting the taken cycle into three pairs and their relationships
            int[] currentPair1 = {cycle[0], cycle[1], cycle[3] / 100, 0};
            int[] currentPair2 = {cycle[1], cycle[2], (cycle[3] / 10) % 10, 0};
            int[] currentPair3 = {cycle[2], cycle[0], cycle[3] % 10, 0};

            int[][] currentPairs = {currentPair1, currentPair2, currentPair3};


            // filling the list with unique pairs
            for (int p = 0; p < 3; p++) {
                int[] currentPair = currentPairs[p];
                boolean addPair = true;
                if (originalPairs.size() == 0) originalPairs.add(currentPair);
                else {
                    for (int[] originalPair : originalPairs) {
                        if (originalPair[0] == currentPair[0] & originalPair[1] == currentPair[1]) {

                            // if we find a pair, stop searching for it, exit the loop and look for the next one
                            addPair = false;
                            break;
                        }
                    }
                    if (addPair) {
                        originalPairs.add(currentPair);
                    }
                }
            }
        }
        return originalPairs;
    }
}
