package solitaio.snippet

import net.liftweb._
import util.Helpers._

import solitaio.model._
import scala.xml._

object Events {
	def render = 
		"#events *" #> Event.findAll.map(event => {
			<tr><td>{event.name}</td></tr>
		})
}
