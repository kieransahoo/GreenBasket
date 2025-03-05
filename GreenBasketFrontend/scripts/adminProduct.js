//   Add Vegetable part

document.querySelector("#addVeg_form").addEventListener("submit",registerNewVegetable);

let user= JSON.parse(localStorage.getItem("userSession")) || [];
console.log(user);
 let key = user[0].uuid;
console.log(key);

async function registerNewVegetable(event){
    event.preventDefault();
    var name = document.getElementById('name').value;
    var type = document.getElementById('type').value;
    var quantity = document.getElementById('quant').value;
    var price = document.getElementById('price').value;
    var url = document.getElementById('url').value;

      var vegParam = {
         name:name,
         type:type,
         quantity:quantity,
         price:price,
         imageUrl:url
      }

    console.log(vegParam);

var addVegHeaders = new Headers();
addVegHeaders.append("Content-Type", "application/json");

var vegRaw = JSON.stringify(vegParam);

var requestOptions = {
  method: 'POST',
  headers: addVegHeaders,
  body: vegRaw,
  redirect: 'follow'
};



fetch(`http://localhost:8083/vegetable/${key}`, requestOptions)
  .then(response => response.text())
  .then(result => {
   console.log(result)
   alert("Product Added")
   location.reload();
  })
  .catch(error => console.log('error', error));

}

// ======================================================================================================================

// Get All Vegetable 

let data;
window.onload=getdata()
 async function getdata(){
    let res = await fetch(`http://localhost:8083/vegetables`)
    data = await res.json()
    console.log(data);
    console.log("yes");
    append(data)
 }

 //append All Vegetables in Product Page

 let container = document.getElementById("container");
 function append(data) {
    data.forEach(function (el) {
        let griddiv = document.createElement("div")
        // griddiv.setAttribute("id","griddiv")
        griddiv.className="card"
        let img = document.createElement("img")
        img.src =el.imageUrl;
        img.className="veg_img"
        let cont = document.createElement("p");
        cont.className="name_price"
        let price = document.createElement("p")
        price.innerText="Rs."+el.price+"/Kg"
        let name = document.createElement("p")
        name.innerText=el.name
        cont.append(name,price)
        let btn = document.createElement("button")
        //btn.setAttribute("class", "removeVeg")
        btn.className="removeVeg";
        btn.innerText = "Remove From Stock"

        btn.addEventListener("click",function(){
           
         removeVegetable(el.vegId)
         
     })


        griddiv.append(img,cont,btn)
        container.append(griddiv)
      });

  }

  // Remove Vegetable From Stock

//   document.querySelector("#removeVeg").addEventListener("submit",removeVegetable);

  async function removeVegetable(id){
   event.preventDefault();
 

   console.log(id);

   var removeraw = "";

   var requestOptions = {
      method: 'DELETE',
      body: removeraw,
      redirect: 'follow'
    };
    
    fetch(`http://localhost:8083/vegetable/${id}/${key}`, requestOptions)
      .then(response => response.text())
      .then(result => {
         console.log(result);
         alert("Vegetable Removed Successfully");
         location.reload();

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
