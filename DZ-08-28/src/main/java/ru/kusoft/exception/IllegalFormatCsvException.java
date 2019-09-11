package ru.kusoft.exception;

public class IllegalFormatCsvException extends RuntimeException {

    public IllegalFormatCsvException() {
    }

    public IllegalFormatCsvException(String message) {
        super(message);
    }
}
