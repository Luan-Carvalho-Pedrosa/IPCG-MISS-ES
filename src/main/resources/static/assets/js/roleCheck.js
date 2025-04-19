function checkAccess() {
    const adminItens = document.querySelectorAll('.admItem');
    const userItem = document.getElementById("userItem");
    const role = localStorage.getItem("role");

    console.log(adminItens);

    if (role === "ROLE_Administrador") {
        //adminItem.style.display = "block";  // Mostra o item
        setFields(adminItens, "block");
        userItem.style.display = "none";
    } else {
       // adminItem.style.display = "none";  // Esconde o item
        setFields(adminItens, "none");
        userItem.style.display = "block"

    }
}

function setFields(documents, status){
    documents.forEach(document => {
        document.style.display = status; // 
    });

}

document.addEventListener("DOMContentLoaded", checkAccess);