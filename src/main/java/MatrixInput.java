import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MatrixInput {

    public static int[][] inputTypeChoice() {
        int[][] result = null;
        int choice = getChoice();

        switch (choice) {
            case 1 -> {
                System.out.println("How many elements will be in your square matrix?");
                int n = 0;
                while (true) {
                    try {
                        n = Integer.parseInt(Main.bufferedReader.readLine());
                        break;
                    } catch (IOException e) {
                        System.out.println("Please type again");
                    }
                }
                result = generateMatrix(n);
                Main.printMatrix(result);
            }
            case 2 -> {
                result = getManualInput();
                Main.printMatrix(result);
            }
            case 3 -> {
                result = getMatrixFromTxt();
                Main.printMatrix(result);
            }
            case 4 -> result = new int[][]{
                    {0, 0, 2, 2, 1},
                    {1, 0, 1, 1, 1},
                    {2, 0, 0, 2, 0},
                    {2, 0, 2, 0, 0},
                    {0, 0, 1, 1, 0}};
        }

        return result;
    }

    private static int getChoice() {
        int choice;
        while (true) {
            System.out.println("Choose the type of import: 1 - Generation; 2 - Manual input; 3 - TXT; 4 - From research ORCON");
            while (true) {
                try {
                    choice = Integer.parseInt(Main.bufferedReader.readLine());
                    if (choice != 1 && choice !=2 && choice != 3 && choice != 4 ){
                        throw new IOException();
                    }
                    return choice;
                } catch (IOException e) {
                    System.out.println("Error. Please retype:\n");
                }
            }
        }
    }

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        Random random = new Random();
        File file = new File("test.txt");

        for (int i = 0; i < n; i++) {
            matrix[i][i] = 0;
            for (int j = i + 1; j < n; j++) {
                matrix[i][j] = random.nextInt(3);
                if (matrix[i][j] == 0){
                matrix[j][i] = random.nextInt(2);
                }
                else if (matrix[i][j] == 1){
                    matrix[j][i] = 0;
                }
                else {
                    matrix[j][i] = 2;
                }
            }

        }

        try (FileWriter writer = new FileWriter(file, true)) {
            if (file.exists()) {
                // Adding two empty lines
                writer.write("\n\n");
            }

            // Writing the matrix into the file
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    writer.write(matrix[i][j] + ",");
                }
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    private static int[][] getManualInput() {
        File file = new File("test.txt");

        try(FileWriter writer = new FileWriter(file, true)){

        // Entering the matrix size
        System.out.print("Enter the number of elements: ");
        int rowsAndColumns = Integer.parseInt(Main.bufferedReader.readLine());

        // Creating the matrix
        int[][] matrix = new int[rowsAndColumns][rowsAndColumns];

        System.out.println("Populating the matrix:");
        for (int i = 0; i < rowsAndColumns; i++) {
            for (int j = 0; j < rowsAndColumns; j++) {
                System.out.println("element [" + i + " ; " + j + "] :");
                matrix[i][j] = Integer.parseInt(Main.bufferedReader.readLine());
            }
        }
        // Adding two empty lines
            if (file.exists()) writer.write("\n\n");

            // Writing the matrix to the file
            for (int[] ints : matrix) {
                for (int anInt : ints) {
                    writer.write(anInt + ",");
                }
                writer.write(System.lineSeparator());
            }
            return matrix;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[1][1];
    }

    private static int[][] getMatrixFromTxt() {
        List<int[]> matrixList = new ArrayList<>();
        String filePath = "";
        while (true) {
            try {
                filePath = Main.bufferedReader.readLine();
                break;
            } catch (IOException e) {
                System.out.println("Wrong path, try again");
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" ");
                    int[] row = new int[parts.length];
                    for (int i = 0; i < parts.length; i++) {
                        row[i] = Integer.parseInt(parts[i]);
                    }
                    matrixList.add(row);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // matrix could be not square in rare cases
            int numRows = matrixList.size();
            int numCols = matrixList.get(0).length;
            int[][] matrix = new int[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                matrix[i] = matrixList.get(i);
            }

            return matrix;
        }
}

