class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    public String toString() {
        return name + ":" + riskScore + " (Bal:" + accountBalance + ")";
    }
}

public class Client_Risk_Scoring_Ranking {

    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swapCount = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapCount++;
                    System.out.println("Swapped: " + arr[j].name + " & " + arr[j + 1].name);
                }
            }
        }

        System.out.println("Total swaps: " + swapCount);
    }

    public static void insertionSort(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                    (arr[j].riskScore < key.riskScore ||
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].accountBalance < key.accountBalance))) {

                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static void printTopN(Client[] arr, int n) {
        System.out.println("Top " + n + " highest risk clients:");
        for (int i = 0; i < n && i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        System.out.println("Original:");
        for (Client c : clients) System.out.println(c);

        bubbleSort(clients);

        System.out.println("\nAfter Bubble Sort (Ascending):");
        for (Client c : clients) System.out.println(c);

        insertionSort(clients);

        System.out.println("\nAfter Insertion Sort (Descending):");
        for (Client c : clients) System.out.println(c);

        printTopN(clients, 3);
    }
}