package stellar_burger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BunTest {

    private String name;
    private float price;
    Bun bun;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getBunInfo() {
        return new Object[][]{
                {"Тестовая", 0},
                {"Beautiful", 150.99f},
                {"#12345", -01.01f}
        };
    }

    @Test
    public void getNameTest() {
        bun = new Bun(name, price);
        Assert.assertEquals("Некорректное наименование бургера", name, bun.getName());
    }

    @Test
    public void getPriceTest() {
        bun = new Bun(name, price);
        Assert.assertEquals("Некорректная цена бургера", price, bun.getPrice(), 0);
    }
}