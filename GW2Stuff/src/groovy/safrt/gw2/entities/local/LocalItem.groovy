package groovy.safrt.gw2.entities.local
import groovy.transform.Sortable;
import groovy.safrt.gw2.entities.Item

@Sortable
class LocalItem extends Item{

	Integer count
	String accountId
	String charId=0

	LocalItem(paramArray){
		for(String param in paramArray){
			//			println prop
			String[] paramDetail = param.split("=")
			assignParameter(paramDetail)
		}
	}


	def assignParameter(String[] param){

		String name = param[0]
		switch(name){
			case "count":
				count = param[1].toInteger()
			case "id":
				this.id = param[1]
		}
	}
}
