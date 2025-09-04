import {validateNumber} from "./number-validation.js";
import {draw} from "./canvas.js";

const rChoiceInput = document.getElementById("r-choice-input");

rChoiceInput.addEventListener('input', () => {redrawCanvas(rChoiceInput.value)});

function redrawCanvas(r) {
    if (typeof validateNumber(r, 2, 5) !== "string") {
        draw(r);
    }
}