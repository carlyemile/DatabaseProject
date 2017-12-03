
function Search(){
document.getElementById("resultsTable").innerHTML="";
var xml = new XMLHttpRequest();
var url = "http://localhost:8080/search"
var inputparam = "input="+document.getElementById("input").value;
    var menu = document.getElementById("menu");
var optionparam = "option="+menu.options[menu.selectedIndex].value.toLowerCase(); 
	xml.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var results = JSON.parse(this.responseText);
        displayResults(results);
    }
};
xml.open("GET", url+"?"+inputparam+"&"+optionparam, true);
xml.send();
}

function Edit(movieId){
/**var xml = new XMLHttpRequest();
var url = "http://localhost:8080/setMovie" 
	xml.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
            window.location="editpage.html";
    }
};
xml.open("GET", url+"?movieId="+movieId, true);
xml.send();
**/
	document.location.href="editpage.html?movie="+movieId;
}
function displayResults(movies){  
    var table = "<table class='table-bordered table-responsive'><tr><th>Edit</th><th>Delete</th><th>IMDB ID</th>"+
    "<th>Title</th><th>Year</th><th>MPAA Rating</th><th>Release Date</th>"+
       "<th>Runtime</th><th>Metascore</th><th>IMDB Rating</th><th>IMDB Votes</th>"+
        "<th>Type</th><th>DVD Release</th><th>Box Office</th><th>Production</th><th>Website</th></tr>";
    
    movies.forEach(function(movie){
        table+='<tr><td><button class="btn btn-default editbtn">Edit</button></td>'+"<td><button class='btn btn-danger deletebtn'>Delete</button></td><td>"+movie.imdbID+"</td>"+
    "<td>"+movie.title+"</td><td>"+movie.year+"</td><td>"+movie.rated+"</td><td>"+movie.releaseDate+"</td>"+
       "<td>"+movie.runtime+"</td><td>"+movie.metascore+"</td><td>"+movie.imdbRating+"</td><td>"+movie.imdbVotes+"</td>"+
        "<td>"+movie.type+"</td><td>"+movie.dvdDate+"</td><td>"+movie.boxOffice+"</td><td>"+movie.production+"</td><td>"+movie.website+"</td></tr>";
    
    });
    table+="</table>"

    document.getElementById("resultsTable").innerHTML+=table;
    
    var i;
    var editBtns = document.getElementById("resultsTable").getElementsByClassName("editbtn");
    var deleteBtns = document.getElementById("resultsTable").getElementsByClassName("deletebtn");
    movies.forEach(function(movie){
    	editBtns[movies.indexOf(movie)].onclick = function(){Edit(movie.imdbID)};
        deleteBtns[movies.indexOf(movie)].onclick = function(){Delete(movie.imdbID)};

    });
}