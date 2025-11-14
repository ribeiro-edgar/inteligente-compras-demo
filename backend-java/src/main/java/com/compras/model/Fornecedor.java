package com.compras.model;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fornecedor {

    private String id;
    private String nome;
    private String contato;
    private String email;
    private String telefone;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
}
