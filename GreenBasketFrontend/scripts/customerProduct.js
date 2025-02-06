
let data;
window.onload=getdata()
 async function getdata(){
    let res = await fetch(`http://localhost:8083/vegetables`)
    data = await res.json()
    console.log(data);
    console.log("yes");
    append(data)
 }
 console.log(data)

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
        btn.innerText = "Add to Product"

        btn.addEventListener("click",function(){
           
         addProductToCart(el.vegId,key)
         
     })


        griddiv.append(img,cont,btn)
        container.append(griddiv)
      });

  }






// get User Seesion key perfom Operations

let user= JSON.parse(localStorage.getItem("userSession")) || [];
console.log(user);
 let key = user[0].uuid;
console.log(key);


// Get All Vegetable 



  async function addProductToCart(id,key){
   event.preventDefault();
 

   let raw = "";

   let requestOptions = {
     method: 'POST',
     body: raw,
     redirect: 'follow'
   };
   
   fetch(`http://localhost:8083/add/${id}/${key}`, requestOptions)
     .then(response => response.json())
     .then(result => {
      if(result.message=="Product is already added into cart"){
        alert(result.message)
      }else{
        alert("Product has been added to cart")
      }
       
     
      location.reload()
   })
     .catch(error => {
      console.log('error', error)
    });

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


    
