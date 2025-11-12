package exercises;

import java.util.Scanner;

/**
 * Towers of Hanoi variation where every move must pass through the middle peg.
 * Prompts the user for the number of rings and reports the total number of moves.
 */
public class TowersVariations {

    // static counter for number of physical moves
    private static int count = 0;

    /**
     * Move n disks from A to C, ensuring every disk move
     * passes through B (i.e. all moves go via the middle peg).
     * Because each "move disk n" actually takes TWO physical moves,
     * the recurrence becomes:: T(n) = 3*T(n-1) + 2
     *
     * @param n number of disks
     * @param from source peg label (1..3)
     * @param mid middle peg label (must be used for every move)
     * @param to destination peg label (1..3)
     */
    public static void solveVariation(int n, int from, int mid, int to) {
        if (n == 0) return;

        // Move n-1 disks from 'from' -> 'mid' (using 'to' as helper)
        solveVariation(n - 1, from, mid, to);

        // Move disk n from 'from' -> 'mid' -> 'to' (two physical moves)
        count += 2;
        System.out.printf("Move disk %d: %d -> %d%n", n, from, mid);
        System.out.printf("Move disk %d: %d -> %d%n", n, mid, to);

        // Move n-1 disks from 'mid' -> 'to' (using 'from' as helper)
        solveVariation(n - 1, mid, from, to);
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter number of rings (negative to quit): ");
                if (!in.hasNextInt()) {
                    in.next(); // consume invalid token
                    System.out.println("Please enter an integer.");
                    continue;
                }

                int n = in.nextInt();
                if (n < 0) {
                    System.out.println("Goodbye!");
                    break;
                }

                count = 0;
                solveVariation(n, 1, 2, 3);
                System.out.printf("Number of moves (variation) = %d%n", count);
            }
        }
    }
}
