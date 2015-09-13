package groovy.safrt.gw2.entities.local

import groovy.safrt.gw2.entities.Item
import groovy.safrt.gw2.json.AnonJson


class LoadedItem {
	
String itemId
Item itemInfo


List<LocalEquipment> itemList=[]

LoadedItem(String id){
	itemId = id
	//ping anonymous json to get item info
//	itemInfo = new Item(new AnonJson().getItemInfo(id))
}

Item getItemInfo(){
	if(itemInfo==null){
	itemInfo = new Item(new AnonJson().getItemInfo(itemId))
	}
	itemInfo
}
	
Integer getItemCount(){
	Integer count = 0
	itemList.each{ item ->count = count + item.count}
	count
}


}
