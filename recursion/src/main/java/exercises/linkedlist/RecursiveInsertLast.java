package exercises.linkedlist;

public class RecursiveInsertLast implements InsertLastStrategy {

    // For demo metrics (not required for a production impl)
    int observedDepth = 0; // package-private so the demo can read it if needed
    private ListNode insertLastHelper(ListNode node, int value, int depth) {
        if (node == null) {
            observedDepth = depth; // record depth at which we inserted
            return new ListNode(value);
        } else {
            node.next = insertLastHelper(node.next, value, depth + 1);
            return node;
        }
    }
    @Override
    public ListNode insertLast(ListNode head, int value) {
        observedDepth = 0;
        // reset before each operation
        return insertLastHelper(head, value, 1);
    }

    // Simple memory helpers for the demo
    private static long usedBytes() {
        Runtime rt = Runtime.getRuntime();
        return rt.totalMemory() - rt.freeMemory();
    }

    public static void main(String[] args) {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 5000;
        int trials = args.length > 1 ? Integer.parseInt(args[1]) : 3;

        // Build an initial list (iteratively to avoid skewing recursion demo)
        ListNode head = LinkedListFixtures.fromArray();
        for (int i = 0; i < n; i++) {
            ListNode node = new ListNode(i);
            if (head == null) head = node;
            else { ListNode p = head; while (p.next != null) p = p.next; p.next = node; }
        }

        RecursiveInsertLast strat = new RecursiveInsertLast();

        System.out.println("=== Recursive insertLast demo ===");
        System.out.println("List length = " + LinkedListFixtures.length(head));

        for (int t = 1; t <= trials; t++) {
            long beforeMem = usedBytes();
            long start = System.nanoTime();

            // Insert at tail (recursion depth ≈ list length)
            try {
                head = strat.insertLast(head, 42);
            } catch (StackOverflowError e) {
                System.err.println("Stack overflow occurred at trial " + t + " — list too deep for recursive insert.");
                break;
            }

            long end = System.nanoTime();
            long afterMem = usedBytes();

            // GC nudge (not guaranteed)
            System.gc();

            System.out.printf(
                    "Trial %d: time=%.3f ms, usedBefore=%.2f MB, usedAfter=%.2f MB, approxDepth=%d%n",
                    t, (end - start) / 1e6, beforeMem / (1024.0 * 1024.0), afterMem / (1024.0 * 1024.0),
                    strat.observedDepth
            );
        }
    }
}
