package com.compras.controller.dto;

import lombok.Data;

@Data
public class FeedbackFornecedorRequest {
    private String fornecedor;
    private int reward; // +1 ou -1
    private String motivo; // exemplo: "Entrega antecipada"
}
