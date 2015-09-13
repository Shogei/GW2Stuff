package groovy.safrt.gw2.json
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import groovy.safrt.gw2.entities.authenticated.Bag
import groovy.safrt.gw2.entities.authenticated.Equipment


class AnonJson {
//https://api.guildwars2.com/v2/quaggans
	private static jsonStart="https://api.guildwars2.com/v2/"
	
	

	
	
	
		static test() {
		def a = new JsonSlurper().parse(new URL("https://api.guildwars2.com/v2/quaggans"))
	
		a.each{code -> println code}
		println a[0]
		def newUrl = "https://api.guildwars2.com/v2/quaggans/${a[0]}"
		println newUrl
		def b = new JsonSlurper().parse(new URL(newUrl))
		println b.size()
		println ("id: " + b.id)
		println("url: " + b.url)
		}
		
		
		def getItemInfo(String itemId){
			//https://api.guildwars2.com/v2/items/28445
			def jsonString = jsonStart + "items/" + itemId
			def itemInfo = new JsonSlurper().parse(new URL(jsonString))
			itemInfo
			
		}
	
		def parseEquipment(String eqString){
//			println eqString = eqString.replace('=',':')
			def equipment = new JsonSlurper().parseText(eqString)
			
//			println equipment
		}
		
		def parseEquipmentArray(String[] equipmentArray){
			List equipment =[]
			for(eqString in equipmentArray){
				def tempEq = new JsonSlurper(type: JsonParserType.INDEX_OVERLAY).parseText(eqString)
				equipment.add(tempEq)
			}
		}
		
		def parseEquipment(equipmentObject){
//			println equipmentObject
			def equipment = new JsonSlurper(type: JsonParserType.INDEX_OVERLAY).parseText(equipmentObject)
			
			
//			println equipment
		}
		
		def parseBagItem(String test){
//			String test='''{"count": 1, "id": 27325, "upgrades": [24578, 24639]}'''
//			test = test.replace("=",": ")
//			def json = JsonOutput.toJson(test)
			def stuff = new JsonSlurper().parseText(test)
//			println stuff
			stuff

		}
}
