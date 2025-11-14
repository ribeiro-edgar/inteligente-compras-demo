# app.py — API Flask DEMO (versão protegida)
from flask import Flask, request, jsonify
from flask_cors import CORS
from datetime import datetime

app = Flask(__name__)
CORS(app, origins=["http://localhost:4200"])

# ==========================
# 🔒 MENSAGEM PADRÃO DO DEMO
# ==========================
def mensagem_demo():
    return {
        "mensagem": (
            "Este é um PROJETO DEMO. "
            "A inteligência proprietária e algoritmos avançados desenvolvidos por Edgar Ribeiro "
            "não estão incluídos nesta versão. "
            "Para acesso ao projeto completo, entre em contato com o autor."
        ),
        "autor": "Edgar Ribeiro",
        "contato": "Entre em contato diretamente com o autor."
    }


# ===========================================
# 🔄 TODOS OS ENDPOINTS APENAS RETORNAM A MSG
# ===========================================

@app.route("/api/sugestao", methods=["POST"])
def gerar_sugestao():
    print("🧩 [DEMO] /api/sugestao chamado — retornando mensagem padrão")
    return jsonify(mensagem_demo())


@app.route("/api/feedback", methods=["POST"])
def registrar_feedback():
    print("🧠 [DEMO] /api/feedback chamado — retornando mensagem padrão")
    return jsonify(mensagem_demo())


@app.route("/api/fornecedores", methods=["GET"])
def listar_fornecedores():
    print("📦 [DEMO] /api/fornecedores chamado — retornando mensagem padrão")
    return jsonify(mensagem_demo())


@app.route("/api/atualizar-historico", methods=["POST"])
def atualizar_historico_endpoint():
    print("♻️ [DEMO] /api/atualizar-historico chamado — retornando mensagem padrão")
    return jsonify(mensagem_demo())


@app.route("/api/registrar-compra", methods=["POST"])
def registrar_compra():
    print("🧾 [DEMO] /api/registrar-compra chamado — retornando mensagem padrão")
    return jsonify(mensagem_demo())


@app.route("/api/historico", methods=["GET"])
def listar_historico():
    print("📚 [DEMO] /api/historico chamado — retornando mensagem padrão")
    return jsonify(mensagem_demo())


@app.route("/api/atualizar-score-tendencia", methods=["POST"])
def atualizar_score_tendencia_endpoint():
    print("📈 [DEMO] /api/atualizar-score chamado — retornando mensagem padrão")
    return jsonify(mensagem_demo())


@app.route("/api/relatorio-desempenho", methods=["GET"])
def relatorio_desempenho():
    print("📊 [DEMO] /api/relatorio-desempenho chamado — retornando mensagem padrão")
    return jsonify(mensagem_demo())


# =====================
# 🔻 EXECUÇÃO DO SERVIDOR
# =====================
if __name__ == "__main__":
    print("🚀 Servidor Flask DEMO rodando em http://127.0.0.1:5000")
    print("🔒 Inteligência REAL DESATIVADA — versão protegida por Edgar Ribeiro")
    app.run(debug=True)
