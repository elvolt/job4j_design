package ru.job4j.food;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Price {
    private final static RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
    private final static int DECIMALS = 2;
    private final static NumberFormat RU_FORMAT =
            NumberFormat.getCurrencyInstance(new Locale("ru", "RU"));

    static {
        RU_FORMAT.setMinimumFractionDigits(1);
        RU_FORMAT.setMaximumFractionDigits(2);
    }

    private BigDecimal price;

    public Price(String price) {
        this.price = rounded(new BigDecimal(price));
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = rounded(new BigDecimal(price));
    }

    private BigDecimal rounded(BigDecimal number) {
        return number.setScale(DECIMALS, ROUNDING_MODE);
    }

    public Price getPriceWithDiscount(int discount) {
        BigDecimal priceWithDiscount = this.getPrice().multiply(
                BigDecimal.valueOf(1 - (double) discount / 100));
        return new Price(priceWithDiscount.toString());
    }

    @Override
    public String toString() {
        return RU_FORMAT.format(price.doubleValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price1 = (Price) o;
        return Objects.equals(price, price1.price);
    }

    @Override
    public int hashCode() {
        return price != null ? price.hashCode() : 0;
    }
}
