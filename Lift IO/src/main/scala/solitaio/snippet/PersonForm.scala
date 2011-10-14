package solitaio.snippet

import net.liftweb._
import http._
import common._
import util.Helpers._
import scala.xml.NodeSeq

import solitaio.model._

class PersonForm extends StatefulSnippet {
	private var firstname = ""
	private var lastname = ""
	  
	private val from_url = S.referer openOr "/"
	
	def dispatch = {case _ => render}
	
	def render =
		"name=firstname" #> SHtml.text(firstname, firstname = _) &
		"name=lastname" #> SHtml.text(lastname, lastname = _) &
		"type=submit" #> SHtml.onSubmitUnit(process)
			
	private def process() = {
	  	if (firstname.length == 0) S.error("firstname", "Etunimi puuttuu");
		if (lastname.length == 0) S.error("lastname", "Sukunimi puuttuu");
		if(S.errors == Nil) { save(); S.redirectTo(from_url); }
	}
		
	private def save() = {
	  val person = Person.create
	  person.firstName(firstname);
	  person.lastName(lastname);
	  person.save
	}

}