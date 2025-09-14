import enteties.request.records.ValidationRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import validation.managers.ValidationManager;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования менеджера для валидации.
 */
public class ValidationManagerTest {
    @Test
    @DisplayName("Валидация правильного запроса.")
    void testChainAllCorrect() {
        ValidationManager validationManager = new ValidationManager();
        BigDecimal x = new BigDecimal("2");
        BigDecimal y = new BigDecimal("2");
        BigDecimal r = new BigDecimal("2");

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertTrue(validationManager.validate(validationRequest));
    }

    @Test
    @DisplayName("Валидация полностью неправильного запроса.")
    void testChainAllIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        BigDecimal x = new BigDecimal("-10");
        BigDecimal y = new BigDecimal("-10");
        BigDecimal r = new BigDecimal("-10");

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }

    @Test
    @DisplayName("Валидация запроса с неправильным X.")
    void testChainXIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        BigDecimal x = new BigDecimal("-10");
        BigDecimal y = new BigDecimal("0");
        BigDecimal r = new BigDecimal("2");

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }

    @Test
    @DisplayName("Валидация запроса с неправильным Y.")
    void testChainYIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        BigDecimal x = new BigDecimal("1");
        BigDecimal y = new BigDecimal("4");
        BigDecimal r = new BigDecimal("2");

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }

    @Test
    @DisplayName("Валидация запроса с неправильным R.")
    void testChainRIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        BigDecimal x = new BigDecimal("2");
        BigDecimal y = new BigDecimal("0");
        BigDecimal r = new BigDecimal("1");

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }
}
