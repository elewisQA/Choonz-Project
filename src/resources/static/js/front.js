$(function() {
  $('#ChangeToggle').click(function() {
    $('#navbar-hamburger').toggleClass('hidden');
    $('#navbar-close').toggleClass('hidden');
  });
});

function showLogin() {
  let x = document.getElementById("login");
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

function register() { 
  // var firstname = document.forms["RegForm"]["FirstName"]; 
  // var lastname = document.forms["RegForm"]["LastName"]; 
  var username = document.forms["RegForm"]["Username"]; 
  // var email = document.forms["RegForm"]["Email"]; 
  var password = document.forms["RegForm"]["Password"]; 

  // if (firstname.value == "") { 
  //     window.alert("Please enter your first name."); 
  //     firstname.focus(); 
  //     return false; 
  // } 

  // if (lastname.value == "") { 
  //     window.alert("Please enter your last name."); 
  //     lastname.focus(); 
  //     return false; 
  // } 

  if (username.value == "") { 
    window.alert("Please enter your username."); 
    username.focus(); 
    return false; 
} 

  // if (email.value == "") { 
  //     window.alert( 
  //       "Please enter a valid e-mail address."); 
  //     email.focus(); 
  //     return false; 
  // } 

  if (password.value == "") { 
      window.alert("Please enter your password"); 
      password.focus(); 
      return false; 
  } 

  return true; 
} 

var token_ // variable will store the token
var userName = "test"; // app clientID
var passWord = "password"; // app clientSecret
var caspioTokenUrl = "http://localhost:8082/users/login"; // Your application token endpoint  
var request = new XMLHttpRequest(); 

function getToken(url, clientID, clientSecret) {
    var key;           
    request.open("POST", url, true); 
    request.setRequestHeader("Content-type", "application/json");
    request.send("grant_type=client_credentials&client_id="+clientID+"&"+"client_secret="+clientSecret); // specify the credentials to receive the token on request
    request.onreadystatechange = function () {
        if (request.readyState == request.DONE) {
            var response = request.responseText;
            var obj = JSON.parse(response); 
            key = obj.access_token; //store the value of the accesstoken
            token_ = key; // store token in your global variable "token_" or you could simply return the value of the access token from the function
            console.log("DONE");
        }
    }
}
// Get the token
getToken(caspioTokenUrl, userName, passWord);

// function CallWebAPI() {
//   var request_ = new XMLHttpRequest();        
//   var encodedParams = encodeURIComponent(params);
//   request_.open("GET", "http://localhost:8082/users/login", true);
//   request_.setRequestHeader("Authorization", "Bearer "+ token_);
//   request_.send();
//   request_.onreadystatechange = function () {
//       if (request_.readyState == 4 && request_.status == 200) {
//           var response = request_.responseText;
//           var obj = JSON.parse(response); 
//           // handle data as needed... 

//       }
//   }
// } 
