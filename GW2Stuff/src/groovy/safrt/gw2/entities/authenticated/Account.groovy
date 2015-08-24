package groovy.safrt.gw2.entities.authenticated

import groovy.safrt.gw2.entities.Guild

class Account {

	def id, name, world, token
	def guilds
	List<Character> characters =[]
	def addCharacter(value) {
		characters << value
	}
	
}
