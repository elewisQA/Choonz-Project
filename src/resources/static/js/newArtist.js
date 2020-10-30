    function validate(){
        stop.preventDefault();
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
                  validation.setCustomValidity = "Enter all the details";
                }else{
                    form.classList.add('was-validated');
                    validation.setCustomValidity = "valid";
                }
              }, false);
            });
          }, false);
        })();
    }
    
    
    document.querySelector("form#createNewArtist").addEventListener("submit", function(stop){
        stop.preventDefault();
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
                  validation.setCustomValidity = "Enter all the details";
                }else{
                    form.classList.add('was-validated');
                    validation.setCustomValidity = "valid";
                }
              }, false);
            });
          }, false);
        })();
        let artistModal = document.querySelector("form#createNewArtist").elements;
        console.log(artistModal);
        let artistName = artistModal['name'].value;
        let artistPic = artistModal['pic'].value;
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
                // location.reload();
            })
            .catch(function (error) {
                console.log('Request failed', error);
            });
    }
