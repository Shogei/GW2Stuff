package groovy.safrt.gw2.entities

import groovy.safrt.gw2.entities.local.LocalEquipment

class Item {
	def id, name, icon,description,type, rarity,level
	def vendor_value,default_skin,flags,game_types,restrictions,details
	
	
	Item(itemInfo){
		id = itemInfo.id
		name = itemInfo.name
		icon = itemInfo.icon
		description = itemInfo.description
		type = itemInfo.type
		rarity = itemInfo.rarity
		level = itemInfo.level
		vendor_value = itemInfo.vendor_value
		default_skin = itemInfo.default_skin
		flags = itemInfo.flags
		game_types = itemInfo.game_types
		restrictions = itemInfo.restrictions
		details = itemInfo.details
	}
}
