package parte3;

public class DeadlockCorrigido {
    // Dois locks usados para sincronização
    static final Object LOCK_A = new Object();
    static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        // Thread t1 que executa o método com argumento "T1"
        Thread t1 = new Thread(() -> executar("T1"));
        // Thread t2 que executa o método com argumento "T2"
        Thread t2 = new Thread(() -> executar("T2"));

        // Inicia a execução das threads t1 e t2
        t1.start();
        t2.start();
    }

    // Método que simula a execução das threads, tentando adquirir LOCK_A e depois LOCK_B
    static void executar(String nome) {
        System.out.println(nome + " tentando LOCK_A");

        // Tenta adquirir LOCK_A
        synchronized (LOCK_A) {
            System.out.println(nome + " pegou LOCK_A");

            // Simula atraso para aumentar chance de condição de disputa
            dormir(50);

            System.out.println(nome + " tentando LOCK_B");

            // Tenta adquirir LOCK_B enquanto ainda segura LOCK_A
            synchronized (LOCK_B) {
                System.out.println(nome + " pegou LOCK_B");
                System.out.println(nome + " concluiu sem deadlock");
            }
            // LOCK_B é liberado aqui ao sair do bloco synchronized
        }
        // LOCK_A é liberado aqui ao sair do bloco synchronized
    }

    // Método auxiliar para dormir a thread por ms
    static void dormir(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // Caso a thread seja interrompida, mantém o status de interrupção
            Thread.currentThread().interrupt();
        }
    }
}
