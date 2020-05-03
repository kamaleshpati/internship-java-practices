public class Garbage {

    public static void main(String[] args) throws InterruptedException {
        Garbage t1 = new Garbage();
        Garbage t2 = new Garbage();

        // Nullifying the reference variable

        // Nullifying the reference variable
        t2 = t1;
        Runtime.getRuntime().gc();

    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Garbage collector called");
        System.out.println("Object garbage collected : " + this);
    }
}
