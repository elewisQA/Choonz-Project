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


function register(){

  let username = document.getElementById("Username").value;
  let password = document.getElementById("Password").value;
  let password2 = document.getElementById("Password2").value;
  let pwSame = false;
  let pw = false;
  let un = false;
  let pw2 = false;

  if(username === ""){
    window.alert("Please enter your username."); 
    return false;
  }
  else{
    un = true;
    // return true;
  }

  if(password === ""){
    window.alert("Please enter your password."); 
    return false;
  }
  else{
    pw = true;
    // return true;
  }

  if(password2 === ""){
    window.alert("Please enter your password again."); 
    return false;
  }
  else{
    pw2 = true;
    // return true;
  }

  if(password !== password2){
    window.alert("The two passwords you entered are not the same. Please try it again.");
    return false;
  }
  else{
    pwSame = true;
    // return true;
  }

  if(un && pw && pw2 && pwSame){
    console.log(username, "pw: ", password);
      fetch('http://localhost:8082/users/create',{
      method: 'post',
      headers: {
        "Content-type": "application/json"
      },
      body:json = JSON.stringify({
        "password": password,
        "username": username
       })
    })
    .then(
      function(response) {
        if (response.status !== 202) {
          console.log('Looks like there was a problem. Status Code: ' +
            response.status);
            window.alert("Something went wrong, please try again!");
          return;

        }
        // Examine the text in the response
        response.json().then(function(data) {
          console.log(data);
        });
      }
    )
    .catch(function(err) {
      console.log('Fetch Error :-S', err);
    });
    return true;
  }
  else{
    return false;
  }
}