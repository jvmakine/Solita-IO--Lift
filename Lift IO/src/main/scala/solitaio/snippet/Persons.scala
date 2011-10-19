package solitaio.snippet

import net.liftweb._
import util.Helpers._

import solitaio.model._
import scala.xml._

class Persons {
	def render =
	  "#persontable *" #> Person.findAll.map(person => {
		  val link = <a>Tiedot</a> % Attribute(None, "href", Text("personshow/" + person.id), Null)
		    <tr><td>{person.firstName}</td>
	  		<td>{person.lastName}</td>
	  		<td>{link}</td></tr>
		  }
	  )
}