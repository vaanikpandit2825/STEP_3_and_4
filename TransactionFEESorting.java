import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp;

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }
}

public class TransactionFEESorting{

    static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0;
        int swaps = 0;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        System.out.println("Bubble passes: " + passes + ", swaps: " + swaps);
    }

    static void insertionSort(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;
            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    static int compare(Transaction a, Transaction b) {
        if (a.fee != b.fee) {
            return Double.compare(a.fee, b.fee);
        }
        return a.timestamp.compareTo(b.timestamp);
    }

    static List<Transaction> findOutliers(List<Transaction> list) {
        List<Transaction> res = new ArrayList<>();
        for (Transaction t : list) {
            if (t.fee > 50) res.add(t);
        }
        return res;
    }

    static void printList(List<Transaction> list) {
        for (Transaction t : list) {
            System.out.print(t.id + ":" + t.fee + "@" + t.timestamp + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        List<Transaction> bubbleList = new ArrayList<>(transactions);
        bubbleSort(bubbleList);
        printList(bubbleList);

        List<Transaction> insertionList = new ArrayList<>(transactions);
        insertionSort(insertionList);
        printList(insertionList);

        List<Transaction> outliers = findOutliers(transactions);
        System.out.print("Outliers: ");
        printList(outliers);
    }
}