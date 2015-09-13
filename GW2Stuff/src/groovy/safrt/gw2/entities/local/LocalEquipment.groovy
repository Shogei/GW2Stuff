package groovy.safrt.gw2.entities.local

import groovy.safrt.gw2.entities.authenticated.Equipment

import groovy.transform.Sortable;
class LocalEquipment extends Equipment{
	int count=1
	def accountName
	def charId=0
	
//		def id, count, skin
//	String[] upgrades, infusions

	LocalEquipment(equip){
		super()
		id = equip.id
		slot = equip.slot
		upgrades = equip.upgrades
		infusions = equip.infusions
//		count = equip.count
		
	}
	
	LocalEquipment(String id, String slot, String[] upgrades, String[] infusions){
		id = id;
		slot = slot
		upgrades = upgrades
		infusions = infusions
	}
	
	LocalEquipment(int item_count, Integer in_id){
		id = in_id.toString()
		this.count = item_count
	}
}
