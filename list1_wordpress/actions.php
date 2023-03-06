<?php 
/*
Plugin Name: actions
Plugin URI: http://wordpress.org/plugins/actions/
Description: This is not just a plugin, it symbolizes the hope and enthusiasm of changes Wordpress. 
Author: Sebastian Bednarski, Wojciech Dominiak
Version: 1.7.2
Author URI: https://github.com/sebo21cc21/Web-Systems
*/

function add_Cookie(){
    setcookie("last_visit_time", date("r"), time()+ 60*60, "/");
}

function mfp_Add_Text() {
    echo "<p style='color:red;background:yellow;'>Ten tekst wyświetla się po wygenerowaniu stopki strony";
}

function mfp_Remove_TextOnWedensday()
{
		if(date("l") === "Wednesday"){ // === identical type and equal
			remove_action("wp_footer", "mfp_Add_Text"); // first Action Hook, second destination, third param priority
		} 
}

function add_something($content) {
    return "text" . $content;
}

function mfp_Fix_Text($the_Post)
{
	//correct double spaces
	$the_New_Post = str_replace("text", " ", $the_Post);
	
	return $the_New_Post;
}

//INIT - Runs after WordPress has finished loading but before any headers are sent. Useful for intercepting $_GET or $_POST triggers.
//HEAD - Runs when the template calls the wp_head() function. This hook is generally placed near the top of a page template between <head> and </head>. This hook does not take any parameters.
//FOOTER - Runs when the template calls the wp_footer() function, generally near the bottom of the blog page.
add_action( 'init', 'add_Cookie'); // first Action Hook, second destination, third param priority, fourth count of arguments
add_action('wp_head', 'mfp_Remove_TextOnWedensday');
add_action('wp_footer', 'mfp_Add_Text');

add_filter('the_content', 'add_something');
add_filter('the_content', 'mfp_Fix_Text');
?>