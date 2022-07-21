package com.example.mmc.dao;

import com.example.mmc.model.StudentEntity;
import com.example.mmc.utility.HibernateUtil;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

/** DAO class to access students' data
 *  @author Kristaps Sebris, Elena Bebrisa, Georgijs Kadolciks
 *  @version 19th July 2022
 */

public class StudentDAO {

    /** Fields hold all students' data and data after filtering
     */
    private List<StudentEntity> allStudents, filteredStudents;

    /** Constructor opens a Hibernate session, loads all data and closes the session
     */
    public StudentDAO() {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(StudentEntity.class);
        Root<StudentEntity> root = query.from(StudentEntity.class);
        allStudents = session.createQuery(query).getResultList();
        session.close();
    }

    /** Receives user's selection and filters data
     *
     * @param selectedStudents User's selection
     * @param selectedGrades User's selection
     * @param selectedInstruments User's selection
     * @param selectedTeachers User's selection
     * @param selectedAccompanists User's selection
     */
    public void filterData(List<String> selectedStudents,
                           List<String> selectedGrades,
                           List<String> selectedInstruments,
                           List<String> selectedTeachers,
                           List<String> selectedAccompanists) {

        // Uses stream for filtering data on user's selection
        filteredStudents = allStudents.stream()
                .filter(st -> selectedStudents.isEmpty()
                        || selectedStudents.contains(st.getStudent()))
                .filter(st -> selectedGrades.isEmpty()
                        || selectedGrades.contains(st.getGrade()))
                .filter(st -> selectedInstruments.isEmpty()
                        || selectedInstruments.contains(st.getInstrumentId().getInstrument()))
                .filter(st -> selectedTeachers.isEmpty()
                        || selectedTeachers.contains(st.getTeacherId().getTeacher()))
                .filter(st -> selectedAccompanists.isEmpty()
                        || selectedAccompanists.contains(st.getAccompanistId().getAccompanist()))
                .collect(Collectors.toList());
    }

    /** Obtains data for displaying students' names
     *
     * @return Returns a tree set containing ordered and distinct students' names
     */
    public TreeSet<String> getStudents() {
        TreeSet<String> studentSet = new TreeSet<>();
        for (StudentEntity st : filteredStudents) {
            studentSet.add(st.getStudent());
        }
        return studentSet;
    }

    /** Obtains data for displaying students' grades
     *
     * @return Returns a tree set containing ordered and distinct students' grades
     */
    public TreeSet<String> getGrades() {
        TreeSet<String> gradeSet = new TreeSet<>();
        for (StudentEntity st : filteredStudents) {
            gradeSet.add(st.getGrade());
        }
        return gradeSet;
    }

    /** Obtains data for displaying instruments
     *
     * @return Returns a tree set containing ordered and distinct instruments
     */
    public TreeSet<String> getInstruments() {
        TreeSet<String> instrumentSet = new TreeSet<>();
        for (StudentEntity st : filteredStudents) {
            instrumentSet.add(st.getInstrumentId().getInstrument());
        }
        return instrumentSet;
    }

    /** Obtains data for displaying teachers
     *
     * @return Returns a tree set containing ordered and distinct teachers
     */
    public TreeSet<String> getTeachers() {
        TreeSet<String> teacherSet = new TreeSet<>();
        for (StudentEntity st : filteredStudents) {
            teacherSet.add(st.getTeacherId().getTeacher());
        }
        return teacherSet;
    }

    /** Obtains data for displaying accompanists
     *
     * @return Returns a tree set containing ordered and distinct accompanists
     */
    public TreeSet<String> getAccompanists() {
        TreeSet<String> accompanistSet = new TreeSet<>();
        for (StudentEntity st : filteredStudents) {
            accompanistSet.add(st.getAccompanistId().getAccompanist());
        }
        return accompanistSet;
    }
}