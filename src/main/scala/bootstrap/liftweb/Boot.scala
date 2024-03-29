package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import mapper._

import sitemap._
import Loc._
import solitaio.model._
import solitaio.snippet._

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    val vendor = new StandardDBVendor(
        Props.get("db.driver") openOr "org.h2.Driver", 
        Props.get("db.url") openOr "jdbc:h2:solitaio.db;AUTO_SERVER=TRUE",
        Props.get("db.user"), 
        Props.get("db.password"))

    LiftRules.unloadHooks.append(vendor.closeAllConnections_! _)

    DB.defineConnectionManager(DefaultConnectionIdentifier, vendor)
    
    Schemifier.schemify(true, Schemifier.infoF _, Person, Event)
    
    // where to search snippet
    LiftRules.addToPackages("solitaio")

    // Use jQuery 1.4
    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    LiftRules.setSiteMapFunc(() => sitemap())
    
    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent)) 
    
    def sitemap(): SiteMap = SiteMap(
        Menu("Etusivu") / "index", 
		Menu("Solita IOt") / "event" / "list" submenus ( 
				Menu("Lisää tapahtuma") / "event" / "new"
		),
	    Menu("Henkilöt") / "person" / "list" submenus (
	    	Menu("Uusi henkilö") / "person" / "new",
	        Menu.param[Integer]("Henkilö", "id", s => Full(Integer.parseInt(s)), i => i.toString) 
	        	/ "person" / "show" >> Hidden
	    )
	)   
    
  }
}
