class OuterN {
    void outerMethod() {
        int x = 98;
        System.out.println("inside outerMethod");
         class Inner {
            void innerMethod() {
                System.out.println("x= " + x);
            }
        }
        Inner y = new Inner();
        y.innerMethod();
    }
}

public class LocalError {
    public static void main(String[] args) {
        OuterN x = new OuterN();
        x.outerMethod();
    }
}
