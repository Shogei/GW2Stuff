package groovy.safrt.gw2.entities.authenticated

import groovy.safrt.gw2.entities.Guild
import groovy.transform.Sortable
import groovy.safrt.gw2.json.AuthenticatedJSON
import groovy.safrt.gw2.entities.authenticated.Character as myCharacter

@Sortable(includes=['name', 'world'])
class Account {

	String name, world, id, token
	List<String> guilds
	List<myCharacter> myCharacters =[]
	
	def addCharacter(myCharacter value) {
		loadCharacters << value
	}
	
	Account(){
		loadCharacters()
	}
	
	def loadCharacters(){
		
		myCharacters = new AuthenticatedJSON().getCharacters(token)
//		characters=AuthenticatedJSON().getCharacters(token)
		
		return myCharacters.sort()
	}
	
	def getBankItems(){
		
	}
	
	def getBankMaterials(){
		
	}
	
}
