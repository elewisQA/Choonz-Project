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

fetch('http://localhost:8082/artists/read')
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

        createArtistCard(data);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

  function createArtistCard(alldata) {

    for (let key of alldata) {
      console.log(key);
      console.log(key['name']);
      console.log(key['id']);

      let find = document.getElementById("artists");
      let column = document.createElement("div");
      column.className = "col mb-4";
      let card = document.createElement("div");
      card.className = "card text-center";
      find.appendChild(column);
      column.appendChild(card);

      let image = document.createElement("img");
      image.className = "card-img-top";
      image.src = key['picture'];
      card.appendChild(image);
      let body = document.createElement("div");
      body.className = "card-body";
      card.appendChild(body);

      let linkArtist = document.createElement("a");
      linkArtist.href = "viewArtist.html?id=" + key['id'];
      let artist = document.createElement("h5");
      artist.className = "card-title";
      let artistText = document.createTextNode(key['name']);
      artist.style = "text-align: center"
      body.appendChild(linkArtist);
      linkArtist.appendChild(artist);
      artist.appendChild(artistText);
      
    }

  }