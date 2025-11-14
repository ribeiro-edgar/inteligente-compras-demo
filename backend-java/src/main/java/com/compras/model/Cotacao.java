package com.compras.model;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cotacao {

    private String id;
    private String produtoId;
    private String fornecedorId;
    private Double preco;
    private Integer prazoEntregaDias;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "produtoId-index")
    public String getProdutoId() {
        return produtoId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "fornecedorId-index")
    public String getFornecedorId() {
        return fornecedorId;
    }
}
