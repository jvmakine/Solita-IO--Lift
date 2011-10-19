package solitaio.snippet

import java.util._
import java.text._
import net.liftweb._
import util.Helpers._

object Timer {
	def render = 
		"#current_date" #> (new SimpleDateFormat("dd.MM.yyyy")).format(new Date) &
		"#current_time" #> (new SimpleDateFormat("hh:mm")).format(new Date)
}