import {IS_NOT_A_NUMBER_CODE, NUMBER_IS_OUT_OF_RANGE_CODE, BLANK_IS_EMPTY_CODE, validateNumber} from '../tools/number-validation.js'
import {drawPoint} from './canvas.js'
import {pointStorage} from '../tools/storage-work.js'
import {toast} from "../tools/toast.js";

import Decimal from "../../libs/decimal.js/decimal.mjs";

const Y_LEFT_BORDER = new Decimal(-3);
const Y_RIGHT_BORDER = new Decimal(3);

const R_LEFT_BORDER = new Decimal(2);
const R_RIGHT_BORDER = new Decimal(5);

const btn = document.getElementById("confirm-button");

btn.addEventListener("click", validate);

async function validate() {

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

    let x = getChosenX();

    if (x.length === 0) {
        toast("Параметр X не выбран");
    } else {
        for (let i = 0; i < x.length; i++) {
            let data = await sendPoint(x[i], y, r);

            if (data) {
                pointStorage.addPoint(x[i], y);
                drawPoint(x[i], y, r);
                addInfoAboutPoint(data.x, data.y, data.r, data.status, data.current_time, data.duration);
            }
        }

    }
}

export function addInfoAboutPoint(x, y, r, status, current_time, duration) {
    let table = document.getElementById("result-table");
    let row = table.insertRow(1);

    row.insertCell(0).textContent = x;
    row.insertCell(1).textContent = y;
    row.insertCell(2).textContent = r;
    row.insertCell(3).textContent = status ? "Попал" : "Не попал";
    row.insertCell(4).textContent = current_time;
    row.insertCell(5).textContent = duration + " ms";
}

async function sendPoint(x, y, r) {
    try {
        const response = await fetch('/fcgi-bin/', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ x, y, r })
        });

        const result = await response.json();

        return { ...result};
    } catch (error) {
        toast(error);
        return null;
    }
}


function getChosenX() {
    let resultX = [];

    const checkboxListElements = document.getElementsByClassName("checkbox-list-element-input");
    const checkboxListElementsValues = document.getElementsByClassName("checkbox-list-element-text");

    for (let i = 0; i < checkboxListElements.length; i++) {
        if (checkboxListElements[i].checked) {
            resultX.push(Number(checkboxListElementsValues[i].innerText));
        }
    }

    return resultX;
}