import java.util.ArrayList;

interface FuncInterface
{
    // An abstract function
    void abstractFun(int x);

    // A non-abstract (or default) function
    default void normalFun()
    {
        System.out.println("Hello");
    }
}

public class LambdaExpr {

    public static void main(String args[])
    {
        ArrayList<Integer> arrL = new ArrayList<Integer>();
        arrL.add(1);
        arrL.add(2);
        arrL.add(3);
        arrL.add(4);

        arrL.forEach(n -> System.out.println(n));

        // Using lambda expression to print even elements
        arrL.forEach(n -> { if (n%2 == 0) System.out.println(n); });
    }
}
