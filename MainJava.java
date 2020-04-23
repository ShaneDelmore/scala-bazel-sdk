import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class MainJava {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Runtime class version is " + System.getProperty("java.version"));

        String classVersion = getClassVersion();
        System.out.println("Binary class version is " + classVersion);
    }

    private static String getClassVersion() throws IOException, InterruptedException {
        String workspace = System.getenv("BUILD_WORKSPACE_DIRECTORY");
        String cmd = System.getProperty("java.home") + "/bin/javap -verbose -classpath " + workspace + "/bazel-bin/MainJava.jar MainJava";

        Process process = Runtime.getRuntime().exec(cmd);

        process.waitFor();
        Stream<String> lines = new BufferedReader(new InputStreamReader(process.getInputStream())).lines();

        return lines.filter(line -> line.contains("major")).findFirst().map(String::trim).orElse("Failed to find class binary version");
    }
}
