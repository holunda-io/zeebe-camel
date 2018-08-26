import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
  kotlin("jvm")

  id("io.spring.dependency-management") version Versions.springBootDependencyManagement
}


configure<DependencyManagementExtension> {
  imports {
    mavenBom("org.apache.camel:camel-parent:2.22.0")
    mavenBom("io.zeebe:zb-bom:0.11.0")
  }
}

dependencies {
  compile("org.apache.camel:camel-core")
  compile("io.zeebe:zeebe-client-java")

  compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  compile("org.jetbrains.kotlin:kotlin-reflect")
  //compile("com.fasterxml.jackson.module:jackson-module-kotlin")
  compile ("io.github.microutils:kotlin-logging:1.5.9")

  testCompile("io.zeebe:zeebe-test")
  testCompile("io.zeebe:zeebe-broker-core")
  testCompile("org.apache.camel:camel-test")
  testCompile("org.springframework.boot:spring-boot-starter-test:2.0.4.RELEASE")

//  {
//    exclude(module = "junit")
//  }
//  testImplementation("org.junit.jupiter:junit-jupiter-api")
//  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

  testCompile("com.tngtech.jgiven:jgiven-junit:0.16.0")
  testCompile("com.tngtech.jgiven:jgiven-spring:0.16.0")
  testCompile("com.tngtech.jgiven:jgiven-html5-report:0.16.0")
  testCompile("org.awaitility:awaitility:3.1.0")
}


repositories {
  maven("https://oss.sonatype.org/content/repositories/snapshots")
}


//val test by tasks.getting(Test::class) {
//  useJUnitPlatform()
//}
