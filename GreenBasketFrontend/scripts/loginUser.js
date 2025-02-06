document.querySelector("#user_login_form").addEventListener("submit",loginUser);

let currentSession =JSON.parse(localStorage.getItem("userSession"))||[];

if(currentSession.length >0){
    alert("Logout  current session !")
    window.location.href="index.html";
}

async function loginUser(event){
    event.preventDefault();
    
    var email = document.getElementById('userEmail').value;
    var password = document.getElementById('userPassword').value;

// http://127.0.0.1:5500/userLogin.html
postAPI('http://localhost:8083/userlogin', {
    emailId:email,
    password:password
});

    async function postAPI(endpoint, data) {
        try {
          const response = await fetch(endpoint, {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
              'Content-Type': 'application/json'
            }
          });
          const json = await response.json();
          console.log(json)
          
          if(json.role==1){
            window.location.href="adminProduct.html";
            currentSession.push(json)
          localStorage.setItem('userSession', JSON.stringify(currentSession));
          }else if(json.role==2){
            window.location.href="customerProduct.html";
            currentSession.push(json)
          localStorage.setItem('userSession', JSON.stringify(currentSession));
            }else{
                    alert("Something went Wrong")
             }
        } catch (error) {
          console.error(error);
        }
      }
      

}

// async function userLogin(event){
//     event.preventDefault();
    
//     var email = document.getElementById('userEmail').value;
//     var password = document.getElementById('userPassword').value;

//       var param = {
//         userId:email,
//         password:password
        
//       }

//     console.log(param);


//     var myHeaders = new Headers();
//     myHeaders.append("Content-Type", "application/json");

//     var raw = JSON.stringify(param);

//     var requestOptions = {
//     method: 'POST',
//     headers: myHeaders,
//     body: raw,
//     redirect: 'follow'
//     };

//     try{
//         let res = await fetch(`http://localhost:8088/userlogin`,requestOptions);
//         let data=await res.json();
//         console.log('data :',data);
//         loginUser.push(data);
//         localStorage.setItem("loggedInUser",JSON.stringify(loginUser));
//         if(role=="admin"){
//             window.location.href="adminProduct.html";
//         }else if(role=="customer"){
//             window.location.href="customerProduct.html";
//         }else{
//             alert("Something went Wrong")
//         }


//     }catch(err){
//         console.log('err :',err);
    // }

    // fetch("http://localhost:8088/userlogin", requestOptions)
    // .then(response => response.text())
    // .then(result => {
    //     // alert(result);
    //     console.log(result);

       
    // })
    // .catch(error => console.log('error', error));


// }