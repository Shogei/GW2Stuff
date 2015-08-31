package groovy.safrt.gw2.json

import groovy.json.JsonSlurper
import groovy.safrt.gw2.entities.authenticated.Account
import groovy.safrt.gw2.entities.authenticated.Character as myCharacter


public class AuthenticatedJSON {

	private static jsonStart="https://api.guildwars2.com/v2/"
	//	List<String> tokens =[]


	static Map getAccountsFromGW2(List<String> tokens) {
		//		 def id, name, world
		//		 def guilds
		def accounts =[:]
		tokens.each {token ->
			def account = new JsonSlurper().parse(new URL(jsonStart + "account?access_token="+ token))
			accounts.put(account.name, new Account(id:account.id, name:account.name, world:account.world,guilds:account.guilds, token:token))
			//			println account.name
		}
		accounts
	}

	static List<Account> getAccountListFromGW2(List<String> tokens) {
		//		 def id, name, world
		//		 def guilds
		List accountList=[]
		tokens.each {token ->
			def account = new JsonSlurper().parse(new URL(jsonStart + "account?access_token="+ token))
			accountList.add(new Account(id:account.id, name:account.name, world:account.world,guilds:account.guilds, token:token))
			//			println account.name
		}
		accountList
	}

	static List<myCharacter> getCharacters(Account account){
		def jsonString = jsonStart + "characters?access_token=" + account.token
		List characters = new JsonSlurper().parse(new URL(jsonStart + "characters?access_token="+account.token))
		//			println characters
		characters.each{charx -> account.loadCharacters.add(getCharacter(charx,account.token, account.name))}
		account.loadCharacters
	}

	static List<myCharacter> getCharacters(String accountToken){
		def jsonString = jsonStart + "characters?access_token=" + accountToken
		List characters = new JsonSlurper().parse(new URL(jsonStart + "characters?access_token="+accountToken))
		//			println characters
		List<myCharacter> charList =[]
		characters.each{charx -> charList.add(getCharacter(charx.toString(),accountToken))}
		charList
	}

	static Object getCharacter(String charx, String accountToken){
		def jsonString = jsonStart+"characters/" + java.net.URLEncoder.encode(charx,"US-ASCII")
				.replace("+","%20")+"?access_token="+accountToken
		
		def fullChar = new JsonSlurper().parse(new URL(jsonStart+"characters/" + java.net.URLEncoder.encode(charx,"US-ASCII")
				.replace("+","%20")+"?access_token="+accountToken))
		new myCharacter(fullChar, accountToken)

	}

	//
	//	static Object getCharacter(String charx, String accountToken, String accountName){
	//		//		println new URL(jsonStart+"characters/" + charx)
	//		//		println jsonStart+"characters/" + java.net.URLEncoder.encode(charx,"US-ASCII").replace("+","%20")+"?access_token="+token
	//		def jsonString = jsonStart+"characters/" + java.net.URLEncoder.encode(charx,"US-ASCII").replace("+","%20")+"?access_token="+accountToken
	//		def fullChar = new JsonSlurper().parse(new URL(jsonStart+"characters/" + java.net.URLEncoder.encode(charx,"US-ASCII").replace("+","%20")+"?access_token="+accountToken))
	//		new myCharacter(fullChar, accountName)
	//
	//	}

	static  getEquipment(String charx, String accountToken){
		def jsonString = jsonStart+"characters/" + java.net.URLEncoder.encode(charx,"US-ASCII")
				.replace("+","%20")+"/equipment?access_token="+accountToken
		def fullEq = new JsonSlurper().parse(new URL(jsonStart+"characters/" + java.net.URLEncoder.encode(charx,"US-ASCII")
				.replace("+","%20")+"/equipment?access_token="+accountToken))
		//new myCharacter(fullChar, accountToken)
		println fullEq
		fullEq
	}


}
