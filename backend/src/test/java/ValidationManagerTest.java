import enteties.request.records.ValidationRequest;
import org.junit.jupiter.api.Test;
import validation.managers.ValidationManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования менеджера для валидации.
 */
public class ValidationManagerTest {
    /**
     * Все корректно
     */
    @Test
    void testChainAllCorrect() {
        ValidationManager validationManager = new ValidationManager();
        double x = 2, y = 2, r = 2;

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertTrue(validationManager.validate(validationRequest));
    }

    /**
     * Все неправильно.
     */
    @Test
    void testChainAllIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        double x = -10, y = -10, r = -10;

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }

    /**
     * X неправильный.
     */
    @Test
    void testChainXIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        double x = -10, y = 0, r = 2;

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }

    /**
     * Y неправильный.
     */
    @Test
    void testChainYIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        double x = 1, y = 4, r = 2;

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }

    /**
     * R неправильный.
     */
    @Test
    void testChainRIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        double x = 2, y = 0, r = 1;

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }
}
