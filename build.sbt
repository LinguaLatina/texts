
resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith", "maven")

libraryDependencies ++= Seq(
  //"com.github.pathikrit" %% "better-files" % "3.5.0",
  "org.wvlet.airframe" %% "airframe-log" % "19.8.10",

  "org.scala-lang.modules" %% "scala-xml" % "1.0.6",

  "edu.holycross.shot.cite" %% "xcite" % "4.3.0",
  "edu.holycross.shot" %% "ohco2" % "10.20.4",

  "edu.holycross.shot" %% "midvalidator" % "13.3.1",
  "edu.holycross.shot.mid" %% "orthography" % "2.1.0",


  "edu.holycross.shot" %% "latphone" % "3.0.0",
  "edu.holycross.shot" %% "latincorpus" % "2.2.2",
  "edu.holycross.shot" %% "tabulae" % "6.0.1",

  "edu.holycross.shot" %% "histoutils" % "2.3.0"
)
