package fr.eni.encheres;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {

private List<Integer> errorCodesList;
	

	//---------------Constructeur

	public BusinessException() {
		super();
		this.errorCodesList = new ArrayList<>();
	}
	

	//------- Méthode d'ajout d'une erreur à la liste d'erreur - le code doit avoir un message associé dans le fichier .properties
	
	public void addError(int code)
	{
		if(!this.errorCodesList.contains(code))
		{
			this.errorCodesList.add(code);
		}
	}
	
	
	//-------- Méthode retournant un boolean, vrai si la liste contient des erreurs
	
	public boolean hasErrors()
	{
		return this.errorCodesList.size()>0;
	}
	
	
	//--------- Méthode retournant la liste des codes d'erreurs  
	
	public List<Integer> getErrorCodesList()
	{
		return this.errorCodesList;
	}
	
	
}
