import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin

buildscript {
  val kotlinVersion = extra["kotlin.version"] as String

  repositories {
    jcenter()
  }
  dependencies {
    classpath(kotlin("gradle-plugin", kotlinVersion))
  }
}

tasks.withType(Wrapper::class) {
  distributionType = Wrapper.DistributionType.ALL
  gradleVersion = "5.0"
}

allprojects {
  buildscript {
    repositories {
      jcenter()
    }
  }
  repositories {
    jcenter()
  }

  val kotlinVersion = extra["kotlin.version"] as String
  val protobufVersion = extra["protobuf.version"] as String
  val grpcVersion = extra["grpc.version"] as String

  configurations.all {
    resolutionStrategy {
      failOnVersionConflict()

      eachDependency {
        when (requested.group) {
          "com.google.protobuf"  -> useVersion(protobufVersion)
          "io.grpc"              -> useVersion(grpcVersion)

          "org.jetbrains.kotlin" -> useVersion(kotlinVersion)

          "org.slf4j"            -> useVersion("1.7.25")
          "ch.qos.logback"       -> useVersion("1.2.3")
        }
      }
    }
  }
}
