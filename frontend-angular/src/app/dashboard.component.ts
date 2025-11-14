import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({ selector:'app-dashboard', templateUrl:'./dashboard.component.html' })
export class DashboardComponent implements OnInit {
  metrics: any;
  chartData:any;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadMetrics('S001');
  }

  loadMetrics(supplierId:string) {
    this.http.get(`/api/fornecedores/metrics?supplierId=${supplierId}`).subscribe((m:any) => {
      this.metrics = m;
      // processa m.dates, m.reputacao, m.score -> alimentar chart
      // exibir 2 linhas (reputacao e score)
    });
  }
}
