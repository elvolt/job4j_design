package ru.job4j.lsp.ex2;

public class Armory {
    public Weapon sellWeapon(Customer buyer) {
        if (!buyer.isLicense()) {
            throw new IllegalArgumentException("You can't buy this weapon");
        }
        /*
            Какой-то код по продаже оружия
         */
        return new Weapon();
    }
}
