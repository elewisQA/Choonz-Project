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

function login() {

  fetch('http://localhost:8082/users/login', {
      method: 'post',
      
      headers: {
           "Content-type": "application/json",
           "username": "username",
           "password": "password",
           "Access-Control-Allow-Origin": '*'       
      }
      })
      .then(
        function(response) {
          if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            console.log(response.headers);
            return;
          }
          response.json().then(function(success) {
            if(success){
              console.log(response.headers);
              console.log('Login successful');
            }
            else{
              console.log("Login failed! Please check your login details.");
            }
          });
        }
      )
      .catch(function (error) {
          console.log('Request failed', error);
          console.log('Login failed');
      });
}
