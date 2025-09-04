import {IS_NOT_A_NUMBER_CODE, NUMBER_IS_OUT_OF_RANGE_CODE, BLANK_IS_EMPTY_CODE, validateNumber} from './number-validation.js'
import {drawPoint} from './canvas.js'

const Y_LEFT_BORDER = -3;
const Y_RIGHT_BORDER = 3;

const R_LEFT_BORDER = 2;
const R_RIGHT_BORDER = 5;

const btn = document.getElementById("confirm-button");

btn.addEventListener("click", validate);

function validate() {
    let x = getChosenX();

    if (x === 'DEFAULT_X') {
        alert("Ошибка: Параметр X не выбран.");
        return;
    }

    let y = validateNumber(document.getElementById("y-choice-input").value, Y_LEFT_BORDER, Y_RIGHT_BORDER);

    if (y === BLANK_IS_EMPTY_CODE) {
        alert("Ошибка: Поля ввода Y является пустым.");
        return;
    }

    if (y === IS_NOT_A_NUMBER_CODE) {
        alert("Ошибка: Параметр Y не является числом.");
        return;
    }

    if (y === NUMBER_IS_OUT_OF_RANGE_CODE) {
        alert(`Ошибка: Параметр Y выходит за границы (${Y_LEFT_BORDER}...${Y_RIGHT_BORDER}).`);
        return;
    }

    let r = validateNumber(document.getElementById("r-choice-input").value, R_LEFT_BORDER, R_RIGHT_BORDER);

    if (r === BLANK_IS_EMPTY_CODE) {
        alert("Ошибка: Поля ввода R является пустым.");
        return;
    }

    if (r === IS_NOT_A_NUMBER_CODE) {
        alert("Ошибка: Параметр R не является числом.");
        return;
    }

    if (r === NUMBER_IS_OUT_OF_RANGE_CODE) {
        alert(`Ошибка: Параметр R выходит за границы (${R_LEFT_BORDER}...${R_RIGHT_BORDER}).`);
        return;
    }

    drawPoint(x, y, r);
}

function getChosenX() {
    let resultX = 'DEFAULT_X';

    const checkboxListElements = document.getElementsByClassName("checkbox-list-element-input");
    const checkboxListElementsValues = document.getElementsByClassName("checkbox-list-element-text");

    for (let i = 0; i < checkboxListElements.length; i++) {
        if (checkboxListElements[i].checked) {
            resultX = Number(checkboxListElementsValues[i].innerText);
            break
        }
    }

    return resultX;
}