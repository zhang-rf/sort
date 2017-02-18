import java.util.Arrays;

public class Main {

    static int n = 0;

    public static void main(String[] args) {
        int[] array = new int[]{49, 38, 65, 97, 76, 13, 27};
        insertSort(array);
        System.out.println(Arrays.toString(array));
    }

    static int[] copyOfRange(int[] original, int from, int to) {
        int[] array = new int[to - from];
        for (int i = from; i < to; i++)
            array[i - from] = original[i];
        return array;
    }

    static void arrayCopy(int[] src, int srcPos, int[] dest, int destPos, int length) {
        for (int i = 0; i < length; i++)
            dest[destPos++] = src[srcPos++];
    }

    static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] < array[j]) {
                    int t = array[i];
                    for (int k = i; k > j; k--)
                        array[k] = array[k - 1];
                    array[j] = t;
                    break;
                }
            }
        }
    }

    static void binaryInsertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int low = 0, high = i - 1, j = 0;
            while (low <= high) {
                j = (low + high) / 2;
                if (array[i] < array[j])
                    high = j - 1;
                else
                    low = j + 1;
            }

            if (array[i] < array[j]) {
                int t = array[i];
                for (int k = i; k > j; k--)
                    array[k] = array[k - 1];
                array[j] = t;
            }
        }
    }

    static void shellSort(int[] array, int[] d) {
        for (int group : d) {
            for (int g = 1; g < array.length && g <= group; g++) {
                for (int i = g * group; i < array.length; i += group) {
                    for (int j = (g - 1) * group; j < i; j += group) {
                        if (array[i] < array[j]) {
                            int t = array[i];
                            for (int k = i; k > j; k -= group)
                                array[k] = array[k - group];
                            array[j] = t;
                            break;
                        }
                    }
                }
            }
        }
    }

    static void bubbleSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = array.length - 1; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    int t = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = t;
                }
            }
        }
    }

    static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    static void quickSort(int[] array, int low, int high) {
        int _low = low;
        int _high = high;

        int pivotKey = low++;
        int pivot = array[pivotKey];
        while (low <= high) {
            for (; high > pivotKey; high--) {
                if (array[high] < pivot) {
                    array[pivotKey] = array[high];
                    pivotKey = high;
                }
            }

            for (; low < pivotKey; low++) {
                if (array[low] > pivot) {
                    array[pivotKey] = array[low];
                    pivotKey = low;
                }
            }
        }
        array[pivotKey] = pivot;

        if (pivotKey - _low > 1)
            quickSort(array, _low, pivotKey - 1);
        if (_high - pivotKey > 1)
            quickSort(array, pivotKey + 1, _high);
    }

    static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int k = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[k])
                    k = j;
            }
            if (k != i) {
                int t = array[i];
                array[i] = array[k];
                array[k] = t;
            }
        }
    }

    static void mergeSort(int[] array) {
        for (int i = 1; i < array.length; i *= 2) {
            for (int j = 0; j < array.length; j += i * 2) {
                int bounds = Math.min(j + i * 2, array.length);
                int[] temp = copyOfRange(array, j, bounds);

                int m = 0, n = i;
                for (int k = j; k < bounds; k++) {
                    if (m == i) {
                        arrayCopy(temp, n, array, k, bounds - k);
                        break;
                    } else if (n == i * 2 || n == temp.length) {
                        arrayCopy(temp, m, array, k, bounds - k);
                        break;
                    }

                    if (temp[m] < temp[n])
                        array[k] = temp[m++];
                    else
                        array[k] = temp[n++];
                }
            }
        }
    }

}
