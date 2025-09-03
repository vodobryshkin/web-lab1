const checkboxListElements = document.getElementsByClassName("checkbox-list-element-input");

for (let i = 0; i < checkboxListElements.length; i++) {
    checkboxListElements[i].addEventListener("change", handleCheckboxChange);
}

function handleCheckboxChange(event) {
    if (event.target.checked) {
        for (let i = 0; i < checkboxListElements.length; i++) {
            if (checkboxListElements[i].checked && event.target !== checkboxListElements[i]) {
                checkboxListElements[i].checked = false;
                break;
            }
        }
    }
}