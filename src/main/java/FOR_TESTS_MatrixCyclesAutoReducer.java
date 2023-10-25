import java.util.*;

public class FOR_TESTS_MatrixCyclesAutoReducer {

    public static int[] lastPair = {0, 0};

    public static int[][] ReduceCycles(List<int[]> cycles, int[][] matrix) {

        List<int[]> originalPairs = new ArrayList<>();

        // 1. Цикл для создания списка уникальных пар с их отношениями

        for (int[] cycle : cycles) {

            // разбиение взятого цикла на три пары и их отношения
            int[] currentPair1 = {cycle[0], cycle[1], cycle[3] / 100, 0};
            int[] currentPair2 = {cycle[1], cycle[2], (cycle[3] / 10) % 10, 0};
            int[] currentPair3 = {cycle[2], cycle[0], cycle[3] % 10, 0};

            int[][] currentPairs = {currentPair1, currentPair2, currentPair3};


            // заполнение списка уникальными парами
            for (int p = 0; p < 3; p++) {
                int[] currentPair = currentPairs[p];
                boolean addPair = true;
                if (originalPairs.size() == 0) originalPairs.add(currentPair);
                else {
                    for (int[] originalPair : originalPairs) {
                        if (originalPair[0] == currentPair[0] & originalPair[1] == currentPair[1]) {

                            // если нашли пару, прекращаем её поиск, выходим из цикла и ищем следующую
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
        // originalPairs - новый cформированный список уникальных пар с их отношением, но ещё без количества циклов при изменении

        // 2. Заполнение количества циклов (формирование нового массива пар с новыми отношениями и расчёт количества циклов с записью их в последнюю ячейку)


        // 3. Вывод уникальных пар с их оригинальными отношениями

        //System.out.println();
        //System.out.println("Уникальные пары: ");
        //for (int counter_unic = 0; counter_unic < originalPairs.size(); counter_unic++) {
        //    System.out.println((counter_unic + 1) + ". А" + (originalPairs.get(counter_unic)[0] + 1) + " " +
        //            (originalPairs.get(counter_unic)[2] == 1 ? ">" : "*=") + " A" + (originalPairs.get(counter_unic)[1] + 1));
        //}

        // System.out.println();


        int counterForMaximum = originalPairs.size();
        int counterForCheckMax = 0;
        // 3.1. изменение массива оригинальных пар с помощью перезаписи первой половины на значение 0 в отношении и подсчёт количества циклов

        int hidenCounter = originalPairs.size();
        for (int i = 0; i < hidenCounter; i++){
            // Если доходим до первого элемента с отношением <, то это означает, что мы прошли все пары и нужно выйти из цикла
            if (originalPairs.get(i)[2] == 0) break;

            // 3.1.1. Временная замена в матрице отношения с > на = и с = на >, изменение отношения в самой паре и расчёт циклов

            // запоминаем значения для возврата исходной матрицы
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

            // добавление значения количества циклов в текущую пару
            originalPairs.get(i)[3] = temporaryCycles.size();
            temporaryCycles = null;

            // 3.1.2. расчёт для отношения < (то есть значения 0) и добавление пары в конец модифицированного списка
            matrix[originalPairs.get(i)[0]][originalPairs.get(i)[1]] = 0;
            matrix[originalPairs.get(i)[1]][originalPairs.get(i)[0]] = 1;
            temporaryCycles = CyclesFinder.findCycles(matrix);
            originalPairs.add(new int[] {originalPairs.get(i)[0], originalPairs.get(i)[1], 0, temporaryCycles.size()});
            temporaryCycles = null;

            // возврат старого значения в матрице
            matrix[originalPairs.get(i)[0]][originalPairs.get(i)[1]] = a;
            matrix[originalPairs.get(i)[1]][originalPairs.get(i)[0]] = b;
        }

        // 4. Сортировка по количеству циклов
        originalPairs.sort(Comparator.comparingInt(a -> a[3]));



        // 5. Вывод пар с изменёнными отношениями и соответствующими количествами циклов
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
                // System.out.println();
                // System.out.println("Ранг " + range + ": ");
            }



            // System.out.println((counter_changed + 1) + ". Новое отношение А" + (originalPairs.get(counter_changed)[0] + 1) + " " + s + " A" + (originalPairs.get(counter_changed)[1] + 1)
            //        + ", приведёт к следующему количеству циклов: " + originalPairs.get(counter_changed)[3]);
        }

        // System.out.println();

        // 6. Предложения по улучшению

        // System.out.println("Путь для эффективного достижения порядковой согласованности: ");


        int comparable_variable = originalPairs.get(0)[3];
        //for (int k = 0; k < originalPairs.size(); k++) {

        // (для ручного) если количество циклов больше предложенного ранее, выходим из цикла
        // if (originalPairs.get(k)[3] > comparable_variable) break;



        int k = 0;
        // если программа уходит в рекурсию (случай, где цикл 1 = 2 > 3 = 1 меняется на 1 = 3 > 2 = 1), пропускаем эту пару
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



        // System.out.println("Новое отношение A" + (originalPairs.get(k)[0] + 1) +
        //         " " + s + " A" + (originalPairs.get(k)[1] + 1) + ", где останется " + originalPairs.get(k)[3] + " циклов.");

        lastPair = new int[] {originalPairs.get(k)[0], originalPairs.get(k)[1]};

        //}

        // System.out.println();

        /*

        System.out.println("Введите номер изменения (или 0 для выхода): ");
        Scanner sc = new Scanner(System.in);
        int ch = sc.nextInt();
        if (ch == 0) {
            return null;
        }
        ch--;

         */

        int ch = 0;
        // в зависимости от выбора меняем значение в оригинальной матрице и возвращаем её в изменённом виде

        int fir = originalPairs.get(ch)[0];
        int sec = originalPairs.get(ch)[1];

        // если значение матрицы ...
        if (matrix[fir][sec] == 2){
            // ... нужно поменять на ...
            if (originalPairs.get(ch)[2] == 0){
                // то
                matrix[fir][sec] = 0;
                // и обязательно зеркальный элемент
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
        //else{
        //    System.out.println(" Внимание! Ошибка алгоритма!");
        //}

        //System.out.println();
        //System.out.println("Изменённая матрица: ");
        //Main.printMatrix(matrix);
        //System.out.println();

        return matrix;
    }

    public static int[][] ReduceCycles(List<int[]> cycles, int[][] matrix, int whichCase) {

        List<int[]> originalPairs = new ArrayList<>();

        // 1. Цикл для создания списка уникальных пар с их отношениями

        for (int[] cycle : cycles) {

            // разбиение взятого цикла на три пары и их отношения
            int[] currentPair1 = {cycle[0], cycle[1], cycle[3] / 100, 0};
            int[] currentPair2 = {cycle[1], cycle[2], (cycle[3] / 10) % 10, 0};
            int[] currentPair3 = {cycle[2], cycle[0], cycle[3] % 10, 0};

            int[][] currentPairs = {currentPair1, currentPair2, currentPair3};


            // заполнение списка уникальными парами
            for (int p = 0; p < 3; p++) {
                int[] currentPair = currentPairs[p];
                boolean addPair = true;
                if (originalPairs.size() == 0) originalPairs.add(currentPair);
                else {
                    for (int[] originalPair : originalPairs) {
                        if (originalPair[0] == currentPair[0] & originalPair[1] == currentPair[1]) {

                            // если нашли пару, прекращаем её поиск, выходим из цикла и ищем следующую
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
        // originalPairs - новый cформированный список уникальных пар с их отношением, но ещё без количества циклов при изменении

        // 2. Заполнение количества циклов (формирование нового массива пар с новыми отношениями и расчёт количества циклов с записью их в последнюю ячейку)


        // 3. Вывод уникальных пар с их оригинальными отношениями

        //System.out.println();
        //System.out.println("Уникальные пары: ");
        //for (int counter_unic = 0; counter_unic < originalPairs.size(); counter_unic++) {
        //    System.out.println((counter_unic + 1) + ". А" + (originalPairs.get(counter_unic)[0] + 1) + " " +
        //            (originalPairs.get(counter_unic)[2] == 1 ? ">" : "*=") + " A" + (originalPairs.get(counter_unic)[1] + 1));
        //}

        // System.out.println();


        int counterForMaximum = originalPairs.size();
        int counterForCheckMax = 0;
        // 3.1. изменение массива оригинальных пар с помощью перезаписи первой половины на значение 0 в отношении и подсчёт количества циклов

        int hidenCounter = originalPairs.size();
        for (int i = 0; i < hidenCounter; i++){
            // Если доходим до первого элемента с отношением <, то это означает, что мы прошли все пары и нужно выйти из цикла
            if (originalPairs.get(i)[2] == 0) break;

            // 3.1.1. Временная замена в матрице отношения с > на = и с = на >, изменение отношения в самой паре и расчёт циклов

            // запоминаем значения для возврата исходной матрицы
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

            // добавление значения количества циклов в текущую пару
            originalPairs.get(i)[3] = temporaryCycles.size();
            temporaryCycles = null;

            // 3.1.2. расчёт для отношения < (то есть значения 0) и добавление пары в конец модифицированного списка
            matrix[originalPairs.get(i)[0]][originalPairs.get(i)[1]] = 0;
            matrix[originalPairs.get(i)[1]][originalPairs.get(i)[0]] = 1;
            temporaryCycles = CyclesFinder.findCycles(matrix);
            originalPairs.add(new int[] {originalPairs.get(i)[0], originalPairs.get(i)[1], 0, temporaryCycles.size()});
            temporaryCycles = null;

            // возврат старого значения в матрице
            matrix[originalPairs.get(i)[0]][originalPairs.get(i)[1]] = a;
            matrix[originalPairs.get(i)[1]][originalPairs.get(i)[0]] = b;
        }

        // 4. Сортировка по количеству циклов
        originalPairs.sort(Comparator.comparingInt(a -> a[3]));



        // 5. Вывод пар с изменёнными отношениями и соответствующими количествами циклов
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
                // System.out.println();
                // System.out.println("Ранг " + range + ": ");
            }



            // System.out.println((counter_changed + 1) + ". Новое отношение А" + (originalPairs.get(counter_changed)[0] + 1) + " " + s + " A" + (originalPairs.get(counter_changed)[1] + 1)
            //        + ", приведёт к следующему количеству циклов: " + originalPairs.get(counter_changed)[3]);
        }

        // System.out.println();

        // 6. Предложения по улучшению

        // System.out.println("Путь для эффективного достижения порядковой согласованности: ");


        int comparable_variable = originalPairs.get(0)[3];
        //for (int k = 0; k < originalPairs.size(); k++) {

        // (для ручного) если количество циклов больше предложенного ранее, выходим из цикла
        // if (originalPairs.get(k)[3] > comparable_variable) break;



        int k = 0;
        // если программа уходит в рекурсию (случай, где цикл 1 = 2 > 3 = 1 меняется на 1 = 3 > 2 = 1), пропускаем эту пару
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



        // System.out.println("Новое отношение A" + (originalPairs.get(k)[0] + 1) +
        //         " " + s + " A" + (originalPairs.get(k)[1] + 1) + ", где останется " + originalPairs.get(k)[3] + " циклов.");

        lastPair = new int[] {originalPairs.get(k)[0], originalPairs.get(k)[1]};

        //}

        // System.out.println();

        /*

        System.out.println("Введите номер изменения (или 0 для выхода): ");
        Scanner sc = new Scanner(System.in);
        int ch = sc.nextInt();
        if (ch == 0) {
            return null;
        }
        ch--;

         */
        int ch;
        Random rnd = new Random();
        if (whichCase == 3){
            ch = rnd.nextInt(6) + 2; // 2 3 4 5 6 7
        }
        else if (whichCase == 2){
            ch = rnd.nextInt(2); // 0 1
        }
        else ch = 0;

        if (ch == 1){
            ch = originalPairs.size()/2;
        }

        if (ch == 2){
            ch = 0;
        }
        else if (ch > 2){
            ch = originalPairs.size() / (ch - 1);
            ch--;
        }


        // в зависимости от выбора меняем значение в оригинальной матрице и возвращаем её в изменённом виде

        int fir = originalPairs.get(ch)[0];
        int sec = originalPairs.get(ch)[1];

        // если значение матрицы ...
        if (matrix[fir][sec] == 2){
            // ... нужно поменять на ...
            if (originalPairs.get(ch)[2] == 0){
                // то
                matrix[fir][sec] = 0;
                // и обязательно зеркальный элемент
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
        //else{
        //    System.out.println(" Внимание! Ошибка алгоритма!");
        //}

        //System.out.println();
        //System.out.println("Изменённая матрица: ");
        //Main.printMatrix(matrix);
        //System.out.println();

        return matrix;
    }


    public static int[][] ReduceCycles(boolean fops, List<int[]> cycles, int[][] matrix) {

        List<int[]> originalPairs = new ArrayList<>();

        // 1. Цикл для создания списка уникальных пар с их отношениями

        for (int[] cycle : cycles) {

            // разбиение взятого цикла на три пары и их отношения
            int[] currentPair1 = {cycle[0], cycle[1], cycle[3] / 100, 0};
            int[] currentPair2 = {cycle[1], cycle[2], (cycle[3] / 10) % 10, 0};
            int[] currentPair3 = {cycle[2], cycle[0], cycle[3] % 10, 0};

            int[][] currentPairs = {currentPair1, currentPair2, currentPair3};


            // заполнение списка уникальными парами
            for (int p = 0; p < 3; p++) {
                int[] currentPair = currentPairs[p];
                boolean addPair = true;
                if (originalPairs.size() == 0) originalPairs.add(currentPair);
                else {
                    for (int[] originalPair : originalPairs) {
                        if (originalPair[0] == currentPair[0] & originalPair[1] == currentPair[1]) {

                            // если нашли пару, прекращаем её поиск, выходим из цикла и ищем следующую
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
        // originalPairs - новый cформированный список уникальных пар с их отношением, но ещё без количества циклов при изменении

        // 2. Заполнение количества циклов (формирование нового массива пар с новыми отношениями и расчёт количества циклов с записью их в последнюю ячейку)


        // 3. Вывод уникальных пар с их оригинальными отношениями

        //System.out.println();
        //System.out.println("Уникальные пары: ");
        //for (int counter_unic = 0; counter_unic < originalPairs.size(); counter_unic++) {
        //    System.out.println((counter_unic + 1) + ". А" + (originalPairs.get(counter_unic)[0] + 1) + " " +
        //            (originalPairs.get(counter_unic)[2] == 1 ? ">" : "*=") + " A" + (originalPairs.get(counter_unic)[1] + 1));
        //}

        // System.out.println();


        int counterForMaximum = originalPairs.size();
        int counterForCheckMax = 0;
        // 3.1. изменение массива оригинальных пар с помощью перезаписи первой половины на значение 0 в отношении и подсчёт количества циклов

        int hidenCounter = originalPairs.size();
        for (int i = 0; i < hidenCounter; i++){
            // Если доходим до первого элемента с отношением <, то это означает, что мы прошли все пары и нужно выйти из цикла
            if (originalPairs.get(i)[2] == 0) break;

            // 3.1.1. Временная замена в матрице отношения с > на = и с = на >, изменение отношения в самой паре и расчёт циклов

            // запоминаем значения для возврата исходной матрицы
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

            // добавление значения количества циклов в текущую пару
            originalPairs.get(i)[3] = temporaryCycles.size();
            temporaryCycles = null;

            // 3.1.2. расчёт для отношения < (то есть значения 0) и добавление пары в конец модифицированного списка
            matrix[originalPairs.get(i)[0]][originalPairs.get(i)[1]] = 0;
            matrix[originalPairs.get(i)[1]][originalPairs.get(i)[0]] = 1;
            temporaryCycles = CyclesFinder.findCycles(matrix);
            originalPairs.add(new int[] {originalPairs.get(i)[0], originalPairs.get(i)[1], 0, temporaryCycles.size()});
            temporaryCycles = null;

            // возврат старого значения в матрице
            matrix[originalPairs.get(i)[0]][originalPairs.get(i)[1]] = a;
            matrix[originalPairs.get(i)[1]][originalPairs.get(i)[0]] = b;
        }

        // 4. Сортировка по количеству циклов
        originalPairs.sort(Comparator.comparingInt(a -> a[3]));



        // 5. Вывод пар с изменёнными отношениями и соответствующими количествами циклов
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
                // System.out.println();
                // System.out.println("Ранг " + range + ": ");
            }



            // System.out.println((counter_changed + 1) + ". Новое отношение А" + (originalPairs.get(counter_changed)[0] + 1) + " " + s + " A" + (originalPairs.get(counter_changed)[1] + 1)
            //        + ", приведёт к следующему количеству циклов: " + originalPairs.get(counter_changed)[3]);
        }

        // System.out.println();

        // 6. Предложения по улучшению

        // System.out.println("Путь для эффективного достижения порядковой согласованности: ");


        int comparable_variable = originalPairs.get(0)[3];
        //for (int k = 0; k < originalPairs.size(); k++) {

        // (для ручного) если количество циклов больше предложенного ранее, выходим из цикла
        // if (originalPairs.get(k)[3] > comparable_variable) break;



        int k = 0;
        // если программа уходит в рекурсию (случай, где цикл 1 = 2 > 3 = 1 меняется на 1 = 3 > 2 = 1), пропускаем эту пару
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



        // System.out.println("Новое отношение A" + (originalPairs.get(k)[0] + 1) +
        //         " " + s + " A" + (originalPairs.get(k)[1] + 1) + ", где останется " + originalPairs.get(k)[3] + " циклов.");

        lastPair = new int[] {originalPairs.get(k)[0], originalPairs.get(k)[1]};

        //}

        // System.out.println();

        /*

        System.out.println("Введите номер изменения (или 0 для выхода): ");
        Scanner sc = new Scanner(System.in);
        int ch = sc.nextInt();
        if (ch == 0) {
            return null;
        }
        ch--;

         */

        int ch = 0;
        // в зависимости от выбора меняем значение в оригинальной матрице и возвращаем её в изменённом виде

        int fir = originalPairs.get(ch)[0];
        int sec = originalPairs.get(ch)[1];

        // если значение матрицы ...
        if (matrix[fir][sec] == 2){
            // ... нужно поменять на ...
            if (originalPairs.get(ch)[2] == 0){
                // то
                matrix[fir][sec] = 0;
                // и обязательно зеркальный элемент
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
        //else{
        //    System.out.println(" Внимание! Ошибка алгоритма!");
        //}

        //System.out.println();
        //System.out.println("Изменённая матрица: ");
        //Main.printMatrix(matrix);
        //System.out.println();

        return matrix;
    }



}
