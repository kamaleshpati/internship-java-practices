class NestedClass {
    class Inner
    {
        void print()
        {
            System.out.println("jcbjk");
        }
    }
}

public class Mains
{
    public static void main(String[] args) {
        NestedClass nc=new NestedClass();
        NestedClass.Inner in=nc.new Inner();
        in.print();
    }
}
