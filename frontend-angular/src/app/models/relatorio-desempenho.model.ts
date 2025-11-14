export interface RelatorioFornecedor {
  fornecedor: string;
  reputacao_antes: number;
  reputacao_depois: number;
  score_antes: number;
  score_depois: number;
  tendencia_nova: string;
  analise_textual: string;
}
