import {draw} from "../blocks/canvas.js";
import {pointStorage} from "./storage-work.js";

export const DEFAULT_R_NAME = "R";

document.addEventListener("DOMContentLoaded", () => reset(DEFAULT_R_NAME));

function reset(r) {
    const yChoiceInput = document.getElementById("y-choice-input");
    yChoiceInput.value = "";

    const rChoiceInput = document.getElementById("r-choice-input");
    rChoiceInput.value = "";

    const checkboxListElements = document.getElementsByClassName("checkbox-list-element-input");

    for (let i = 0; i < checkboxListElements.length; i++) {
        checkboxListElements[i].checked = false;
    }

    pointStorage.clearPoints();

    draw(r);
}

