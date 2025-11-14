import { Routes } from '@angular/router';

// 🔹 Páginas principais
import { ComprasComponent } from './pages/compras/compras.component';
import { HistoricoComponent } from './pages/historico/historico.component';
import { DesempenhoFornecedoresComponent } from './pages/desempenho-fornecedores/desempenho-fornecedores.component';

export const routes: Routes = [
  // Página inicial → redireciona para compras
  { path: '', redirectTo: '/compras', pathMatch: 'full' },

  // 🔹 Compras e histórico
  { path: 'compras', component: ComprasComponent },
  { path: 'historico', component: HistoricoComponent },

  // 🔹 Relatório de desempenho (via Flask)
  {
    path: 'desempenho',
    component: DesempenhoFornecedoresComponent,
    title: '📈 Desempenho dos Fornecedores',
  },

  // 🔹 Fallback — caso não encontre rota
  { path: '**', redirectTo: '/compras' },
];
