import entities.request.implementations.messages.ValidationRequest;
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
        String x = "2";
        String y = "2";
        String r = "2";

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertTrue(validationManager.validate(validationRequest));
    }

    @Test
    @DisplayName("Валидация полностью неправильного запроса.")
    void testChainAllIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        String x = "-10";
        String y = "-10";
        String r = "-10";

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }

    @Test
    @DisplayName("Валидация запроса с неправильным X.")
    void testChainXIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        String x = "-10";
        String y = "0";
        String r = "2";

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }

    @Test
    @DisplayName("Валидация запроса с неправильным Y.")
    void testChainYIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        String x = "1";
        String y = "4";
        String r = "2";

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }

    @Test
    @DisplayName("Валидация запроса с неправильным R.")
    void testChainRIncorrect() {
        ValidationManager validationManager = new ValidationManager();
        String x = "2";
        String y = "0";
        String r = "1";

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }

    @Test
    @DisplayName("Валидация нецифрового запроса.")
    void testNotNumber() {
        ValidationManager validationManager = new ValidationManager();
        String x = "2askfmw";
        String y = "0vovan";
        String r = "1111111";

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }

    @Test
    @DisplayName("Валидация цифрового запроса с запятыми.")
    void testNumberButCommas() {
        ValidationManager validationManager = new ValidationManager();
        String x = "2,0";
        String y = "0,0001";
        String r = "-1,23";

        ValidationRequest validationRequest = new ValidationRequest(x, y, r);
        assertFalse(validationManager.validate(validationRequest));
    }
}
