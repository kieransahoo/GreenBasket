


let user= JSON.parse(localStorage.getItem("userSession")) || [];
console.log(user);
 let key = user[0].uuid;
console.log(key);





let cartdata;
window.onload=getCartData(key)
  async function getCartData(key){
    let res = await fetch(`http://localhost:8083/view/${key}`)
    cartdata = await res.json()
    console.log(cartdata);
   
    appendCartData(cartdata);
 }



 var element = document.getElementById("table_Body");

 function appendCartData(data) {
    element.innerHTML=""
    data.forEach(function (el) {
     var row = document.createElement("tr");
     row.className="text-center";

    //  td1-Remove Product from Cart
     var remove = document.createElement("td"); //create a tabledata
     remove.className = "product-remove";

     var removeA = document.createElement("a");
     removeA.setAttribute("href", "#");

     var removeSpan = document.createElement("span");
     removeSpan.className="ion-ios-close";

     removeA.append(removeSpan);
     remove.append(removeA);
     remove.addEventListener("click",function(){
      
      removeProductFromCart(el.vegId,key)
      
    })

      //create td element for product remove
      // var remove = document.createElement("td");
      // remove.classList.add("product-remove");
      // var removeA = document.createElement("a");
      // removeA.setAttribute("href", "#");
      // var removeSpan = document.createElement("span");
      // removeSpan.classList.add("ion-ios-close");
      // removeA.appendChild(removeSpan);
      // remove.appendChild(removeA);
  
  
      // remove.addEventListener("click",function(){
             
      //     removeProductFromCart(el.vegId,key)
          
      // })

     // Image table data 
     var image = document.createElement("td");
     image.className="image-prod";

     var imageDiv = document.createElement("div");
     imageDiv.className="img"
     imageDiv.style.backgroundImage = "url(" + el.imageUrl + ")";

     image.append(imageDiv);

     // Append Product Name 
    var name = document.createElement("td");
    name.className="product-name";

    var nameH3 = document.createElement("h3"); 
    nameH3.innerText = el.name;
    name.append(nameH3);

    //Append Price Of the per Vegetable per kg or item
     var price = document.createElement("td");
     price.className="price";
     price.innerText = "Rs. " + el.price;

    //Append Quantity with Increase and Decrease button;
    var quantity = document.createElement("td");
    quantity.className="quantity";

    var quantityDiv = document.createElement("div");
    quantityDiv.className="quantityDiv"
    var decBtn = document.createElement("h4");
    decBtn.innerText="-"
    decBtn.style.cursor = "pointer";
    decBtn.onclick = function() {
      // Decrease quantity code here
      decreaseVegQuantity(el.vegId,key);
    };
    var quantityValue = document.createElement("h5");
    quantityValue.innerText=el.quantity;
    var incBtn = document.createElement("h4");
    incBtn.innerText="+";
    incBtn.style.cursor = "pointer";
    incBtn.onclick = function() {

      // Increase quantity code here
      increaseVegQuantiy(el.vegId,key);
    
    };

    quantityDiv.append(decBtn,quantityValue,incBtn);
    quantity.append(quantityDiv)
    

    //Count Total Amount for particular Vegetable
     var total = document.createElement("td");
     total.className="total";

     total.innerText="Rs. " + (el.price*el.quantity);


     row.append(remove,image,name,price,quantity,total)

     element.append(row);


      });

  }


  //Remove From Cart Function
  function removeProductFromCart(vegId,key){

    var requestOptionsOfDelete = {
    method: 'DELETE',
    redirect: 'follow'
    };

      fetch(`http://localhost:8083/remove/${vegId}/${key}`, requestOptionsOfDelete)
     .then(response => response.text())
     .then(result => console.log(result))
      .catch(error => console.log('error', error));


      getCartData(key);

 }

//Increase Quantity By 1 Function
 function increaseVegQuantiy(vegId,key){

  var rawInc = "";

  var requestOptionsOfIncr = {
  method: 'PUT',
  body: rawInc,
  redirect: 'follow'
 };

  fetch(`http://localhost:8083/ince/${vegId}/${key}`, requestOptionsOfIncr)
   .then(response => response.text())
  .then(result => console.log(result))
  .catch(error => console.log('error', error));
    getCartData(key);
    location.reload();
  
  }

  //Decrease Quantity By 1 Function 
  function decreaseVegQuantity(vegId,key){

     var rawDec = "";

     var requestOptionsOfDecr = {
     method: 'PUT',
     body: rawDec,
    redirect: 'follow'
   };

    fetch(`http://localhost:8083/decr/${vegId}/${key}`, requestOptionsOfDecr)
   .then(response => response.text())
   .then(result => console.log(result))
   .catch(error => console.log('error', error));
   getCartData(key);
   location.reload();

  }

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
