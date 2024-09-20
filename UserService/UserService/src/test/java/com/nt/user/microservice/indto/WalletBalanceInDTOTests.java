package com.nt.user.microservice.indto;

import com.nt.user.microservice.dto.WalletBalanceInDTO;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class WalletBalanceInDTOTest {

  @Test
  void testGettersAndSetters() {
    WalletBalanceInDTO walletBalanceInDTO = new WalletBalanceInDTO();

    walletBalanceInDTO.setId(101);
    assertThat(walletBalanceInDTO.getId()).isEqualTo(101);

    walletBalanceInDTO.setBalance(500.50);
    assertThat(walletBalanceInDTO.getBalance()).isEqualTo(500.50);
  }

  @Test
  void testEqualsAndHashCode() {
    WalletBalanceInDTO wallet1 = new WalletBalanceInDTO();
    WalletBalanceInDTO wallet2 = new WalletBalanceInDTO();

    wallet1.setId(101);
    wallet1.setBalance(500.50);

    wallet2.setId(101);
    wallet2.setBalance(500.50);

    assertThat(wallet1).isEqualTo(wallet2);

    assertThat(wallet1.hashCode()).isEqualTo(wallet2.hashCode());
  }

  @Test
  void testNotEquals() {
    WalletBalanceInDTO wallet1 = new WalletBalanceInDTO();
    WalletBalanceInDTO wallet2 = new WalletBalanceInDTO();

    wallet1.setId(101);
    wallet1.setBalance(500.50);

    wallet2.setId(102);
    wallet2.setBalance(500.50);

    assertThat(wallet1).isNotEqualTo(wallet2);
  }

  @Test
  void testToString() {
    WalletBalanceInDTO walletBalanceInDTO = new WalletBalanceInDTO();
    walletBalanceInDTO.setId(101);
    walletBalanceInDTO.setBalance(500.50);

    assertThat(walletBalanceInDTO.toString()).isEqualTo(
      "WalletBalanceInDTO{id=101, balance=500.5}");
  }

  @Test
  void testValidationAnnotations() {
    WalletBalanceInDTO walletBalanceInDTO = new WalletBalanceInDTO();

    walletBalanceInDTO.setId(null);
    walletBalanceInDTO.setBalance(500.50);
    assertThat(walletBalanceInDTO.getId()).isNull();

    walletBalanceInDTO.setBalance(0.0);
    assertThat(walletBalanceInDTO.getBalance()).isEqualTo(0.0);
  }
}
