# Relatório — Jantar dos Filósofos

## Dinâmica do problema
O **Jantar dos Filósofos** é um problema clássico que modela cinco filósofos sentados ao redor de uma mesa circular.  
Cada filósofo alterna entre os estados **pensando**, **com fome** e **comendo**.  

Para comer, cada filósofo precisa utilizar dois garfos, um à sua esquerda e outro à sua direita, que são compartilhados com os filósofos vizinhos.

O protocolo ingênuo para o problema consiste em que cada filósofo pegue primeiro o garfo à sua esquerda e depois tente pegar o garfo à sua direita.  
Essa abordagem pode causar um impasse (**deadlock**), porque se todos os filósofos pegarem simultaneamente o garfo da esquerda, eles ficarão esperando indefinidamente pelo garfo da direita, que nunca estará disponível, impedindo que qualquer um progrida.

---

## Por que o impasse surge?
O impasse ocorre porque todas as quatro condições necessárias para deadlock, conhecidas como condições de Coffman, estão presentes simultaneamente:

- **Exclusão mútua:** Cada garfo é um recurso exclusivo que só pode ser usado por um filósofo por vez.  
- **Manter-e-esperar:** Os filósofos seguram um garfo e esperam pelo outro.  
- **Não preempção:** Os garfos não podem ser tomados à força; só podem ser liberados voluntariamente.  
- **Espera circular:** Há um ciclo de espera entre os filósofos, onde cada um espera pelo garfo que o vizinho está segurando.

---

## Condição negada na solução proposta
Para evitar o impasse, a solução adotada é a imposição de uma **hierarquia de recursos**.  
Cada garfo recebe um índice único, e todos os filósofos devem sempre pegar primeiro o garfo com o menor índice e, em seguida, o garfo com o maior índice.

Com essa ordem global fixa para aquisição dos recursos, a condição de espera circular é eliminada, pois não pode mais existir um ciclo de dependência entre os filósofos.

## Justiça e progresso

Este protocolo garante que:

- A **exclusão mútua** é respeitada, pois apenas um filósofo pode usar um garfo por vez.  
- A **espera circular** é evitada pela hierarquia fixa na ordem de aquisição dos garfos.  
- **Não há deadlock** e todos os filósofos eventualmente conseguem comer, preservando a justiça e evitando a inanição (bloqueio indefinido).

---

## Pseudocódigo do protocolo com hierarquia de recursos

```pseudo
Dados:
  N = 5 filósofos
  Garfos 0..N-1 (garfo i fica entre filósofos i e (i+1) mod N)

Para cada filósofo p:
  left = min(garfo_esquerda(p), garfo_direita(p))
  right = max(garfo_esquerda(p), garfo_direita(p))

Loop:
  pensar()
  estado[p] ← "com fome"
  adquirir(left)    // bloqueia até o garfo estar livre
  adquirir(right)   // bloqueia até o garfo estar livre
  estado[p] ← "comendo"
  comer()
  liberar(right)


  liberar(left)
  estado[p] ← "pensando"
