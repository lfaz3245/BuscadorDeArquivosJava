# BuscadorDeArquivosJava
Uma ferramenta de busca de arquivos rápida e intuitiva para Windows, desenvolvida em Java com interface gráfica Swing + FlatLaf.
O aplicativo permite localizar arquivos no sistema mesmo quando o usuário não digita o nome exatamente correto, utilizando técnicas de fuzzy search para sugerir resultados semelhantes.

O objetivo do projeto é oferecer uma alternativa simples e leve para localizar arquivos no computador com uma interface moderna e funcionalidades úteis.

Funcionalidades
Busca de arquivos

Pesquisa arquivos dentro de qualquer diretório

Busca recursiva em subpastas

Filtragem por nome aproximado (fuzzy search)

Busca tolerante a erros

Mesmo que o usuário digite o nome incorretamente, o programa ainda encontra resultados relevantes.

Exemplo:

Entrada do usuário	Resultado encontrado
gta5	gta5.exe
gts5	gta5.exe
gtasa	gta_sa.exe

Isso é feito usando distância de Levenshtein.

Interface gráfica moderna

Interface desenvolvida com:

Java Swing

FlatLaf (tema moderno)

Características:

tema moderno estilo IntelliJ / Windows

tabela de resultados

ícones reais do Windows para arquivos

Resultados em tempo real

Durante a busca:

arquivos aparecem conforme são encontrados

a interface permanece responsiva

barra de progresso indica atividade

Isso é implementado usando:

SwingWorker
Menu contextual

Clique com botão direito sobre um resultado para acessar:

Abrir arquivo

Abrir local do arquivo

Copiar caminho

Duplo clique para abrir arquivos

Assim como no explorador de arquivos do Windows, o usuário pode abrir arquivos com duplo clique.

Ordenação de resultados

Os resultados podem ser ordenados por:

Nome

Tamanho

Data de modificação

Tecnologias utilizadas

Java

Java Swing

FlatLaf

Java NIO (File API)

Levenshtein Distance

SwingWorker

WiX Toolset

jpackage

Estrutura do projeto
JavaProject
│
├─ src
│  ├─ Main.java
│
│  ├─ ui
│  │   └─ SearchUI.java
│
│  ├─ service
│  │   └─ FileSearchService.java
│
│  ├─ model
│  │   └─ FileResult.java
│
│  └─ utils
│      └─ LoggerConfig.java
│
├─ lib
│   └─ flatlaf-3.4.jar
│
├─ bin
│
└─ README.md
Como executar o projeto
Requisitos

JDK 17 ou superior

Windows

Compilar o projeto

No diretório raiz execute:

javac -d bin -cp "lib/flatlaf-3.4.jar" src\Main.java src\model\*.java src\service\*.java src\ui\*.java src\utils\*.java
Criar o arquivo executável JAR
jar cfe FileSearch.jar Main -C bin .
Executar o programa
java -jar FileSearch.jar
Gerar executável para Windows

O projeto utiliza jpackage para gerar um executável .exe.

Requisitos adicionais

WiX Toolset 3.14

Download:

https://wixtoolset.org
Gerar o executável
jpackage --input . --name FileSearch --main-jar FileSearch.jar --main-class Main --type exe

Isso criará uma pasta contendo:

FileSearch.exe
runtime/
app/

O executável inclui o Java Runtime, então funciona em qualquer computador Windows.

Exemplo de uso

Abra o programa

Escolha uma pasta para busca

Digite parte do nome do arquivo

O sistema exibirá arquivos semelhantes encontrados



Objetivo do projeto

Este projeto foi desenvolvido como:

estudo de Java Desktop Development

prática de arquitetura de software

implementação de algoritmos de busca aproximada

desenvolvimento de interfaces gráficas modernas

Autor

Desenvolvido por:

Elifaz Emanuel
