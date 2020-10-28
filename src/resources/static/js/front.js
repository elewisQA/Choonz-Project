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
  var firstname = document.forms["RegForm"]["FirstName"]; 
  var lastname = document.forms["RegForm"]["LastName"]; 
  var username = document.forms["RegForm"]["Username"]; 
  var email = document.forms["RegForm"]["Email"]; 
  var password = document.forms["RegForm"]["Password"]; 

  if (firstname.value == "") { 
      window.alert("Please enter your first name."); 
      firstname.focus(); 
      return false; 
  } 

  if (lastname.value == "") { 
      window.alert("Please enter your last name."); 
      lastname.focus(); 
      return false; 
  } 

  if (username.value == "") { 
    window.alert("Please enter your username."); 
    username.focus(); 
    return false; 
} 

  if (email.value == "") { 
      window.alert( 
        "Please enter a valid e-mail address."); 
      email.focus(); 
      return false; 
  } 

  if (password.value == "") { 
      window.alert("Please enter your password"); 
      password.focus(); 
      return false; 
  } 

  return true; 
} 
