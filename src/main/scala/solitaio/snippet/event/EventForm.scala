package solitaio.snippet

import net.liftweb._
import http._
import common._
import util.Helpers._
import scala.xml.NodeSeq

import solitaio.model._

class EventForm extends StatefulSnippet {

	var name = ""

	def dispatch = {case _ => render}
	
	private val from_url = S.referer openOr "/"
	
	def render =
		"name=name" #> SHtml.text(name, name = _) &
		"type=submit" #> SHtml.onSubmitUnit(process)
		
	private def process() = {
	    if (name.length == 0) S.error("name", "Nimi puuttuu");
		
		if (S.errors == Nil) {
			var event = Event.create
			event.name(name)
			event.save
			S.redirectTo(from_url);
		}
	}

}