package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessUtils {

    public static void execute(String path, String waitForExpectedMessage) {
        execute(path, waitForExpectedMessage, false);
    }

    public static void execute(String path, boolean waitForCompletion) {
        execute(path, null, waitForCompletion);
    }

    private static void execute(String path, String waitForExpectedMessage, boolean waitForCompletion) {

        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(path);

            if (waitForCompletion) {
                process.waitFor();
            }

            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null
                    && (waitForExpectedMessage != null && !line.contains(waitForExpectedMessage))) {
                stringBuilder.append(line).append("\n");
            }

            stringBuilder.append(line).append("\n");

            // To Debug
            if (line != null) {
                System.out.println("### " + stringBuilder);
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
