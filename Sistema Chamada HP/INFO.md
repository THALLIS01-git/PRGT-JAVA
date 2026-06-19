# Sistema de Fluxo Hospitalar Distribuído

Este documento descreve a arquitetura, os princípios de design e os conceitos técnicos de um **Sistema de Gestão de Fluxo Hospitalar em Tempo Real**.

Desenvolvido em **Java nativo**, o projeto simula um ambiente distribuído de produção executado de forma assíncrona em múltiplos terminais (**Recepção**, **Triagem** e **Consultório Médico**) interconectados via rede.

---

# 💻 Visão Geral do Sistema

O objetivo principal do sistema é gerenciar a jornada de um paciente dentro de uma unidade de saúde, desde o cadastro inicial até o atendimento médico final, garantindo que os dados transitem de forma segura, ordenada e sem perdas entre diferentes estações de trabalho.

## Componentes do Ecossistema

O sistema é composto por quatro aplicações independentes que executam simultaneamente:

### 1. Servidor Central (Hub)

O "cérebro" do hospital.

Responsável por:

- Centralizar conexões
- Gerenciar filas de atendimento
- Coordenar o fluxo de pacientes
- Garantir a comunicação entre os setores

### 2. PC Recepção (Cliente A)

Responsável pelo cadastro inicial do paciente.

Dados coletados:

- Nome
- CPF
- Data de nascimento

### 3. PC Triagem (Cliente B)

Utilizado pela equipe de enfermagem para:

- Resgatar pacientes da fila
- Registrar sintomas
- Registrar sinais vitais
- Encaminhar pacientes ao médico

### 4. PC Médico (Cliente C)

Utilizado no consultório médico para:

- Receber prontuários atualizados
- Consultar informações da triagem
- Realizar o atendimento final

---

# 🌐 Arquitetura de Rede

## Modelo Cliente-Servidor com Sockets

Para permitir a comunicação entre computadores diferentes (ou instâncias simuladas utilizando `localhost` ou `127.0.0.1`), o sistema utiliza uma arquitetura baseada em **Sockets TCP/IP**.

### Como os Sockets são Aplicados

#### ServerSocket

O Servidor Central abre uma porta fixa para escuta:

```java
ServerSocket serverSocket = new ServerSocket(12345);
```

Função:

- Receber conexões dos clientes
- Permanecer em modo passivo aguardando solicitações

#### Socket Cliente

Cada terminal abre conexões ativas com o servidor:

```java
Socket socket = new Socket("127.0.0.1", 12345);
```

Função:

- Enviar comandos
- Solicitar pacientes
- Atualizar prontuários

#### Desacoplamento Técnico

Os clientes nunca se comunicam diretamente.

Fluxo correto:

```text
Recepção
    ↓
Servidor Central
    ↓
Triagem
    ↓
Servidor Central
    ↓
Médico
```

Benefícios:

- Menor acoplamento
- Maior manutenção
- Escalabilidade facilitada
- Centralização das regras de negócio

---

# 🧵 Engenharia de Concorrência

## O Problema

Um servidor monotarefa processaria apenas uma solicitação por vez.

Consequências:

- Travamentos
- Filas artificiais
- Baixo desempenho

---

## Solução Implementada: Multi-threading

Sempre que uma conexão é recebida, o servidor cria uma nova Thread dedicada.

```java
Socket socket = serverSocket.accept();

new Thread(new ClientHandler(socket)).start();
```

### Como Funciona

A classe `ClientHandler` implementa a interface `Runnable`.

Cada cliente conectado recebe um processo de atendimento independente.

Benefícios:

- Processamento simultâneo
- Escalabilidade horizontal
- Menor tempo de resposta
- Melhor utilização da CPU

### Exemplo Prático

Enquanto:

- A Recepção cadastra um paciente;
- A Triagem registra sintomas;
- O Médico solicita um prontuário;

O servidor consegue atender todos simultaneamente sem bloqueios.

---

# 📦 Estrutura de Dados Concorrente

## LinkedBlockingQueue

Como o projeto não utiliza banco de dados nesta fase, o gerenciamento dos pacientes é realizado inteiramente em memória.

Estrutura utilizada:

```java
LinkedBlockingQueue<Paciente>
```

---

## Thread-Safety

Estruturas tradicionais como:

```java
ArrayList
```

não são seguras para acesso simultâneo.

Problemas possíveis:

- Corrupção de dados
- Condições de corrida (Race Conditions)
- ConcurrentModificationException

A `LinkedBlockingQueue` resolve esses problemas utilizando mecanismos internos de sincronização.

---

## Modelo Produtor-Consumidor

### Recepção → Produtor

Adiciona pacientes à fila.

```java
fila.put(paciente);
```

### Triagem e Médico → Consumidores

Retiram pacientes da fila.

```java
Paciente paciente = fila.take();
```

---

## Comportamento Bloqueante

O método `take()` possui comportamento inteligente.

### Se houver pacientes

```text
Fila → remove primeiro paciente → retorna imediatamente
```

### Se a fila estiver vazia

```text
Thread entra em espera
```

Sem:

- Loops infinitos
- Consumo excessivo de CPU
- Verificações desnecessárias

Quando um novo paciente é inserido:

```text
put()
   ↓
take() é desbloqueado
   ↓
Paciente entregue automaticamente
```

---

# 🔀 Comunicação e Serialização

## Problema

Objetos Java não podem trafegar diretamente pela rede.

A rede trabalha apenas com:

```text
Bytes
```

---

## Serialização

A classe `Paciente` implementa:

```java
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
}
```

### O que acontece?

O objeto:

```java
Paciente
```

é convertido para:

```text
Fluxo de bytes
```

antes de ser enviado pelo Socket.

---

## Desserialização

Ao receber os bytes através de um:

```java
ObjectInputStream
```

o sistema reconstrói automaticamente o objeto original.

Resultado:

```text
Objeto enviado
=
Objeto recebido
```

com todos os atributos preservados.

---

# 📡 Protocolo de Comunicação

Antes de transmitir objetos, os clientes enviam comandos em formato textual.

Método utilizado:

```java
writeUTF()
```

---

## Exemplos de Comandos

### Cadastro de Paciente

```text
RECEPCAO:NOVO_PACIENTE
```

Ação:

```text
Servidor aguarda receber um objeto Paciente
```

---

### Solicitação da Triagem

```text
TRIAGEM:PUXAR_PROXIMO
```

Ação:

```text
Servidor remove um paciente da fila
e envia para a Triagem
```

---

### Solicitação Médica

```text
MEDICO:PUXAR_PROXIMO
```

Ação:

```text
Servidor envia o próximo prontuário disponível
```

---

# 🚀 Diferenciais Técnicos

## Pronto para Produção

Para migrar do ambiente local para uma rede real basta alterar:

```java
"127.0.0.1"
```

para o IP do servidor hospitalar.

Nenhuma outra modificação estrutural é necessária.

---

## Arquitetura Orientada a Eventos

O sistema reage a eventos em tempo real:

- Cadastro de pacientes
- Solicitações da triagem
- Chamadas médicas

Benefícios:

- Menor tráfego de rede
- Maior desempenho
- Menor latência

---

## Preparado para Interfaces Gráficas

A lógica de negócio está desacoplada da interface.

Atualmente:

```java
Scanner
```

Pode ser substituído futuramente por:

- JavaFX
- Swing
- Spring Boot + Front-end Web
- Aplicações Mobile

Sem alterações no Servidor Central.

---

# 📈 Escalabilidade Futura

Possíveis evoluções do projeto:

### Banco de Dados

- PostgreSQL
- MySQL
- SQL Server

### Interface Web

- React
- Angular
- Vue.js

### API REST

- Spring Boot

### Comunicação em Tempo Real

- WebSockets
- RabbitMQ
- Apache Kafka

### Infraestrutura

- Docker
- Kubernetes
- Cloud Computing

---

# 📋 Resumo Executivo

O sistema utiliza:

| Tecnologia | Finalidade |
|------------|------------|
| Sockets TCP/IP | Comunicação entre máquinas |
| Threads | Processamento concorrente |
| LinkedBlockingQueue | Controle seguro das filas |
| Serializable | Transporte de objetos |
| Client-Server | Organização da arquitetura |
| Produtor-Consumidor | Gerenciamento do fluxo hospitalar |

A combinação dessas tecnologias resulta em uma solução distribuída, escalável, segura e preparada para evoluir para ambientes corporativos reais.