package solitaio.snippet

import net.liftweb._
import util.Helpers._

import solitaio.model._

class Persons {
	def render = 
	  "table *" #> Person.findAll.map(person => <tr><td>{person.firstName}</td><td>{person.lastName}</td></tr>)
	  
}