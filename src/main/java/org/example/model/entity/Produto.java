package org.example.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 150)
    @NotEmpty (message = "{campo.nome.obrigatorio}")
    private String nomeProduto;

    @Column(nullable = false,length = 225)
    @NotEmpty (message = "{campo.descricao.obrigatorio}")
    private String descricaoProduto;

    @Column(nullable = false,length = 10)
    @Digits(integer=6, fraction=2)
    @NotNull(message = "{campo.valor.invalido}")
    private BigDecimal valorProduto;

    @Column(name="data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }

    public static void main(String[] args) {


    }

}
