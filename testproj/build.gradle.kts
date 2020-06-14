import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun
import org.springframework.boot.gradle.tasks.bundling.BootJar
plugins {
	id("org.springframework.boot") version "2.3.0.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	id("com.ewerk.gradle.plugins.querydsl") version "1.0.10" //querydsl
	id("java") //to make jar
	id("idea") //querydsl

	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
	kotlin("plugin.jpa") version "1.3.72" // jpa
	kotlin("plugin.allopen") version "1.3.72" //allopen for hibernate lazy loading
	kotlin("plugin.noarg") version "1.3.72" //no arg constructor for hibernate lazy loading
}

apply(plugin = "io.spring.dependency-management") //to make jar
allOpen{
	annotation("javax.persistence.Entity") //allopen
}
noArg{
	annotation("javax.persistence.Entity") //no arg constructor
}
group = "com.example.testproj"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
	jcenter()
}
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa") // jpa
	implementation("org.springframework.boot:spring-boot-starter-jdbc")     // jdbc

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("com.querydsl:querydsl-jpa") //querydsl
	implementation("com.querydsl:querydsl-apt") //querydsl

	implementation("au.com.console:kassava:2.1.0-rc.1")
	//kassava for tostring hashcode equals over 2.1 (kotlin) 2.0 -> have to define hashcode

	implementation("mysql:mysql-connector-java") //mysql
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.getByName<BootRun>("bootRun") {
	main = "com.example.testproj.testproj.TestprojApplicationKt"
}

tasks.getByName<BootJar>("bootJar"){ //to make jar
	enabled = true
	mainClassName = "com.example.testproj.testproj.TestprojApplicationKt"
}
