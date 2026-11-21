package br.com.exemplos.integration.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EventoProdutoApiDTO(

        Integer idParametro,
        Integer nuEvento,
        String nmEvento,
        Integer nuProduto,
        String nmProduto,
        @JsonProperty("nuUnidadeResp")
        Integer nuUnidadeResp,

        @JsonProperty("nuNaturalResp")
        Integer nuNaturalResp,

        @JsonProperty("vrMinimo")
        BigDecimal vrMinimo,

        @JsonProperty("dataAtualizacao")
        LocalDate dataAtualizacao

) {
}
