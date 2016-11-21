package ejb;

import data.Professor;

/**
 * Created by danielamaral on 21/11/2016.
 */
public interface ProfessorEJBRemote {
    Professor getProfessor(int professorId);
}
