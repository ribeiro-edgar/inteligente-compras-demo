import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { RelatorioFornecedor } from '../models/relatorio-desempenho.model';

@Injectable({
  providedIn: 'root',
})
export class DesempenhoFornecedoresService {
  private apiUrl = 'http://127.0.0.1:5000/api'; // endpoint Flask

  constructor(private http: HttpClient) {}

  listarDesempenho(): Observable<RelatorioFornecedor[]> {
    return this.http
      .get<any[]>(`${this.apiUrl}/relatorio-desempenho`)
      .pipe(
        map((dados) =>
          dados.map((item) => ({
            fornecedor: item.fornecedor,
            reputacao_antes: item.reputacao_antes,
            reputacao_depois: item.reputacao_depois,
            score_antes: item.score_antes,
            score_depois: item.score_depois,
            tendencia_nova: item.tendencia_nova,
            analise_textual: item.analise_textual,
          }))
        )
      );
  }
}
