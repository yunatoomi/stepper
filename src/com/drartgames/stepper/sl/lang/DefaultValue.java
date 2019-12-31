package com.drartgames.stepper.sl.lang;

public class DefaultValue implements Value {
    private ValueType valueType;
    private String stringValue;
    private int numericValue;
    private GeneralLiteral generalLiteralValue;

    public DefaultValue(String stringValue) {
        valueType = ValueType.STRING_LITERAL;

        this.stringValue = stringValue;
    }

    public DefaultValue(int numericValue) {
        valueType = ValueType.NUMBER_LITERAL;

        this.numericValue = numericValue;
    }

    public DefaultValue(GeneralLiteral generalLiteralValue) {
        valueType = ValueType.GENERAL_LITERAL;

        this.generalLiteralValue = generalLiteralValue;
    }

    @Override
    public ValueType getValueType() {
        return null;
    }

    @Override
    public String getStringValue() {
        return null;
    }

    @Override
    public int getNumericValue() {
        return 0;
    }

    @Override
    public GeneralLiteral getGeneralLiteralValue() {
        return null;
    }
}