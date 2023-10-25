import java.util.*;

public class AutoReducer {
    // last pair of the last iteration
    private volatile static int[] lastPair = {0, 0};

    /**
     * An algorithm that works with unique pairs and reduces cycles.
     *
     * @param cycles An ArrayList that contains found cycles in the matrix as the second parameter.
     * @param matrix The matrix that has cycles to be reduced.
     * @return The modified matrix.
     */
    public synchronized static int[][] ReduceCycles(ArrayList<int[]> cycles, int[][] matrix) {
        // 1. Creating a list of unique pairs with their relationships
        ArrayList<int[]> originalPairs = calculateAndGetUniquePairs(cycles);

        // (optional) 2. Populating cycle counts (creating a new array of pairs with new
        // relationships and calculating the cycle counts, storing them in the last cell)

        // 3. Displaying unique pairs with their original relationships
        displayUniquePairs(originalPairs);

        // 3.1. Modifying the array of original pairs by overwriting the first
        // half with a relationship value of 0 and counting the cycle counts
        modifyOriginalPairs(originalPairs, matrix);

        // 4. Sorting by the number of cycle counts
        originalPairs.sort(Comparator.comparingInt(a -> a[3]));

        // 5. Displaying pairs with modified relationships and their respective cycle counts
        displayOriginalPairs(originalPairs);

        // 6. Suggestions for improvement
        doSuggestions(originalPairs, matrix);

        return matrix;
    }

    private static void displayUniquePairs(ArrayList<int[]> originalPairs) {
        System.out.println("\nUnique pairs: ");
        for (int counter_unic = 0; counter_unic < originalPairs.size(); counter_unic++) {
            System.out.println((counter_unic + 1) + ". А" + (originalPairs.get(counter_unic)[0] + 1) + " " +
                    (originalPairs.get(counter_unic)[2] == 1 ? ">" : "*=") + " A" + (originalPairs.get(counter_unic)[1] + 1));
        }
        System.out.println();
    }

    private static void doSuggestions(ArrayList<int[]> originalPairs, int[][] matrix) {
        System.out.println("A path for achieving ordinal consistency effectively: ");

        int k = 0;

        // If the program goes into recursion (a case where
        // cycle 1 = 2 > 3 = 1 changes to 1 = 3 > 2 = 1), we skip this pair.
        if (lastPair[0] == originalPairs.get(k)[0] & lastPair[1] == originalPairs.get(k)[1]) k++;

        String operator = "";

        switch (originalPairs.get(k)[2]) {
            case 0:
                operator = "<";
                break;
            case 1:
                operator = ">";
                break;
            case 2:
                operator = "*=";
                break;
        }

        System.out.printf("New relationship A%d %s A%d, where will be %d cycles.%n", originalPairs.get(k)[0] + 1, operator, originalPairs.get(k)[1] + 1, originalPairs.get(k)[3]);

        lastPair = new int[] {originalPairs.get(k)[0], originalPairs.get(k)[1]};
        System.out.println();

        int choice = 0;
        // Depending on the choice, we change the value in the original
        // matrix and return it in a modified form.
        int firstIndex = originalPairs.get(choice)[0];
        int secondIndex = originalPairs.get(choice)[1];

        // If the value in the matrix...
        if (matrix[firstIndex][secondIndex] == 2){
            // ... needs to be changed to...
            if (originalPairs.get(choice)[2] == 0){
                // then
                matrix[firstIndex][secondIndex] = 0;
                // and be sure to have a mirror element.
                matrix[secondIndex][firstIndex] = 1;
            }
            else if(originalPairs.get(choice)[2] == 1){
                matrix[firstIndex][secondIndex] = 1;
                matrix[secondIndex][firstIndex] = 0;
            }
        }
        else if (matrix[firstIndex][secondIndex] == 1){
            if (originalPairs.get(choice)[2] == 0){
                matrix[firstIndex][secondIndex] = 0;
                matrix[secondIndex][firstIndex] = 1;
            }
            else if(originalPairs.get(choice)[2] == 2){
                matrix[firstIndex][secondIndex] = 2;
                matrix[secondIndex][firstIndex] = 2;
            }
        }
        else{
            System.out.println(" Attention! Algorithm error!");
        }

        System.out.println("\nChanged matrix: ");
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
                System.out.println("\nRange " + range + ": ");
            }

            System.out.println((counter_changed + 1) + ". New relationship А" + (originalPairs.get(counter_changed)[0] + 1) + " " + s + " A" + (originalPairs.get(counter_changed)[1] + 1)
                    + ", results in the following number of cycles: " + originalPairs.get(counter_changed)[3]);
        }
        System.out.println();
    }

    private static void modifyOriginalPairs(ArrayList<int[]> originalPairs, int[][] matrix) {
        int hiddenCounter = originalPairs.size();
        for (int i = 0; i < hiddenCounter; i++){
            // If we reach the first element with a < relationship, it means
            // we have gone through all pairs and should exit the loop
            if (originalPairs.get(i)[2] == 0) break;

            // 3.1.1. Temporary replacement in the relationship matrix: >
            // becomes =, and = becomes >; changing the relationship within
            // the pair and calculating cycles

            // Remembering values to revert the original matrix
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

            // Adding the cycle count value to the current pair
            originalPairs.get(i)[3] = temporaryCycles.size();

            // 3.1.2. Calculation for the < relationship (i.e., value 0) and adding
            // the pair to the end of the modified listmatrix[originalPairs.get(i)[0]][originalPairs.get(i)[1]] = 0;
            matrix[originalPairs.get(i)[0]][originalPairs.get(i)[1]] = 0;
            matrix[originalPairs.get(i)[1]][originalPairs.get(i)[0]] = 1;
            temporaryCycles = CyclesFinder.findCycles(matrix);
            originalPairs.add(new int[] {originalPairs.get(i)[0], originalPairs.get(i)[1], 0, temporaryCycles.size()});

            // Reverting to the old value in the relationship matrix
            matrix[originalPairs.get(i)[0]][originalPairs.get(i)[1]] = a;
            matrix[originalPairs.get(i)[1]][originalPairs.get(i)[0]] = b;
        }
    }

    private static ArrayList<int[]> calculateAndGetUniquePairs(ArrayList<int[]> cycles){
        ArrayList<int[]> originalPairs = new ArrayList<>();

        for (int[] cycle : cycles) {

            // Splitting the taken cycle into three pairs and their relationships
            int[] currentPair1 = {cycle[0], cycle[1], cycle[3] / 100, 0};
            int[] currentPair2 = {cycle[1], cycle[2], (cycle[3] / 10) % 10, 0};
            int[] currentPair3 = {cycle[2], cycle[0], cycle[3] % 10, 0};

            int[][] currentPairs = {currentPair1, currentPair2, currentPair3};


            // Populating the list with unique pairs
            for (int p = 0; p < 3; p++) {
                int[] currentPair = currentPairs[p];
                boolean addPair = true;
                if (originalPairs.size() == 0) originalPairs.add(currentPair);
                else {
                    for (int[] originalPair : originalPairs) {
                        if (originalPair[0] == currentPair[0] & originalPair[1] == currentPair[1]) {

                            // If a pair is found, stop the search, exit the loop, and look for the next one
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
        // originalPairs - a newly formed list of unique pairs
        // with their relationships, but without cycle counts upon modification
        return originalPairs;
    }
}
