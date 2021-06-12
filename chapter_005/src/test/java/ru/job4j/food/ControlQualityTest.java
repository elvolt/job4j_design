package ru.job4j.food;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class ControlQualityTest {
    @Test
    public void whenExpiredThenDistributeToTrash() {
        Food bread = new Bread("Bread", LocalDate.of(2021, 4, 12),
                LocalDate.of(2021, 2, 2), "12");
        Storage trash = new Trash();
        List<Storage> storages = List.of(new Warehouse(), trash, new Shop());
        ControlQuality controlQuality = new ControlQuality(storages);
        controlQuality.distribute(bread);
        assertEquals(1, trash.getStorage().size());
        assertEquals("Bread", trash.getStorage().get(0).getName());
    }

    @Test
    public void whenPeriodToExpirationDateBetween25And75PercentsThenDistributeToShop() {
        Food bread = new Bread("Bread", LocalDate.now().plusDays(6),
                LocalDate.now().minusDays(4), "12");
        Storage shop = new Shop();
        List<Storage> storages = List.of(new Warehouse(), new Trash(), shop);
        ControlQuality controlQuality = new ControlQuality(storages);
        controlQuality.distribute(bread);
        assertEquals(1, shop.getStorage().size());
        assertEquals("Bread", shop.getStorage().get(0).getName());
    }

    @Test
    public void whenPeriodToExpirationLessThan25PercentsThenDistributeToShopWithDiscount() {
        Food bread = new Bread("Bread", LocalDate.now().plusDays(2),
                LocalDate.now().minusDays(7), "12");
        Storage shop = new Shop();
        List<Storage> storages = List.of(new Warehouse(), new Trash(), shop);
        ControlQuality controlQuality = new ControlQuality(storages);
        controlQuality.distribute(bread);
        assertEquals(1, shop.getStorage().size());
        assertEquals("Bread", shop.getStorage().get(0).getName());
        assertEquals(new Price("6"), bread.getPriceWithDiscount());
    }

    @Test
    public void whenPeriodToExpirationDateHigher75PercentsThenDistributeToWarehouse() {
        Food bread = new Bread("Bread", LocalDate.now().plusDays(7),
                LocalDate.now().minusDays(2), "12");
        Storage warehouse = new Warehouse();
        List<Storage> storages = List.of(warehouse, new Trash(), new Shop());
        ControlQuality controlQuality = new ControlQuality(storages);
        controlQuality.distribute(bread);
        assertEquals(1, warehouse.getStorage().size());
        assertEquals("Bread", warehouse.getStorage().get(0).getName());
    }
}