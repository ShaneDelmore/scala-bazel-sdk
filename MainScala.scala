import java.io.{BufferedReader, InputStreamReader}

object MainScala {
  def main(args: Array[String]): Unit = {
    println(s"Runtime class version is ${System.getProperty("java.version")}")
    println(s"Binary class version is $getClassVersion")
  }

  def getClassVersion: String = {
    val workspace = System.getenv("BUILD_WORKSPACE_DIRECTORY")
    val cmd = System.getProperty("java.home") + s"/bin/javap -verbose -classpath $workspace/bazel-bin/MainScala.jar Hello"

    val process = Runtime.getRuntime.exec(cmd)
    process.waitFor()

    val lines = new BufferedReader(new InputStreamReader(process.getInputStream)).lines()
    lines.toArray.toSeq.map(_.toString.trim).find(_.contains("major")).getOrElse("Failed to find class version")
  }
}
