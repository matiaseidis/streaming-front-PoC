<?php 
	// output must be in JSON format
	// random values below will be replaced with actual database values
	
	$json = '{"rating_number": "'.$_GET['rating_number'].'",';
	$json .= '"rating_label": "'.str_replace('\\', '', $_GET['rating_label']).'",';
	$json .= '"rating_view": "'.$_GET['rating_view'].'",';
	$json .= '"max_stars": "'.$_GET['max_stars'].'",';

	for($i = 1; $i <= $_GET['max_stars']; $i++) { 
		$json .= '"star_'.$i.'_rating": "'.rand(1,100).'",';
	}
		
	echo trim($json,',').'}';
	exit();
?>