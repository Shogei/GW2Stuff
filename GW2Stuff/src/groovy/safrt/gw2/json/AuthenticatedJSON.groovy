package groovy.safrt.gw2.json

import groovy.json.JsonSlurper
import groovy.safrt.gw2.entities.authenticated.Account

public class AuthenticatedJSON {

	
	def accounts =[:]

	def jsonStart="https://api.guildwars2.com/v2/"


	AuthenticatedJSON(){
		List tokens =[]
		def file = new File ("./config/tokens.txt")
		file.eachLine {line -> tokens.add(line)}

		this.getAccountsFromGW2(tokens)
		
		accounts.each {account -> getCharacters(account.value)}
	}

	private getAccountsFromGW2(List tokens) {
		//		 def id, name, world
		//		 def guilds
		tokens.each {token ->
			def account = new JsonSlurper().parse(new URL(jsonStart + "account?access_token="+ token))
			accounts.putAt(account.name, new Account(id:account.id, name:account.name, world:account.world,guilds:account.guilds, token:token))
			println account.name
		}
	}
	
	private getCharacters(Account account){
		def jsonString = jsonStart + "characters?access_token=" + account.token
		List characters = new JsonSlurper().parse(new URL(jsonStart + "characters?access_token="+account.token))
			println characters
			characters.each{charx -> account.characters.add(getCharacter(charx,account.token))}
	}
	
	def getCharacter(String charx, token){
//		println new URL(jsonStart+"characters/" + charx)
//		println jsonStart+"characters/" + java.net.URLEncoder.encode(charx,"US-ASCII").replace("+","%20")+"?access_token="+token
		def fullChar = new JsonSlurper().parse(new URL(jsonStart+"characters/" + java.net.URLEncoder.encode(charx,"US-ASCII").replace("+","%20")+"?access_token="+token))
		new groovy.safrt.gw2.entities.authenticated.Character(fullChar)
		
	}
	
	
	
	
}
