package groovy.safrt.gw2.json
import groovy.json.JsonSlurper
import groovy.json.JsonOutput


class AnonJson {
//https://api.guildwars2.com/v2/quaggans
	
	
	

	
	
	
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
		
}
