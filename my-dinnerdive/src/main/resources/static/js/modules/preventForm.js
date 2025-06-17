const preventForm = document.getElementById("preventForm");
preventForm.addEventListener("submit", preventFormSubmit);

function preventFormSubmit(event) {
    event.preventDefault();
}