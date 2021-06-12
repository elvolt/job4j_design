package ru.job4j.food;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class PriceTest {
    @Test
    public void whenCreatePriceThenGet() {
        Price price = new Price("12.3");
        BigDecimal expected = BigDecimal.valueOf(12.3).setScale(2, RoundingMode.HALF_EVEN);
        assertEquals(expected, price.getPrice());
    }

    @Test
    public void whenSetPriceThenGet() {
        Price price = new Price("12.3");
        price.setPrice("14.23");
        BigDecimal expected = BigDecimal.valueOf(14.23).setScale(2, RoundingMode.HALF_EVEN);
        assertEquals(expected, price.getPrice());
    }

    @Test
    public void whenGetPriceWithDiscount() {
        Price price = new Price("10");
        BigDecimal expected = BigDecimal.valueOf(7).setScale(2, RoundingMode.HALF_EVEN);
        assertEquals(expected, price.getPriceWithDiscount(30).getPrice());
    }
}