class Main
{
    // Naive recursive solution to calculate pow(x, n)
    // using Divide & Conquer
    public static int power(int x, int n)
    {
        // base condition
        if (n == 0) {
            return 1;
        }

        if ((n & 1) == 1) { // if n is odd
            System.out.println("res odd(" + x + "," + n);
            return x * power(x, n / 2) * power(x, n / 2);
        }

        System.out.println("res(" + x + "," + n);

        // else n is even
        return power(x, n / 2) * power(x, n / 2);
    }

    public static void main(String[] args)
    {
        int x = -2;
        int n = 10;

        System.out.println("pow(" + x + "," + n + ") = " + power(x, n));
    }
}
