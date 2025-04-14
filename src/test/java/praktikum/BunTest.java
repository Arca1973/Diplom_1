package praktikum;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTest {

    private Bun bun;

    @Before
    public void setUp() {
        // Создаем объект булочки перед каждым тестом
        bun = new Bun("Классическая булочка", 50f);
    }

    @Test
    public void testGetName() {
        // Проверяем метод getName()
        assertEquals(
                "Метод getName() должен возвращать корректное название булочки",
                "Классическая булочка",
                bun.getName()
        );
    }

    @Test
    public void testGetPrice() {
        // Проверяем метод getPrice()
        assertEquals(
                "Метод getPrice() должен возвращать корректную цену булочки",
                50f,
                bun.getPrice(),
                0.001 // Точность сравнения для float
        );
    }
}