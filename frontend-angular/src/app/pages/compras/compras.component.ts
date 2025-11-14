import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ComprasService } from '../../services/compras.service';
import { FornecedoresService } from '../../services/fornecedores.service';

@Component({
  selector: 'app-compras',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './compras.component.html',
  styleUrls: ['./compras.component.css']
})
export class ComprasComponent implements OnInit {
  produtoId: string = '';
  quantidade: number = 1;
  comentario: string = '';
  fornecedores: any[] = [];
  resultado: any = {};
  carregando: boolean = false;

  // 🔹 Histórico de compras
  mostrarHistorico: boolean = false;
  historico: any[] = [];

  // ✅ Adiciona o campo "data"
  compra = {
    fornecedor: '',
    produto: '',
    quantidade: 1,
    prazo_real: 0,
    atraso: 0,
    custo_final: 0,
    satisfacao: 0,
    data: '' // <-- aqui!
  };

  constructor(
    private comprasService: ComprasService,
    private fornecedoresService: FornecedoresService
  ) {}

  ngOnInit() {
    this.carregarHistorico();
  }

  // ✅ Mantém toda a lógica atual
  buscarSugestao() {
    this.carregando = true;
    this.resultado = {};
    this.fornecedores = [];

    this.comprasService.getSugestao({
      produtoId: this.produtoId,
      quantidade: this.quantidade
    }).subscribe({
      next: (data) => {
        this.resultado = data;
        this.fornecedores = data.melhores_fornecedores || [];
        this.carregando = false;
      },
      error: () => {
        this.resultado = { erro: 'Falha ao obter sugestão' };
        this.carregando = false;
      }
    });
  }

  enviarFeedback(supplierId: string, reward: number) {
    const motivo = this.comentario || (reward > 0 ? 'Entrega boa' : 'Problema na entrega');

    this.fornecedoresService.feedback(supplierId, reward, motivo).subscribe({
      next: () => alert('Feedback enviado com sucesso!'),
      error: (e) => {
        console.error(e);
        alert('Erro ao enviar feedback');
      }
    });
  }

  avaliar() {
    this.fornecedoresService.avaliar().subscribe({
      next: (res) => {
        this.fornecedores = res.fornecedores || [];
      },
      error: () => alert('Erro ao recalcular reputações')
    });
  }

  // 🔹 Novo bloco - Histórico
  alternarHistorico() {
    this.mostrarHistorico = !this.mostrarHistorico;
  }

  carregarHistorico() {
    this.comprasService.getHistorico().subscribe({
      next: (data: any) => this.historico = data || [],
      error: (e) => console.warn('Falha ao carregar histórico', e)
    });
  }

  registrarCompra() {
    // ✅ Preenche automaticamente a data se estiver vazia
    if (!this.compra.data) {
      const hoje = new Date();
      this.compra.data = hoje.toISOString().split('T')[0]; // formato yyyy-MM-dd
    }

    this.comprasService.registrarCompra(this.compra).subscribe({
      next: () => {
        alert('Compra registrada ✅');
        this.carregarHistorico();
        // ✅ Reset mantendo a data no objeto
        this.compra = {
          fornecedor: '',
          produto: '',
          quantidade: 1,
          prazo_real: 0,
          atraso: 0,
          custo_final: 0,
          satisfacao: 0,
          data: '' // <-- e aqui também
        };
      },
      error: () => alert('Erro ao registrar compra')
    });
  }

  atualizarModelo() {
    this.comprasService.atualizarHistorico().subscribe({
      next: () => alert("✅ Modelo atualizado com sucesso!"),
      error: () => alert("⚠️ Erro ao atualizar o modelo.")
    });
  }

  atualizarHistorico() {
    this.comprasService.atualizarHistorico().subscribe({
      next: () => alert('Modelo atualizado com histórico 📈'),
      error: () => alert('Erro ao atualizar modelo')
    });
  }
}
