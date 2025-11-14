import { Component, OnInit } from '@angular/core';
import { CommonModule, DecimalPipe } from '@angular/common';
import { DesempenhoFornecedoresService } from '../../services/desempenho-fornecedores.service';
import { RelatorioFornecedor } from '../../models/relatorio-desempenho.model';



@Component({
  selector: 'app-desempenho-fornecedores',
  standalone: true, // 👈 importante se você estiver usando standalone components
  imports: [CommonModule, DecimalPipe], // 👈 habilita ngIf, ngFor, ngSwitch, ngClass e pipes
  templateUrl: './desempenho-fornecedores.component.html',
  styleUrls: ['./desempenho-fornecedores.component.scss'],
})
export class DesempenhoFornecedoresComponent implements OnInit {
  relatorio: RelatorioFornecedor[] = [];

  constructor(private desempenhoService: DesempenhoFornecedoresService) {}

  ngOnInit(): void {
    this.desempenhoService.listarDesempenho().subscribe({
      next: (dados) => (this.relatorio = dados ?? []),
      error: (err) => console.error('Erro ao carregar desempenho', err),
    });
  }
}
