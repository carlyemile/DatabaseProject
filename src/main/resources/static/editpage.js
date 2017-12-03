window.onload = function(){
	var url = document.location.href;
	var params = url.split('?')[1];
	var movieId = params.split('=')[1];
var xml = new XMLHttpRequest();
var url = "http://localhost:8080/selectedMovie"
	xml.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var results = JSON.parse(this.responseText);
        setFields(results);
    }
};
xml.open("GET", url+"?imdbId="+movieId, true);
xml.send();
}
function setFields(results){
document.getElementById("title").value=results.title;
document.getElementById("year").value=results.title;
document.getElementById("rated").value=results.title;


}