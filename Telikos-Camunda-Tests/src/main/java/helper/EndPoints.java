package helper;

public enum EndPoints {
    TEMPLATE_PET_STORE_ORDER("/store/order");


    private final String value;

    public String getValue()
    {
        return this.value;
    }

    EndPoints(String value)
    {
        this.value = value;
    }
}
