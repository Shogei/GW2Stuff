package groovy.safrt.gw2

import groovy.safrt.gw2.entities.authenticated.Account
import groovy.safrt.gw2.json.AuthenticatedJSON;
import groovy.safrt.gw2.entities.authenticated.Character as myCharacter

class MyStuff {
	Map allToons = [:]
	List<Character> listToons = []

	static main(String[] args) {
		MyStuff myStuff = new MyStuff(args)
	}

	MyStuff(args){
		List<String> tokens =[]
		String fileName = args?args[0]:"./config/tokens.txt"
		//		loadTokens(fileName)
		new File(fileName).eachLine {line -> tokens.add(line)}
		this.getListOfAccounts(tokens)
		getEquipment()
	}



	def getAccounts(tokens){
		AuthenticatedJSON authJson = new AuthenticatedJSON()
		Map accounts = authJson.getAccountsFromGW2(tokens).sort()
		//		 println accounts
		//		 accounts = accounts.sort()
		//		 println accounts
		accounts.each {accountx ->  accountx.getValue().getCharacters().each {charx -> allToons.put(charx.name, charx)}}
		accounts.each {accountx ->  accountx.getValue().getCharacters().each {myCharacter charx -> listToons.add(charx)}}
		allToons = allToons.sort()
//		allToons.each {name, charx ->
//			println "$name:"+charx.yearsOld() + " " + charx.daysToNextBirthday()
//		}
	}
	
	def getListOfAccounts(tokens){
		AuthenticatedJSON authJson = new AuthenticatedJSON()
		List<Account> accounts = authJson.getAccountListFromGW2(tokens).sort()
		//		 println accounts
		//		 accounts = accounts.sort()
		//		 println accounts
//		accounts.each {accountx ->  accountx.getCharacters().each {charx -> allToons.put(charx.name, charx)}}
		accounts.each {accountx ->  accountx.loadCharacters().each {myCharacter charx -> listToons.add(charx)}}
		listToons = listToons.sort()
//		allToons.each {name, charx ->
//			println "$name:"+charx.yearsOld() + " " + charx.daysToNextBirthday()
//		}
	}


	def getListOfBirthdays(){
		//To get a list of all characters and their ages
		listToons.sort{it.daysOld()}
		
		listToons.each {myCharacter charx -> charx.getBirthdayStatement()
		}
	}

	def getUpcomingBirthdays(){
		listToons.sort{it.daysToNextBirthday()}
		
		for(groovy.safrt.gw2.entities.authenticated.Character toon in listToons){
			if (toon.daysToNextBirthday()<30) {
				println toon.name + " is "+toon.yearsOld() + " and has " + toon.daysToNextBirthday() +" days until " + toon.getGenderPossessive()+  " next birthday"
			}
		}
		
	}
	
	def getRecentBirthdays(){
		listToons.sort{it.daysSinceLastBirthday()}
		for(groovy.safrt.gw2.entities.authenticated.Character toon in listToons){
			if (toon.daysSinceLastBirthday()<10 && toon.yearsOld()>=1) println(toon.name +" just turned " + toon.yearsOld() )
		}
	}

	def getBags(){
		List Bags =[]
		listToons.each{myCharacter toon -> Bags.add(toon.koadBags())}
		
	}
	
	def getEquipment(){
		List Equipment=[]
		listToons.each{myCharacter toon -> Equipment.add(toon.getEquipment())}
	}
	
}
