package com.comitialsign.First_RESTful_API.service.exception;

import java.io.Serial;

public class NotFoundException extends BusinessException{

    @Serial
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super("User not found");
    }
}
