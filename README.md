# 🔎 BuscadorDeArquivosJava

Uma ferramenta de **busca de arquivos rápida e intuitiva para Windows**, desenvolvida em **Java** com interface gráfica **Swing + FlatLaf**.

O aplicativo permite localizar arquivos no sistema mesmo quando o usuário **não digita o nome exatamente correto**, utilizando técnicas de **fuzzy search** para sugerir resultados semelhantes.

O objetivo do projeto é oferecer uma alternativa **leve, rápida e fácil de usar** para localizar arquivos no computador com uma interface moderna.

---

# 📌 Funcionalidades

## 🔍 Busca de Arquivos

- Pesquisa arquivos dentro de qualquer diretório
- Busca recursiva em subpastas
- Filtragem por nome aproximado (**fuzzy search**)

---

## 🧠 Busca Tolerante a Erros

Mesmo que o usuário digite o nome incorretamente, o programa ainda encontra resultados relevantes.

| Entrada do usuário | Resultado encontrado |
|-------------------|---------------------|
| gta5 | gta5.exe |
| gts5 | gta5.exe |
| gtasa | gta_sa.exe |

Essa funcionalidade é implementada usando **distância de Levenshtein**.

---

# 🖥 Interface Gráfica

Interface desenvolvida com:

- **Java Swing**
- **FlatLaf (tema moderno)**

### Características

- Interface moderna inspirada em **IntelliJ / Windows**
- Tabela de resultados organizada
- Ícones reais do Windows para arquivos
- Experiência semelhante ao **Windows Explorer**

---

# ⚡ Resultados em Tempo Real

Durante a busca:

- Arquivos aparecem **conforme são encontrados**
- A interface permanece **responsiva**
- Uma **barra de progresso** indica atividade

Isso é implementado utilizando:


SwingWorker


---

# 🖱 Menu Contextual

Clique com o **botão direito** em um resultado para acessar:

- Abrir arquivo
- Abrir local do arquivo
- Copiar caminho

---

# 🖱 Duplo Clique para Abrir Arquivos

Assim como no **Explorador de Arquivos do Windows**, o usuário pode abrir arquivos com **duplo clique**.

---

# 📊 Ordenação de Resultados

Os resultados podem ser ordenados por:

- Nome
- Tamanho
- Data de modificação

---

# 🛠 Tecnologias Utilizadas

- Java
- Java Swing
- FlatLaf
- Java NIO (File API)
- Levenshtein Distance
- SwingWorker
- WiX Toolset
- jpackage

---

# 📁 Estrutura do Projeto


BuscadorDeArquivosJava
│
├─ src
│ ├─ Main.java
│
│ ├─ ui
│ │ └─ SearchUI.java
│
│ ├─ service
│ │ └─ FileSearchService.java
│
│ ├─ model
│ │ └─ FileResult.java
│
│ └─ utils
│ └─ LoggerConfig.java
│
├─ lib
│ └─ flatlaf-3.4.jar
│
├─ bin
│
└─ README.md


---

# ▶ Como Executar o Projeto

## Requisitos

- **JDK 17 ou superior**
- **Windows**

---

## Compilar o Projeto

Execute no diretório raiz:

```bash
javac -d bin -cp "lib/flatlaf-3.4.jar" src\Main.java src\model\*.java src\service\*.java src\ui\*.java src\utils\*.java
Criar o Arquivo Executável JAR
jar cfe FileSearch.jar Main -C bin .
Executar o Programa
java -jar FileSearch.jar
📦 Gerar Executável para Windows

O projeto utiliza jpackage para gerar um executável .exe.

Requisitos adicionais

WiX Toolset 3.14

Download:

https://wixtoolset.org

Gerar o Executável
jpackage --input . --name FileSearch --main-jar FileSearch.jar --main-class Main --type exe

Isso criará uma pasta contendo:

FileSearch.exe
runtime/
app/

O executável inclui o Java Runtime, então funciona em qualquer computador Windows.

🧪 Exemplo de Uso

Abra o programa

Escolha uma pasta para busca

Digite parte do nome do arquivo

O sistema exibirá arquivos semelhantes encontrados

🎯 Objetivo do Projeto

Este projeto foi desenvolvido para:

Estudo de Java Desktop Development

Prática de arquitetura de software

Implementação de algoritmos de busca aproximada

Desenvolvimento de interfaces gráficas modernas

👨‍💻 Autor

Desenvolvido por:

Elifaz Emanuel

GitHub:
https://github.com/lfaz3245
