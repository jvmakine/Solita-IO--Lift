package solitaio.model

import net.liftweb.mapper._

class Event extends LongKeyedMapper[Event] {
	def getSingleton = Event
	def primaryKeyField = id

	object id extends MappedLongIndex(this)
	
	object name extends MappedString(this, 20)

}

object Event extends Event with LongKeyedMetaMapper[Event] {
}