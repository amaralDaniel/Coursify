package servlet;

import ejb.AuthEJB;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet("/download")
@MultipartConfig
public class Download extends HttpServlet {
    static final Logger logger = LogManager.getLogger(AuthEJB.class);
    private static final int BYTES_DOWNLOAD = 1024;
    final String path = "/Users/danielamaral/Documents/Universidade/MEI_2ano_1sem/IS/15_16/Coursify/files";

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {


        String relativePath = getServletContext().getRealPath("");

        //String filename = request.getParameter("filename");
        String filename = "text.txt";
        String filePath = relativePath + "/files/" + filename;

        File downloadFile = new File(filePath);
        logger.info("The file was found.");
        FileInputStream inStream = new FileInputStream(downloadFile);


        // obtains ServletContext
        ServletContext context = getServletContext();

        // gets MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            // set to binary type if MIME mappFing not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();
    }
}
