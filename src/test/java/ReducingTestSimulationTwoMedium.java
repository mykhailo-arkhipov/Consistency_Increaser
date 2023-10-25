import org.junit.Test;

import java.util.List;

public class ReducingTestSimulationTwoMedium {
    @Test
    public void ReducingMinLoadTest10Size() {


        int[][] matrix = MatrixInput.generateMatrix(10);

        List<int[]> cycles = CyclesFinder.findCycles(matrix);

        long startTime = System.currentTimeMillis();

        while (true){

            matrix = FOR_TESTS_MatrixCyclesAutoReducer.ReduceCycles(cycles, matrix, 2);

            cycles = CyclesFinder.findCycles(matrix);
            // GraphVizualizer.VisualizeMatrix(matrix);

            if (cycles.size() == 0){
                int hop = matrix[0].length / 10;
                System.out.println(hop + ". Циклов больше нет. Программа завершила работу.");
                break;
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Симуляция лучшего выбора для " + matrix[0].length + " альтернатив заняло " + elapsedTime + " миллисекунд.");
        System.out.println();

    }
    @Test
    public void ReducingMinLoadTest20Size() {


        int[][] matrix = MatrixInput.generateMatrix(20);

        List<int[]> cycles = CyclesFinder.findCycles(matrix);

        long startTime = System.currentTimeMillis();

        while (true){

            matrix = FOR_TESTS_MatrixCyclesAutoReducer.ReduceCycles(cycles, matrix, 2);

            cycles = CyclesFinder.findCycles(matrix);
            // GraphVizualizer.VisualizeMatrix(matrix);

            if (cycles.size() == 0){
                int hop = matrix[0].length / 10;
                System.out.println(hop + ". Here is no cycles anymore. Program was ended.");
                break;
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Симуляция лучшего выбора для " + matrix[0].length + " альтернатив заняло " + elapsedTime + " миллисекунд.");
        System.out.println();

    }

    @Test
    public void ReducingMinLoadTest30Size() {


        int[][] matrix = MatrixInput.generateMatrix(30);

        List<int[]> cycles = CyclesFinder.findCycles(matrix);

        long startTime = System.currentTimeMillis();

        while (true){

            matrix = FOR_TESTS_MatrixCyclesAutoReducer.ReduceCycles(cycles, matrix, 2);

            cycles = CyclesFinder.findCycles(matrix);
            // GraphVizualizer.VisualizeMatrix(matrix);

            if (cycles.size() == 0){
                int hop = matrix[0].length / 10;
                System.out.println(hop + ". Циклов больше нет. Программа завершила работу.");
                break;
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Симуляция лучшего выбора для " + matrix[0].length + " альтернатив заняло " + elapsedTime + " миллисекунд.");
        System.out.println();

    }

    @Test
    public void ReducingMinLoadTest40Size() {


        int[][] matrix = MatrixInput.generateMatrix(40);

        List<int[]> cycles = CyclesFinder.findCycles(matrix);

        long startTime = System.currentTimeMillis();

        while (true){

            matrix = FOR_TESTS_MatrixCyclesAutoReducer.ReduceCycles(cycles, matrix, 2);

            cycles = CyclesFinder.findCycles(matrix);
            // GraphVizualizer.VisualizeMatrix(matrix);

            if (cycles.size() == 0){
                int hop = matrix[0].length / 10;
                System.out.println(hop + ". Циклов больше нет. Программа завершила работу.");
                break;
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Симуляция лучшего выбора для " + matrix[0].length + " альтернатив заняло " + elapsedTime + " миллисекунд.");
        System.out.println();

    }

    @Test
    public void ReducingMinLoadTest50Size() {


        int[][] matrix = MatrixInput.generateMatrix(50);

        List<int[]> cycles = CyclesFinder.findCycles(matrix);

        long startTime = System.currentTimeMillis();

        while (true){

            matrix = FOR_TESTS_MatrixCyclesAutoReducer.ReduceCycles(cycles, matrix, 2);

            cycles = CyclesFinder.findCycles(matrix);
            // GraphVizualizer.VisualizeMatrix(matrix);

            if (cycles.size() == 0){
                int hop = matrix[0].length / 10;
                System.out.println(hop + ". Циклов больше нет. Программа завершила работу.");
                break;
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Симуляция лучшего выбора для " + matrix[0].length + " альтернатив заняло " + elapsedTime + " миллисекунд.");
        System.out.println();

    }
}