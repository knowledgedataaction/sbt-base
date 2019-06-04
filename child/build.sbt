name := "child"

// docs for why this is necessary are here: https://github.com/scalatra/sbt-scalatra
enablePlugins( JavaAppPackaging )

// Enable these if you are using Scalatra
// enablePlugins( JettyPlugin )
// enablePlugins( ScalatraPlugin )

// ignore tests when building fat-jars
test in assembly := {}

// this section handles conflicts when building the "fat jar"
// these configurations are necessary in order for  all the drools internal framework wiring to provide all the correct components
assemblyMergeStrategy in assembly := {
    case PathList( "META-INF", "MANIFEST.MF" ) => MergeStrategy.discard
    case PathList( "reference.conf" ) => MergeStrategy.concat
    case PathList( "META-INF", "kie.conf" ) => MergeStrategy.concat
    case PathList( "META-INF", "sisu", "javax.inject.Named" ) => MergeStrategy.concat
    case PathList( "META-INF", "plexus", "components.xml" ) => MergeStrategy.first
    //    case PathList( "META-INF", xs@_* ) => MergeStrategy.first
    case x => MergeStrategy.last
}