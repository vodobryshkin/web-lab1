package entities.geometry.areas.factory;

import entities.geometry.areas.factory.creators.interfaces.AreaCreator;
import lombok.Setter;

import java.util.List;

/**
 * Класс для автоматического создания и управления областями.
 */
public class AreaFactory {
    @Setter
    private AreaCreator areaCreator;
}
