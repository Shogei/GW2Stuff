package groovy.safrt.gw2.entities.local

import groovy.safrt.gw2.entities.authenticated.Bag
import groovy.transform.Sortable;
@Sortable
class LocalBag extends Bag{
	int count
	String accountId
	String charId=0
	
}
