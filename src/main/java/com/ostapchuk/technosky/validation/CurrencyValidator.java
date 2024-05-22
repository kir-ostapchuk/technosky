package com.ostapchuk.technosky.validation;

import com.ostapchuk.technosky.entity.Currency;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

    @Override
    public boolean isValid(String currency, ConstraintValidatorContext context) {
        return Optional.ofNullable(currency)
              .filter(StringUtils::hasText)
              .map(Currency::hasCurrency)
              .orElse(false);
    }
}
