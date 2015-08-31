package groovy.safrt.gw2.utility



public enum Birthday {

//	firstYear('one',365),
//	secondYear('two',730),
//	thirdYear('three',1095),
//	fourthYear('four',1361),
//	fifthYear('five',1726),
//	sixthYear('six',2191),
//	seventhYear('seven',2556),
//	eigthYear('eight',2922),
//	ninthYear('nine',3287),
//	tenthYear('ten',3652),
//	eleventhYear('eleven',4017),
//	twelfthYear('twelve',4383),
//	thirteenthYear('thirteen',4748),
//	fourteethYear('fourteen',5113),
//	fifteenthYear('fifteen',5478),
//	sixteenthYear('sixteen',5844),
//	seventeethYear('seventeen',6209),
//	eighteenthYear('eighteen',6574),
//	nineteenthYear('nineteen',6939)
	
	zero(0),
	one(365),
	two(730),
	three(1095),
	four(1461),
	five(1826),
	six(2191),
	seven(2556),
	eight(2922),
	nine(3287),
	ten(3652),
	eleven(4017),
	twelve(4383),
	thirteen(4748),
	fourteen(5113),
	fifteen(5478),
	sixteen(5844),
	seventeen(6209),
	eighteen(6574),
	nineteen(6939)

	//	static final Map map
	private final Integer value

	static getYearsOld(age){
		def result
		switch(age){
			case Birthday.zero.value()..<Birthday.one.value():
				result=Birthday.zero
				break
			case Birthday.one.value()..<Birthday.two.value():
				result=Birthday.one
				break
			case Birthday.two.value()..<Birthday.three.value():
				result=Birthday.two
				break
			case Birthday.three.value()..<Birthday.four.value():
				result=Birthday.three
				break
			case Birthday.four.value()..<Birthday.five.value():
				result=Birthday.four
				break
			case Birthday.five.value()..<Birthday.six.value():
				result=Birthday.five
				break
			case Birthday.six.value()..<Birthday.seven.value():
				result=Birthday.six
				break
			case Birthday.seven.value()..<Birthday.eight.value():
				result=Birthday.seven
				break
			case Birthday.eight.value()..<Birthday.nine.value():
				result=Birthday.eight
				break
			case Birthday.nine.value()..<Birthday.ten.value():
				result=Birthday.nine
				break
			case Birthday.ten.value()..<Birthday.eleven.value():
				result=Birthday.ten
				break
			case Birthday.eleven.value()..<Birthday.twelve.value():
				result=Birthday.eleven
				break
			default:
			result = Birthday.twelve
			break
		}
		result
	}

	static getNextBirthday(bday){
		def result
		switch(bday){
			case Birthday.zero:
				result =Birthday.one
				break
			case Birthday.one:
				result =Birthday.two
				break
			case Birthday.two:
				result =Birthday.three
				break
			case Birthday.three:
				result =Birthday.four
				break
			case Birthday.four:
				result =Birthday.five
				break
			case Birthday.five:
				result =Birthday.six
				break
			case Birthday.six:
				result =Birthday.seven
				break
			case Birthday.seven:
				result =Birthday.eight
				break
			case Birthday.eight:
				result =Birthday.nine
				break
			case Birthday.nine:
				result =Birthday.ten
				break
			case Birthday.ten:
				result =Birthday.eleven
				break
			default:
				result=Birthday.twelve
				break
		}
		result
	}
	
	static getDaysToNextBirthday(age){
		def current = getYearsOld(age)
		def nextBday = getNextBirthday(current)
		nextBday.value - age
	}

//	public getNextBirthday(age){
//	}

	private Birthday(Integer value){
		this.value = value
	}
	
	
	public int value() { return value}
}
