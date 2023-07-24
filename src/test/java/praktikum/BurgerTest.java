package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static praktikum.helpers.Constants.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun = new Bun(BUN_NAME, PRICE);
    @Mock
    Ingredient ingredientSauce = new Ingredient(IngredientType.SAUCE, SAUCE_NAME, PRICE);
    @Mock
    Ingredient ingredientFilling = new Ingredient(IngredientType.FILLING, FILLING_NAME, PRICE);
    @Spy
    Burger burger;

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        Assert.assertNotNull("Отсутствует экземпляр класса", burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        Assert.assertEquals("Некорректное число элементов в массиве", 2, burger.ingredients.size());
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        burger.removeIngredient(1);
        Assert.assertEquals("Некорректное число элементов в массиве после удаления",
                1, burger.ingredients.size());
    }

    @Test
    public void moveIngredientTest() {
        String message = "Ошибка при перемещении ингредиентов";
        Mockito.when(ingredientSauce.getType()).thenReturn(IngredientType.SAUCE);
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);
        burger.moveIngredient(0, 1);
        Assert.assertEquals(message, IngredientType.SAUCE, burger.ingredients.get(1).getType());
    }

    @Test
    public void getPriceTest() {
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredientFilling.getPrice()).thenReturn(100f);
        burger.addIngredient(ingredientFilling);
        burger.setBuns(bun);
        Assert.assertEquals("Расчет стоимости произведен некорректно",
                300, burger.getPrice(), 0);
        Mockito.verify(bun, Mockito.times(1)).getPrice();
        Mockito.verify(ingredientFilling, Mockito.times(1)).getPrice();
    }

    @Test
    public void getReceiptTest() {
        StringBuilder expected = new StringBuilder()
                .append(String.format("(==== %s ====)%n", BUN_NAME))
                .append(String.format("= %s %s =%n", "filling", FILLING_NAME))
                .append(String.format("(==== %s ====)%n", BUN_NAME))
                .append(String.format("%nPrice: %f%n", 400f));
        Mockito.when(bun.getName()).thenReturn(BUN_NAME);
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredientFilling.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredientFilling.getName()).thenReturn(FILLING_NAME);
        burger.addIngredient(ingredientFilling);
        burger.setBuns(bun);
        Mockito.when(burger.getPrice()).thenReturn(400f);
        Assert.assertEquals("Ошибка в структуре рецепта", expected.toString(), burger.getReceipt());
        Mockito.verify(ingredientFilling, Mockito.times(1)).getType();
        Mockito.verify(ingredientFilling, Mockito.times(1)).getName();
        Mockito.verify(bun, Mockito.times(2)).getName();
        Mockito.verify(burger, Mockito.times(1)).getPrice();
    }
}