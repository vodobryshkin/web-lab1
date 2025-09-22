import {draw, drawPoint} from "../blocks/canvas.js";
import {pointStorage} from "./storage-work.js";
import {toast} from "./toast.js";

export const DEFAULT_R_NAME = "R";

document.addEventListener("DOMContentLoaded", () => reset(DEFAULT_R_NAME));

async function reset(r) {
    const yChoiceInput = document.getElementById("y-choice-input");
    yChoiceInput.value = "";

    const rChoiceInput = document.getElementById("r-choice-input");
    rChoiceInput.value = "";

    const checkboxListElements = document.getElementsByClassName("checkbox-list-element-input");

    for (let i = 0; i < checkboxListElements.length; i++) {
        checkboxListElements[i].checked = false;
    }

    pointStorage.clearPoints();

    try {
        let sendDataResponse = false;
        let minioRequestType = "get";

        const response = await fetch('/fcgi-bin/', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({sendDataResponse, minioRequestType})
        });

        const result = await response.json();

        result.points.forEach(point => {
            pointStorage.addPoint(point.x, point.y)
            drawPoint(point.x, point.y, r)
        })

        draw(r);
    } catch (error) {
        toast(error);
        draw(r);
        return null;
    }

}

