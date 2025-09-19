import {validateNumber} from "../tools/number-validation.js";
import {draw, drawPoints} from "./canvas.js";

import Decimal from "../../libs/decimal.js/decimal.mjs";

const rChoiceInput = document.getElementById("r-choice-input");

rChoiceInput.addEventListener('input', () => {redrawCanvas(rChoiceInput.value)});

function redrawCanvas(r) {
    if (typeof validateNumber(r.toString(), new Decimal(2), new Decimal(5)) !== "string") {
        draw(Number(r));
        drawPoints(Number(r));
    }
}