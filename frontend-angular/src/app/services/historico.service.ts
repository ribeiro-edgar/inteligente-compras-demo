import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class HistoricoService {
  private apiUrl = 'http://127.0.0.1:5000/api'; // Flask local

  constructor(private http: HttpClient) {}

  listarHistorico(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/historico`);
  }

  registrarCompra(compra: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/registrar-compra`, compra);
  }

  atualizarModelo(): Observable<any> {
    return this.http.post(`${this.apiUrl}/atualizar-historico`, {});
  }

  relatorioDesempenho(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/relatorio-desempenho`);
  }
}
