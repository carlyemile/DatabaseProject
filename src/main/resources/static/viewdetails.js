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
if(results.title!=null)
document.getElementById("title").innerHTML+=results.title;
if(results.year!=null)
document.getElementById("year").innerHTML+=results.year;
if(results.rated!=null)
document.getElementById("mpaa_rating").innerHTML+=results.rated;
if(results.releaseDate!=null)
document.getElementById("released").innerHTML+=results.releaseDate;
if(results.runtime!=null)
document.getElementById("runtime").innerHTML+=results.runtime;
if(results.plot!=null)
document.getElementById("plot").innerHTML+=results.plot;
if(results.poster!=null)
document.getElementById("poster").getElementsByTagName("img")[0].src=results.poster;
if(results.metascore!=null)
document.getElementById("metascore").innerHTML+=results.metascore;
if(results.imdbRating!=null)
document.getElementById("imdbrating").innerHTML+=results.imdbRating;
if(results.imdbVotes!=null)
document.getElementById("imdbvotes").innerHTML+=results.imdbVotes;
if(results.type!=null)
document.getElementById("mtype").innerHTML+=results.type;
if(results.dvd!=null)
document.getElementById("dvd").innerHTML+=results.dvd;
if(results.boxoffice!=null)
document.getElementById("boxoffice").innerHTML+=results.boxoffice;
if(results.production!=null)
document.getElementById("production").innerHTML+=results.production;
if(results.website!=null)
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