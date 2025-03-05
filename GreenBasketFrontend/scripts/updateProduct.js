
// get User Seesion key perfom Operations

let user= JSON.parse(localStorage.getItem("userSession")) || [];
console.log(user);
 let key = user[0].uuid;
console.log(key);

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
 

 // get the form ;
//  let updateForm = document.querySelector("#addVeg_form");
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
        let quantity = document.createElement("p")
        quantity.className="stock_quantity"
        quantity.innerText="Available : "+el.quantity+"kg";
        cont.append(name,price)
        let btn = document.createElement("button")
        //btn.setAttribute("class", "removeVeg")
        btn.className="removeVeg";
        btn.innerText = "Update Vegetable"

        btn.addEventListener("click",function(){
           
         getVegetable(el)
         
     })

        griddiv.append(img,cont,btn,quantity)
        container.append(griddiv)
      });

  }

// Get Existing Vegetable Detail in the Form 

  async function getVegetable(el){

    console.log(el)

// {
//     imageUrl: "https://images.pexels.com/photos/161559/background-bitter-breakfast-bright-161559.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
//     name :"orange"
//     price:42
//     quantity:50
//     type:"Fruit"
//     vegId:33
// }

    let id = document.getElementById('id');
    id.value=el.vegId;
    let name = document.getElementById('name');
    name.value = el.name;
    let type = document.getElementById('type');
    type.value = el.type;
    let quantity = document.getElementById('quant');
    quantity.value = el.quantity;
    let price = document.getElementById('price');
    price.value= el.price;
    let url = document.getElementById('url');
    url.value=el.imageUrl;
   
  }

  document.querySelector("#updateVeg_form").addEventListener("submit",updateVegetable);

  async function updateVegetable(event){
    event.preventDefault();
    let id = document.getElementById('id').value;
    let name = document.getElementById('name').value;
    let type = document.getElementById('type').value;
    let quantity = document.getElementById('quant').value;
    let price = document.getElementById('price').value;
    let url = document.getElementById('url').value;

      let vegParam = {
        vegId:id,
         name:name,
         type:type,
         quantity:quantity,
         price:price,
         imageUrl:url
      }

    console.log(vegParam,id);

    var updateVegHeaders = new Headers();
    updateVegHeaders.append("Content-Type", "application/json");
    
    var updateVeg = JSON.stringify(vegParam);
    
    var requestOptions = {
      method: 'PUT',
      headers: updateVegHeaders,
      body: updateVeg,
      redirect: 'follow'
    };



// fetch("http://localhost:8088/vegetable", requestOptions)
//   .then(response => response.text())
//   .then(result => {
//    console.log(result)
//    alert("Product Added")
//    location.reload();
//   })
//   .catch(error => console.log('error', error));

fetch(`http://localhost:8083/vegetable/${key}`, requestOptions)
  .then(response => response.text())
  .then(result => {
    console.log(result);
    alert("Vegetable Updated")
    location.reload();
  })
  .catch(error => console.log('error', error));

}