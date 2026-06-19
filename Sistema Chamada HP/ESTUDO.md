# Guia de Estudo: Arquitetura Distribuída e Concorrência em Java

Este documento serve como material de apoio e estudo para o sistema hospitalar distribuído desenvolvido em Java utilizando **Sockets**, **Threads** e **Filas Concorrentes**.

---

## 1. O Conceito de Threads (Multi-tarefas)

No `ServidorCentral`, toda vez que um cliente (Recepção, Triagem ou Médico) se conecta, o servidor executa a seguinte linha de código:

```java
new Thread(new ClientHandler(socket)).start();
```

### Por que isso é importante?

Se o servidor não utilizasse Threads, ele seria **síncrono e monotarefa**.

Isso significa que, enquanto o médico estivesse puxando um paciente ou o enfermeiro estivesse digitando os sintomas, a recepção ficaria totalmente travada, impossibilitada de cadastrar novos pacientes.

As Threads criam linhas de execução paralelas, permitindo que o servidor assinale um **"atendente virtual"** (`ClientHandler`) para cada máquina cliente, processando requisições simultâneas sem gerar gargalos.

---

## 2. A Fila Concorrente (`LinkedBlockingQueue`)

O gerenciamento dos pacientes na memória do servidor utiliza a estrutura `LinkedBlockingQueue`.

Os dois métodos fundamentais observados no código são:

- `.put()`
- `.take()`

### O Método `.put()`

Responsável por inserir um elemento no final da fila.

Ele é categorizado como **thread-safe** (seguro para concorrência).

Mesmo se o PC da Recepção e outra máquina tentarem alterar a fila no exato mesmo milissegundo, a estrutura interna do Java garante a sincronização, impedindo a corrupção dos dados.

### O Método `.take()`

Este método é o grande diferencial da arquitetura.

Ele possui um comportamento **bloqueante**:

- Se a fila contiver elementos, ele retira e retorna o primeiro da fila imediatamente.
- Se a fila estiver vazia, ele suspende a execução daquela Thread específica.

Ou seja, ele "trava" aquela linha de código de forma inteligente.

A Thread fica aguardando pacientemente até que outra Thread execute um `.put()`.

Assim que um novo elemento entra na fila, a Thread é acordada automaticamente e consome o item, sem desperdiçar processamento do computador com verificações constantes.

---

## 3. A Serialização (`Serializable`)

Para que a classe `Paciente` pudesse trafegar pela rede através dos Sockets, foi necessário implementar a interface `Serializable`:

```java
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    // ...
}
```

### Por que isso é necessário?

Os objetos armazenados na memória RAM são representados internamente por estruturas complexas de ponteiros e endereços.

Essas estruturas só fazem sentido dentro da aplicação que as criou.

Quando precisamos enviar um objeto para outro computador ou processo, o Java deve transformar essa estrutura complexa em um fluxo linear de bytes.

Esse processo é chamado de **Serialização**.

A operação inversa, que reconstrói o objeto a partir dos bytes recebidos, é chamada de **Desserialização**.

A interface `Serializable` funciona como uma autorização para que o Java realize essa conversão automaticamente.

---

## 4. Resumo do Fluxo de Dados Local (`localhost`)

### Recepção

```text
Recepção
    ↓
Captura dados via Scanner
    ↓
Serializa objeto Paciente
    ↓
Envia via Socket (127.0.0.1:12345)
```

### Servidor Central

```text
Recebe conexão
    ↓
Dispara nova Thread
    ↓
Armazena paciente na LinkedBlockingQueue (.put())
```

### Triagem ou Médico

```text
Solicita próximo paciente
    ↓
Servidor executa .take()
(bloqueia se a fila estiver vazia)
    ↓
Paciente é removido da fila
    ↓
Objeto é serializado novamente
    ↓
Enviado ao cliente solicitante
```

---

## Conceitos-Chave para Revisão

| Conceito | Função |
|-----------|---------|
| Thread | Permite múltiplos atendimentos simultâneos |
| ClientHandler | Gerencia cada cliente conectado |
| Socket | Canal de comunicação entre máquinas |
| LinkedBlockingQueue | Fila segura para acesso concorrente |
| put() | Adiciona elementos à fila |
| take() | Remove elementos e espera caso a fila esteja vazia |
| Serializable | Permite converter objetos em bytes |
| Serialização | Transformação do objeto em fluxo de bytes |
| Desserialização | Reconstrução do objeto a partir dos bytes |
| localhost (127.0.0.1) | Comunicação na própria máquina |

---

## Resumo Final

A arquitetura do sistema utiliza:

- **Sockets** para comunicação entre processos;
- **Threads** para atendimento simultâneo de múltiplos clientes;
- **LinkedBlockingQueue** para gerenciamento seguro da fila de pacientes;
- **Serializable** para transporte de objetos pela rede.

Essa combinação cria um sistema distribuído simples, eficiente e escalável, capaz de simular o fluxo de atendimento de um ambiente hospitalar real.