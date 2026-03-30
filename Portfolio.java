class Asset {
    String name;
    double returnRate; // in percent, e.g., 12.0 for 12%
    double volatility; // optional, for tie-breaker in quick sort

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    public String toString() {
        return name + ":" + returnRate + "% (Vol:" + volatility + ")";
    }
}

public class Portfolio {

    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].returnRate <= arr[j].returnRate) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(Asset[] arr, int low, int high) {
        int pivotIndex = low + (high - low) / 2; // median-of-3 simplified
        Asset pivot = arr[pivotIndex];
        Asset temp = arr[pivotIndex];
        arr[pivotIndex] = arr[high];
        arr[high] = temp;

        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot.returnRate ||
                    (arr[j].returnRate == pivot.returnRate && arr[j].volatility < pivot.volatility)) {
                i++;
                Asset swap = arr[i];
                arr[i] = arr[j];
                arr[j] = swap;
            }
        }

        Asset swap = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = swap;

        return i + 1;
    }

    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12.0, 0.3),
                new Asset("TSLA", 8.0, 0.5),
                new Asset("GOOG", 15.0, 0.2)
        };

        mergeSort(assets, 0, assets.length - 1);
        System.out.println("Merge Sort (Ascending returnRate):");
        for (Asset a : assets) System.out.println(a);

        quickSort(assets, 0, assets.length - 1);
        System.out.println("\nQuick Sort (Descending returnRate, Vol ASC):");
        for (Asset a : assets) System.out.println(a);
    }
}