# Projeto de Concorrência - Threads, Semáforos e Deadlock

**Integrantes:**  
Giovanna Rita Santos Daniel, Leonardo Stall, Mirella Haisi Szvarça e João Lucas do Prado Ribeiro

---

## Vídeo de Explicação  
[Link para o vídeo explicativo do projeto](COLE_AQUI_O_LINK_DO_VIDEO)

---

## Detalhes de cada parte

### Parte 1 - Jantar dos Filósofos  
- **Arquivos:**  
  - `JantarFilosofos.md` (relatório explicativo do problema e solução)  
- **Descrição:**  
  Implementação e análise do problema clássico do Jantar dos Filósofos, que demonstra exclusão mútua, deadlock e estratégias para evitar bloqueios permanentes entre múltiplas threads competindo por recursos compartilhados (garfos).

---

### Parte 2 - Condição de corrida e Semáforo  
- **Local:** `src/parte2/`  
- **Arquivos:**  
  - `CorridaComSemaphore.java` (implementação da correção usando semáforo para evitar condição de corrida)  
  - `CorridaSemControle.java` (implementação sem controle para demonstrar condição de corrida)  
  - `relatorioparte2.md` (relatório resumido explicando o problema, a solução com semáforo e análise de trade-offs)  
- **Descrição:**  
  Simula múltiplas threads incrementando um contador compartilhado sem controle, evidenciando a condição de corrida. Apresenta solução com uso de Semaphore para garantir exclusão mútua, explicando a relação happens-before, resultados obtidos e impacto no desempenho.

---

### Parte 3 - Deadlock e Correção  
- **Local:** `src/parte3/`  
- **Arquivos:**  
  - `DeadlockDemo.java` (exemplo de código que provoca deadlock)  
  - `DeadlockCorrigido.java` (solução aplicando hierarquia de recursos para evitar deadlock)  
  - `relatorioparte3.md` (relatório explicando o deadlock, condições de Coffman, e a correção aplicada com justificativa)  
- **Descrição:**  
  Demonstra o problema do deadlock causado pela aquisição de locks em ordens conflitantes. A solução aplica uma hierarquia fixa para aquisição de recursos, eliminando a espera circular e prevenindo deadlock.


