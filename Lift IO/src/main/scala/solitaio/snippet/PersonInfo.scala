package solitaio.snippet

import net.liftweb._
import util.Helpers._
import http._
import common._

import solitaio.model._

class PersonInfo(param : Integer) {
	def render = {
	  val person = Person.findByKey(param.toLong);
	  if(person.isEmpty) {
	    S.error("person","Henkilöä ei löydy")
	    "#firstname *" #> "" & "#lastname *" #> ""
	  } else {
	    "#firstname *" #> person.get.firstName & "#lastname *" #> person.get.lastName
	  }
	}
	  
}