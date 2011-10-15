package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import mapper._

import net.liftweb.sitemap._
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
        Props.get("db.url") openOr "jdbc:h2:lift_proto.db;AUTO_SERVER=TRUE",
        Props.get("db.user"), 
        Props.get("db.password"))

    LiftRules.unloadHooks.append(vendor.closeAllConnections_! _)

    DB.defineConnectionManager(DefaultConnectionIdentifier, vendor)
    
    Schemifier.schemify(true, Schemifier.infoF _, Person)
    
    // where to search snippet
    LiftRules.addToPackages("solitaio")

    // Use jQuery 1.4
    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    //LiftRules.dispatch.append(solitaio.Controller)
    
    LiftRules.setSiteMapFunc(() => sitemap())
    
    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent)) 
    
    def sitemap(): SiteMap = SiteMap(
        Menu.i("Etusivu") / "index", 
        Menu.i("Henkilöt") / "persons",
        Menu.i("Uusi henkilö") / "newperson",
        Menu.i("Info") / "about"
    )    
    
  }
}