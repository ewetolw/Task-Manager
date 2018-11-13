package com.pd.eweltol.taskmanager.validator;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> response = new ArrayList<>();


    public void addValidationError(String error) {
        response.add(error);
    }

    public List<String> getErrors() {
        return response;
    }


}