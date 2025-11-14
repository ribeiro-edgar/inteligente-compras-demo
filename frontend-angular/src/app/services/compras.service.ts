import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ComprasService {

  private API_URL = 'http://127.0.0.1:8080/api/compras'; // backend Java

  constructor(private http: HttpClient) {}

  getSugestao(payload: { produtoId: string, quantidade: number }): Observable<any> {
    return this.http.post(`${this.API_URL}/sugestao`, payload);
  }

  getHistorico() {
    return this.http.get<any[]>(`${this.API_URL}/historico`);
  }

  registrarCompra(data: any) {
    return this.http.post(`${this.API_URL}/realizadas`, data);
  }

  atualizarHistorico() {
    return this.http.post(`${this.API_URL}/compras/atualizar-historico`, {});
  }

  atualizarScoreTendencia() {
    return this.http.post(`${this.API_URL}/atualizar-score-tendencia`, {});
  }

}
