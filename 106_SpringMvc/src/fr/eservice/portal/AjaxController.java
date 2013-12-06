package fr.eservice.portal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/*
 * Une requ�te ajax est une requ�te HTTP comme les autres,
 * sauf qu'elle est execut�e par la page web, � l'int�rieur
 * d'un javascript plutot qu'en cliquant sur un lien ou en
 * tapant une adresse web.
 * 
 * r�pondre � une requ�te ajax se fait de la m�me mani�re que
 * pour les autres requ�tes ... sauf qu'au lieu de renvoyer
 * une page compl�te en html, on renverra un bout de html ou uniquement
 * des donn�es � exploit�es en javascript dans le navigateur.
 * Pour cette raison, les r�ponses sont souvent encod�es en "JSON",
 * un format de s�rialisation javascript facilement exploitable.
 *  
 * essayons de faire une salle de discussion "temps r�el"
 */
@Controller
public class AjaxController {
	
	List<String> history;
	
	public AjaxController() {
		history = new ArrayList<String>();
	}

	@RequestMapping("/say")
	@ResponseBody
	public String saySomething(@RequestParam("msg") String msg) {
		history.add( msg );
		return "Ok";
	}
	
	@RequestMapping(value = "/history", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String[] getHistory( @RequestParam(value="last", defaultValue="0") int last) {
		if ( last >= history.size() ) return new String[0];
		return Arrays.copyOfRange(history.toArray( new String[0] ), last, history.size());
	}
	
	
}
