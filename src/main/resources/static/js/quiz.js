function toggleHint(id) {
    const hint = document.getElementById("hint_" + id);

    if (!hint) return;

    if (hint.style.display === "none" || hint.style.display === "") {
        hint.style.display = "block";
    } else {
        hint.style.display = "none";
    }
}


function toggleHint(id) {
    const hint = document.getElementById("hint_" + id);

    if (hint.style.display === "none") {
        hint.style.display = "block";

        fetch("/quiz/hint-used", {
            method: "POST"
        });
    } else {
        hint.style.display = "none";
    }
}