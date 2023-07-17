package praktikum;

import org.junit.Assert;
import org.junit.Test;

import static praktikum.helpers.Constants.*;

public class BunTest {

    Bun bun = new Bun(BUN_NAME, PRICE);

    @Test
    public void getNameTest() {
        Assert.assertEquals("Некорректное наименование бургера", BUN_NAME, bun.getName());
    }

    @Test
    public void getPriceTest() {
        Assert.assertEquals("Некорректная цена бургера", PRICE, bun.getPrice(), 0);
    }
}