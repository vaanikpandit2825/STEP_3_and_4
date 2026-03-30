public class AccountID {


    public static int linearFirstOccurrence(String[] logs, String target) {
        int comparisons = 0;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                System.out.println("Linear first occurrence of " + target + ": index " + i + " (" + comparisons + " comparisons)");
                return i;
            }
        }
        System.out.println("Linear first occurrence of " + target + ": not found (" + comparisons + " comparisons)");
        return -1;
    }


    public static int linearLastOccurrence(String[] logs, String target) {
        int comparisons = 0;
        int lastIndex = -1;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) lastIndex = i;
        }
        if (lastIndex != -1) {
            System.out.println("Linear last occurrence of " + target + ": index " + lastIndex + " (" + comparisons + " comparisons)");
        } else {
            System.out.println("Linear last occurrence of " + target + ": not found (" + comparisons + " comparisons)");
        }
        return lastIndex;
    }

    // Binary search for first found index + count occurrences
    public static int binarySearch(String[] logs, String target) {
        int low = 0, high = logs.length - 1;
        int comparisons = 0;
        int foundIndex = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (logs[mid].equals(target)) {
                foundIndex = mid;
                break;
            } else if (logs[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (foundIndex == -1) {
            System.out.println("Binary search " + target + ": not found (" + comparisons + " comparisons)");
            return -1;
        }

        // Count duplicates around foundIndex
        int count = 1;
        int left = foundIndex - 1;
        while (left >= 0 && logs[left].equals(target)) { count++; left--; comparisons++; }
        int right = foundIndex + 1;
        while (right < logs.length && logs[right].equals(target)) { count++; right++; comparisons++; }

        System.out.println("Binary search " + target + ": index " + foundIndex + " (" + comparisons + " comparisons), count=" + count);
        return foundIndex;
    }

    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        System.out.println("Original logs:");
        for (String log : logs) System.out.print(log + " ");
        System.out.println();

        // Linear search examples
        linearFirstOccurrence(logs, "accB");
        linearLastOccurrence(logs, "accB");

        // Sort logs for binary search
        java.util.Arrays.sort(logs);

        System.out.println("\nSorted logs for binary search:");
        for (String log : logs) System.out.print(log + " ");
        System.out.println();

        // Binary search example
        binarySearch(logs, "accB");
    }
}