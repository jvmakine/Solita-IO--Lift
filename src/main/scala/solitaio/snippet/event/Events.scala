package solitaio.snippet

import net.liftweb._
import util.Helpers._

import solitaio.model._
import scala.xml._

object Events {
	def render = 
		"#events *" #> Event.findAll.map(event => {
			val org =
			if(event.organizer.isEmpty) {
				var p = Person.create
				p.firstName("NONE")
				p.lastName("NONE")
				p
			} else {
				Person.findByKey(event.organizer.get).get
			}
			<tr><td>{event.name}</td><td>{org.lastName}, {org.firstName}</td></tr>
		})
}
