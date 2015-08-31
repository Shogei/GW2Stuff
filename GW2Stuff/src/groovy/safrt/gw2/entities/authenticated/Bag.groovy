package groovy.safrt.gw2.entities.authenticated

import groovy.safrt.gw2.entities.local.LocalItem
import groovy.safrt.gw2.json.AnonJson

import groovy.transform.Sortable;


class Bag {
	String id
	Integer size
	String[] inventory
	List<BagInventory> inventoryItems =[]


	//	static parseEquipment(bags){
	//		println bags
	//
	//		for(String prop in bags){
	//			//			println prop
	//			String[] param = prop.split("=")
	//			assignParameter(param)
	//		}
	//	}


	Bag(bag){
//		println bag
		for(String prop in bag){
//			println prop
			if (prop.startsWith("inventory")){
				processInventory(prop)
			} else {
				String[] param = prop.split("=")
				assignParameter(param)
			}
		}


	}

	def assignParameter(String[] param){

		String name = param[0]
		switch(name){
			case "id":
				id = param[1]
				break
			case "size":
				size=param[1].toInteger()
				break
			default:
				println "Error assigning equipment to: " + param
		}
	}

	def processInventory(String inventoryString){
//		println inventoryString
		inventoryString = inventoryString.replace("}]","").replace("inventory=[{","")
		//for empty bags
		inventoryString = inventoryString.replace(", null]","").replace("inventory=[","")
		inventoryString = inventoryString.replace(", null","").replace("]]","]")
		inventoryString = inventoryString.replace("null, {", "")
		if (inventoryString.endsWith("}")) {
			inventoryString = inventoryString.substring(0,inventoryString.lastIndexOf("}"))
		}

		inventory = inventoryString.split("\\}, \\{")
		for(String item in inventory){
			if (!item.contains("null")){
				assignInventory(item)
			}
			
		}}

	def assignInventory(String itemString){
//		println itemString
		//		Collection itemAttribs = itemString.collect()
//		
		println itemString
		itemString = itemString.replace("count=","\"count\": ")
		itemString = itemString.replace("id=","\"id\": ")
		itemString = itemString.replace("skin=","\"skin\": ")
		itemString = itemString.replace("upgrades=","\"upgrades\": ")
		itemString = itemString.replace("infusions=","\"infusions\": ")
		itemString = "{" + itemString + "}"
		println itemString
		def bagItem = new AnonJson().parseBagItem(itemString)
		
		//'''{"count": 1, "id": 27325, "upgrades": [24578, 24639]}'''
		BagInventory item = new BagInventory(bagItem)
//		String[] itemArray = itemString.split(", ")
//		BagInventory item = new BagInventory(itemArray);
		inventoryItems.add(item)
	}

}
