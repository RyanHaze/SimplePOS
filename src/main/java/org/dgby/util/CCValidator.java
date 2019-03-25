package org.dgby.util;

import java.util.EnumMap;
import java.util.Map;

import javafx.util.Pair;

/**
 * Validates credit/debit card numbers.
 */
public class CCValidator {
    private enum ECardType {
        AMEX, DISC, MC, VISA, INVALID
    }

    private static Map<ECardType, Pair<Integer, Integer>> validLength = new EnumMap<>(ECardType.class) {
        private static final long serialVersionUID = 121088074870188678L;

        {
            put(ECardType.AMEX, new Pair<Integer, Integer>(15, 15));
            put(ECardType.DISC, new Pair<Integer, Integer>(16, 19));
            put(ECardType.MC, new Pair<Integer, Integer>(16, 16));
            put(ECardType.VISA, new Pair<Integer, Integer>(13, 19));
        }
    };

    private static ECardType getCardType(String number) {
        CharSequence prefix = number.subSequence(0, 6);
        Integer prefixInt = Integer.parseInt(number.substring(0, 6));

        switch (prefix.charAt(0)) {
            case '2':
                if (222100 < prefixInt && prefixInt < 272099)
                    return ECardType.MC;
            case '3':
                switch (prefix.charAt(1)) {
                    case '4':
                    case '7':
                        return ECardType.AMEX;
                }
            case '4':
                return ECardType.VISA;
            case '5':
                if (510000 < prefixInt && prefixInt < 559999)
                    return ECardType.MC;
            case '6':
                if ((601100 < prefixInt && prefixInt < 601109) || (601120 < prefixInt && prefixInt < 601149)
                        || (prefixInt == 601174) || (601177 < prefixInt && prefixInt < 601179)
                        || (601186 < prefixInt && prefixInt < 601199) || (644000 < prefixInt && prefixInt < 659999))
                    return ECardType.DISC;
        }
        return ECardType.INVALID;
    }

    /**
     * Validates a credit/debit card number using the Luhn Formula.
     * 
     * @param number
     *                   The credit/debit card number.
     * @return Result of the validation.
     */
    public static boolean isValid(String number) {
        Integer ccLength = number.length();
        if (!number.matches("^[0-9]+$") || ccLength < 6)
            return false;

        ECardType cardType = getCardType(number);
        if (cardType == ECardType.INVALID)
            return false;

        Pair<Integer, Integer> lengthRange = validLength.get(cardType);
        if ((ccLength < lengthRange.getKey()) || (ccLength > lengthRange.getValue()))
            return false;

        Integer checksum = Integer.parseInt(number.substring(number.length() - 1));
        number = new StringBuilder(number.substring(0, number.length() - 1)).reverse().toString();

        Integer sum = 0;
        for (int i = 0; i < number.length(); ++i) {
            Integer digit = Character.getNumericValue(number.charAt(i));

            if (((i + 1) % 2) == 1)
                digit *= 2;

            if (digit > 9)
                digit -= 9;

            sum += digit;
        }

        return (sum + checksum) % 10 == 0;
    }
}
