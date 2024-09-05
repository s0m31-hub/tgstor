plugins {
	java
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "org.nwolfhub"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
	maven(url="https://mvn.mchv.eu/repository/mchv/")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation (platform("it.tdlight:tdlight-java-bom:3.4.0+td.1.8.26"))
	implementation (group="it.tdlight", name="tdlight-java")
	implementation (group="it.tdlight", name="tdlight-natives", classifier="windows_amd64")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
