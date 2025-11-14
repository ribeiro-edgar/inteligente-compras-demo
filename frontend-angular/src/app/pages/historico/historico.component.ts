import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HistoricoService } from '../../services/historico.service'; // ✅ novo serviço

interface Compra {
  fornecedor: string;
  produto: string;
  quantidade: number;
  prazo_real: number;
  atraso: number;
  custo_final: number;
  satisfacao: number;
  data: string;
}

@Component({
  selector: 'app-historico',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './historico.component.html',
  styleUrls: ['./historico.component.scss'],
})
export class HistoricoComponent implements OnInit {
  formularioAberto = false;
  compra: Compra = this.novaCompra();
  historico: Compra[] = [];

  constructor(private historicoService: HistoricoService) {} // ✅

  ngOnInit(): void {
    this.carregarHistorico();
  }

  toggleFormulario(): void {
    this.formularioAberto = !this.formularioAberto;
  }

  novaCompra(): Compra {
    return {
      fornecedor: '',
      produto: '',
      quantidade: 0,
      prazo_real: 0,
      atraso: 0,
      custo_final: 0,
      satisfacao: 0,
      data: new Date().toISOString().substring(0, 10),
    };
  }

  carregarHistorico(): void {
    this.historicoService.listarHistorico().subscribe({
      next: (dados) => {
        this.historico = dados;
        console.log('📜 Histórico carregado:', this.historico);
      },
      error: (err) => {
        console.error('❌ Erro ao carregar histórico:', err);
        this.historico = [];
      },
    });
  }

  registrarCompra(): void {
    if (!this.compra.fornecedor || !this.compra.produto) {
      alert('Preencha fornecedor e produto antes de registrar.');
      return;
    }

    this.historicoService.registrarCompra(this.compra).subscribe({
      next: (res) => {
        console.log('✅ Compra registrada:', res);
        this.carregarHistorico();
        this.compra = this.novaCompra();
        this.formularioAberto = false;
      },
      error: (err) => console.error('❌ Erro ao registrar compra:', err),
    });
  }

  atualizarHistorico(): void {
    this.historicoService.atualizarModelo().subscribe({
      next: () => {
        console.log('♻️ Modelo atualizado com sucesso!');
        this.carregarHistorico();
      },
      error: (err) => console.error('❌ Erro ao atualizar modelo:', err),
    });
  }
}
