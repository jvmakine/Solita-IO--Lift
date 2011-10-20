package solitaio.snippet

import net.liftweb._
import http._
import common._
import util.Helpers._
import scala.xml._

//import java.awt._
import solitaio.model._

class EventForm extends StatefulSnippet {

	var name = ""
	var person: Person = null

	def dispatch = {case _ => render}
	
	private val from_url = S.referer openOr "/"
	
	def render = {
		val persons = Person.findAll.map(p => Pair(p, p.lastName.toString + ", " + p.firstName))
		"name=name" #> SHtml.text(name, name = _) &
		"name=organizer" #> SHtml.selectObj[Person](persons, Full(person), person = _) &
		"type=submit" #> SHtml.onSubmitUnit(process)
	}	
	private def process() = {
	    if (name.length == 0) S.error("name", "Nimi puuttuu");
		
		if (S.errors == Nil) {
			var event = Event.create
			event.name(name)
			event.organizer(person)
			event.save
			S.redirectTo(from_url);
		}
	}

}