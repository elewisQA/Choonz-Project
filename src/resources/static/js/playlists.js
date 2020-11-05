window.onload = loginout;

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

fetch('http://localhost:8082/playlists/read')
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

        createPlaylist(data);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

  function createPlaylist(data) {

    for (let key of data) {
        console.log(key);

        let find = document.getElementById("playlists");
        let column = document.createElement("div");
        column.className = "col mb-4";
        let container = document.createElement("div");
        container.className = "card text-center text-white";
        find.appendChild(column);
        column.appendChild(container);

        let cover = document.createElement("img");
        cover.className = "card-img-top";
        cover.src = key['artwork'];
        container.appendChild(cover);
        
        let info = document.createElement("div");
        info.className = "card-img-overlay text-center";
        container.appendChild(info);
        
        let link = document.createElement("a");
        link.href = "viewPlaylist.html?id=" + key['id'];
        let text = document.createElement("h1");
        text.className = "card-title";
        text.textContent = key['name'];
        link.appendChild(text);
        info.appendChild(link);
    }

    let find = document.getElementById("playlists");
    let column = document.createElement("div");
    column.className = "col mb-4";
    let container = document.createElement("div");
    container.className = "card text-center text-white";
    container.style = "background-color: #242424; opacity: 0.5";
    find.appendChild(column);
    column.appendChild(container);

    let linkAdd = document.createElement("a");
    linkAdd.setAttribute("id", "addnew");
    linkAdd.href ="#";
    linkAdd.setAttribute("data-toggle", "modal")
    linkAdd.setAttribute("data-target", "#exampleModal")
    container.appendChild(linkAdd);

    let icon = document.createElement("i");
    icon.className = "fas fa-plus";
    icon.style = "color: whitesmoke;";
    linkAdd.appendChild(icon);
  }