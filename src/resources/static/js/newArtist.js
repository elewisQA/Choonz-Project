function validateForm(){

    var name = document.forms["createNewArtist"]["name"];
    var pic = document.forms["createNewArtist"]["pic"];
    if (name.value == "") { 
        window.alert("Please enter the name of the artist."); 
        name.focus(); 
        return true; 
    }
    if (pic.value == "") { 
        window.alert("Please add the link of the image for this artist."); 
        pic.focus();
        return false; 
    }

    return false;
}
    document.querySelector("form.artists").addEventListener("submit", function(stop){
        stop.preventDefault();
        let artistModal = document.querySelector("form.artists").elements;
        console.log(artistModal);
        let artistName = artistModal['artistName'].value;
        let artistPic = artistModal['artistPic'].value;
        addAlbum(artistName, artistPic);
        
    })
    
    function addAlbum(artistName, artistPic) {
    
        fetch('http://localhost:8082/artists/create', {
            method: 'post',
            headers: {
                  "Content-type": "application/json"
            },
            body:json = JSON.stringify({
                "name": artistName,
                "picture": artistPic
            })
            })
            .then(json)
            .then(function (data) {
                console.log('Request succeeded with JSON response', data);
                // window.location.href = "albums.html";
            })
            .catch(function (error) {
                console.log('Request failed', error);
            });
    }
