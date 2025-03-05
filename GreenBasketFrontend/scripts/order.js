let user= JSON.parse(localStorage.getItem("userSession")) || [];
console.log(user);
let key = user[0].uuid;
console.log(key);
let id = user[0].id;


let orderdata;
window.onload= getOrderData(key);
  async function getOrderData(key){
    let res = await fetch(`http://localhost:8083/orders/${key}`)
    orderdata = await res.json()
    console.log(orderdata);
   
    appendOrderData(orderdata);
 }



 var element = document.getElementById("table_Body");

 function appendOrderData(data) {
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
   

     // Image table data 
     var image = document.createElement("td");
     image.className="image-prod";

     var imageDiv = document.createElement("div");
     imageDiv.className="img"
     imageDiv.style.backgroundImage = "url(" + el.vegetableList[Math.floor(Math.random() * el.vegetableList.length)].imageUrl + ")";

     image.append(imageDiv);

     // Append Product Name 
    var name = document.createElement("td");
    name.className="product-name";

    var nameH3 = document.createElement("h3"); 
    nameH3.innerText = el.orderNo;
    name.append(nameH3);

    //Append Price Of the per Vegetable per kg or item
     var price = document.createElement("td");
     price.className="price";
     price.innerText = "Rs. " + el.totalAmount;

    //Append Quantity with Increase and Decrease button;
    var quantity = document.createElement("td");
    quantity.className="quantity";

    var quantityDiv = document.createElement("div");
    quantityDiv.className="quantityDiv"
    // var decBtn = document.createElement("h4");
    // decBtn.innerText="-"
    // decBtn.style.cursor = "pointer";
    // decBtn.onclick = function() {
    //   // Decrease quantity code here
    //   decreaseVegQuantity(el.vegId,key);
    // };
    var quantityValue = document.createElement("h5");
    quantityValue.innerText= el.vegetableList.length;
    // var incBtn = document.createElement("h4");
    // incBtn.innerText="+";
    // incBtn.style.cursor = "pointer";
    // incBtn.onclick = function() {

    //   // Increase quantity code here
    //   increaseVegQuantiy(el.vegId,key);
    
    // };

    quantityDiv.append(quantityValue);
    quantity.append(quantityDiv)
    

    //Count Total Amount for particular Vegetable
     var total = document.createElement("td");
     total.className="total";

     total.innerText= "Online";


     row.append(remove,image,name,price,quantity,total)

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
