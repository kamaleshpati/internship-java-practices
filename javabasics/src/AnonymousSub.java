class Demo {
    void show() {
        System.out.println("i am in show method of super class");
    }
}

public class AnonymousSub {

    static Demo d = new Demo() {
        void show() {
            super.show();
            System.out.println("i am in Flavor1Demo class");
        }
    };

    public static void main(String[] args) {
        d.show();
    }

}
