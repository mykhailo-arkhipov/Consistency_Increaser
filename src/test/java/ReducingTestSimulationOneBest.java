import org.junit.Test;

import java.util.List;

public class ReducingTestSimulationOneBest {
    @Test
    public void reducingMinLoadTest10Size() {


        int[][] matrix = MatrixInput.generateMatrix(10);

        List<int[]> cycles = CyclesFinder.findCycles(matrix);

        long startTime = System.currentTimeMillis();

        while (true){

            matrix = FOR_TESTS_MatrixCyclesAutoReducer.ReduceCycles(cycles, matrix);

            cycles = CyclesFinder.findCycles(matrix);
            // GraphVisualizer.VisualizeMatrix(matrix);

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
    public void reducingMinLoadTest20Size() {


        int[][] matrix = MatrixInput.generateMatrix(20);

        List<int[]> cycles = CyclesFinder.findCycles(matrix);

        long startTime = System.currentTimeMillis();

        while (true){

            matrix = FOR_TESTS_MatrixCyclesAutoReducer.ReduceCycles(cycles, matrix);

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
    public void reducingMinLoadTest30Size() {


        int[][] matrix = MatrixInput.generateMatrix(30);

        List<int[]> cycles = CyclesFinder.findCycles(matrix);

        long startTime = System.currentTimeMillis();

        while (true){

            matrix = FOR_TESTS_MatrixCyclesAutoReducer.ReduceCycles(cycles, matrix);

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
    public void reducingMinLoadTest40Size() {


        int[][] matrix = MatrixInput.generateMatrix(40);

        List<int[]> cycles = CyclesFinder.findCycles(matrix);

        long startTime = System.currentTimeMillis();

        while (true){

            matrix = FOR_TESTS_MatrixCyclesAutoReducer.ReduceCycles(cycles, matrix);

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
    public void reducingMinLoadTest50Size() {


        int[][] matrix = MatrixInput.generateMatrix(50);

        List<int[]> cycles = CyclesFinder.findCycles(matrix);

        long startTime = System.currentTimeMillis();

        while (true){

            matrix = FOR_TESTS_MatrixCyclesAutoReducer.ReduceCycles(cycles, matrix);

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

    /*
    @Test
    public void ReducingMinLoadTest60Size() {


        int[][] matrix = MatrixInput.generateMatrix(60);

        List<int[]> cycles = CyclesActions.findCycles(matrix);

        long startTime = System.currentTimeMillis();

        while (true){

            matrix = MatrixCyclesAutoReducer.ReduceCycles(cycles, matrix);

            cycles = CyclesActions.findCycles(matrix);
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
    public void ReducingMinLoadTest70Size() {


        int[][] matrix = MatrixInput.generateMatrix(70);

        List<int[]> cycles = CyclesActions.findCycles(matrix);

        long startTime = System.currentTimeMillis();

        while (true){

            matrix = MatrixCyclesAutoReducer.ReduceCycles(cycles, matrix);

            cycles = CyclesActions.findCycles(matrix);
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
    public void ReducingMinLoadTest80Size() {


        int[][] matrix = MatrixInput.generateMatrix(80);

        List<int[]> cycles = CyclesActions.findCycles(matrix);

        long startTime = System.currentTimeMillis();

        while (true){

            matrix = MatrixCyclesAutoReducer.ReduceCycles(cycles, matrix);

            cycles = CyclesActions.findCycles(matrix);
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
    */
}