package fr.eni.encheres.messages;

import java.util.ResourceBundle;

//Cette classe permet de le lire le contenu du fichier messages_erreurs.properties

public class MessagesReader {
	
private static ResourceBundle rb;
	
	static
	{
		try
		{
			rb = ResourceBundle.getBundle("fr.eni.encheres.messages.error_messages");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public static  String getErrorMessage (int code)
	{
		String message="";
		
		try
		{
			if(rb!=null)
			{
				message = rb.getString(String.valueOf(code));
			}
			else
			{
				message="Problème à la lecture du fichier contenant les messages";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			message="Une erreur inconnue est survenue";
		}
		return message;
	}
	
}
