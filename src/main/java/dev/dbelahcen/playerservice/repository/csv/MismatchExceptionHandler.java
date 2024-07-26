package dev.dbelahcen.playerservice.repository.csv;

import com.opencsv.bean.exceptionhandler.CsvExceptionHandler;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import dev.dbelahcen.playerservice.exceptions.InitializationException;

public class MismatchExceptionHandler implements CsvExceptionHandler {
    @Override
    public CsvException handleException(CsvException e) throws CsvException {
        if (e instanceof CsvDataTypeMismatchException) {
            throw new InitializationException("Error while reading CSV", e);
        }
        // We don't know how to handle other exceptions, so we return null
        return null;
    }
}
