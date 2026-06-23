function submitOnboarding() {

    const checkboxes = document.querySelectorAll(".lesson-checkbox");

    let selected = [];

    checkboxes.forEach(cb => {
        if (cb.checked) {
            selected.push(cb.value);
        }
    });

    fetch("/onboarding/save", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(selected)
    })
    .then(() => {
        const modal = document.getElementById("onboardingModal");
        modal.classList.remove("show");

        location.reload();
    });
}
document.addEventListener("DOMContentLoaded", function () {

    const modal = document.getElementById("onboardingModal");

    if (modal) {
        modal.classList.add("show");
    }
});

window.addEventListener("DOMContentLoaded", function () {
    const modal = document.getElementById("onboardingModal");

    if (modal) {
        modal.classList.add("show");
    }
});