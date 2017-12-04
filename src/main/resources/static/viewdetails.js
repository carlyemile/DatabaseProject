window.onload = function(){
	
	var url = document.location.href;
	var params = url.split('?')[1];
	var movieId = params.split('=')[1];

var xml = new XMLHttpRequest();
var url = "http://localhost:8080/selectedMovie"
	xml.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var results = JSON.parse(this.responseText);
        setFields(results, movieId);
    }
};
xml.open("GET", url+"?imdbId="+movieId, true);
xml.send();
}

function setFields(results, movieId){
console.log(results);
document.getElementById("imdbId").innerHTML+=movieId;
document.getElementById("title").innerHTML+=results.title;
document.getElementById("year").innerHTML+=results.year;
document.getElementById("mpaa_rating").innerHTML+=results.rated;
document.getElementById("released").innerHTML+=results.releaseDate;
document.getElementById("runtime").innerHTML+=results.runtime;
document.getElementById("plot").innerHTML+=results.plot;
document.getElementById("poster").getElementsByTagName("img")[0].src=results.poster;
document.getElementById("metascore").innerHTML+=results.metascore;
document.getElementById("imdbrating").innerHTML+=results.imdbRating;
document.getElementById("imdbvotes").innerHTML+=results.imdbVotes;
document.getElementById("mtype").innerHTML+=results.type;
document.getElementById("dvd").innerHTML+=results.dvd;
document.getElementById("boxoffice").innerHTML+=results.boxoffice;
document.getElementById("production").innerHTML+=results.production; 
document.getElementById("website").innerHTML+=results.website;
console.log(results.actors);
results.actors.forEach(function(item){
        document.getElementById("actors").innerHTML+="<li>"+item+"</li>";
});
    results.countries.forEach(function(item){
        document.getElementById("countries").innerHTML+="<li>"+item+"</li>";
});
    results.directors.forEach(function(item){
        document.getElementById("directors").innerHTML+="<li>"+item+"</li>";
});
    results.genres.forEach(function(item){
    document.getElementById("genres").innerHTML+="<li>"+item+"</li>";
});
    results.languages.forEach(function(item){
    document.getElementById("languages").innerHTML+="<li>"+item+"</li>";
});
    results.writers.forEach(function(item){
    document.getElementById("writers").innerHTML+="<li>"+item+"</li>";
});
    results.ratings.forEach(function(item){
    document.getElementById("ratings").innerHTML+="<li>Rating: "+item.value+" Source: "+item.source+"</li>";
});

}