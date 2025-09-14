package logic;

import entities.geometry.areas.interfaces.Area;
import entities.request.implementations.messages.CheckoutRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для реализации бизнес-логики лабораторной работы.
 * Проверяет вхождение точки в области.
 */
public class CheckoutManager {
    private final AreaContext areaContext = new AreaContext();
    private final List<Area> areaList = new ArrayList<>();

    /**
     * Метод для запуска проверок на запрос
     * Каждый раз устанавливаем область в AreaContext и он её проверяет.
     * Если точка лежит хоть в одной из областей, то status=true и нет смысла смотреть вхождение дальше.
     *
     * @param request запрос для проверки.
     * @return информацию входит ли точка в область (true) или нет (false).
     */
    public boolean checkRequest(CheckoutRequest request) {
        boolean status = false;

        for (Area area: areaList) {
            areaContext.setGeometryArea(area);

            if (areaContext.execute(request.point())) {
                status = true;
                break;
            }
        }

        return status;
    }

    private void addArea(Area area) {
        areaList.add(area);
    }
}
