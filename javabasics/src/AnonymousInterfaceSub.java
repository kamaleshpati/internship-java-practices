public class AnonymousInterfaceSub {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("Child Thread");
            }
        };
        System.out.println(runnable.getClass().getName());
        Thread t = new Thread(runnable);
        System.out.println(t.getClass().getName());
        t.start();
        System.out.println("Main Thread");
    }
}
