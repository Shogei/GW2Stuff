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
//		loadCharacters << value
		value.accountName=name
		myCharacters <<value
	}

	Account(String id, String name, Integer world,List<String> guilds, String token){
		this.id = id;
		this.name = name;
		this.world=world.toString();
		this.guilds = guilds;
		this.token = token;
		loadCharacters()
	}

	Account(accout, token){
		id:account.id
		name:account.name
		world:account.world
		guilds:account.guilds
		this.token=token
	}

	Account(){
		loadCharacters()
	}

	def loadCharacters(){

		myCharacters = new AuthenticatedJSON().getCharacters(token)
		//		characters=AuthenticatedJSON().getCharacters(token)
		myCharacters.each {charx -> charx.accountName = name}
		return myCharacters.sort()
	}

	def getBankItems(){
	}

	def getBankMaterials(){
	}
}
