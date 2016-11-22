package servlet;

import dto.CourseDTO;
import dto.MaterialDTO;
import ejb.CourseEJBRemote;
import ejb.MaterialEJBRemote;
import utils.Utils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/material")
public class Material extends HttpServlet {
    @EJB
    MaterialEJBRemote materialEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseId = req.getParameter("courseId");
        String delete = req.getParameter("delete");
        String materialId = req.getParameter("id");

        if(delete != null && delete.equals("true")) {
            materialEJB.deleteMaterial(materialId);
            resp.sendRedirect(req.getContextPath() + "/");
            return;
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
