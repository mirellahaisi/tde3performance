package parte2;

import java.util.concurrent.*;

public class CorridaSemControle {
    static int count = 0;

    public static void main(String[] args) throws Exception {
        int T = 8;                // quantidade de threads
        int M = 250_000;          // incrementos por thread

        ExecutorService pool = Executors.newFixedThreadPool(T);

        // Tarefa sem sincronização → condição de corrida
        Runnable r = () -> {
            for (int i = 0; i < M; i++) {
                count++; // operação não atômica → perde incrementos
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
                "SEM CONTROLE → Esperado=%d | Obtido=%d | Tempo=%.3fs%n",
                (T * M), count, (fim - inicio) / 1e9
        );
    }
}
