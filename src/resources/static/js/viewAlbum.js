const findId = new URLSearchParams(window.location.search);
for (let found of findId) {
    let id = found[1];
    console.log(id);
    getID(id);  
 } 

function getID(id) {
 fetch('http://localhost:8082/albums/read/'+id)
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
        populate(data);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });
}

function populate(data) {
    console.log(data['name']);
    let find = document.getElementById("main_info");
    let image = document.createElement("img");
    image.src = data['cover'];
    image.id = "image_album";
    find.appendChild(image);

    let textContainer = document.createElement("div");
    textContainer.className ="container";
    textContainer.id = "text_container";
    find.appendChild(textContainer);

    let albumName = document.createElement("h1");
    albumName.style = "font-size: 45px;";
    albumName.textContent = data["name"];

    let linkArtist = document.createElement("a");
    linkArtist.href="#";
    let artist = document.createElement("h2");
    artist.style = "font-size: 25px;";
    artist.textContent = data["artistName"];
    linkArtist.appendChild(artist);

    let linkGenre = document.createElement("a");
    linkGenre.href="#";
    let genre = document.createElement("p");
    genre.style = "font-size: 17px;";
    genre.textContent = data["genreName"];
    linkGenre.appendChild(genre);

    textContainer.appendChild(albumName);
    textContainer.appendChild(linkArtist);
    textContainer.appendChild(linkGenre);

    let tableContainer = document.createElement("div");
    tableContainer.id = "table_container";
    let table = document.createElement("table");
    table.className = "table";
    let tableBody = document.createElement("tbody");
    find.appendChild(tableContainer);
    tableContainer.appendChild(table);
    table.appendChild(tableBody);

    let songCount = 1;
    // Populate the table
    for (let key in data['tracks']) { 
    console.log(key);
     let value = data['tracks'][key];
     console.log(value);
     let row = document.createElement("tr");
     tableBody.appendChild(row);

     let songId = document.createElement("th");
     songId.scope = "row";
     songId.textContent = songCount;
     row.appendChild(songId);

     let songName = document.createElement("td");
     let link = document.createElement("a");
     link.href = "#";
     link.textContent = value;
     row.appendChild(songName);
     songName.appendChild(link);
     songCount++;
    }
}