import {validateNumber} from "../tools/number-validation.js";
import {draw, drawPoints} from "./canvas.js";

const rChoiceInput = document.getElementById("r-choice-input");

rChoiceInput.addEventListener('input', () => {redrawCanvas(rChoiceInput.value)});

function redrawCanvas(r) {
    if (typeof validateNumber(r, 2, 5) !== "string") {
        draw(r);
        drawPoints(r);
    }
}