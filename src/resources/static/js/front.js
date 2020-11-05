window.onload = loginout;
// window.onload = console.log("token is: ",sessionStorage.getItem("token"));

function loginout(){
    //let lIn = document.getElementById("loginBtn");
    let lOut = document.getElementById("logoutBtn");
    let logPanel = document.getElementById("login");
    let welcomePanel = document.getElementById("userlogedin");
    if ((sessionStorage.getItem("token") === null) || (sessionStorage.getItem("token") === "")) {
      //lIn.style.display = "none";
      lOut.style.display = "none";
      logPanel.style.display = "block";
      welcomePanel.style.display = "none";
    } else {
      //lIn.style.display = "block";
      logPanel.style.display = "none";
      welcomePanel.style.display = "block";
      lOut.style.display = "block";
    }
}

// $(function() {
//   $('#ChangeToggle').click(function() {
//     $('#navbar-hamburger').toggleClass('hidden');
//     $('#navbar-close').toggleClass('hidden');
//   });
// });

// function showLogin() {
//   let x = document.getElementById("login");
//   if (x.style.display === "none") {
//     x.style.display = "block";
//   } else {
//     x.style.display = "none";
//   }
// } 

function login() {

  let username = document.getElementById("user_login").value;
  let password = document.getElementById("password_login").value;
  let pw = false;
  let un = false;

  if(username === ""){
    window.alert("Please enter your username."); 
    return false;
  }
  else{
    un = true;
  }

  if(password === ""){
    window.alert("Please enter your password."); 
    return false;
  }
  else{
    pw = true;
  }
  if(un && pw ){
  fetch('http://localhost:8082/users/login', {
      method: 'get', 
      headers: {
           "Content-type": "text/plain",
           "username": username,
           "password": password, 
      }
      })
      .then(
        function(response) {
          if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            console.log(response.headers);
            window.alert("Login failed! Please check your login details.");
            document.getElementById("userLog").value = '';
            document.getElementById("pwLog").value = '';
            return;
          }
          response.text().then(function(token) {
            if(token){
              console.log("Token: " + token); // <-- This is the username I would suggest putting it into session storage
              sessionStorage.setItem("token", token);
              console.log(sessionStorage.getItem("token"));
              sessionStorage.setItem("username", username);
              console.log('Login successful');
              window.alert("Login Successfull! Welcome here " + username + "!");
              let welcome = document.getElementById("welcome");
              let span = document.createElement("a");
              span.textContent = " " + username + "!";
              welcome.appendChild(span);
              getUsers();
              loginout();
              let x = document.getElementById("login");
              x.style.display = "none";
            }
            else{
              console.log("Login failed! Please check your login details.");
              window.alert("Login failed! Please check your login details.");
            }
          });
        }
      )
      .catch(function (error) {
          console.log('Request failed', error);
          console.log('Login failed');
          window.alert("Login failed! Please try again.");
      });
      return true;
    }
    else{
      console.log("Login failed! Please check your login details.");
      window.alert("Login failed! Please check your login details.");
      return false;
    }
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
        "username": username,
        "password": password,
        "playlists": []
       })
    })
    .then(
      function(response) {
        if (response.status !== 201) {
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
    resetThis();
    window.alert("You are successfully registered! Please login now!");
    $('#exampleModal').modal('hide');
    return true;
  }
  else{
    return false;
  }
}

function resetThis(){
  document.getElementById("Username").value = '';
  document.getElementById("Password").value = '';
  document.getElementById("Password2").value = '';
}

function logoutNow(){
  sessionStorage.setItem("token", "");
  sessionStorage.setItem("userId", "");
  window.alert("Logout successful! Come back soon!");
  window.location.reload();

}



function getUsers () {
  fetch('http://localhost:8082/users/read')
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }

      // Examine the text in the response
      response.json().then(function(data) {
        console.log(data);
        for (let key of data) {
          console.log(key);
          getId(key);
        }
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });
}

function getId(data) { 
  if (data['username'] === sessionStorage.getItem('username')){
    console.log(data['id']);
    sessionStorage.setItem("userId", data['id']);
  }

  
}