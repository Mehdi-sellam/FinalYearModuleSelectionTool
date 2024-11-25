package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.ReserveModulesTab;


public class StudentProfile implements Serializable{

	private String studentPnumber;
	private Name studentName;
	private String studentEmail;
	private LocalDate studentDate;
	private Course studentCourse;
	private Set<Module> selectedModules;
	private Set<Module> reservedModules;
	private Set<Course> allCourses = new HashSet<>();	
	
    public void printAllCourses() {
        System.out.println("All Courses: " + allCourses);
    }
	
	public StudentProfile() {
		studentPnumber = "";
		studentName = new Name();
		studentEmail = "";
		studentDate = null;
		studentCourse = null;
		selectedModules = new TreeSet<Module>();
		reservedModules = new TreeSet<Module>();
	}
	
	public String getStudentPnumber() {
		return studentPnumber;
	}
	
	public void setStudentPnumber(String studentPnumber) {
		this.studentPnumber = studentPnumber;
	}
	
	public Name getStudentName() {
		return studentName;
	}
	
	public void setStudentName(Name studentName) {
		this.studentName = studentName;
	}
	
	public String getStudentEmail() {
		return studentEmail;
	}
	
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	public LocalDate getSubmissionDate() {
		return studentDate;
	}
	
	public void setSubmissionDate(LocalDate studentDate) {
		this.studentDate = studentDate;
	}
	
	public Course getStudentCourse() {
		return studentCourse;
	}
	
	public void setStudentCourse(Course studentCourse) {
		this.studentCourse = studentCourse;
	}
	
	public boolean addSelectedModule(Module m) {
		return selectedModules.add(m);
	}
	
	public Set<Module> getAllSelectedModules() {
		return selectedModules;
	}
	
	
	public void clearSelectedModules() {
		selectedModules.clear();
	}	
	
	
	
	public Set<Module> getAllReservedModules() {
		return reservedModules;
	}
	
	public void clearReservedModules() {
		reservedModules.clear();
	}
	
	
	
	
	
	
	
    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentPnumber = studentProfile.getStudentPnumber();
        this.studentName = studentProfile.getStudentName();
        this.studentEmail = studentProfile.getStudentEmail();
        this.studentDate = studentProfile.getSubmissionDate();
        this.studentCourse = studentProfile.getStudentCourse();
        this.selectedModules = studentProfile.getAllSelectedModules();
        this.reservedModules = studentProfile.getAllReservedModules();
    }
	
	
	
	
	
	
	@Override
	public String toString() {
		return "StudentProfile:[Pnumber=" + studentPnumber + ", studentName="
				+ studentName + ", studentEmail=" + studentEmail + ", studentDate="
				+ studentDate + ", studentCourse=" + studentCourse.actualToString() 
				+ ", selectedModules=" + selectedModules
				+ ", reservedModules=" + reservedModules + "]";
	}

	public Set<Module> getUnselectedModules() {
	    Set<Module> unselectedModules = new HashSet<>(/* Add your comparator if needed */);
	    if (studentCourse != null) {
	        unselectedModules.addAll(studentCourse.getAllModulesOnCourse());
	        unselectedModules.removeAll(selectedModules);
	        System.out.println("Unselected Modules: " + unselectedModules);
	        
	    }
	    return unselectedModules;
	}
	
	
	public boolean setReservedModule(Module m) {
		// TODO Auto-generated method stub
		return reservedModules.add(m);
	}

	public ObservableList<Module> getReservedModules() {
	    ObservableList<Module> reservedModulesList = FXCollections.observableArrayList();
	    if (studentCourse != null) {
	        Set<Module> reservedModulesSet = new HashSet<>(/* Add your comparator if needed */);
	        reservedModulesSet.addAll(studentCourse.getAllModulesOnCourse());

	        for (Module reservedModule : reservedModules) {
	            if (reservedModulesSet.contains(reservedModule)) {
	                reservedModulesList.add(reservedModule);
	            }
	        }
	    }
	    return reservedModulesList;
	}




	public void setSelectedModules(ObservableList<Module> selectedModules2) {
		// TODO Auto-generated method stub
		
	}




	
    public void addCourse(Course course) {
        allCourses.add(course);
    }

    // Add a method to remove a course from the set of all courses
    public void removeCourse(Course course) {
        allCourses.remove(course);
    }
    public Set<Course> getAllCourses() {
        return allCourses;
    }


    
    public StudentProfile getStudentProfile() {
        return this;
    }


    
    public boolean addReservedModule(Module m) {
        if (m != null) {
            return reservedModules.add(m);
        }
        return false;
    }


}
