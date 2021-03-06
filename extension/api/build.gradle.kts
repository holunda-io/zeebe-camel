import org.gradle.api.tasks.bundling.Jar

plugins {
  kotlin("jvm")
  `maven-publish`
}

dependencies {
  compile("org.apache.camel:camel-core:2.22.0")
  testCompile("junit:junit:4.12")
  testCompile("org.assertj:assertj-core:3.10.0")
  testCompile("org.slf4j:slf4j-simple:1.7.25")
}


val sourcesJar by tasks.registering(Jar::class) {
  classifier = "sources"
  from(sourceSets["main"].allSource)
}
publishing {
  repositories {
    maven {
      // change to point to your repo, e.g. http://my.org/repo
      url = uri("$buildDir/repo")
    }
  }
  publications {
    register("mavenJava", MavenPublication::class.java) {
      from(components["java"])
      artifact(sourcesJar.get())
    }
  }
}
