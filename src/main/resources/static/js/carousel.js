let artistID = 2;
let albumID = 4;
let trackID = 10;

fetch('http://localhost:8082/artists/read/'+artistID)
    .then(
       function(response) {
         if (response.status !== 200) {
           console.log('Looks like there was a problem. Status Code: ' +
             response.status);
           return;
         }
   
         // Examine the text in the response
         response.json().then(function(data) {
             console.log(data['picture']);
            let findImage = document.getElementById("img1");
            let img = document.createElement("img");
            img.className = "d-block w-100";
            img.id = "artistIMG";
            img.alt = "artist";
            img.src = data["picture"];
            findImage.appendChild(img);
        });
        }
     )
     .catch(function(err) {
       console.log('Fetch Error :-S', err);
     });

     fetch('http://localhost:8082/albums/read/'+albumID)
     .then(
        function(response) {
          if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
    
          // Examine the text in the response
          response.json().then(function(data) {
              console.log(data['picture']);
             let findImage = document.getElementById("img2");
             let img = document.createElement("img");
             img.className = "d-block w-100";
             img.id = "albumIMG";
             img.alt = "album";
             img.src = data["cover"];
             findImage.appendChild(img);
         });
         }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });

      fetch('http://localhost:8082/tracks/read/'+trackID)
      .then(
         function(response) {
           if (response.status !== 200) {
             console.log('Looks like there was a problem. Status Code: ' +
               response.status);
             return;
           }
     
           // Examine the text in the response
           response.json().then(function(data) {
               console.log(data['picture']);
              let findImage = document.getElementById("img3");
              let img = document.createElement("img");
              img.className = "d-block w-100";
              img.id = "trackIMG";
              img.alt = "track";
              img.src = data["album"]["picture"];
              findImage.appendChild(img);
          });
          }
       )
       .catch(function(err) {
         console.log('Fetch Error :-S', err);
       });
