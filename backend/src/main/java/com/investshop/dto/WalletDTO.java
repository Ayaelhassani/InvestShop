package com.investshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletDTO {

    private Long id;
    private String name;
    private String assetGroup;
    private Double gainsLastYear;
    private Double price;

    //S'il y a un wallet dans le panie = true sinon false c'est pour desactiver le bonton AddCart 
    private Boolean inCart;
}