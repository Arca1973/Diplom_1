package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class IngredientTest {

    @Test
    public void testGetPrice() {
        // Создаем объект ингредиента
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Кетчуп", 10f);

        // Проверяем, что метод getPrice возвращает правильное значение
        assertEquals("Цена должна быть равна 10", 10f, ingredient.getPrice(), 0.001f); // 0.001f — допустимая погрешность для сравнения чисел с плавающей точкой
    }

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Колбаса", 15f);

        // Проверяем, что метод getName возвращает правильное значение
        assertEquals("Название должно быть 'Колбаса'", "Колбаса", ingredient.getName());
    }

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Майонез", 8f);

        // Проверяем, что метод getType возвращает правильный тип
        assertEquals("Тип должен быть SAUCE", IngredientType.SAUCE, ingredient.getType());
    }

}