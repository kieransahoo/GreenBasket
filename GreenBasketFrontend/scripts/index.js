
let value = document.querySelector("#login_logout");
console.log(value.innerHTML);

let allowShop = document.querySelector("#shop");


let user = JSON.parse(localStorage.getItem("userSession")) || [];
console.log(user.length)
let key;

if(user.length>0){

    document.querySelector("#shop").addEventListener("click",function(){
        goToShop(user);
    });

    value.innerHTML="Logout";
    document.getElementById("dropdown04").hidden = true
    console.log(value.innerHTML);

 value.addEventListener("click",function(){
    userLogout(user);
 });


    async function userLogout(user){
    //console.log("Logout")
    event.preventDefault();
    let logoutRaw = "";

    let logoutRequestOptions = {
        method: 'DELETE',
        body: logoutRaw,
        redirect: 'follow'
    };
    console.log(user);
    key = user[0].uuid;
    
    fetch(`http://localhost:8083/userlogout/${key}`, logoutRequestOptions)
        .then(response => response.text())
        .then(result => {
        console.log(result);
        user.splice(0,1);
        localStorage.setItem("userSession",JSON.stringify(user));
        alert("User Logged out Successfully !");
        window.location.href="index.html" ; 
        })
        .catch(error => console.log('error', error));
    }
}
//     // allowShop.addEventListener("click",alert("Login to continue"));


   function goToShop(user){
    console.log(user[0].role)
    if(user[0].role==1){
        window.location.href="/adminProduct.html"
    }else if(user[0].role==2){
        window.location.href="/customerProduct.html"
    }else{
        alert("Login First")
    }
        
}



