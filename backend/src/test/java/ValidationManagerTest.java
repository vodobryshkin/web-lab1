import enteties.request.records.ValidationRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import validation.managers.ValidationManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования менеджера для валидации.
 */
public class ValidationManagerTest {
    @Test
    @DisplayName("Валидация правильного запроса.")
    void testChainAllCorrect() {
        ValidationManager validationManager = new ValidationManager();
        double x = 2, y = 2, r = 2;

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertTrue(validationManager.validate(validationRequest));
    }

    @Test
    @DisplayName("Валидация полностью неправильного запроса.")
    void testChainAllIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        double x = -10, y = -10, r = -10;

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }

    @Test
    @DisplayName("Валидация запроса с неправильным X.")
    void testChainXIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        double x = -10, y = 0, r = 2;

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }

    @Test
    @DisplayName("Валидация запроса с неправильным Y.")
    void testChainYIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        double x = 1, y = 4, r = 2;

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }

    @Test
    @DisplayName("Валидация запроса с неправильным R.")
    void testChainRIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        double x = 2, y = 0, r = 1;

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }
}
