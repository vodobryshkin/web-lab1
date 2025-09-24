import {draw, drawPoint} from "../blocks/canvas.js";
import {pointStorage} from "./storage-work.js";
import {toast} from "./toast.js";
import {addInfoAboutPoint} from "../blocks/confirm.js"

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

    draw(r);

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
            pointStorage.addPoint(point.x, point.y);
            drawPoint(point.x, point.y, r);
            console.log("1")
            addInfoAboutPoint(point.x, point.y, point.r, point.status, point.current_time, point.duration);
        })
    } catch (error) {
        toast(error);
        return null;
    }

}

