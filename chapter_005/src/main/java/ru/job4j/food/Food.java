package ru.job4j.food;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public abstract class Food {
    private String name;
    private LocalDate expiryDate;
    private LocalDate createDate;
    private final Price price;
    private int discount;

    public Food(String name, LocalDate expiryDate, LocalDate createDate, String price) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = new Price(price);
    }

    public double getPeriodToExpirationDateInPercent() {
        long shelfLifeInDays = DAYS.between(createDate, expiryDate);
        long restToExpirationDateInDays = DAYS.between(createDate, LocalDate.now());
        return 100 * (1 - (double) restToExpirationDateInDays / shelfLifeInDays);
    }

    public Price getPriceWithDiscount() {
        return price.getPriceWithDiscount(discount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price.setPrice(price);
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
