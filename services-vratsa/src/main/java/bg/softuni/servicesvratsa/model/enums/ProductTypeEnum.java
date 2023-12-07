package bg.softuni.servicesvratsa.model.enums;

public enum ProductTypeEnum {

    WATER_METER("Водомер"),
    WATER_TAP("Кран"),
    PLUMBING_WRENCH("Водопроводен ключ");

    private final String description;

    ProductTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
