package com.compras.controller.dto;

import lombok.Data;

@Data
public class FornecedorScoreResponse {
    private String fornecedor;
    private double score;
    private String comentarioUltimaAvaliacao;
}
