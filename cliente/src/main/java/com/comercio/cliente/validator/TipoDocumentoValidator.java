package com.comercio.cliente.validator;

import com.comercio.cliente.dto.TipoDocumento;
import com.comercio.cliente.exception.InvalidTipoDocumentoException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoDocumentoValidator implements ConstraintValidator<ValidTipoDocumento, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        try {
            TipoDocumento tipo = TipoDocumento.valueOf(value.toUpperCase().replace(" ", "_"));
            return tipo != null;
        } catch (IllegalArgumentException e) {
            throw new InvalidTipoDocumentoException("Tipo de documento inválido: " + value);
        }
    }
}
