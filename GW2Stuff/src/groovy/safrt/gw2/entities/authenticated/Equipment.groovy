package groovy.safrt.gw2.entities.authenticated

import groovy.safrt.gw2.json.AnonJson

class Equipment {
	String id, slot, skin
	String[] upgrades, infusions

	Equipment(equipment){

		for(String prop in equipment){
//			println prop
			String[] param = prop.split("=")
			assignParameter(param)
		}


	}

	def assignParameter(String[] param){

		String name = param[0]
		switch(name){
			case "id":
				id = param[1]
				break
			case "slot":
				slot=param[1]
				break
			case "skin":
				skin = param[1]
				break
			case "upgrades":
				assignUpgrades(param[1])
				break
			case "infusions":
				assignInfusions(param[1])
				break
			default:
				println "Error assigning equipment to: " + param
		}
	}


	def assignUpgrades(String upgradeString){
//		println upgradeString
		upgradeString = upgradeString.replace("[","").replace("]","")
		upgrades = upgradeString.split(",")
		
	}
	
	def assignInfusions(String infusionString){
//		println infusionString
		infusionString = infusionString.replace("[","").replace("]","")
		infusions = infusionString.split(",")
	}
}
