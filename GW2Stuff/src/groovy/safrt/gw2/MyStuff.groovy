package groovy.safrt.gw2

import groovy.safrt.gw2.entities.authenticated.Account
import groovy.safrt.gw2.json.AuthenticatedJSON;
import groovy.safrt.gw2.entities.authenticated.Character as myCharacter
import groovy.safrt.gw2.entities.local.LoadedItem
import groovy.safrt.gw2.entities.local.LocalEquipment

class MyStuff {
	Map allToons = [:]
	List<myCharacter> listToons =[]

	static main(String[] args) {
		MyStuff myStuff = new MyStuff(args)
	}

	MyStuff(args){
		List<String> tokens =[]
		String fileName = args?args[0]:"./config/tokens.txt"
		//		loadTokens(fileName)
		new File(fileName).eachLine {line -> tokens.add(line)}
		this.getListOfAccounts(tokens)
		getUpcomingBirthdays()
		getRecentBirthdays()
//		getEquipment()

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
//		accounts.each {accountx ->  accountx.myCharacters.each {charx -> listToons.put(charx.name, charx)}}
		accounts.each {accountx ->  accountx.myCharacters.each {charx -> listToons.add(charx)}}
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
		listToons.each{myCharacter toon -> Bags.add(toon.loadBags())}
		
	}
	
	def getEquipment(){
		List<LocalEquipment> equipment=[]
	
//		listToons.each{myCharacter toon -> Equipment.add(toon.equipmentList)}
		listToons.each{myCharacter toon -> toon.equipmentList.each {LocalEquipment eq -> equipment.add(eq)}}
		listToons.each{toon -> toon.bagList.each {bag ->bag.equipmentItems.each{LocalEquipment beq -> equipment.add(beq)}}}
		equipment = equipment.sort{it.id}
		sortEquipment(equipment)
	}

	def sortEquipment(List<LocalEquipment> equipment){
		//total up the counts
		//eventually add all the bank items
		HashMap<String, LoadedItem> items =[:]
		String itemId = "0"
		equipment.each{thing ->
			if(!thing.id.equals(itemId)){
				itemId = thing.id
				LoadedItem item = new LoadedItem(itemId)
				item.itemList.add(thing)
				items.put(itemId.toString(),item)				
			} else {
				items.get(itemId).itemList.add(thing)
			}
			
		}
//		items.each{k,item -> println item.getItemCount() + " " + item.itemInfo.name + ${item.itemList.each{toonInfo -> " " + toonInfo.charId}}}
		items.each{k, item ->
			print item.getItemCount() + " " + item.itemInfo.name +" " + item.itemId + " : "
			item.itemList.each { toonInfo -> print " " + toonInfo.charId+ "(" + toonInfo.count+ ")"}
			print "\n"
			}
			
			
		}

}
