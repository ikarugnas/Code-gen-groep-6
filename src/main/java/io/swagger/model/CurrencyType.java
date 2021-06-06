package io.swagger.model;

public enum CurrencyType {
    EUR("EUR");

    private String value;

    CurrencyType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static CurrencyType fromValue(String text) {
        for (CurrencyType b : CurrencyType.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
