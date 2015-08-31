package groovy.safrt.gw2.entities.authenticated

class BagInventory {
	def id, count, skin
	String[] upgrades, infusions
	
	BagInventory(String[] paramArray){
		for(String param in paramArray){
			//			println prop
			String[] paramDetail = param.split("=")
			assignParameter(paramDetail)
		}
	}
	
	BagInventory(jsonObject){
		for (String param in jsonObject)
		{
			String[] paramDetail = param.split("=")
			assignParameter(paramDetail)
		}
	}
	
	
	def assignParameter(String[] param){
		
				String name = param[0]
				switch(name){
					case "count":
						count = param[1].toInteger()
						break
					case "id":
						this.id = param[1]
						break
					case "skin":
						this.skin = param[1]
						break
					case "upgrades":
						assignUpgrades(param[1])
						break
					case "infusions":
						assignInfusions(param[1])
						break
					case null:
					case "null":
					break
					default: println "Difficulty assigning parameters to BagInventory"
				}
			}
	
	def assignUpgrades(String upgradeString){
		//		println upgradeString
				upgradeString = upgradeString.replace("[","").replace("]","")
				upgrades = upgradeString.split(", ")
				
			}
			
			def assignInfusions(String infusionString){
		//		println infusionString
				infusionString = infusionString.replace("[","").replace("]","")
				infusions = infusionString.split(", ")
			}
	
}
