
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
	document.location.href="editpage.html?movie="+movieId;
}

function Delete(movieId){
	if(confirm("Are you sure you want to delete this movie? If so, press OK to continue.")==true)
		var xml = new XMLHttpRequest();
	var url = "http://localhost:8080/deleteMovie"
	var param = "imdbId="+movieId;
		xml.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	        alert("Deletion complete.")
	    }
	};
	xml.open("POST", url+"?"+param, true);
	xml.send();
}

function ViewDetails(movieId){
	window.open("viewdetails.html?movie="+movieId);
}
function displayResults(movies){  
    var table = "<table class='table-bordered table-responsive'><tr><th>Edit</th><th>Delete</th><th>View Details</th><th>IMDB ID</th>"+
    "<th>Title</th><th>Year</th><th>MPAA Rating</th><th>Release Date</th>"+
       "<th>Runtime</th><th>Metascore</th><th>IMDB Rating</th><th>IMDB Votes</th>"+
        "<th>Type</th><th>DVD Release</th><th>Box Office</th><th>Production</th><th>Website</th></tr>";
    
    movies.forEach(function(movie){
        table+='<tr><td><button class="editbtn btn btn-primary">Edit</button></td>'+"<td><button class='deletebtn btn btn-danger'>Delete</button></td><td><button class='viewbtn btn btn-info'>View Details</button></td><td>"+movie.imdbID+"</td>"+
    "<td>"+movie.title+"</td><td>"+movie.year+"</td><td>"+movie.rated+"</td><td>"+movie.releaseDate+"</td>"+
       "<td>"+movie.runtime+"</td><td>"+movie.metascore+"</td><td>"+movie.imdbRating+"</td><td>"+movie.imdbVotes+"</td>"+
        "<td>"+movie.type+"</td><td>"+movie.dvdDate+"</td><td>"+movie.boxOffice+"</td><td>"+movie.production+"</td><td>"+movie.website+"</td></tr>";
    
    });
    table+="</table>"

    document.getElementById("resultsTable").innerHTML+=table;
    
    var i;
    var editBtns = document.getElementById("resultsTable").getElementsByClassName("editbtn");
    var deleteBtns = document.getElementById("resultsTable").getElementsByClassName("deletebtn");
    var viewBtns =document.getElementById("resultsTable").getElementsByClassName("viewbtn");
    movies.forEach(function(movie){
    	editBtns[movies.indexOf(movie)].onclick = function(){Edit(movie.imdbID)};
        deleteBtns[movies.indexOf(movie)].onclick = function(){Delete(movie.imdbID)};
        viewBtns[movies.indexOf(movie)].onclick = function(){ViewDetails(movie.imdbID)};

    });
}