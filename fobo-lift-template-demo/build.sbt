organization := "se.media4u101"

name := "FoBo-Lift-Template-Demo"

version := "2.8.1-SNAPSHOT"

crossScalaVersions := Seq("2.10.4", "2.9.2", "2.9.1-1", "2.9.1")

scalaVersion := "2.10.4"

seq(webSettings :_*)

scalacOptions ++= Seq("-deprecation", "-unchecked")

scanDirectories in Compile := Nil

logLevel := Level.Info

resolvers ++= Seq("snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
                  "staging"       at "https://oss.sonatype.org/content/repositories/staging",
                  "releases"      at "https://oss.sonatype.org/content/repositories/releases"
                 )

EclipseKeys.withSource := true

transitiveClassifiers := Seq("sources")

libraryDependencies <++= (liftVersion,liftEdition,version) { (v,e,mv) =>
    "net.liftweb"     %% "lift-webkit"            % v    % "compile" ::
    "net.liftweb"     %% "lift-mapper"            % v    % "compile" ::
    "net.liftweb"     %% "lift-squeryl-record"    % v    % "compile" ::
    "net.liftweb"     %% "lift-testkit"           % v    % "compile" ::
    "net.liftmodules" %% ("fobo"+"_"+e)           % "1.3" % "compile" ::
    Nil
}

// Customize any further dependencies as desired
libraryDependencies ++= { 
  "ch.qos.logback" % "logback-classic" % "1.0.0" % "provided" ::
  "log4j" % "log4j" % "1.2.16" % "provided" ::
  "org.eclipse.jetty"        % "jetty-webapp"  % "8.0.3.v20111011" % "container" ::
  "org.eclipse.jetty"        % "jetty-plus"    % "8.0.3.v20111011" % "container" :: 
  "commons-lang"             % "commons-lang"  % "2.0"             % "compile->default" ::
  "com.jolbox"               % "bonecp"        % "0.7.1.RELEASE"   % "compile->default" ::
  "com.h2database"           % "h2"            % "1.3.167" ::  
  Nil
}

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


