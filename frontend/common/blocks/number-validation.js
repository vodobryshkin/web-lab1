export const NUMBER_REGEX = /^-?\d*[.,]?\d+$/;

export const IS_NOT_A_NUMBER_CODE = "IS_NOT_A_NUMBER";
export const NUMBER_IS_OUT_OF_RANGE_CODE = "NUMBER_IS_OUT_OF_RANGE";
export const BLANK_IS_EMPTY_CODE = "BLANK_IS_EMPTY";

export function validateNumber(number, leftBorder, rightBorder) {
    if (number.length === 0) {
        return BLANK_IS_EMPTY_CODE;
    }

    if (!NUMBER_REGEX.test(number)) {
        return IS_NOT_A_NUMBER_CODE;
    }

    let numNumber = Number(number);

    if (numNumber < leftBorder || numNumber > rightBorder) {
        return NUMBER_IS_OUT_OF_RANGE_CODE;
    }

    return numNumber;
}