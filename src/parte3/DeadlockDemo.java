package parte3;

public class DeadlockDemo {
    static final Object LOCK_A = new Object();
    static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("T1 tentando LOCK_A");
            synchronized (LOCK_A) {
                System.out.println("T1 pegou LOCK_A");
                dormir(50);
                System.out.println("T1 tentando LOCK_B");
                synchronized (LOCK_B) {
                    System.out.println("T1 concluiu");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("T2 tentando LOCK_B");
            synchronized (LOCK_B) {
                System.out.println("T2 pegou LOCK_B");
                dormir(50);
                System.out.println("T2 tentando LOCK_A");
                synchronized (LOCK_A) {
                    System.out.println("T2 concluiu");
                }
            }
        });

        t1.start();
        t2.start();
    }

    static void dormir(long ms) {
        try { Thread.sleep(ms); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
