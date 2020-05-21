package kgu.lab.utils;

import kgu.lab.model.Response;

public enum ErrorEnum {
    SUCCESS(0, "Обработка запроса прошла без системных ошибок"),
    REQUEST_PARAMETERS_MISSING(1, "Отсутсвие обязательных параметров запроса"),
    DATA_FROM_DB_LOADING_ERROR(2, "Ошибка загрузки данных из БД"),
    SYSTEM_ERROR(3, "Системная ошибка"),
    INCORRECT_DATA_INPUT(4, "Неккоректно введены данные");

    private final int code;
    private final String message;

    ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static Response createResponse(ErrorEnum error) {
        Response response = new Response();
        response.setErrorCode(error.getCode());
        response.setErrorMessage(error.getMessage());
        return response;
    }
}
