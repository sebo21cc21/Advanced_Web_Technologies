<?php
/**
 * Plugin Name: Announcements
 * Plugin URI: https://example.com/plugins/Announcements/
 * Description: Add an announcement between title and post body.
 * Version: 1.0
 * Requires at least: 5.0
 * Requires PHP: 7.2
 * Author: Sebastian Bednarski, Wojciech Dominiak
 * Author URI: https://github.com/sebo21cc21/Web-Systems
 * License: GPL v2 or later
 * License URI: https://www.gnu.org/licenses/gpl-2.0.html
 */
// first Page Title, Second Menu Admin title, Third capability, fourth URL from admin side, fifth function to generate admin web
function naph_admin_actions_register_menu(){ 
    add_options_page("Announcements Post", "Announcements", 'manage_options', "naph", "naph_admin_page"); 
} 

function naph_admin_page(){ 
    // get _POST variable from globals 
    global $_POST; $opAnon = 'opAnon'; $counter = 3;
    // process changes from form
    if(isset($_POST['naph_do_change'])){ 
		if($_POST['naph_do_change'] == 'Y'){ 
			for ($i=1; $i<=$counter; $i++) {
				${$opAnon . $i} = wp_kses_post($_POST['naph_announcements'.$i]);
			}
			echo '<div class="notice notice-success is dismissible"><p>Settings saved.</p></div>'; 
			for ($i=1; $i<=$counter; $i++) {
				update_option('naph_announcements'.$i, ${$opAnon . $i});
			}
		} 
    } 
    //read current option value
	for ($i=1; $i<=$counter; $i++) {
		${$opAnon . $i} = get_option('naph_announcements'.$i);
	}
    //display admin page
   ?>
    <div class="wrap">
        <h1>Announcements</h1>
        <form name="naph_form" method="post">
            <input type="hidden" name="naph_do_change" value="Y">
			<textarea type="text" name="naph_announcements1" cols="60" rows="6"><?php echo $opAnon1 ?></textarea>
			<textarea type="text" name="naph_announcements2" cols="60" rows="6"><?php echo $opAnon2 ?></textarea>
			<textarea type="text" name="naph_announcements3" cols="60" rows="6"><?php echo $opAnon3 ?></textarea><p></p>
			<?php
			if(isset($_POST['number'])) {
                $number = $_POST['number'];
				$opAnon = 'opAnon';
                switch($number) {
                    case 1:
						$settings = array(
							'textarea_rows' => 6,
							'media_buttons' => true,
							'textarea_name' => 'naph_announcements1'
						);	
                        wp_editor(${$opAnon . $number}, 'naph_announcements_editor1', $settings);
                        break;
                    case 2:
						$settings = array(
							'textarea_rows' => 6,
							'media_buttons' => true,
							'textarea_name' => 'naph_announcements2'
						);	
                        wp_editor(${$opAnon . $number}, 'naph_announcements_editor2', $settings);
                        break;
                    case 3:
						$settings = array(
							'textarea_rows' => 6,
							'media_buttons' => true,
							'textarea_name' => 'naph_announcements3'
						);	
                        wp_editor(${$opAnon . $number}, 'naph_announcements_editor3', $settings);
                        break;
                    default:
                        break;
                }
            }
			?>
			<h1>Wybierz ogłoszenie do zmiany :</h1>
			<input type="number" name="number" id="number" min="1" max="3" value="<?php echo $number ?>">
			<?php submit_button( 'Submit' );?>
        </form>
    </div>
<?php
	
	
}   
	

function naph_add_announcements($content){ 
	$opAnon1 = get_option('naph_announcements1');
	$opAnon2 = get_option('naph_announcements2');
	$opAnon3 = get_option('naph_announcements3');
	if ( empty( $opAnon1 ) || empty( $opAnon2 ) || empty( $opAnon3 ) ) {
		return $content;
	}
	// czy to jest post
	if ( is_single() ) {
		$anon_array = [$opAnon1, $opAnon2, $opAnon3];
		shuffle( $anon_array );
		$content = $anon_array[0] . $content;
	}
	return $content;
} 

function naph_register_styles(){ 
    //enable style (load in meta of html)
    wp_enqueue_style( 'style', plugin_dir_url( __FILE__ ) . '/css/style.css' );
} 


add_action('init', 'naph_register_styles'); 
add_action('admin_menu', 'naph_admin_actions_register_menu'); 
add_filter('the_content', "naph_add_announcements"); 


?>