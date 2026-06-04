<h1 align="center"> Projeto: Programação para Dispositivos Móveis </h1>
🎵 GrooveLog

O **GrooveLog** é um aplicativo Android nativo desenvolvido para ser o seu diário musical pessoal. Com ele, você pode catalogar seus artistas favoritos e registrar análises detalhadas (reviews) sobre os álbuns que você ouviu.

Desenvolvido com foco em Experiência do Usuário (UX) e persistência de dados local, o projeto utiliza um banco de dados relacional e apresenta uma interface moderna em Dark Mode (paleta "Fiery Ocean").

---

## ✨ Funcionalidades

* **Gerenciamento de Artistas (CRUD):** Adicione, edite, visualize e exclua artistas, incluindo nome e gênero musical.
* **Diário de Reviews (CRUD Relacional):** Registre álbuns vinculando-os obrigatoriamente a um artista cadastrado (relação 1:N).
* **Campos Detalhados:** Registre a data da audição (via `DatePicker` nativo), faixas favoritas e uma análise em texto livre.
* **Validação de Dados:** Feedbacks visuais (`setError`) impedem o salvamento de formulários em branco.
* **Empty States:** Telas amigáveis informam o usuário quando o banco de dados está vazio.
* **Design Flat & Dark Mode:** Interface sem sombras (elevação 0dp), cores de alto contraste para leitura e orientação travada em modo retrato (Portrait) para evitar quebras de layout.

---

## 📱 Telas do Aplicativo



| Tela Inicial | Lista de Artistas | Nova Review | Detalhes da Review |
| :---: | :---: | :---: | :---: |
| <img width="474" height="927" alt="Captura de tela 2026-06-04 154528" src="https://github.com/user-attachments/assets/d36f727f-7618-458b-ae4a-d780b64120a7" />
 | <img width="458" height="924" alt="Captura de tela 2026-06-04 154623" src="https://github.com/user-attachments/assets/212a2a01-08a5-40c8-acc9-01a46d2e0702" />
 | <img width="459" height="916" alt="Captura de tela 2026-06-04 154745" src="https://github.com/user-attachments/assets/338e42df-baa2-47b1-ae46-b54fd98e173d" />
|<img width="468" height="926" alt="Captura de tela 2026-06-04 154831" src="https://github.com/user-attachments/assets/c726980b-2224-4952-8e54-f729608bcb01" />
|

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java
* **Plataforma:** Android SDK (API 34)
* **Banco de Dados:** [Room Persistence Library](https://developer.android.com/training/data-storage/room) (Abstração do SQLite)
* **Interface:** XML + Material Design Components
* **Arquitetura:** Padrão de repositório local com DAOs (Data Access Objects) e Entidades.

---

## 🗄️ Estrutura do Banco de Dados

O aplicativo utiliza a biblioteca **Room** para gerenciar o SQLite nativo do Android. O banco consiste em duas entidades principais:

1. **Artista:** `id` (Primary Key), `nome`, `genero`.
2. **Review:** `id` (Primary Key), `nomeAlbum`, `dataAudicao`, `faixasFavoritas`, `analise`, `artistaId` (Foreign Key).

---

## 🚀 Como Executar o Projeto

Para clonar e executar este aplicativo no seu computador, você precisará do [Git](https://git-scm.com) e do [Android Studio](https://developer.android.com/studio) instalados.

### Passo a passo:

1. Abra o terminal do seu computador e clone o repositório
2. Abra o Android Studio.
3. Clique em File > Open e selecione a pasta do projeto clonado.
4. Aguarde o Gradle sincronizar todas as dependências (uma barra de carregamento aparecerá no rodapé).
5. Após a sincronização, conecte o seu smartphone Android (com a depuração USB ativada) ou inicie um Emulador Virtual (recomendado: Pixel 7, API 33 ou superior).
6. Clique no botão de Run (Shift + F10) na barra superior verde.

