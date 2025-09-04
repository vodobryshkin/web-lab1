const DEFAULT_R_NAME = "R";
const rLength = 250;

document.addEventListener("DOMContentLoaded", () => draw(DEFAULT_R_NAME));

function draw(rName) {
    drawCoordinatePlane(rName);
    drawAreas();
}

function drawCoordinatePlane(rName) {
    const canvas = document.getElementById("canvas");
    const ctx = canvas.getContext("2d");

    ctx.clearRect(0, 0, canvas.width, canvas.height);

    ctx.strokeStyle = "black";
    ctx.lineCap = "round";
    ctx.lineWidth = 3;
    ctx.font = '20px Arial';
    ctx.fillStyle = 'black';

    let halfRName = rName === DEFAULT_R_NAME ? "R/2" : String(Number(rName) / 2);

    ctx.beginPath();
    ctx.moveTo(0, canvas.height / 2);
    ctx.lineTo(canvas.width, canvas.height / 2);
    ctx.moveTo(canvas.width / 2, 0);
    ctx.lineTo(canvas.width / 2, canvas.height);


    ctx.moveTo(canvas.width, canvas.height / 2);
    ctx.lineTo(canvas.width - 15, canvas.height / 2 - 7);
    ctx.moveTo(canvas.width, canvas.height / 2);
    ctx.lineTo(canvas.width - 15, canvas.height / 2 + 7);

    ctx.moveTo(canvas.width / 2, 0);
    ctx.lineTo(canvas.width / 2 - 7, 15);
    ctx.moveTo(canvas.width / 2, 0);
    ctx.lineTo(canvas.width / 2 + 7, 15);

    ctx.fillText("x", canvas.width - 10, canvas.height / 2 + 20)
    ctx.fillText("y", canvas.width / 2 + 12, 12)

    // x -r
    ctx.moveTo(canvas.width / 2 - rLength, canvas.height / 2 + 10);
    ctx.lineTo(canvas.width / 2 - rLength, canvas.height / 2 - 10);
    ctx.fillText(`-${rName}`, canvas.width / 2 - rLength - 14, canvas.height / 2 - 15)

    // x -r/2
    ctx.moveTo(canvas.width / 2 - rLength / 2, canvas.height / 2 + 10);
    ctx.lineTo(canvas.width / 2 - rLength / 2, canvas.height / 2 - 10);
    ctx.fillText(`-${halfRName}`, canvas.width / 2 - rLength / 2 - 24, canvas.height / 2 - 15)

    // x r/2
    ctx.moveTo(canvas.width / 2 + rLength / 2, canvas.height / 2 + 10);
    ctx.lineTo(canvas.width / 2 + rLength / 2, canvas.height / 2 - 10);
    ctx.fillText(`${halfRName}`, canvas.width / 2 + rLength / 2 - 14, canvas.height / 2 - 15)

    // x r
    ctx.moveTo(canvas.width / 2 + rLength, canvas.height / 2 + 10);
    ctx.lineTo(canvas.width / 2 + rLength, canvas.height / 2 - 10);
    ctx.fillText(`${rName}`, canvas.width / 2 + rLength - 7, canvas.height / 2 - 15)

    // y -r
    ctx.moveTo(canvas.width / 2 - 10, canvas.height / 2 + rLength);
    ctx.lineTo(canvas.width / 2 + 10, canvas.height / 2 + rLength);
    ctx.fillText(`-${rName}`, canvas.width / 2 + 14, canvas.height / 2 + rLength + 7)

    // y -r/2
    ctx.moveTo(canvas.width / 2 - 10, canvas.height / 2 + rLength / 2);
    ctx.lineTo(canvas.width / 2 + 10, canvas.height / 2 + rLength / 2);
    ctx.fillText(`-${halfRName}`, canvas.width / 2 + 14, canvas.height / 2 + rLength / 2 + 7)

    // y r/2
    ctx.moveTo(canvas.width / 2 - 10, canvas.height / 2 - rLength / 2);
    ctx.lineTo(canvas.width / 2 + 10, canvas.height / 2 - rLength / 2);
    ctx.fillText(`${halfRName}`, canvas.width / 2 + 14, canvas.height / 2 - rLength / 2 + 7)

    // y r
    ctx.moveTo(canvas.width / 2 - 10, canvas.height / 2 - rLength);
    ctx.lineTo(canvas.width / 2 + 10, canvas.height / 2 - rLength);
    ctx.fillText(`${rName}`, canvas.width / 2 + 14, canvas.height / 2 - rLength + 7)

    ctx.stroke();
    ctx.closePath();
}

function drawAreas() {
    const canvas = document.getElementById("canvas");
    const ctx = canvas.getContext("2d");
    ctx.fillStyle = "rgba(0, 0, 255, 0.2)";

    ctx.beginPath();
    ctx.lineCap = "round";
    ctx.lineWidth = 1;

    ctx.rect(canvas.width / 2, canvas.height / 2 - rLength, rLength / 2, rLength)

    ctx.moveTo(canvas.width / 2, canvas.height / 2);
    ctx.lineTo(canvas.width / 2 + rLength, canvas.height / 2);
    ctx.lineTo(canvas.width / 2, canvas.height / 2 + rLength);
    ctx.lineTo(canvas.width / 2, canvas.height / 2);

    ctx.arc(canvas.width / 2, canvas.height / 2, rLength / 2, 1.5 * Math.PI, 3 * Math.PI, false);

    ctx.fill();
    ctx.closePath();
}