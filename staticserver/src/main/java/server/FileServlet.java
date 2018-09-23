package server;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileServlet extends HttpServlet {

    public static final String JAR_FILE_NAME = "server.jar";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = new File(this.getClass().getResource("/").getPath(), JAR_FILE_NAME);
        response.setHeader("Content-Disposition", "filename=\"" + file.getName() + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
    }
}