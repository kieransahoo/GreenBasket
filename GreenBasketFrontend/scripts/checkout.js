
let user= JSON.parse(localStorage.getItem("userSession")) || [];
console.log(user);
let key = user[0].uuid;
console.log(key);
let id = user[0].id;




// Get Customer to Fetch Address

var customer;
getdata();
async function getdata(){
    let res = await fetch(`http://localhost:8083/customer/${id}/${key}`)
    customer = await res.json()
    console.log(customer);
    setAddressInForm(customer)
  
 }


 function setAddressInForm(customer){
    console.log(customer);

    document.getElementById("name").value = customer.customerName;
    document.getElementById("phone").value = customer.mobileNumber;
    document.getElementById("email").value = customer.emailId;
    document.getElementById("state").value = customer.address[0].state;
    document.getElementById("country").value = "India"
    document.getElementById("address").value = customer.address[0].buildingName;
    document.getElementById("apartment_area").value = customer.address[0].flatNo+" , "+customer.address[0].area;
    document.getElementById("city").value = customer.address[0].city;
    document.getElementById("pincode").value =customer.address[0].pincode;
    
 }






  // Fetch Cart Count in The Cart Icon

let cartCount = document.querySelector("#cartCount");

let countRequestOptions = {
  method: 'GET',
  redirect: 'follow'
};

fetch(`http://localhost:8083/count/${key}`, countRequestOptions)
  .then(response => response.text())
  .then(count => {

      console.log(count);
      if(count>0){
         cartCount.innerText=""
         cartCount.append(count);
      }
     
   })
  .catch(error => console.log('error', error));



  // Calculate Sub-Total

  let subtotal = document.querySelector("#subtotal");
  let totalAmount = document.querySelector("#totalAmount");
 
 let subTotalRequestOptions = {
   method: 'GET',
   redirect: 'follow'
 };
 
 fetch(`http://localhost:8083/getTotalAmount/${key}`, subTotalRequestOptions)
   .then(response => response.text())
   .then(result => {
     console.log(result)
     if(result>0){
        subtotal.innerHTML="";
     subtotal.innerHTML="Rs."+result;
 
     
     totalAmount.innerHTML ="Rs."+(parseFloat(result)+parseFloat(20.00));
     }
     
 
   })
   .catch(error => console.log('error', error));
    
   //get payment value 
    let onlinePayemnt = document.getElementById("online_payment").value;
    console.log(onlinePayemnt)

   document.querySelector("#placeOrder").addEventListener("click",placeOrderFunction);

   

   async function placeOrderFunction(event){
        console.log("OrderPlaced");

        let today = new Date().toLocaleDateString()
        //console.log(today);
        let billObject={
            transactionDate:today,
            transactionMode:onlinePayemnt,
            transactionStatus:"Successfull"
        }

        // add and save bill 

        let billHeaders = new Headers();
        billHeaders.append("Content-Type", "application/json");

        let billRaw = JSON.stringify(billObject);

        let billRequestOptions = {
        method: 'POST',
        headers: billHeaders,
        body: billRaw,
        redirect: 'follow'
        };

        fetch(`http://localhost:8083/BillingService/addBill/${key}`, billRequestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));

        // Adding and Saving Order

        let orderRaw = "";

        let orderRequestOptions = {
        method: 'POST',
        body: orderRaw,
        redirect: 'follow'
        };

        fetch(`http://localhost:8083/order/${key}`, orderRequestOptions)
        .then(response => response.text())
        .then(result => {
            console.log(result);
            alert("Order Placed Successfully");
            window.location.href="order.html";
        })
        .catch(error => console.log('error', error));

        

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

   
   