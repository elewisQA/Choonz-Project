function validateForm(){

    var name = document.forms["createNewGenre"]["name"];
    var pic = document.forms["createNewGenre"]["pic"];
    var desc = document.forms["createNewGenre"]["desc"];
    if (name.value == "") { 
        window.alert("Please enter the name of the genre."); 
        name.focus(); 
        return true; 
    }
    if (pic.value == "") { 
        window.alert("Please add the link of the image for this genre."); 
        pic.focus();
        return false; 
    }
    if (desc.value == "") { 
        window.alert("Please enter the description of the genre."); 
        desc.focus(); 
        return false; 
    }

    return false;
}
    document.querySelector("form.genres").addEventListener("submit", function(stop){
        stop.preventDefault();
        let genreModal = document.querySelector("form.genres").elements;
        console.log(genreModal);
        let genreName = genreModal['genreName'].value;
        let genrePic = genreModal['genrePic'].value;
        let genreDesc = genreModal['genreDesc'].value;
        addAlbum(genreName, genrePic, genreDesc);
        
    })
    
    function addAlbum(genreName, genrePic, genreDesc) {
    
        fetch('http://localhost:8082/genres/create', {
            method: 'post',
            headers: {
                  "Content-type": "application/json"
            },
            body:json = JSON.stringify({
                "name": genreName,
                "piture": genrePic,
                "description": genreDesc
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
