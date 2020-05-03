/*public class Recursion {
        static int factTR(int n, int a)
        {
            if (n == 0)
                return a;

            return factTR(n - 1, n * a);
        }

        static int fact(int n)
        {
            return factTR(n, 1);
        }

        static public void main (String[] args)
        {
            System.out.println(fact(5));
        }
}*/


class Recursion {

    static int fact(int n)
    {
        if (n == 0) return 1;

        return n*fact(n-1);
    }

    public static void main(String[] args)
    {
        System.out.println(fact(5));
    }
}

