package ejb;

import data.Professor;

import javax.ejb.Remote;

@Remote
public interface ProfessorEJBRemote {
    Professor getProfessor(String professorId);
}
