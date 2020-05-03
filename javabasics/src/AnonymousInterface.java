public class AnonymousInterface {
    static Hello h = new Hello() {
        public void show() {
            System.out.println("i am in anonymous class");
        }
    };

    public static void main(String[] args) {
        h.show();
    }
}

interface Hello {
    void show();
}
