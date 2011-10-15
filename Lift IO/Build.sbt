name := "Lift IO"

version := "0.1"

scalaVersion := "2.9.1"

seq(webSettings :_*)

libraryDependencies ++= Seq(
	"net.liftweb" %% "lift-webkit" % "2.4-M4" % "compile",
	"net.liftweb" %% "lift-mapper" % "2.4-M4" % "compile",
	"org.mortbay.jetty" % "jetty" % "6.1.26" % "test",
	"junit" % "junit" % "4.8" % "test",
	"ch.qos.logback" % "logback-classic" % "0.9.26",
	"org.scala-tools.testing" %% "specs" % "1.6.9" % "test",
	"com.h2database" % "h2" % "1.2.147",
	"org.mortbay.jetty" % "jetty" % "6.1.26" % "container"
)
