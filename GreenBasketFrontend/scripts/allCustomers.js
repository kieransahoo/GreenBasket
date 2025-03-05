let user= JSON.parse(localStorage.getItem("userSession")) || [];
console.log(user);
let key = user[0].uuid;
console.log(key);
let id = user[0].id;


let orderdata;
window.onload= getOrderData(key);
  async function getOrderData(key){
    let res = await fetch(`http://localhost:8083/customer/all/${key}`)
    orderdata = await res.json()
    console.log(orderdata);
   
    appendCustomerData(orderdata);
 }



 var element = document.getElementById("table_Body");

 function appendCustomerData(data) {
    element.innerHTML=""
    data.forEach(function (el) {
     var row = document.createElement("tr");
     row.className="text-center";

    //  td1-Customer Id
    let customerId = document.createElement("td");
    customerId.innerText=el.customerId;

    let customerEmail = document.createElement("td");
    customerEmail.innerText=el.emailId;

    let customerNumber = document.createElement("td");
    customerNumber.innerText=el.mobileNumber;

    let customerName = document.createElement("td");
    customerName.innerText=el.customerName;

    let customerAddress = document.createElement("td");
    customerAddress.innerText=el.address[0].area+" , "+el.address[0].city+" , "+el.address[0].state;

    let customerPincode = document.createElement("td");
    customerPincode.innerText=el.address[0].pincode;

    row.append(customerId,customerEmail,customerNumber,customerName,customerAddress,customerPincode);
    
    element.append(row);


      });

  }

// Logout Button Function

document.querySelector("#logout").addEventListener("click",userLogout);


async function userLogout(event){
  //console.log("Logout")
  event.preventDefault();
  let logoutRaw = "";

  let logoutRequestOptions = {
    method: 'DELETE',
    body: logoutRaw,
    redirect: 'follow'
  };
  
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
