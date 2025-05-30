import java.util.Scanner;
import java.util.Random;

public class sorts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean salir = false;

        int[] arr200 = new int[80000];
        int[] arr50 = new int[5000];
        int[] arr10 = new int[100];

        for (int i = 0; i < arr200.length; i++) {
            arr200[i] = random.nextInt(100000);
        }
        for (int i = 0; i < arr50.length; i++) {
            arr50[i] = random.nextInt(100000);
        }
        for (int i = 0; i < arr10.length; i++) {
            arr10[i] = random.nextInt(100000);
        }

        while (!salir) {
            System.out.println("\nSeleccione el array a ordenar:");
            System.out.println("1. Array de " + arr200.length + " elementos");
            System.out.println("2. Array de " + arr50.length + " elementos");
            System.out.println("3. Array de " + arr10.length + " elementos");
            System.out.println("4. Salir");
            
            int arrayChoice = scanner.nextInt();
            
            if (arrayChoice == 4) {
                salir = true;
                continue;
            }
            
            if (arrayChoice < 1 || arrayChoice > 3) {
                System.out.println("Opción inválida");
                continue;
            }

            int[] selectedArray = (arrayChoice == 1) ? arr200 : (arrayChoice == 2) ? arr50 : arr10;
            System.out.println("\nArray seleccionado: ");
            printArray(selectedArray);

            System.out.println("\nSeleccione el algoritmo de ordenamiento:");
            System.out.println("1. Insertion Sort");
            System.out.println("2. Heap sort");
            System.out.println("3. Quick Sort");
            System.out.println("4. Merge Sort");
            System.out.println("5. Selection Sort");
            System.out.println("6. Bubble Sort");
            
            int opcion = scanner.nextInt();
            
            if (opcion < 1 || opcion > 6) {
                System.out.println("Opción inválida");
                continue;
            }

            int[] arrCopy = selectedArray.clone();
            long startTime = System.nanoTime();
            
            switch (opcion) {
                case 1:
                    insertionSort(arrCopy);
                    break;
                case 2:
                    heapSort(arrCopy);
                    break;
                case 3:
                    quickSort(arrCopy, 0, arrCopy.length - 1);
                    break;
                case 4:
                    mergeSort(arrCopy, 0, arrCopy.length - 1);
                    break;
                case 5:
                    selectionSort(arrCopy);
                    break;
                case 6:
                    bubbleSort(arrCopy);
                    break;
            }
            
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1_000_000.0; //dividido entre 1000000 para convertir a milisegundos
            System.out.println("Array ordenado: ");
            printArray(arrCopy);
            System.out.println("\nTiempo de ejecución: " + duration + " milisegundos");
        }
    }


    //print array
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //insertion sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    //heap sort
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    //quick sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }


    //merge sort
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;    
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    //selection sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }


    //bubble sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


}
