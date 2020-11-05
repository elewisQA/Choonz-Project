function validateForm(){

    var name = document.forms["createNewPlaylist"]["name"];
    var pic = document.forms["createNewPlaylist"]["pic"];
    var desc = document.forms["createNewPlaylist"]["desc"];
    if (name.value == "") { 
        window.alert("Please enter the name of the playlist."); 
        name.focus(); 
        return true; 
    }
    if (pic.value == "") { 
        window.alert("Please add a link for the cover image."); 
        pic.focus();
        return false; 
    }
    if (desc.value == "") { 
        window.alert("Please enter a brief description"); 
        desc.focus(); 
        return false; 
    }

    return false;
}

document.querySelector("form.playlist").addEventListener("submit", function(stop){
    stop.preventDefault();
    let playlistModal = document.querySelector("form.playlist").elements;
    console.log(playlistModal);
    let playlistName = playlistModal['playlistName'].value;
    let playlistPic = playlistModal['playlistPic'].value;
    let playlistDesc = playlistModal['playlistDesc'].value;
    addPlaylist(playlistName, playlistPic, playlistDesc);
    
})


function addPlaylist(playlistName, playlistPic, playlistDesc) {
    
    fetch('http://localhost:8082/playlists/create', {
        method: 'post',
        headers: {
              "Content-type": "application/json",
              "token": sessionStorage.getItem('token'),
              "uid": sessionStorage.getItem('userId')
        },
        body:json = JSON.stringify({
            "name": playlistName,
            "artwork": playlistPic,
            "description": playlistDesc,
            "tracks": [null],
            "user": {
                "id": sessionStorage.getItem("userId")
              }
        })
        })
        .then(json)
        .then(function (data) {
            console.log('Request succeeded with JSON response', data);
            window.location.href = "playlists.html";
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}
