document.querySelector("#customer_reg_form").addEventListener("submit",registerCustomer)

async function registerCustomer(event){
    event.preventDefault();
    var name = document.getElementById('customerName').value;
    var phone = document.getElementById('phoneNumber').value;
    var email = document.getElementById('customerEmail').value;
    var password = document.getElementById('customerPassword').value;

    var flat = document.getElementById('flatNo').value;
    var building = document.getElementById('buildingName').value;
    var area = document.getElementById('area').value;
    var city = document.getElementById('city').value;
    var state = document.getElementById('state').value;
    var pincode = document.getElementById('pincode').value;

    var address={
        flatNo:flat,
        buildingName:building,
        area: area,
        city: city,
        state: state,
        pincode: pincode      
    }


      var param = {
        customerName:name,
        mobileNumber:phone,
        emailId:email,
        password:password,
        address:[address]
      }

    console.log(param);


    var myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify(param);

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw,
  redirect: 'follow'
};

fetch("http://localhost:8083/customer", requestOptions)
  .then(response => response.text())
  .then(result => {
    alert("Customer registered")
    window.location.href="./userLogin.html"
    console.log(result)}
    )
  .catch(error => console.log('error', error));
    

}