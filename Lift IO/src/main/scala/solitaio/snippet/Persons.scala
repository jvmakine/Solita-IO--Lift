package solitaio.snippet

import net.liftweb._
import util.Helpers._

import solitaio.model._

class Persons {
	def render = 
	  "td *" #> Person.findAll.map(person => person.firstName + " " + person.lastName)
	  
}