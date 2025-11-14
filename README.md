# 🧠 Inteligente Compras — Demo
---

## 💡 Visão Geral

O **Inteligente Compras** é um sistema que une **Java (Spring Boot)**, **Python (Flask)** e **Angular**  
para criar um **gestor de compras com inteligência artificial** capaz de:

- 🧾 Gerar relatórios de desempenho de fornecedores  
- 💬 Sugerir o melhor fornecedor com base em histórico, preço, prazos e reputação  
- 📈 Aprender com o feedback do usuário  

Esta versão **demo** demonstra o fluxo de ponta a ponta entre os módulos, **sem expor a lógica proprietária da IA**.

---

## 🏗️ Arquitetura Geral
```mermaid
    A[🧑‍💻 Usuário (Angular Frontend)] -->|HTTP| B[☕ Backend Java (Spring Boot)]
    B -->|REST API /api/*| C[🐍 Flask (Python)]
    C -->|Mock ou IA Real| D[(🤖 Módulo Inteligente Privado)]

📂 Estrutura do Projeto
inteligente-compras-demo/
│
├── frontend-angular/           # Interface web Angular
│   ├── src/app/
│   ├── package.json
│   └── ...
│
├── backend-java/               # API Java (Spring Boot)
│   ├── src/main/java/com/compras/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── model/
│   │   └── InteligenciaClient.java
│   ├── pom.xml
│   └── ...
│
├── inteligencia-flask/         # Microserviço Flask (Python)
│   ├── app.py
│   ├── routes/
│   │   ├── relatorio.py
│   │   └── sugestao.py
│   ├── requirements.txt
│   └── modules/
│       ├── inteligencia_privada/    # 🔒 IA real (oculta nesta demo)
│       └── inteligencia_mock/       # 🤖 Mock para demonstração
│           └── agente_demo.py
│
└── README.md
```mermaid
🚀 Como Executar Localmente
1️⃣ Flask (Python)
cd inteligencia-flask
python -m venv venv
source venv/bin/activate  # (Linux/macOS)
venv\Scripts\activate     # (Windows)

pip install -r requirements.txt
python app.py

2️⃣ Backend Java (Spring Boot)
cd backend-java
mvn clean package
mvn spring-boot:run
API disponível em http://localhost:8080

3️⃣ Frontend Angular
cd frontend-angular
npm install
npm start
Acesse o frontend em http://localhost:4200

🧩 Fluxo de Comunicação
O usuário interage com o Angular.
O Spring Boot recebe a requisição e aciona o Flask.
O Flask processa (ou simula) a inteligência de compras.
A resposta volta para o frontend, que exibe insights, gráficos e sugestões.

🧠 Sobre o Módulo de Inteligência
🔒 Por motivos de sigilo técnico, a lógica real do modelo de IA e os pesos de aprendizado não são públicos.
Esta demo utiliza um mock funcional, simulando as respostas da IA real.
Exemplo:
# inteligencia-flask/modules/inteligencia_mock/agente_demo.py
def gerar_sugestao_mock():
    return {
        "produto": "Produto Exemplo",
        "fornecedores": [
            {"fornecedor": "Fornecedor A", "score": 4.7, "preco": 123.45},
            {"fornecedor": "Fornecedor B", "score": 4.2, "preco": 118.90}
        ],
        "explicacao": "Sugestão gerada pela IA (versão demonstrativa)."
    }

📊 Tecnologias Utilizadas
Camada	Tecnologia	Função
Frontend	Angular 17+	Interface e interação com o usuário
Backend	Java 21 + Spring Boot 3	Lógica de negócio e API REST
Inteligência	Flask (Python 3.10)	Motor de IA / análises preditivas
Banco de Dados	DynamoDB / Mock	Persistência de dados de fornecedores

💬 Autor

👨‍💻 Edgar Ribeiro
Analista de Engenharia de Software — Itaú Unibanco
📍 Focado em integração de sistemas, IA aplicada e arquitetura em nuvem (AWS).

🔗 LinkedIn
https://www.linkedin.com/in/edgar-ribeiro-88a88796/
💡 "Simplicidade e inteligência aplicada em cada decisão de compra."
