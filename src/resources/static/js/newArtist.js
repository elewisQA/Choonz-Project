// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
    'use strict';
    window.addEventListener('load', function() {
      // Fetch all the forms we want to apply custom Bootstrap validation styles to
      var forms = document.getElementsByClassName('needs-validation');
      // Loop over them and prevent submission
      var validation = Array.prototype.filter.call(forms, function(form) {
        form.addEventListener('submit', function(event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }
            form.classList.add('was-validated');
        }, false);
      });
    }, false);
  })();

    document.querySelector("form#createNewArtist").addEventListener("submit", function(stop){
        stop.preventDefault();
        let artistModal = document.querySelector("form#createNewArtist").elements;
        console.log(artistModal);
        let artistName = artistModal['artistName'].value;
        let artistPic = artistModal['artistPic'].value;
        addArtist(artistName, artistPic);
        
    });

    
    function addArtist(artistName, artistPic) {
    
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
                location.reload();
            })
            .catch(function (error) {
                console.log('Request failed', error);
            });
    }
