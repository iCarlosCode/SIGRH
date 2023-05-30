package br.ufrb.edu.gcet236.sigrh.responses;

public record ItemBaixoEstoque(
    String nomeFornecedor,
    String telefoneFornecedor,
    String nomeMedicamento,
    int quantidadeMedicamento
) {}
