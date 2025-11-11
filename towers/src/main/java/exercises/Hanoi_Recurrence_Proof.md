# Proof that T(n) = 3T(n-1) + 2 grows as 3ⁿ

## Step 1. Recurrence meaning
Each time we solve for **n disks**, the algorithm makes **three recursive calls** on smaller subproblems of size (n − 1), plus a constant amount of work (the `+2`).

\[
T(n) = 3T(n-1) + 2
\]

This describes a recursion tree where every level of the tree spawns **3 times** as many calls as the level above.

---

## Step 2. Expand the recurrence

We can expand \( T(n) \) repeatedly:

\[
\begin{aligned}
T(n) &= 3T(n-1) + 2 \\
     &= 3(3T(n-2) + 2) + 2 \\
     &= 3^2 T(n-2) + 3×2 + 2 \\
     &= 3^3 T(n-3) + 3^2×2 + 3×2 + 2 \\
     &\;\vdots \\
     &= 3^n T(0) + 2(3^{n-1} + 3^{n-2} + \dots + 3 + 1)
\end{aligned}
\]

---

## Step 3. Simplify the geometric series

The series in parentheses is a finite geometric sum:

\[
3^{n-1} + 3^{n-2} + \dots + 3 + 1 = \frac{3^n - 1}{3 - 1} = \frac{3^n - 1}{2}.
\]

Substitute back:

\[
T(n) = 3^n T(0) + 2 × \frac{3^n - 1}{2} = 3^n T(0) + (3^n - 1)
\]

Assuming \( T(0) = 0 \) (no disks → no moves):

\[
\boxed{T(n) = 3^n - 1}
\]

---

## Step 4. Big-O growth rate

For large \( n \), constants like “−1” don’t affect asymptotic behavior:

\[
T(n) = O(3^n)
\]

That means each additional disk roughly **triples** the total number of moves.

---

## Step 5. Comparison with standard Towers of Hanoi

| Version | Recurrence | Closed Form | Growth Base |
|----------|-------------|--------------|--------------|
| **Standard Hanoi** | \( T(n) = 2T(n-1) + 1 \) | \( T(n) = 2^n - 1 \) | base = 2 |
| **Must use middle peg** | \( T(n) = 3T(n-1) + 2 \) | \( T(n) = 3^n - 1 \) | base = 3 |

---

## Step 6. Intuitive summary

In recursive problems, the **multiplier** in front of \( T(n-1) \) determines the **base of exponential growth**.

- \( 2T(n-1) \) → doubles each level → \( 2^n \)
- \( 3T(n-1) \) → triples each level → \( 3^n \)
- \( kT(n-1) \) → k times bigger each level → \( k^n \)

So in this Towers of Hanoi variation, every move must pass through an extra step (the middle peg), causing the recursion tree to expand by a factor of 3 — that’s why the growth rate is **exponential with base 3**.
