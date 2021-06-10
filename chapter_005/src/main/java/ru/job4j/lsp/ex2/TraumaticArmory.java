package ru.job4j.lsp.ex2;

/*
    Есть класс оружейного склада-магазина Weapon. Изначально там
    продавалось только огнестрельное боевое оружие покупателям с лицензией.
    Со временем открылось отделение по продаже травматического оружия,
    для которого лицензия не нужно.
    Нарушение приницпа LSP в ослаблении постусловия в методе sellWeapon для
    класса TraumaticArmory.
 */

public class TraumaticArmory extends Armory {
    @Override
    public Weapon sellWeapon(Customer buyer) {
        /*
            Какой-то код по продаже травматического оружия
         */
        return new Weapon();
    }
}
