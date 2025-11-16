# RELATÓRIO – Parte 2: Threads e Semáforos 

---

## 1. Dinâmica do Problema

Criamos um contador estático compartilhado entre **T** threads, onde cada thread executa **M** incrementos.  
Como a operação `count++` não é atômica, várias threads podem atualizar o contador ao mesmo tempo, causando condição de corrida e produzindo um valor menor que o esperado.

---

## 2. Por que ocorre

O problema aparece por falta de exclusão mútua: várias threads entram na seção crítica simultaneamente e sobrescrevem os incrementos umas das outras.

---

## 3. Solução com Semáforo

A correção usa `Semaphore(1, true)`:

- **1 permissão** → garante que apenas uma thread execute `count++` por vez.
- **Modo justo (fair)** → FIFO: threads são atendidas na ordem de chegada.
- A sincronização cria uma relação *happens-before*, garantindo visibilidade e consistência das atualizações.

Assim, o contador passa a atingir sempre o valor correto **T × M**.

---

## 4. Resultados Obtidos

Com **T = 8** e **M = 250.000**:

### Sem controle

- Esperado: 2.000.000
- Obtido: 751.825
- Tempo: 0,029 s

### Com semáforo

- Esperado: 2.000.000
- Obtido: 2.000.000
- Tempo: 14,228 s

---

## 5. Diferenças e Fairness

Sem sincronização, o valor final é incorreto devido à condição de corrida.  
Com semáforo justo, o valor é exato e as threads ganham acesso de forma ordenada (FIFO), evitando que alguma fique esperando indefinidamente.

---

## 6. Trade-off: Desempenho × Correção

O semáforo:

- resolve completamente a condição de corrida,
- traz correção, ordem e consistência,

mas reduz o throughput, pois:

- apenas uma thread executa por vez,
- há espera na fila do semáforo,
- e o modo justo adiciona custo extra.

Ou seja: ganha-se segurança e previsibilidade, mas perde-se velocidade.
