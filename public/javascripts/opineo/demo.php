<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/default.css" rel="stylesheet" type="text/css" />
<link href="style/opineo.css" rel="stylesheet" type="text/css" />

<title>Feedback Collection and Polling Widget</title>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/opineo.js"></script>
	<script>
		
		$(document).ready(function (){
			
			$('#DefaultOptions').opineo('results.php', {curvalue:3, view: 'detailed', animation_speed: 'super', show_total_votes_counter: false, show_overall_rating: true});
			
			$('#CompactView').opineo('results.php', {curvalue:3, view: 'compact', animation_speed: 'super'});
			
			$('#MiniView').opineo('results.php', {curvalue:0, view: 'mini', callback: myCallback});
												  
		})
		
		function myCallback(responseData){
			var userRating = responseData.rating_label;
			if(userRating == '' || userRating == undefined){
				userRating = 'None';
			}
			
			$('#UserVoteSpan').html(userRating);
		}
		
	</script>

</head>

<body class="one-column-centered">
	<div id="widget-name"> <img src="images/logo.png" alt="Opineo" /> </div>
	<div class="header">
	  <h1>Feedback Collection and Polling Widget</h1>
	</div>
	<div id="wrapper">
		<h4>Widget With Detailed View</h4>
		<div id="DefaultOptions" style="width:500px"></div>
		<hr />
		
		<br />
		<h4>Widget With Compact View</h4>
		<div id="CompactView" style="width:500px"></div>
		<hr />
		
		<br />
		<h4>Widget With Mini View</h4>
		
		<div style="font-size:12px;">You Voted: <span id="UserVoteSpan" style="font-weight:bold;"></span></div>
		<div id="MiniView" style="width:500px">
			
		</div>
	</div>
</body>	

</html>