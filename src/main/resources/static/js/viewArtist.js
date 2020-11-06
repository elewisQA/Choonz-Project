const findId = new URLSearchParams(window.location.search);
for (let found of findId) {
    let id = found[1];
    console.log(id);
    getID(id);  
 } 

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

function getID(id) {
 fetch('http://localhost:8082/artists/read/'+id)
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
        populateArtist(data);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });
}

function populateArtist(data) {
    console.log(data['albums']);
    console.log(data['name']);

    let findImage = document.getElementById("artist_photo_container");
    let image = document.createElement("img");
    image.src = data['picture'];
    image.id = "image_artist";
    findImage.appendChild(image);

    let artistContainer = document.getElementById("artist_info_container");
    let artistName = document.createElement("h2");
    artistName.style = "font-size: 45px;";
    artistName.textContent = data["name"];
    artistContainer.appendChild(artistName);

    let cardContainer = document.createElement("div");
    cardContainer.className="row row-cols-1 row-cols-md-4";
    artistContainer.appendChild(cardContainer);

    for(let key in data['albums']) {
        console.log(data['albums'][key]);

        let card1 = document.createElement("div");
        card1.className="col mb-4";
        let card2 = document.createElement("div");
        card2.className="card h-100 text-center";
        cardContainer.appendChild(card1);
        card1.appendChild(card2);

        let albumPicture = document.createElement("img");
        albumPicture.className = "card-img-top";
        albumPicture.src = data['albums'][key]['cover'];
        card2.appendChild(albumPicture);

        let cardBody = document.createElement("div");
        cardBody.className = "card-body";
        let linkAlbum = document.createElement("a");
        linkAlbum.id="artist_to_album";
        linkAlbum.href = "viewAlbum.html?id=" + data['albums'][key]['id'];
        card2.appendChild(cardBody);
        cardBody.appendChild(linkAlbum);

        let albumName = document.createElement("p");
        albumName.textContent = data['albums'][key]['name'];
        linkAlbum.appendChild(albumName);
    }

}