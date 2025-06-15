const preventForm = document.getElementById("preventForm");
preventForm.addEventListener("click", preventFormSubmit);

function preventFormSubmit(event) {
    event.preventDefault();
}