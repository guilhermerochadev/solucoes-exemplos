package br.com.exemplos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EventoProdutoDTO(
        Integer idParametro,
        Integer nuEvento,
        String nmEvento,
        Integer nuProduto,
        String nmProduto,
        Integer nuUnidadeResp,
        Integer nuNaturalResp,
        BigDecimal vrMinimo,
        LocalDate dataAtualizacao
) {
}
