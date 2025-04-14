package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BurgerTests {

    private Bun mockBun;
    private Ingredient mockIngredient1;
    private Ingredient mockIngredient2;
    private Burger burger;

    @Before
    public void setup() {
        // Создаем моки для булочки и ингредиентов
        mockBun = Mockito.mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(10f); // Булочка стоит 10 единиц
        when(mockBun.getName()).thenReturn("Булочка");

        mockIngredient1 = Mockito.mock(Ingredient.class);
        when(mockIngredient1.getPrice()).thenReturn(15f); // Первый ингредиент стоит 15 единиц
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("Соус");

        mockIngredient2 = Mockito.mock(Ingredient.class);
        when(mockIngredient2.getPrice()).thenReturn(20f); // Второй ингредиент стоит 20 единиц
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn("Начинка");

        // Создаем объект Burger
        burger = new Burger();
        burger.setBuns(mockBun);
    }

    @Test
    public void testGetPriceWithParams() {
        // Добавляем ингредиенты
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Проверка цены
        assertEquals(55f, burger.getPrice(), 0.001f);
    }

    @Test
    public void testRemoveIngredient_Count() {
        // Добавляем два ингредиента
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Удаляем первый ингредиент
        burger.removeIngredient(0);

        // Проверяем количество ингредиентов
        assertEquals(1, burger.getIngredients().size());
    }

    @Test
    public void testRemoveIngredient_Price() {
        // Добавляем два ингредиента
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Удаляем первый ингредиент
        burger.removeIngredient(0);

        // Проверяем итоговую цену
        assertEquals(40f, burger.getPrice(), 0.01f);
    }

    @Test
    public void testMoveIngredient_Order() {
        // Добавляем два ингредиента
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Перемещаем второй ингредиент на первое место
        burger.moveIngredient(1, 0);

        // Проверяем порядок ингредиентов
        assertEquals(mockIngredient2, burger.getIngredients().get(0));
        assertEquals(mockIngredient1, burger.getIngredients().get(1));
    }

    @Test
    public void testMoveIngredient_Price() {
        // Добавляем два ингредиента
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Перемещаем второй ингредиент на первое место
        burger.moveIngredient(1, 0);

        // Цена должна остаться прежней
        assertEquals(55f, burger.getPrice(), 0.01f);
    }

    @Test
    public void testGetReceiptWithoutIngredients() {
        // Получаем чек
        String receipt = burger.getReceipt();

        // Проверяем содержимое чека
        assertEquals(
                "(==== Булочка ====)\n" +
                        "(==== Булочка ====)\n\n" +
                        "Price: 20,000000\n",
                receipt);
    }

    @Test
    public void testGetReceiptWithTwoIngredients() {
        // Добавляем ингредиенты
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Получаем чек
        String receipt = burger.getReceipt();

        // Проверяем содержимое чека
        assertEquals(
                "(==== Булочка ====)\n" +
                        "= sauce Соус =\n" +
                        "= filling Начинка =\n" +
                        "(==== Булочка ====)\n\n" +
                        "Price: 55,000000\n",
                receipt);
    }
}