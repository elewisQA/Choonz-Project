window.onload = loginout;

// Show login button if you are logged out and show logout button if you are logged in
function loginout(){
  let lIn = document.getElementById("loginBtn");
  let lOut = document.getElementById("logoutBtn");
  if ((sessionStorage.getItem("token") === null) || (sessionStorage.getItem("token") === "")) {
    lIn.style.display = "block";
    lOut.style.display = "none";
  } else {
    lIn.style.display = "none";
    lOut.style.display = "block";
  }
}

//Logout if button clicked
function logoutNow(){
  sessionStorage.setItem("token", "");
  sessionStorage.setItem("userId", "");
  fetch('http://localhost:8082/users/logout', {
    method: 'get',
    headers: { 
      'token': sessionStorage.getItem('token')
    },
  })
  .then(
    function(response) {
      if (response.status !== 201) {
        console.log("Problem with back-end logout - response code:", response.status);
        return;
      }
      response.test(data).then(function(data){
        console.log(data);
      })
    }
  )
  window.alert("Logout successful! Come back soon!");
  window.location.reload();

}

fetch('http://localhost:8082/genres/read')
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

        createGenreCard(data);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

  function createGenreCard(alldata) {

    for (let key of alldata) {
      console.log(key);
      console.log(key['genreName']);

      let find = document.getElementById("genres");
      let column = document.createElement("div");
      column.className = "col mb-4";
      let card = document.createElement("div");
      card.className = "card text-center text-white";
      find.appendChild(column);
      column.appendChild(card);

      let image = document.createElement("img");
      image.className = "card-img-top";
      image.src = key['picture'];
      card.appendChild(image);
      let body = document.createElement("div");
      body.className = "card-body";
      card.appendChild(body);

      let text = document.createElement("div");
      text.className = "card-img-overlay text-center";
      let linkGenre = document.createElement("a");
      linkGenre.href = "viewGenre.html?id=" + key['id'];
      let genre = document.createElement("h1");
      genre.className = "card-title";
      let genreText = document.createTextNode(key['name']);
      genre.style = "text-align: center";
      card.appendChild(text);
      text.appendChild(linkGenre);
      linkGenre.appendChild(genre);
      genre.appendChild(genreText);
      column.appendChild(card);
      
    }

  }