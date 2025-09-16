import {IS_NOT_A_NUMBER_CODE, NUMBER_IS_OUT_OF_RANGE_CODE, BLANK_IS_EMPTY_CODE, validateNumber} from '../tools/number-validation.js'
import {drawPoint} from './canvas.js'
import {pointStorage} from '../tools/storage-work.js'
import {toast} from "../tools/toast.js";

const Y_LEFT_BORDER = -3;
const Y_RIGHT_BORDER = 3;

const R_LEFT_BORDER = 2;
const R_RIGHT_BORDER = 5;

const btn = document.getElementById("confirm-button");

btn.addEventListener("click", validate);

async function validate() {
    let x = getChosenX();

    if (x === 'DEFAULT_X') {
        toast("Параметр X не выбран");
        return;
    }

    let y = validateNumber(document.getElementById("y-choice-input").value, Y_LEFT_BORDER, Y_RIGHT_BORDER);
    switch (y) {
        case BLANK_IS_EMPTY_CODE :
            toast("Поля ввода параметра Y является пустым");
            return;
        case IS_NOT_A_NUMBER_CODE :
            toast("Параметр Y не является числом");
            return;
        case NUMBER_IS_OUT_OF_RANGE_CODE :
            toast(`Параметр Y выходит за границы (${Y_LEFT_BORDER}...${Y_RIGHT_BORDER})`);
            return;
    }

    let r = validateNumber(document.getElementById("r-choice-input").value, R_LEFT_BORDER, R_RIGHT_BORDER);
    switch (r) {
        case BLANK_IS_EMPTY_CODE :
            toast("Поля ввода параметра R является пустым");
            return;
        case IS_NOT_A_NUMBER_CODE :
            toast("Параметр R не является числом");
            return;
        case NUMBER_IS_OUT_OF_RANGE_CODE :
            toast(`Параметр R выходит за границы (${R_LEFT_BORDER}...${R_RIGHT_BORDER})`);
            return;
    }

    console.log("123")

    pointStorage.addPoint(x, y);
    drawPoint(x, y, r);

    const result = await sendPoint(x, y, r);
    if (result) {
        toast(`Сервер ответил: ${JSON.stringify(result)}`);
    }
}

async function sendPoint(x, y, r) {
    fetch('/fcgi-bin/', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ x: x, y: y, r: r })
    })

        .then(response => response.json())
        .then(result => {
            console.log(result.x, result.y, result.r, result.status);
        })
        .catch(error => {
            alert(error);
        });
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