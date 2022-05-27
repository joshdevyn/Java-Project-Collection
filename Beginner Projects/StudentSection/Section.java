/**Class: Section
 * @author Joshua Sims
 * @version 1.0
 *
 * This class will capture information about the section for each student in the students<> Array
 */

package StudentSection;

import java.util.ArrayList;

public class Section {
    private int sectionNumber;
    private String courseTitle;
    private String instructor;
    private String classRoom;

    public ArrayList<Student> students;

    /**
     * Method: Section
     *
     * Empty constructor. Initializes the students array.
     */
    public Section() {
         students = new ArrayList<Student>();
    }

    /**
     * Method: Section
     *
     * Non-empty constructor. Validates error messages and sets values for parameters below for instance.
     *
     * @param sectionNumber
     * @param courseTitle
     * @param instructor
     * @param classRoom
     */
    public Section(int sectionNumber, String courseTitle, String instructor, String classRoom) {
//        this();
        boolean valid;
        valid = setSectionNumber(sectionNumber);
        if (!valid) {
            System.out.println("Invalid section. Please enter the section number (1 to 15)");
        }
        else {
            this.sectionNumber = getSectionNumber();
        }
        valid = setCourseTitle(courseTitle);
        if (!valid) {
            System.out.println("Course must be either Programming Fundamentals or Intermediate");
        }
        else {
            this.courseTitle = getCourseTitle();
        }
        valid = setInstructor(instructor);
        if (!valid) {
            System.out.println("Invalid instructor name. This cannot be blank");
        }
        else {
            this.instructor = getInstructor();
        }
        valid = setClassRoom(classRoom);
        if (!valid) {
            System.out.println("Invalid classroom name. This cannot be blank");
        }
        else {
            this.classRoom = getClassRoom();
        }
    }

    /**
     * Method: getSectionNumber
     *
     * Returns the section number for the instance.
     *
     * @return
     */
    public int getSectionNumber() {
        return sectionNumber;
    }

    /**
     * Method: setSectionNumber
     *
     * Boolean that validates the section number and sets it if appropriate, returning true if so.
     *
     * @param sectionNumber
     * @return
     */
    public boolean setSectionNumber(int sectionNumber) {
        boolean valid = (sectionNumber >= 1 && sectionNumber <= 15);
        if (!valid) {
            System.out.println("Invalid section. Please enter the section number (1 to 15)");
        }
        else if (valid) {
            this.sectionNumber = sectionNumber;
        }
        return(valid);
    }

    /**
     * Method: getCourseTitle
     *
     * Returns the course title for the instance.
     *
     * @return
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * Method: setCourseTitle
     *
     * Boolean that validates the course title and sets it if appropriate, returning true if so.
     *
     * @param courseTitle
     * @return
     */
    public boolean setCourseTitle(String courseTitle) {
        boolean valid = (courseTitle.equalsIgnoreCase("Programming Fundamentals") || courseTitle.equalsIgnoreCase("Intermediate Programming"));
        if (!valid) {
            System.out.println("Course must be either Programming Fundamentals or Intermediate Programming");
        }
        else if (valid) {
            this.courseTitle = courseTitle;
        }
        return(valid);
    }

    /**
     * Method: getInstructor
     *
     * Returns the instructor for the instance.
     *
     * @return
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Method: setInstructor
     *
     * Boolean that validates the instructor and sets it if appropriate, returning true if so.
     *
     * @param instructor
     * @return
     */
    public boolean setInstructor(String instructor) {
        boolean valid = instructor.length() > 0;
        if (!valid) {
            System.out.println("Invalid instructor name. This cannot be blank");
        }
        else if (valid) {
            this.instructor = instructor;
        }
        return(valid);
    }

    /**
     * Method: getClassRoom
     *
     * Returns the class room for the instance.
     *
     * @return
     */
    public String getClassRoom() {
        return classRoom;
    }

    /**
     * Method: setClassRoom
     *
     * Boolean that validates the class room and sets it if appropriate, returning true if so.
     *
     * @param classRoom
     * @return
     */
    public boolean setClassRoom(String classRoom) {
        boolean valid = classRoom.length() > 0;
        if (!valid) {
            System.out.println("Invalid classroom name. This cannot be blank");
        }
        else if (valid) {
            this.classRoom = classRoom;
        }
        return(valid);
    }

    /**
     * Method: addStudents
     *
     * Adds instances of class object Student() to the Student<> ArrayList.
     *
     * @param student
     */
    public void addStudents(Student student) {
        this.students.add(student);
    }

    /**
     * Method: toString
     *
     * Returns a string for printing the section info in the SectionDriver() class, main method.
     *
     * @param sectionNumber
     * @param courseTitle
     * @param instructor
     * @param classRoom
     * @return
     */
    public String toString(int sectionNumber, String courseTitle, String instructor, String classRoom) {
        return "Section" + "\t\t" + "Course" + "\t\t\t\t\t\t\t" + "Inst" + "\t\t" + "Room" + "\n" +
                sectionNumber + "\t\t\t" + courseTitle + "\t\t" + instructor + "\t\t" + classRoom;
    }
}
