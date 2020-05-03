class OuterWork {

    void outerMethod() {
        System.out.println("inside outerMethod");

        class Inner {
            void innerMethod() {
                System.out.println("inside innerMethod");
            }
        }
        Inner y = new Inner();
        y.innerMethod();
    }
}

public class Local {
    public static void main(String[] args) {
        OuterWork x = new OuterWork();
        x.outerMethod();
    }
}

