document.querySelector("form.form-inline.my-2.my-lg-0").addEventListener("submit", function(stop){
    stop.preventDefault();
    let searchBox = document.querySelector("form.form-inline.my-2.my-lg-0").elements;
    let searchTerms = document.querySelector("input.form-control.mr-sm-2").value; // TODO set some ID for the search bar

    sessionStorage.setItem("query", searchTerms);
    window.location.href="viewSearch.html"
})
