package io.batchflow.settlement.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money {

  private final BigDecimal amount;
  private static final BigDecimal MAX_AMOUNT = new BigDecimal("9999999999.99");

  private Money(BigDecimal amount) {
    this.amount = validateAmount(amount);
  }

  private static BigDecimal validateAmount(BigDecimal amount) {
    Objects.requireNonNull(amount, "amount must not be null");

    if (amount.signum() == -1) throw new IllegalArgumentException("amount must not be negative");

    if (amount.compareTo(MAX_AMOUNT) > 0)
      throw new IllegalArgumentException("amount must not exceed " + MAX_AMOUNT);

    try {
      return amount.setScale(2, RoundingMode.UNNECESSARY);
    } catch (ArithmeticException e) {
      throw new IllegalArgumentException("amount must have at most 2 decimal places", e);
    }
  }

  public static Money of(BigDecimal amount) {
    return new Money(amount);
  }

  public BigDecimal amount() {
    return amount;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((amount == null) ? 0 : amount.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Money other = (Money) obj;
    if (amount == null) {
      if (other.amount != null) return false;
    } else if (!amount.equals(other.amount)) return false;
    return true;
  }
}
