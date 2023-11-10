package bg.softuni.vikuslugivratsa.model.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ServiceAddBindingModel {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    @Positive
    private BigDecimal price;
}
