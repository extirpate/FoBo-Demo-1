name := "Pimping-Lift-Advanced-Bs3"

organization := "net.liftweb"

version := "0.2.3-SNAPSHOT"

liftVersion := "3.0-SNAPSHOT" //"2.6.2" 

liftEdition <<= liftVersion apply { _.substring(0,3) }

scalaVersion := "2.11.2"

crossScalaVersions := Seq("2.11.2") 

resolvers ++= Seq("snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
                  "staging"       at "https://oss.sonatype.org/content/repositories/staging",
                  "releases"      at "https://oss.sonatype.org/content/repositories/releases"
                 )

seq(webSettings :_*)

unmanagedResourceDirectories in Test <+= (baseDirectory) { _ / "src/main/webapp" }

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies <++= (liftVersion,liftEdition,version) { (v,e,mv) =>
    "net.liftweb"     %% "lift-webkit"            % v    % "compile" ::
    "net.liftweb"     %% "lift-mapper"            % v    % "compile" ::
    "net.liftmodules" %% ("fobo"+"_"+e)          % "1.4-SNAPSHOT" % "compile" ::
    Nil
}

libraryDependencies ++= Seq(
    "org.eclipse.jetty"       % "jetty-webapp"            % "8.1.7.v20120910"     % "container,test",
    "org.eclipse.jetty.orbit" % "javax.servlet"           % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "ch.qos.logback"          % "logback-classic"         % "1.0.6",
    "com.h2database"          % "h2"                      % "1.3.167",
    "com.andersen-gott"       %% "scravatar"              % "1.0.3"
  )
  
libraryDependencies <++= scalaVersion { sv => 
  (sv match {
      case "2.9.2" | "2.9.1" | "2.9.1-1" => "org.specs2" %% "specs2" % "1.12.3" % "test"
      case "2.10.4" => "org.specs2" %% "specs2" % "1.13" % "test"
      case _ => "org.specs2" %% "specs2" % "2.3.11" % "test"
 }) ::
    (sv match {
      case "2.10.4" | "2.9.2" | "2.9.1" | "2.9.1-1" => "org.scalacheck" %% "scalacheck" % "1.10.0" % "test"
      case _ => "org.scalacheck" %% "scalacheck" % "1.11.4" % "test"
      }) ::
  Nil
}
