/**Class: SectionDriver
 * @author Joshua Sims
 * @version 1.0
 *
 * This class will capture information about the section and student for each student in the students<> Array in the Section() class then print it.
 */


import java.util.Scanner;

public class SectionDriver {
    private Scanner input = new Scanner(System.in);

    /**
     * Method: main
     *
     * Gets the information for the section and student(s) and then prints them.
     * @param args
     */
    public static void main(String[] args) {
        Section section = new Section();
        SectionDriver sectionDriver = new SectionDriver();

        section = sectionDriver.getSectionInfo();

        section = new Section(section.getSectionNumber(), section.getCourseTitle(), section.getInstructor(), section.getClassRoom());

        Student stu;

        // Loops through each Student() object and adds it to the student<> array if the name isn't blank.
        do {
            stu = sectionDriver.getStudentInfo();

            if (!stu.getName().equalsIgnoreCase("not set")) {
                stu = new Student(stu.getName(), stu.getMajor(), stu.getGpa(), stu.getHours());
                section.addStudents(stu);
            }

        } while (!stu.getName().equalsIgnoreCase("not set"));

        System.out.println(section.toString(section.getSectionNumber(), section.getCourseTitle(), section.getInstructor(), section.getClassRoom()));

        System.out.println("Name\t\tMajor\t\tGPA\t\tHours");

        // Loops through the student<> array in Section() and prints each students information using the Section.toString() method.
        for (int i = 0; i < section.students.size(); i++) {
            System.out.println(section.students.get(i).toString(section.students.get(i).getName(), section.students.get(i).getMajor(), section.students.get(i).getGpa(), section.students.get(i).getHours()));
        }

        sectionDriver.input.close();
    }

    /**
     * Method: getStudentInfo
     *
     * Returns the Student() class object after getting all the information based on the prompts for each instance variable in the Student() class.
     *
     * @return
     */
    private Student getStudentInfo() {
        Student stu = new Student();
        boolean condition;
        System.out.println("Please enter student name");
        condition = stu.setName(input.nextLine());
        if (condition) {
            boolean valid;
            do {
                System.out.printf("Please enter the major for %s\n", stu.getName());
                valid = stu.setMajor(input.nextLine());
            } while (!valid);

            do {
                System.out.printf("Please enter the GPA for %s\n", stu.getName());
                valid = stu.setGpa(input.nextDouble());
            } while (!valid);

            do {
                System.out.printf("Please enter the hours for %s\n", stu.getName());
                valid = stu.setHours(input.nextInt());
                input.nextLine();
            } while (!valid);
        }
        return stu;
    }

    /**
     * Method: getSectionInfo
     *
     * Returns the Section() class object after getting all the information based on the prompts for each instance variable in the Section() class.
     *
     * @return
     */
    private Section getSectionInfo() {
        Section sec = new Section();
        boolean valid;
        do {
            System.out.println("Please enter the section number");
            valid = sec.setSectionNumber(input.nextInt());
            input.nextLine();
        } while (!valid);

        do {
            System.out.println("Please enter the course name");
            valid = sec.setCourseTitle(input.nextLine());
        } while (!valid);

        do {
            System.out.println("Please enter the instructor's name");
            valid = sec.setInstructor(input.nextLine());
        } while (!valid);

        do {
            System.out.println("Please enter the class room");
            valid = sec.setClassRoom(input.nextLine());
        } while (!valid);

        return sec;
    }
}
