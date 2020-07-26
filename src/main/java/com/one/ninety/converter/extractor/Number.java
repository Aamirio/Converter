package com.one.ninety.converter.extractor;

import java.util.Arrays;

public enum Number {

    ZERO(0, "zero"),
    ONE(1, "one"),
    TWO(2, "two"),
    THREE(3, "three"),
    FOUR(4, "four"),
    FIVE(5, "five"),
    SIX(6, "six"),
    SEVEN(7, "seven"),
    EIGHT(8, "eight"),
    NINE(9, "nine"),
    TEN(10, "ten"),
    ELEVEN(11, "eleven"),
    TWELVE(12, "twelve"),
    THIRTEEN(13, "thirteen"),
    FOURTEEN(14, "fourteen"),
    FIFTEEN(15, "fifteen"),
    SIXTEEN(16, "sixteen"),
    SEVENTEEN(17, "seventeen"),
    EIGHTEEN(18, "eighteen"),
    NINETEEN(19, "nineteen"),
    TWENTY(20, "twenty"),
    THIRTY(30, "thirty"),
    FORTY(40, "forty"),
    FIFTY(50, "fifty"),
    SIXTY(60, "sixty"),
    SEVENTY(70, "seventy"),
    EIGHTY(80, "eighty"),
    NINETY(90, "ninety");

    public final Integer number;
    public final String name;

    Number(int number, String name) {
        this.number = number;
        this.name = name;
    }

    /**
     * Gets corresponding Number enum for given integer
     * @param number number of required enum
     * @return Corresponding Number enum for given integer, null if corresponding Number enum does not exist
     */
    public static Number getNumber(int number) {

        return Arrays.stream(Number.values())
                .filter(n -> n.number == number)
                .findFirst()
                .orElse(null);
    }
}
