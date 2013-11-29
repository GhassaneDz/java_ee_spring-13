package fr.eservice.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/*
 * Le scope permet de controller la dur�e de vie 
 * du controller et donc des attributs qui y sont inject�s (autowired)
 */
@Scope("session")
public class TestController {
	
	
	/*
	 * Un objet Person va �tre cr�� et inject� par
	 * spring, en suivant le pattern Singleton par d�faut
	 */
	@Autowired
	Person myself;
	

	/*
	 * Traite la requ�te associ� � l'url /helloworld
	 * Ajoute un attribut message dans le model qui pourra
	 * �tre utilis� dans la vue.
	 * 
	 * Retourne une chaine de caract�re qui permettra de d�terminer
	 * quelle jsp doit �tre appel� gr�ce au UrlBasedViewResolver
	 * configur� dans le contexte spring.
	 */
	@RequestMapping("/helloworld")
	public String helloWorld(Model model, @RequestParam(value = "name", defaultValue = "world") String name) {
		/*
		// Autre solution possible : ajouter le HTTPServletRequest en param�tre
		// et l'utiliser dans l'op�ration...
		// inconv�niant : votre controller devient d�pendant de l'api servlet,
		//                ce n'est pas id�al pour les tests ou la r�utilisation.
		String name = req.getParameter("name");
		if ( name == null || name.isEmpty() ) {
			name = "world";
		}
		*/
		model.addAttribute("message", "Hello " + name + " !");
		return "helloWorld";
	}
	
	
	/*
	 * Gestion de formulaire simplifi�e avec spring.
	 * 
	 * On se branche � la m�thode GET de l'url /form
	 * On affecte l'objet Person inject� par spring dans le model
	 * et on affiche le formualire ...
	 */
	@RequestMapping( value= "/form", method=RequestMethod.GET )
	public String form(Model model) {
		model.addAttribute("person", myself);
		return "form";
	}
	
	
	/*
	 * On peut traiter le POST du formulaire dans une autre m�thode.
	 * Spring se charge de transpormer les param�tre de la requ�te
	 * HTTP en un objet Person dont les attributs sont public
	 * ou poss�de un accesseur (get/set)
	 * 
	 * l'annotation @ResponseBody indique � spring que l'on souhaite
	 * rendre le r�sultat de cette m�thode directement vers le 
	 * la r�ponse HTTP, sans passer par une vue.
	 */
	@RequestMapping( value= "/form", method=RequestMethod.POST )
	@ResponseBody
	public String handleForm(Person person) {
		myself = person;
		return "name = " + person.name + "\n"
				+ "gender = " + person.gender;
	}
}
