
function formatPhone(document) {
    let pnum = document.value.replace(/\D*/g,"");
    if (pnum.length >=2) {
       
         pnum = "("+pnum.slice(0,2)+") "+ pnum.slice(2,pnum.length);

         }
  
    if (pnum.length >= 10) {
         pnum = pnum.slice(0,10)+"-"+pnum.slice(10); } 
    if (pnum.length >= 15) {
        pnum = pnum.slice(0,15) } 
    document.value = pnum;
    }

    function formatCpf(document) {
    let pnum = document.value.replace(/\D/g,"");
    if (pnum.length >=3) {
         pnum = pnum.slice(0,3)+"."+ pnum.slice(3,pnum.length);

         }
    if (pnum.length >=7) {
    pnum = pnum.slice(0,7)+"."+ pnum.slice(7,pnum.length);

    }
  
    if (pnum.length >= 11) {
         pnum = pnum.slice(0,11)+"-"+pnum.slice(11); } 
    if (pnum.length >= 14) {
        pnum = pnum.slice(0,14) } 
    document.value = pnum;
    }


