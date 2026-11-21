package br.com.exemplos.mapper;

import br.com.exemplos.entity.EventoProduto;
import br.com.exemplos.integration.api.dto.EventoProdutoApiDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(
        componentModel = "cdi",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface EventoProdutoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idParametro", source = "idParametro")
    @Mapping(target = "numeroEvento", source = "nuEvento")
    @Mapping(target = "nomeEvento", source = "nmEvento")
    @Mapping(target = "numeroProduto", source = "nuProduto")
    @Mapping(target = "nomeProduto", source = "nmProduto")
    @Mapping(target = "icEventoProdutoHabilitado", constant = "S")
    @Mapping(target = "dataAtualizacaoRegistro", source = "dataAtualizacao")
    @Mapping(target = "numeroUnidadeResponsavel", source = "nuUnidadeResp")
    @Mapping(target = "numeroNaturalResponsavel", source = "nuNaturalResp")
    @Mapping(target = "valorMinimoAnalise", source = "vrMinimo")
    EventoProduto toEntity(EventoProdutoApiDTO dto);

    List<EventoProduto> toEntityList(
            List<EventoProdutoApiDTO> dtos
    );

    default LocalDateTime map(LocalDate data) {
        return data != null ? data.atStartOfDay() : null;
    }
}