var movie = new Object();
movie["actors"] = [];
movie["genres"] = [];
movie["writers"] = [];
movie["directors"] = [];
movie["languages"] = [];
movie["ratings"] = [];
movie["countries"] = [];
var pageNum = 0;
window.onload = function(){
	
	var url = document.location.href;
	var params = url.split('?')[1];
	var movieId = params.split('=')[1];
    movie["imdbID"] = movieId;
    console.log(movie);
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
document.getElementById("year").value=results.year;
document.getElementById("mprating").value=results.rated;
document.getElementById("release").value=results.releaseDate;
document.getElementById("runtime").value=results.runtime;
document.getElementById("plot").value=results.plot;
document.getElementById("awards").value=results.awards;
document.getElementById("poster").value=results.poster;
document.getElementById("metascore").value=results.metascore;
document.getElementById("imdbRating").value=results.imdbRating;
document.getElementById("imdbVotes").value=results.imdbVotes;
document.getElementById("type").value=results.type;
document.getElementById("dvd").value=results.dvd;
document.getElementById("boxoffice").value=results.boxoffice;
document.getElementById("production").value=results.production;
document.getElementById("website").value=results.website;
movie["actors"] = results.actors;
movie["genres"] = results.genres;
movie["writers"] = results.writers;
movie["directors"] = results.directors;
movie["languages"] = results.languages;
movie["ratings"] = results.ratings;
movie["countries"] = results.countries;

}

function Next(){
    if(pageNum==0){
	movie["title"]=document.getElementById("title").value;
	movie["year"]=document.getElementById("year").value;
	movie["rated"]=document.getElementById("mprating").value;
	movie["releaseDate"]=document.getElementById("release").value;
	movie["runtime"]=document.getElementById("runtime").value;
	movie["plot"]=document.getElementById("plot").value;
	movie["awards"]=document.getElementById("awards").value;
	movie["poster"]=document.getElementById("poster").value;
	movie["metascore"]=document.getElementById("metascore").value;
	movie["imdbRating"]=document.getElementById("imdbRating").value;
	movie["imdbVotes"]=document.getElementById("imdbVotes").value;
	movie["type"]=document.getElementById("type").value;
	movie["dvdDate"]=document.getElementById("dvd").value;
	movie["boxOffice"]=document.getElementById("boxoffice").value;
	movie["production"]=document.getElementById("production").value;
	movie["website"]=document.getElementById("website").value;
    
        document.body.innerHTML= '<h1>Add Actors</h1><table id ="table" class="table-bordered table-responsive">'+
        '<tr><th>Actor</th></tr></table><form><div class="form-group"><label for="input">Enter Actor:</label>'+
    '<input type="text" class="form-control" id="input"></div></form><button onclick="Add()">Add</button>'
        +'<button onclick="Next()">Next</button>';
        
        movie["actors"].forEach(function(actor){
            document.getElementById("table").innerHTML+="<tr><td>"+actor+"</td></tr>";
        })
        movie["actors"]=[];
        
    }
    
    if(pageNum==1){
    	 document.body.innerHTML= '<h1>Add Genres</h1><table id ="table" class="table-bordered table-responsive">'+
         '<tr><th>Genre</th></tr></table><form><div class="form-group"><label for="input">Enter Genre:</label>'+
     '<input type="text" class="form-control" id="input"></div></form><button onclick="Add()">Add</button>'
         +'<button onclick="Next()">Next</button>';
    	 
    	 movie["genres"].forEach(function(genre){
             document.getElementById("table").innerHTML+="<tr><td>"+genre+"</td></tr>";

         })
         
         movie["genres"]=[];
    }
    
    if(pageNum==2){
   	 document.body.innerHTML= '<h1>Add Writers</h1><table id ="table" class="table-bordered table-responsive">'+
        '<tr><th>Writer</th></tr></table><form><div class="form-group"><label for="input">Enter Writer:</label>'+
    '<input type="text" class="form-control" id="input"></div></form><button onclick="Add()">Add</button>'
        +'<button onclick="Next()">Next</button>';
   	 
   	 movie["writers"].forEach(function(item){
         document.getElementById("table").innerHTML+="<tr><td>"+item+"</td></tr>";

     })
     movie["writers"]=[];
   }
    
    
    if(pageNum==3){
      	 document.body.innerHTML= '<h1>Add Directors</h1><table id ="table" class="table-bordered table-responsive">'+
           '<tr><th>Director</th></tr></table><form><div class="form-group"><label for="input">Enter Director:</label>'+
       '<input type="text" class="form-control" id="input"></div></form><button onclick="Add()">Add</button>'
           +'<button onclick="Next()">Next</button>';
      	 
      	movie["directors"].forEach(function(item){
            document.getElementById("table").innerHTML+="<tr><td>"+item+"</td></tr>";

        })
        movie["directors"]=[];
      }
    
    
    if(pageNum==4){
     	 document.body.innerHTML= '<h1>Add Languages</h1><table id ="table" class="table-bordered table-responsive">'+
          '<tr><th>Language</th></tr></table><form><div class="form-group"><label for="input">Enter Language:</label>'+
      '<input type="text" class="form-control" id="input"></div></form><button onclick="Add()">Add</button>'
          +'<button onclick="Next()">Next</button>';
     	 
     	movie["languages"].forEach(function(item){
            document.getElementById("table").innerHTML+="<tr><td>"+item+"</td></tr>";

        })
        movie["languages"]=[];
     }
    
    if(pageNum==5){
     	 document.body.innerHTML= '<h1>Add Ratings</h1><table id ="table" class="table-bordered table-responsive">'+
          '<tr><th>Rating</th><th>Source</th></tr></table><form><div class="form-group"><label for="input">Enter Rating:</label>'+
      '<input type="text" class="form-control" id="input"></div><div class="form-group"><label for="input2">Enter Source:</label>'+
      '<input type="text" class="form-control" id="input2"></div></form><button onclick="Add()">Add</button>'
          +'<button onclick="Next()">Next</button>';
     	 
     	movie["ratings"].forEach(function(item){
            document.getElementById("table").innerHTML+="<tr><td>"+item.value+"</td><td>"+item.source+"</td></tr>";

        })
        movie["ratings"]=[];
     	 
     }
    
    if(pageNum==6){
    	 document.body.innerHTML= '<h1>Add Countries</h1><table id ="table" class="table-bordered table-responsive">'+
         '<tr><th>Country</th></tr></table><form><div class="form-group"><label for="input">Enter Country:</label>'+
     '<input type="text" class="form-control" id="input"></div></form><button onclick="Add()">Add</button>'
         +'<button onclick="Done()">Done</button>';
    	 
    	 movie["countries"].forEach(function(item){
             document.getElementById("table").innerHTML+="<tr><td>"+item+"</td></tr>";

         })
         
         movie["countries"]=[];
    }
    
    pageNum++;
	
}

function Add(){
	if (pageNum==1){
		movie["actors"].push(document.getElementById("input").value);
        document.getElementById("table").innerHTML+="<tr><td>"+document.getElementById("input").value+"</td></tr>";
	}
	
	if (pageNum==2){
		movie["genres"].push(document.getElementById("input").value);
        document.getElementById("table").innerHTML+="<tr><td>"+document.getElementById("input").value+"</td></tr>";
	}
	
	if (pageNum==3){
		movie["writers"].push(document.getElementById("input").value);
        document.getElementById("table").innerHTML+="<tr><td>"+document.getElementById("input").value+"</td></tr>";
	}
	
	if (pageNum==4){
		movie["directors"].push(document.getElementById("input").value);
        document.getElementById("table").innerHTML+="<tr><td>"+document.getElementById("input").value+"</td></tr>";
	}
	
	if (pageNum==5){
		movie["languages"].push(document.getElementById("input").value);
        document.getElementById("table").innerHTML+="<tr><td>"+document.getElementById("input").value+"</td></tr>";
	}
	
	if (pageNum==6){
		movie["ratings"].push({"value":document.getElementById("input").value, "source":document.getElementById("input2").value});
        document.getElementById("table").innerHTML+="<tr><td>"+document.getElementById("input").value+"</td><td>"+document.getElementById("input2").value+"</td></tr>";
	}
	
	if (pageNum==7){
		movie["countries"].push(document.getElementById("input").value);
        document.getElementById("table").innerHTML+="<tr><td>"+document.getElementById("input").value+"</td></tr>";
	}
	
}

function Done(){
    var xml = new XMLHttpRequest();
    xml.open("POST", "http://localhost:8080/updateMovie");
    xml.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        alert("Movie has been updated.");
	window.location="index.html";
    }
    }
    xml.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xml.send(JSON.stringify(movie));
	
}