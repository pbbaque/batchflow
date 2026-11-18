package io.batchflow.settlement.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class MoneyTest {
  @Test
  void shouldCreateMoneyWithTwoDecimalPlaces() {
    BigDecimal expectedAmount = new BigDecimal("25.00");

    Money money = Money.of(expectedAmount);

    assertEquals(expectedAmount, money.amount());
  }

  @Test
  void shouldRejectNullAmount() {
    assertThrows(NullPointerException.class, () -> Money.of(null));
  }

  @Test
  void shouldRejectNegativeAmount() {

    assertThrows(IllegalArgumentException.class, () -> Money.of(new BigDecimal("-0.01")));
  }

  @Test
  void shouldAcceptZeroAmount() {
    BigDecimal expectedAmount = new BigDecimal("0.00");

    Money money = Money.of(expectedAmount);

    assertEquals(expectedAmount, money.amount());
  }

  @Test
  void shouldNormalizeWholeAmount() {
    Money money = Money.of(new BigDecimal("25"));

    assertEquals(new BigDecimal("25.00"), money.amount());
  }

  @Test
  void shouldNormalizeSigngleDecimalPlace() {
    Money money = Money.of(new BigDecimal("25.5"));

    assertEquals(new BigDecimal("25.50"), money.amount());
  }

  @Test
  void shouldRemoveTrailingZerosWithoutLosingValue() {
    Money money = Money.of(new BigDecimal("25.000"));

    assertEquals(new BigDecimal("25.00"), money.amount());
  }

  @Test
  void shouldRejectMoreThanTwoSignificantDecimalPlaces() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> Money.of(new BigDecimal("25.001")));

    assertInstanceOf(ArithmeticException.class, exception.getCause());
  }

  @Test
  void shouldAcceptMaximumAmount() {
    BigDecimal expectedAmount = new BigDecimal("9999999999.99");

    Money money = Money.of(expectedAmount);

    assertEquals(expectedAmount, money.amount());
  }

  @Test
  void shouldRejectAmountExceedingMaximum() {
    assertThrows(IllegalArgumentException.class, () -> Money.of(new BigDecimal("10000000000.00")));
  }

  @Test
  void shouldConsiderEquivalentAmountsEqual() {
    Money first = Money.of(new BigDecimal("25"));
    Money second = Money.of(new BigDecimal("25.00"));

    assertEquals(first, second);
    assertEquals(first.hashCode(), second.hashCode());
  }

  @Test
  void shouldConsiderDifferentAmountsNotEqual() {
    Money first = Money.of(new BigDecimal("25"));
    Money second = Money.of(new BigDecimal("26"));

    assertNotEquals(first, second);
  }
}
