package com.compras.controller.dto;

import lombok.Data;
@Data
public class CompraRealizadaDTO {

    private String fornecedor;
    private String produto;
    private int quantidade;
    private int prazo_real;
    private int atraso;
    private double custo_final;
    private double satisfacao;
    private String data;
}
