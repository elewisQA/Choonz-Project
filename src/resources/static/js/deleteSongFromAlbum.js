
document.getElementById("delete-track").addEventListener("submit", function(stop){
    stop.preventDefault();
    let listForm = document.getElementById("delete-track").elements;
    console.log(listForm);
    //  let id = listForm["listId"].value; 
     
    //  deleteList(id);    
})

function deleteTrack(id) {
    fetch('http://localhost:8082/tracks/delete/' + id, {
    method: 'DELETE',
    })
    .then(res => res.text()) // or res.json()
    .then(res => console.log(res))
  
  }