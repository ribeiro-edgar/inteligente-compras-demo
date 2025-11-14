import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class FornecedoresService {

  private baseUrl = 'http://127.0.0.1:8080/api/fornecedores'; // backend Java

  constructor(private http: HttpClient) {}

  avaliar(): Observable<any> {
    return this.http.post(`${this.baseUrl}/avaliar`, {});
  }

  getRanking(produtoId: string, top = 3): Observable<any> {
      return this.http.get(`${this.baseUrl}/ranking?produtoId=${produtoId}&top=${top}`);
    }

  feedback(fornecedor: string, reward: number, motivo: string) {
    // ✅ Garante que reward tenha 3 casas decimais
    const rewardFormatado = Number(reward.toFixed(3));

    return this.http.post(`${this.baseUrl}/feedback`, {
      fornecedor,
      reward: rewardFormatado,
      motivo
    });
  }

  avaliarTodos() {
    return this.http.post(`${this.baseUrl}/avaliar`, {});
  }
}
