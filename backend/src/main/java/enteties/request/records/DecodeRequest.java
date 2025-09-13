package enteties.request.records;

/**
 * Класс с данными для отправки на десериализацию в JSON.
 * Получается в результате обработки поступивших данных бизнес-логикой.
 *
 * @param x координата по оси x.
 * @param y координата по оси y.
 * @param r радиус на координатной плоскости.
 * @param status результат обработки бизнес-логикой.
 */
public record DecodeRequest(double x, double y, double r, boolean status) {}
