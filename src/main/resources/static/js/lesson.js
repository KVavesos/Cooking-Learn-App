let currentPage = 0 ;
const totalPages= 3 ;

function showPage(index) {
    for (let i = 0; i < totalPages; i++) {
        document.getElementById("page" + i).style.display =
          (i === index) ? "block" : "none";
    }
}

function nextPage() {
    if (currentPage<totalPages-1) {
        currentPage++;
        showPage(currentPage);
    }
}

function previousPage(){
    if(currentPage>0){
        currentPage--;
        showPage(currentPage);
    }
}