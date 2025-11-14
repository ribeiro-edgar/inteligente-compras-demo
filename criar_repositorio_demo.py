from pathlib import Path

# ===== Criar estrutura de pastas =====
repo = Path("inteligente-compras-demo")
backend = repo / "backend" / "java-springboot"
frontend = repo / "frontend" / "angular"
inteligencia = repo / "inteligencia" / "simulador_ia"
docs = repo / "docs"

for path in [backend, frontend, inteligencia, docs]:
    path.mkdir(parents=True, exist_ok=True)

# ===== Criar README principal =====
(repo / "README.md").write_text("""
# Inteligente Compras - Demo

Demo de aplicação fullstack para apoio à decisão em compras, com histórico de fornecedores, score, tendência e insights explicativos.

## Como usar

1. Backend: executar Spring Boot no backend/java-springboot/
2. Frontend: abrir Angular no frontend/angular/
3. IA demo: `python simulador_ia/analise_desempenho_demo.py`

""".strip(), encoding="utf-8")

# ===== Criar README do módulo de IA =====
(inteligencia / "README_IA_OVERVIEW.md").write_text("""
# Simulador IA - Visão Geral

Este módulo contém uma versão simplificada da inteligência de análise de desempenho de fornecedores.
Não contém dados sensíveis nem integrações reais com OpenAI.

## Scripts disponíveis

- `analise_desempenho_demo.py`: Gera relatórios mockados
- `fornecedores_exemplo.csv`: Exemplo de dados de fornecedores
- `relatorio_desempenho_demo.csv`: Exemplo de relatório gerado

""".strip(), encoding="utf-8")

# ===== Criar scripts mockados =====
( inteligencia / "analise_desempenho_demo.py").write_text("""
import pandas as pd

# Carrega dados mockados
df = pd.read_csv("fornecedores_exemplo.csv")

# Simula cálculo de score e tendência
df["score"] = df["reputacao"] * 0.8 + df["quantidade"] * 0.2
df["tendencia"] = ["Melhora" if s > 70 else "Piora" for s in df["score"]]

# Salva relatório
df.to_csv("relatorio_desempenho_demo.csv", index=False)
print("Relatório demo gerado!")
""".strip(), encoding="utf-8")

( inteligencia / "fornecedores_exemplo.csv").write_text("""
fornecedor,quantidade,reputacao
Fornecedor A,10,80
Fornecedor B,5,60
Fornecedor C,8,75
""".strip(), encoding="utf-8")

( inteligencia / "relatorio_desempenho_demo.csv").write_text("""
fornecedor,quantidade,reputacao,score,tendencia
Fornecedor A,10,80,74.0,Melhora
Fornecedor B,5,60,52.0,Piora
Fornecedor C,8,75,66.0,Melhora
""".strip(), encoding="utf-8")

# ===== Criar imagens mockadas =====
for img_name in ["dashboard_preview.png", "fluxo_ia.png", "arquitetura.png"]:
    (docs / img_name).write_text("Imagem mockada: " + img_name, encoding="utf-8")

print("Repositório demo criado com sucesso!")
