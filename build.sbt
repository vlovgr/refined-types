lazy val root = project
  .in(file("."))
  .settings(scalaSettings)
  .settings(publishSettings)
  .settings(dependencySettings)
  .enablePlugins(GhpagesPlugin, TutPlugin)

lazy val scalaSettings = Seq(
  scalaVersion := "2.12.2",
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-unchecked",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfuture"
  )
)

lazy val publishSettings = Seq(
  ghpagesNoJekyll := true,
  makeSite := (makeSite dependsOn tut).value,
  siteSourceDirectory := tutTargetDirectory.value,
  ghpagesPushSite := (ghpagesPushSite dependsOn makeSite).value,
  git.remoteRepo := "git@github.com:vlovgr/refined-types.git",
  mappings in makeSite ++= Seq(
    (resourceDirectory in Compile).value / "try.gif" -> "images/try.gif",
    (resourceDirectory in Compile).value / "ovo.svg" -> "images/ovo.svg",
    (resourceDirectory in Compile).value / "ciris.svg" -> "images/ciris.svg"
  )
)

lazy val cirisVersion = "0.3.2"
lazy val refinedVersion = "0.8.1"
lazy val pureConfigVersion = "0.7.0"

lazy val dependencySettings = Seq(
  libraryDependencies ++= Seq(
    "com.github.pureconfig" %% "pureconfig" % pureConfigVersion,
    "com.lihaoyi" %% "pprint" % "0.5.2",
    "eu.timepit" %% "refined" % refinedVersion,
    "eu.timepit" %% "refined-pureconfig" % refinedVersion,
    "is.cir" %% "ciris-core" % cirisVersion,
    "is.cir" %% "ciris-enumeratum" % cirisVersion,
    "is.cir" %% "ciris-generic" % cirisVersion,
    "is.cir" %% "ciris-refined" % cirisVersion,
    "is.cir" %% "ciris-squants" % cirisVersion
  )
)
