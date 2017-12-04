var movie = new Object();
movie["actors"] = [];
movie["genres"] = [];
movie["writers"] = [];
movie["directors"] = [];
movie["languages"] = [];
movie["ratings"] = [];
movie["countries"] = [];
var pageNum = 0;

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
    }
    
    if(pageNum==1){
    	 document.body.innerHTML= '<h1>Add Genres</h1><table id ="table" class="table-bordered table-responsive">'+
         '<tr><th>Genre</th></tr></table><form><div class="form-group"><label for="input">Enter Genre:</label>'+
     '<input type="text" class="form-control" id="input"></div></form><button onclick="Add()">Add</button>'
         +'<button onclick="Next()">Next</button>';
    }
    
    if(pageNum==2){
   	 document.body.innerHTML= '<h1>Add Writers</h1><table id ="table" class="table-bordered table-responsive">'+
        '<tr><th>Writer</th></tr></table><form><div class="form-group"><label for="input">Enter Writer:</label>'+
    '<input type="text" class="form-control" id="input"></div></form><button onclick="Add()">Add</button>'
        +'<button onclick="Next()">Next</button>';
   }
    
    if(pageNum==3){
      	 document.body.innerHTML= '<h1>Add Directors</h1><table id ="table" class="table-bordered table-responsive">'+
           '<tr><th>Director</th></tr></table><form><div class="form-group"><label for="input">Enter Director:</label>'+
       '<input type="text" class="form-control" id="input"></div></form><button onclick="Add()">Add</button>'
           +'<button onclick="Next()">Next</button>';
      }
    
    
    if(pageNum==4){
     	 document.body.innerHTML= '<h1>Add Languages</h1><table id ="table" class="table-bordered table-responsive">'+
          '<tr><th>Language</th></tr></table><form><div class="form-group"><label for="input">Enter Language:</label>'+
      '<input type="text" class="form-control" id="input"></div></form><button onclick="Add()">Add</button>'
          +'<button onclick="Next()">Next</button>';
     }
    
    if(pageNum==5){
     	 document.body.innerHTML= '<h1>Add Ratings</h1><table id ="table" class="table-bordered table-responsive">'+
          '<tr><th>Rating</th><th>Source</th></tr></table><form><div class="form-group"><label for="input">Enter Rating:</label>'+
      '<input type="text" class="form-control" id="input"></div><div class="form-group"><label for="input2">Enter Source:</label>'+
      '<input type="text" class="form-control" id="input2"></div></form><button onclick="Add()">Add</button>'
          +'<button onclick="Next()">Next</button>';
     }
    
    if(pageNum==6){
    	 document.body.innerHTML= '<h1>Add Countries</h1><table id ="table" class="table-bordered table-responsive">'+
         '<tr><th>Country</th></tr></table><form><div class="form-group"><label for="input">Enter Country:</label>'+
     '<input type="text" class="form-control" id="input"></div></form><button onclick="Add()">Add</button>'
         +'<button onclick="Done()">Done</button>';
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
    xml.open("POST", "http://localhost:8080/addMovie");
    xml.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        alert("Movie has been added.");
	window.location="index.html";
    }
    }
    xml.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xml.send(JSON.stringify(movie));
	
}