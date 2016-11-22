package servlet;

import dto.CourseDTO;
import dto.MaterialDTO;
import ejb.CourseEJBRemote;
import ejb.MaterialEJBRemote;
import filter.Session;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import utils.Utils;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/material")
@MultipartConfig
public class Material extends HttpServlet {
    @EJB
    MaterialEJBRemote materialEJB;

    private final Logger logger = Logger.getLogger(Material.class);

    private static final int BYTES_DOWNLOAD = 1024;
    final String path = "/Users/tomasfrancisco/Desktop";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseId = req.getParameter("courseId");
        String delete = req.getParameter("delete");
        String download = req.getParameter("download");
        String materialId = req.getParameter("id");

        if(delete != null && delete.equals("true")) {
            materialEJB.deleteMaterial(materialId);
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        if(download != null && download.equals("true")) {
            MaterialDTO material = materialEJB.getMaterial(materialId);

            //String filename = request.getParameter("filename");
            String filename = material.getFilename();
            String filePath = path + "/" + filename + "." + material.getType();

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
            resp.setContentType(mimeType);
            resp.setContentLength((int) downloadFile.length());

            // forces download
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            resp.setHeader(headerKey, headerValue);

            // obtains response's output stream
            OutputStream outStream = resp.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            inStream.close();
            outStream.close();
            resp.sendRedirect(req.getContextPath() + "/");
        }

        ArrayList<MaterialDTO> materials = materialEJB.getMaterials(courseId);

        req.setAttribute("materials", materials);

        req.getRequestDispatcher("/course-response.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String update = req.getParameter("update");

        String create = req.getParameter("create");

        String materialId = req.getParameter("id");
        String filename = req.getParameter("filename");
        String professorId = req.getParameter("professorId");
        String courseId = req.getParameter("courseId");
        String type = req.getParameter("type");

        if(create != null && create.equals("true")) {
            materialEJB.createMaterial(filename, courseId, professorId, type);

            // Create path components to save the file
            final Part filePart = req.getPart("file");
            final String fileName = filename + "." + type;

            OutputStream out = null;
            InputStream filecontent = null;
            final PrintWriter writer = resp.getWriter();

            try {
                out = new FileOutputStream(new File(path + File.separator
                        + fileName));
                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                logger.info("New file " + fileName + " created at " + path);
                logger.log(Level.INFO, "File being uploaded to ");
            } catch (FileNotFoundException fne) {
                logger.error("You either did not specify a file to upload or are "
                        + "trying to upload a file to a protected or nonexistent "
                        + "location. : " + fne.getMessage());


            } finally {
                resp.sendRedirect(req.getContextPath() + "/");
            }


        } else if(update != null && update.equals("true")) {
            materialEJB.updateMaterial(createMaterialDTO(materialId, filename));
        }

        resp.sendRedirect(req.getContextPath() + "/");
        return;
    }

    private MaterialDTO createMaterialDTO(String materialId, String filename) {
        MaterialDTO materialDTO = new MaterialDTO();

        materialDTO.setMaterialId(materialId);
        materialDTO.setFilename(filename);

        return materialDTO;
    }
}
