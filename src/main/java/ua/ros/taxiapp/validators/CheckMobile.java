package ua.ros.taxiapp.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MobileValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckMobile {

    String message() default "must be in format +380XXXXXXXXX";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

