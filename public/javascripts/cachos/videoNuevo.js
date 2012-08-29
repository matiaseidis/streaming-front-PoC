$(function(){

	url = "/ficha/actualizar/";
	requests = new Object();
	counter = 0;
	var apikey = "re2tr9fjggx3we73vzs8rkk4";
	var baseUrl = "http://api.rottentomatoes.com/api/public/v1.0";

	var moviesSearchUrl = baseUrl + '/movies.json?apikey=' + apikey;

	$(".updateLink").live('click',function(e){
		console.log($(this).attr('href'));
		console.log(requests[$(this).attr('href')]);

		body = requests[$(this).attr('href')];

		console.log("body: "+body)
		console.log("body: "+body["title"])

		if(body){
			var request = $.ajax({
				url: url,
				type: "POST",
				data: body,
				dataType: "html"
			});

			request.done(function(msg) {
				console.log( msg );
				$('#ficha').html(msg).modal('show');
			});
		}

		return false;
	});

	$('#video-ficha-manual-search').live('submit',function(e){
		e.preventDefault();

		var videoId = $('#video-ficha-manual-search input[name=videoId]').val();
		var nombre = $('#video-ficha-manual-search input[name=nombre]').val();

		console.log(nombre);
		console.log(videoId);

		buscarFicha(nombre, videoId);
	});

	// send off the query
	buscarFicha = function(nombre, videoId){

		console.log("llamada: nombre: "+nombre+" videoId: "+videoId);

		$('#movie-search-results').empty();
		var requestUrl = moviesSearchUrl + '&q=' + encodeURI(nombre);
		console.log(requestUrl);
		$.ajax({
			url: requestUrl,
			dataType: "jsonp"
		}).success(	function(data){
			searchCallback(videoId,data);
		});
	}


	// callback for when we get back the results
	searchCallback = function(videoId, data) {

		console.log(videoId);
		console.log(data);

		var resultsToShow = '<h4>No encontramos resultados para este video.</h4>'+
		'<br><br><h5>Intente buscar la ficha con otro nombre, o complete los campos manualmente para poder publicarlo</h5><br>'+
		'<form id="video-ficha-manual-search" class="form-inline"><input class="input-large" type="text" name="nombre">'+
		'<input type="hidden" name="videoId" value="'+videoId+'"><button type="submit" class="btn" id="video-ficha-manual-search-btn">Buscar ficha</button>'+
//		'<button type="submit" onclick="javascript:alert()" class="btn" id="video-ficha-manual-edit-btn">Editar ficha</button>'+
		'</form>';

		if(data.total != 0) {
			resultsToShow = showResults(data, videoId);
			$('#movie-search-results').html(resultsToShow);
		} else {
			$('#movie-search-no-results').addClass("alert").addClass("alert-error").html(resultsToShow);
		}
		return;
	}

	showResults = function(data, videoId) {
		var title = '<div><p><h2>¿El video es alguno de estos ' + data.total + ' resultados?</h2></p></div>';
		var resultToShow = "";

		var i = 0;
		counter = 0;
		$.each(data.movies, function(index, movie) {
			counter = counter+1;
			if(counter == 1) {
				resultToShow += "<div class='row'>";
			} 
			resultToShow += videoBox(movie, videoId, data);
			if (counter == 4) {
				resultToShow += "</div>";
				counter = 0;
			}

		});

		return title+resultToShow+"</div>";
	}

	videoBox = function(movie, videoId, data) {

		return 	"<div class='video-result span3' style='float:left; display:inline; margin:5px;'>"+
		"<p><a class='updateLink' href='"+updateVideoRequest(movie, videoId, data)+"' >" + movie.title + "</a></p>"+
		"<img height='200' width='130' src='" + movie.posters.detailed + "' />"+
		"</div>";
	}

	updateVideoRequest = function(movie, videoId, data) {

		var imdbId = null;
		var alternateIds = $(movie['alternate_ids']); 
		if(alternateIds && alternateIds[0]) {
			imdbId = alternateIds[0]['imdb']
		}
		
		reparto="";
		var nombres = $(movie['abridged_cast']);
		
		jQuery.each(nombres, function(index, actor){
			console.log(index+" "+actor.name);
			reparto += actor.name+"+" ;
		});
		console.log("reparto "+movie.title+" - "+movie.id+": "+reparto);
		var params = { 
				videoId:videoId,
				rottenTomatoesId:movie.id,
				imdbId:imdbId,
				title: movie.title,
				posterUrl: movie.posters.detailed,
				year:movie.year,
				runtime:movie.runtime,
				synopsis:movie.synopsis,
				reparto:reparto
		};

		requests[movie.id]=params;
		return movie.id;
	}

});
