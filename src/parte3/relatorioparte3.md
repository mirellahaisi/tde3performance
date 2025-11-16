# Parte 3 -
## Relatório — Deadlock e Correção com Hierarquia de Recursos

---

### 1. Descrição do Problema

O cenário simula duas threads que disputam acesso a dois locks (`LOCK_A` e `LOCK_B`) de forma conflitante:

- A Thread 1 tenta adquirir o lock A primeiro, depois o lock B.
- A Thread 2 tenta adquirir o lock B primeiro, depois o lock A.

Essa situação cria uma espera circular: cada thread segura um lock e espera o outro, causando travamento do sistema, ou **deadlock**.

---

### 2. Condições de Coffman que ocorrem no deadlock

O deadlock ocorre porque todas as quatro condições necessárias de Coffman estão satisfeitas simultaneamente:

- **Exclusão mútua:** Os locks são recursos que só podem ser usados por uma thread por vez.
- **Manter-e-esperar:** As threads mantêm um lock e esperam pelo outro.
- **Não preempção:** Locks não podem ser retirados à força de uma thread.
- **Espera circular:** Há um ciclo de espera entre threads — T1 espera T2 e T2 espera T1.

Essas quatro condições causam o deadlock observado no código.

---

### 3. Correção aplicada e justificativa

Para resolver o deadlock, aplicamos a estratégia de hierarquia de recursos:

- Todas as threads são obrigadas a adquirir os locks na mesma ordem fixa — primeiro `LOCK_A`, depois `LOCK_B`.

Isso elimina a condição de espera circular, pois não é mais possível formar um ciclo de dependência entre os locks. Consequentemente, o sistema fica livre do deadlock.

Essa técnica é a mesma utilizada para solucionar o problema clássico do jantar dos filósofos, quebrando a espera circular para garantir progresso e evitar bloqueios permanentes.

---

### 4. Conclusão

O deadlock decorre da combinação das quatro condições de Coffman, principalmente da espera circular, que cria uma situação de impasse.

Impondo uma ordem global para aquisição de recursos (locks), a espera circular é removida, prevenindo o deadlock.

Essa solução garante que o sistema possa continuar operando sem travamentos, preservando a exclusão mútua e a sincronização correta entre threads.
