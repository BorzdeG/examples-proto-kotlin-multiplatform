import com.google.protobuf.gradle.GenerateProtoTask
import com.google.protobuf.gradle.ProtobufConvention
import com.google.protobuf.gradle.ProtobufPlugin
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.proto
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import org.gradle.language.base.plugins.LifecycleBasePlugin.BUILD_TASK_NAME

buildscript {
  dependencies {
    classpath("com.google.protobuf:protobuf-gradle-plugin:+")
  }
}
//9J+ZrvNW
apply {
  plugin<JavaPlugin>()
  plugin<ProtobufPlugin>()
}

dependencies {
  "compile"("com.google.protobuf:protobuf-java")
  "compile"("io.grpc:grpc-stub")
  "compile"("io.grpc:grpc-protobuf")

  if (JavaVersion.current().isJava9Compatible) {
    "implementation"("javax.annotation:javax.annotation-api:+")
  }
}

configure<ProtobufConvention> {
  protobuf {
    protoc {
      artifact = "com.google.protobuf:protoc"
    }
  }
}

configure<JavaPluginConvention> {
  sourceSets {
    this["main"].java.srcDir(the<ProtobufConvention>().protobuf.generatedFilesBaseDir + "/main/java")
  }
}

tasks[BUILD_TASK_NAME].dependsOn(tasks.withType(GenerateProtoTask::class))
