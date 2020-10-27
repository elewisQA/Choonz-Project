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
      console.log(key['artistName']);

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
      let body = document.createElement("body");
      body.className = "card-body";
      card.appendChild(body);

      let linkTitle = document.createElement("a");
      linkTitle.href = "#";
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
      artist.style = "text-align: center"
      let artistSmall = document.createElement("small");
      artistSmall.className = "text-muted";
      let artistSmallText = document.createTextNode(key['artistName']);
      body.appendChild(linkArtist);
      linkArtist.appendChild(artist);
      artist.appendChild(artistSmall);
      artistSmall.appendChild(artistSmallText);
      
    }
  }