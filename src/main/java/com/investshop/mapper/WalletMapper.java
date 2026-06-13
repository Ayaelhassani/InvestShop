package com.investshop.mapper;

import com.investshop.dto.WalletDTO;
import com.investshop.entity.Wallet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    WalletDTO toDTO(Wallet wallet);

    List<WalletDTO> toDTOList(List<Wallet> wallets);

    Wallet toEntity(WalletDTO dto);
}