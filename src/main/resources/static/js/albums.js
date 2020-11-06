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

fetch('http://localhost:8082/albums/read')
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

        createAlbumCard(data);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

  function createAlbumCard(alldata) {

    for (let key of alldata) {
      console.log(key);
      console.log(key['artist']['name']);

      let find = document.getElementById("albums");
      let column = document.createElement("div");
      column.className = "col mb-4";
      let card = document.createElement("div");
      card.className = "card text-center";
      find.appendChild(column);
      column.appendChild(card);

      let image = document.createElement("img");
      image.className = "card-img-top";
      image.src = key['cover'];
      card.appendChild(image);
      let body = document.createElement("div");
      body.className = "card-body";
      card.appendChild(body);

      console.log(key['artist']['id']);
      let linkTitle = document.createElement("a");
      linkTitle.href = "viewAlbum.html?id=" + key['id'];
      let title = document.createElement("h5");
      title.className = "card-title";
      let titleText = document.createTextNode(key['name']);
      title.style = "text-align: center"
      body.appendChild(linkTitle);
      linkTitle.appendChild(title);
      title.appendChild(titleText);

      let linkArtist = document.createElement("a");
      linkArtist.href = "viewArtist.html?id=" + key['artist']['id'];
      let artist = document.createElement("p");
      artist.className = "card-text";
      let artistSmall = document.createElement("small");
      artistSmall.className = "text-muted";
      let artistSmallText = document.createTextNode(key['artist']['name']);
      body.appendChild(linkArtist);
      linkArtist.appendChild(artist);
      artist.appendChild(artistSmall);
      artistSmall.appendChild(artistSmallText);
      
    }

  }