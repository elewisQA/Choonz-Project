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
      linkArtist.href = "#";
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
    let find = document.getElementById("albums");
    let column = document.createElement("div");
    column.className = "col mb-4";
    let card = document.createElement("div");
    card.className = "card text-center";
    find.appendChild(column);
    column.appendChild(card);

    let image = document.createElement("img");
    image.className = "card-img-top";
    image.src = "https://i.pinimg.com/originals/c9/db/b1/c9dbb1ad7c558a37c6291681aca99058.jpg";
    card.appendChild(image);

    let body = document.createElement("div");
    body.className = "card-body";
    card.appendChild(body);

    let linkAdd = document.createElement("a");
    linkAdd.className = "btn btn-info";
    linkAdd.href ="#";
    linkAdd.setAttribute("data-toggle", "modal")
    linkAdd.setAttribute("data-target", "#exampleModal")
    body.appendChild(linkAdd);

    let icon = document.createElement("i");
    icon.className = "fas fa-plus";
    icon.style = "color: whitesmoke;";
    linkAdd.appendChild(icon);

  }