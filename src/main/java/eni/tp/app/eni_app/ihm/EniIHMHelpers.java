package eni.tp.app.eni_app.ihm;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class EniIHMHelpers {

    //Refactorer du code :
//Se poser  la question "Quel code est redondant ? Quel code peut-on centralier ?"
//Créer la classe/la fonction/la variable qui va contenir ce code centraliser (on va déplacer le code)
//On met en paramètres ce qui nous manque (ce qui est en rouge quand on a déplacé le code)
//On met en paramètre ce qui est variable
//Méthode 1 :

//Penser à faire de la doc pour avoir des suggestions, par exemple les paramètres
    /**
     *
     * @param redirectAttributes Attention : c'est le RedirectAttributes qu'on va injecter dans les controller
     * @param type Le type du message; Exemple : EniFlashMessage.TYPE_FLASH_SUCCESS
     * @param message Le message du flash
     */
    public static void sendCommonFlashMessage(RedirectAttributes redirectAttributes, int type, String message) {
        //Avant :
        //redirectAttributes.addFlashAttribute("flashMessage",
                //new EniFlashMessage(EniFlashMessage.TYPE_FLASH_SUCCESS, "Vous êtes déjà connecté(e)"));

        //Après :
        redirectAttributes.addFlashAttribute("flashMessage",
                new EniFlashMessage(type, message));
    }

    /**
     * Version plus courte pour envoyer un fash message succès
     * @param redirectAttributes
     * @param message
     */
    public static void sendSuccessFlashMessage(RedirectAttributes redirectAttributes, String message) {
        sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_SUCCESS, message);

    }
}
