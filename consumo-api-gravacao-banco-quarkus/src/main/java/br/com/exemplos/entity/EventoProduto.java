package br.com.exemplos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "CIRTB007_EVENTO_PRODUTO")
@NoArgsConstructor
public class EventoProduto {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SQ_CIRTB007_EVENTO_PRODUTO"
    )
    @SequenceGenerator(
            name = "SQ_CIRTB007_EVENTO_PRODUTO",
            sequenceName = "SQ_CIRTB007_EVENTO_PRODUTO",
            allocationSize = 1
    )
    @Column(name = "NU_EVENTO_PRODUTO", precision = 5, scale = 0, nullable = false)
    private Long id;

    @Column(name = "ID_PARAMETRO", precision = 4, scale = 0, nullable = false)
    private Integer idParametro;

    @Column(name = "NU_EVENTO", precision = 4, scale = 0, nullable = false)
    private Integer numeroEvento;

    @Column(name = "NM_EVENTO", nullable = false, length = 150)
    private String nomeEvento;

    @Column(name = "NU_PRODUTO", precision = 4, scale = 0, nullable = false)
    private Integer numeroProduto;

    @Column(name = "NM_PRODUTO", nullable = false, length = 150)
    private String nomeProduto;

    @Column(name = "IC_EVENTO_PRODUTO_HABILITADO", length = 1, nullable = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    private String icEventoProdutoHabilitado;

    @Column(name = "DT_ATUALIZACAO_REGISTRO", nullable = false)
    private LocalDateTime dataAtualizacaoRegistro;

    @Column(name = "NU_UNIDADE_RESPONSAVEL", precision = 4, scale = 0, nullable = false)
    private Integer numeroUnidadeResponsavel;

    @Column(name = "NU_NATURAL_RESPONSAVEL", precision = 6, scale = 0, nullable = false)
    private Long numeroNaturalResponsavel;

    @Column(name = "VR_MINIMO_ANALISE", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorMinimoAnalise;
}