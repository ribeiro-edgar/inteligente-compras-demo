package com.compras.controller.dto;


public record FornecedorRankingDto(
        String supplierId,
        String fornecedor,
        double preco,
        String prazo,
        double score,
        String classificacao,
        String cor,
        String motivo
) {}

