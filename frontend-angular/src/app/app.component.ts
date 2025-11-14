import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet, RouterLink } from '@angular/router';
import { ComprasComponent } from './pages/compras/compras.component';
import { ComprasService } from './services/compras.service'; // ✅ importa o service

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, ComprasComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'ComprAI';

  constructor(private comprasService: ComprasService) {} // ✅ injeta o service

  // 🔹 Controle do menu hamburguer
    menuAtivo = false;

    toggleMenu() {
      this.menuAtivo = !this.menuAtivo;
    }

    fecharMenu() {
      this.menuAtivo = false;
    }

  atualizarScoreTendencia() {
    console.log('🔄 Atualizando fornecedores...');
    this.comprasService.atualizarScoreTendencia().subscribe({
      next: (res: any) => {
        alert(res.mensagem || 'Score e tendência atualizados com sucesso ✅');
      },
      error: (err: any) => {
        console.error('Erro ao atualizar score e tendência:', err);
        alert('Falha ao atualizar score e tendência ❌');
      }
    });
  }
}
