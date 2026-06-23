function openModal(btn) {

    const id = btn.getAttribute("data-id");

    const form = document.getElementById("uploadForm");
    form.action = "/challenges/submit/" + id;

    document.getElementById("modal").style.display = "flex";
}

function closeReward() {
    document.getElementById("rewardModal").style.display = "none";
}

function openReward() {
    document.getElementById("finalRewardModal").style.display = "flex";
}

function closeFinalReward() {
    document.getElementById("finalRewardModal").style.display = "none";
}