package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerTests {

    private Bun mockedBun;
    private Ingredient[] mockedIngredients;
    private float expectedPrice;

    private Burger burger;
    private Bun mockBun;
    private Ingredient mockIngredient1;
    private Ingredient mockIngredient2;

    // Конструктор для параметризованных тестов
    public BurgerTests(Bun bun, Ingredient[] ingredients, float expectedPrice) {
        this.mockedBun = bun;
        this.mockedIngredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "{index}: {0}, Ингредиенты: {1}, Ожидаемая Цена: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // Тестовые данные
                {mockBun(8f), new Ingredient[]{mockIngredient(3f)}, 19f},
                {mockBun(12f), new Ingredient[]{mockIngredient(7f), mockIngredient(5f)}, 36f},
                {mockBun(9f), new Ingredient[]{mockIngredient(6f), mockIngredient(4f)}, 28f},
        });
    }

    // Метод для создания мока Bun
    private static Bun mockBun(float price) {
        Bun mock = Mockito.mock(Bun.class);
        when(mock.getPrice()).thenReturn(price);
        return mock;
    }

    // Метод для создания мока Ingredient
    private static Ingredient mockIngredient(float price) {
        Ingredient mock = Mockito.mock(Ingredient.class);
        when(mock.getPrice()).thenReturn(price);
        return mock;
    }

    @Before
    public void setup() {
        // Создаем моки для булочки и ингредиентов
        mockBun = Mockito.mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(10f); // Булочка стоит 10 единиц

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
        // Создаем объект бургера
        Burger burger = new Burger();
        burger.setBuns(mockedBun);

        // Добавляем ингредиенты
        for (Ingredient ingredient : mockedIngredients) {
            burger.addIngredient(ingredient);
        }

        // Проверка цены
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
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
        // Настройка моков
        when(mockBun.getName()).thenReturn("Булочка");
        burger.setBuns(mockBun);

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
        // Настройка моков
        when(mockBun.getName()).thenReturn("Булочка");
        burger.setBuns(mockBun);
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
