
public class OnesCounter {

//    Дан массив из нулей и единиц. Нужно определить, какой максимальный по длине
//    подынтервал единиц можно получить,
//    удалив ровно один элемент массива.
//    Вернуть 0, если такого подынтервала не существует.

    public static int count(int[] array) {
        if (array.length < 2) return 0;
        int idx = 0;
        int max = 0;
        int prevCount = 0;
        int curCount = 0;
        while (idx < array.length) {
            if (array[idx] == 0) {
                if (max < prevCount + curCount) max = prevCount + curCount;
                prevCount = curCount;
                curCount = 0;
            } else {
                curCount++;
            }
            idx++;
        }
        if (max < prevCount + curCount) max = prevCount + curCount;
        if (max == array.length) return max - 1;
        return max;
    }
}
