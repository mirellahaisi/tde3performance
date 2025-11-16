package parte2;

import java.util.concurrent.*;

public class CorridaComSemaphore {
    static int count = 0;

    // Semáforo binário (1 permissão) e justo (FIFO)
    static final Semaphore sem = new Semaphore(1, true);

    public static void main(String[] args) throws Exception {
        int T = 8;
        int M = 250_000;

        ExecutorService pool = Executors.newFixedThreadPool(T);

        Runnable r = () -> {
            for (int i = 0; i < M; i++) {
                try {
                    sem.acquire();    // exclusão mútua
                    count++;          // operação protegida
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    sem.release();    // libera para próxima thread da fila
                }
            }
        };

        long inicio = System.nanoTime();

        for (int i = 0; i < T; i++) {
            pool.submit(r);
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        long fim = System.nanoTime();

        System.out.printf(
                "COM SEMÁFORO → Esperado=%d | Obtido=%d | Tempo=%.3fs%n",
                (T * M), count, (fim - inicio) / 1e9
        );
    }
}
