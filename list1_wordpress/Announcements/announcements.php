<?php
/**
 * Plugin Name: Announcements
 * Plugin URI: https://example.com/plugins/Announcements/
 * Description: Add an announcement between title and post body.
 * Version: 1.0
 * Requires at least: 5.0
 * Requires PHP: 7.2
 * Author: Sebastian Bednarski, Wojciech Dominiak
 * Author URI: https://github.com/sebo21cc21/Advanced-Web-Technologies
 * License: GPL v2 or later
 * License URI: https://www.gnu.org/licenses/gpl-2.0.html
 */
// first Page Title, Second Menu Admin title, Third capability, fourth URL from admin side, fifth function to generate admin web
function naph_admin_actions(){ 
    add_options_page("Announcements Post", "Announcements", 'manage_options', "naph_anon", "naph_admin_page_anon"); 
}  

function naph_admin_page_anon(){ 
	$amount = get_option('amount') ?? 0;
	$opAnon = array();

    // process changes from form
    if(isset($_POST['naph_do_change'])){
		if($_POST['naph_do_change'] == 'Y'){ 
			$amount = $_POST['amount']; 

			for ($i=1; $i<=$amount; $i++) {
				$opAnon[$i] = wp_kses_post($_POST['naph_announcements'.$i] ?? '');
				update_option('naph_announcements'.$i, $opAnon[$i]);
			}
			echo '<div class="notice notice-success is dismissible"><p>Settings saved.</p></div>'; 
			update_option('amount', $_POST['amount']);
		}
    } 

    //read current option value
	for ($i=1; $i<=$amount; $i++) {
		$opAnon[$i] = get_option('naph_announcements'.$i);
	}

    //display admin page
   ?>
    <div class="wrap">
        <h1>Announcements</h1>
        <form name="naph_form" method="post">
			<label for="amount">Amount:</label>
			<input type="number" name="amount" id="amount" min="0" max="10" value="<?php echo $amount; ?>">    
			<div class="announcements-wrapper">
					<input type="hidden" name="naph_do_change" value="Y">
					<?php for($i=1; $i<=$amount; $i++): ?>
						<textarea type="text" name="<?= 'naph_announcements'.$i?>" cols="60" rows="6"><?= $opAnon[$i] ?></textarea>
					<?php endfor ?>
				</div>
				<?php
				if(isset($_POST['number']) && $_POST['number'] != "") {
					$number = $_POST['number'];
					if($number <= $amount) {
						$settings = array(
							'textarea_rows' => 6,
							'media_buttons' => true,
							'textarea_name' => 'naph_announcements'.$number
						);	
						wp_editor($opAnon[$number], 'naph_announcements_editor'.$number, $settings);
					}
				}
				?>
				<h1>Choose announcement to change:</h1>
				<input type="number" name="number" id="number" min="1" max="<?= $amount ?>" value="<?= ($number <= $amount) ? $number : '' ?>">
				<?php submit_button( 'Submit' );?>
        </form>
    </div>
<?php
}   
	

function naph_add_announcements($content){ 
	// is it a post
	if ( is_single() ) {
		$rand_number = rand(1, get_option('amount'));
		$content = get_option('naph_announcements'.$rand_number) . $content;
	}
	return $content;
} 

function naph_register_styles_css(){ 
    //enable style (load in meta of html)
    wp_enqueue_style( 'style', plugin_dir_url( __FILE__ ) . '/css/style.css' );
} 



add_action('init', 'naph_register_styles_css'); 
add_action('admin_menu', 'naph_admin_actions'); 
add_filter('the_content', "naph_add_announcements"); 


?>
