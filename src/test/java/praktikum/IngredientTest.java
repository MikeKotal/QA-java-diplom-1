package praktikum;

import org.junit.Assert;
import org.junit.Test;

import static praktikum.helpers.Constants.PRICE;
import static praktikum.helpers.Constants.SAUCE_NAME;

public class IngredientTest {

    Ingredient ingredient = new Ingredient(IngredientType.SAUCE, SAUCE_NAME, PRICE);
    @Test
    public void getPriceTest() {
        Assert.assertEquals("Присвоена некорректная стоимость", PRICE, ingredient.getPrice(), 0);
    }

    @Test
    public void getNameTest() {
        Assert.assertEquals("Присвоено некорректное наименование", SAUCE_NAME, ingredient.getName());
    }

    @Test
    public void getTypeTest() {
        Assert.assertEquals("Присвоен некорректный тип", IngredientType.SAUCE, ingredient.getType());
    }
}