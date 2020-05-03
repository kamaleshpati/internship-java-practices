public class ThreadEx extends Thread {

    public void run() {
        System.out.println("Hello from a thread!"+this.getName());
    }

    public static void main(String args[]) {
        ThreadEx threadEx1=new ThreadEx();
        ThreadEx threadEx2=new ThreadEx();
        threadEx1.start();
        threadEx2.start();
        System.out.println("nsdkjdcnk");
        System.out.println(threadEx1.getName());
        System.out.println(threadEx2.getName());
    }
}
