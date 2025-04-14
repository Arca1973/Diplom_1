package praktikum;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class ParameterizedIngredientTypeTest {

    private IngredientType ingredientType;

    public ParameterizedIngredientTypeTest(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }

    @Parameters(name = "{index}: Test with {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE},
                {IngredientType.FILLING}
        });
    }

    @Test
    public void testParameterizedValueName() {
        assertEquals(ingredientType.name(), ingredientType.toString()); // Проверка имени значения
    }

    @Test
    public void testParameterizedNotNull() {
        assertNotNull(ingredientType); // Проверка наличия значения
    }
}
