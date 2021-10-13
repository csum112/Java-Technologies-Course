package ro.uaic.info.lab1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class HomeworkServlet extends HttpServlet {
    static Logger logger = LoggerFactory.getLogger(HomeworkServlet.class);
    final static String REPOSITORY_PATH = "/var/tmp/repository";

    private static class RequestParameters {
        public String key;
        public Integer value;
        public Boolean mock;
        public Boolean sync;

        public RequestParameters(Map<String, String[]> params) {
            if (params.containsKey("key"))
                this.key = Arrays.stream(params.get("key")).findFirst().orElse("");
            else
                this.key = "";

            if (params.containsKey("value"))
                this.value = Arrays.stream(params.get("value")).map(Integer::parseInt).findFirst().orElse(0);
            else
                this.value = 0;

            if (params.containsKey("mock"))
                this.mock = Arrays.stream(params.get("mock")).map(Boolean::parseBoolean).findFirst().orElse(false);
            else
                this.mock = false;

            if (params.containsKey("sync"))
                this.sync = Arrays.stream(params.get("sync")).map(Boolean::parseBoolean).findFirst().orElse(false);
            else
                this.sync = false;
        }

        @Override
        public String toString() {
            return "{key=" + key + "; value=" + value + "; mock=" + mock + "sync=" + sync + "}";
        }
    }

    synchronized public static void persistSync(String key, Integer value) throws
            IOException {
        File file = new File(REPOSITORY_PATH);
        final FileWriter writer = new FileWriter(file, true);
        writer.write(key.repeat(value) + Instant.now().toString() + "\n");
        writer.close();
    }

    public static void persistUnstable(String key, Integer value) throws
            IOException {
        File file = new File(REPOSITORY_PATH);
        final FileWriter writer = new FileWriter(file, true);
        writer.write(key.repeat(value) + Instant.now().toString() + "\n");
        writer.close();
    }

    public static String dumpFile() throws IOException {
        File file = new File(REPOSITORY_PATH);
        BufferedReader br = new BufferedReader(new FileReader(file));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null)
            lines.add(line);
        br.close();

        return lines.stream().sorted().collect(Collectors.joining("\n"));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestParameters params = new RequestParameters(request.getParameterMap());
        PrintWriter out = response.getWriter();

        logger.info(request.getMethod() + " " +
                request.getRemoteAddr() + " " +
                request.getHeader("User-Agent") + " " +
                request.getLocale().getLanguage() + " " +
                params
        );

        if (params.mock) {
            response.setContentType("text/plain");
            out.println("This is a confirmation");
            return;
        }

        if (params.sync)
            persistSync(params.key, params.value);
        else
            persistUnstable(params.key, params.value);

        if (request.getHeader("User-Agent").equals("python"))
            response.setContentType("text/plain");
        else
            response.setContentType("text/html");

        out.println(dumpFile());
    }
}
