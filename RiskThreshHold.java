public class RiskThreshHold {

    public static void linearSearch(int[] risks, int target) {
        int comparisons = 0;
        boolean found = false;
        for (int i = 0; i < risks.length; i++) {
            comparisons++;
            if (risks[i] == target) {
                System.out.println("Linear: threshold=" + target + " found at index " + i + " (" + comparisons + " comparisons)");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Linear: threshold=" + target + " not found (" + comparisons + " comparisons)");
        }
    }


    public static void binaryFloorCeiling(int[] risks, int target) {
        int low = 0, high = risks.length - 1;
        int comparisons = 0;
        int floor = Integer.MIN_VALUE;
        int ceiling = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (risks[mid] == target) {
                floor = ceiling = risks[mid];
                break;
            } else if (risks[mid] < target) {
                floor = risks[mid];
                low = mid + 1;
            } else {
                ceiling = risks[mid];
                high = mid - 1;
            }
        }

        System.out.println("Binary search floor(" + target + "): " + floor + ", ceiling: " + ceiling + " (" + comparisons + " comparisons)");
    }

    public static void main(String[] args) {
        int[] sortedRisks = {10, 25, 50, 100};
        int threshold = 30;

        linearSearch(sortedRisks, threshold);
        binaryFloorCeiling(sortedRisks, threshold);
    }
}