import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin

apply {
  plugin<KotlinPlatformJvmPlugin>()
}

dependencies {
  "compile"(project(":proto2"))

  "compile"(kotlin("stdlib-jdk8"))
}
