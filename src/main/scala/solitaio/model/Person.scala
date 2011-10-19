package solitaio.model

import net.liftweb.mapper._

class Person extends LongKeyedMapper[Person] {
	def getSingleton = Person
	def primaryKeyField = id
	
	object id extends MappedLongIndex(this)
	object firstName extends MappedString(this, 20)
	object lastName extends MappedString(this, 20)	
}

object Person extends Person with LongKeyedMetaMapper[Person] {
}