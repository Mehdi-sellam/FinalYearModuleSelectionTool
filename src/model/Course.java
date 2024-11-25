package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Course implements Serializable{
	
	private String courseName;
	private Map<String, Module> modulesOnCourse;
	private Set<Module> modules;
	
	public Course(String courseName) {
		this.courseName = courseName;
		modulesOnCourse = new HashMap<String, Module>();
		modules = new HashSet<>();
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public void addModule(Module m) {
		modulesOnCourse.put(m.getModuleCode(), m);
	}
	
	public Module getModuleByCode(String code) {
		return modulesOnCourse.get(code);
	}
	
	public Collection<Module> getAllModulesOnCourse() {
		return modulesOnCourse.values();
	}
	
	@Override
	public String toString() {
		//a non-standard toString that simply returns the course name,
		//so as to assist in displaying courses correctly in a ComboBox<Course>
		return courseName;
	}
	
	public String actualToString() {
		return "Course:[courseName=" + courseName + ", modulesOnCourse=" + modulesOnCourse + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Course other)
				&& this.courseName.equals(other.courseName) 
				&& this.modulesOnCourse.equals(other.modulesOnCourse);
	}
	
	public Set<Module> getAllModules() {
        return new HashSet<>(modules); // Assuming you store modules in a Set
    }
	
	
	
	
	
	   public static List<Module> getBlock1Modules() {
	        List<Module> block1Modules = new ArrayList<>();

	        block1Modules.add(new Module("CTEC3701", "Software Development: Methods & Standards", 30, true, Block.BLOCK_1));
	        // Add other Block 1 modules as needed

	        return block1Modules;
	    }

	    public static List<Module> getBlock2Modules() {
	        List<Module> block2Modules = new ArrayList<>();

	        block2Modules.add(new Module("CTEC3702", "Big Data and Machine Learning", 30, true, Block.BLOCK_2));
	        block2Modules.add(new Module("CTEC3703", "Mobile App Development and Big Data", 30, true, Block.BLOCK_2));
	        // Add other Block 2 modules as needed

	        return block2Modules;
	    }

	    public static List<Module> getBlock3_4Modules() {
	        List<Module> block3_4Modules = new ArrayList<>();

	        block3_4Modules.add(new Module("CTEC3451", "Development Project", 30, true, Block.BLOCK_3_4));
	        block3_4Modules.add(new Module("CTEC3704", "Functional Programming", 30, false, Block.BLOCK_3_4));
	        block3_4Modules.add(new Module("CTEC3705", "Advanced Web Development", 30, false, Block.BLOCK_3_4));
	        block3_4Modules.add(new Module("IMAT3711", "Privacy and Data Protection", 30, false, Block.BLOCK_3_4));
	        block3_4Modules.add(new Module("IMAT3722", "Fuzzy Logic and Inference Systems", 30, false, Block.BLOCK_3_4));
	        block3_4Modules.add(new Module("CTEC3706", "Embedded Systems and IoT", 30, false, Block.BLOCK_3_4));
	        // Add other Block 3/4 modules as needed

	        return block3_4Modules;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    public List<Module> getModulesForBlock(Block block) {
	        List<Module> modules = new ArrayList<>();

	        for (Module module : modulesOnCourse.values()) {
	            if (module.getBlock() == block) {
	                modules.add(module);
	            }
	        }

	        return modules;
	    }


	    public List<Module> getModulesForBlock1() {
	        List<Module> modules = getModulesForBlock(Block.BLOCK_1);

	        // Compulsory modules for Block 1 in Course 1
	        if (this.getCourseName().equals("Course1")) {
	            modules.add(new Module("CTEC3701", "Software Development: Methods & Standards", 30, true, Block.BLOCK_1));
	            // Add other compulsory Block 1 modules for Course 1 as needed
	        }

	        // Compulsory modules for Block 1 in Course 2
	        if (this.getCourseName().equals("Course2")) {
	            modules.add(new Module("CTECXXXX", "Another Compulsory Module for Course 2", 30, true, Block.BLOCK_1));
	            // Add other compulsory Block 1 modules for Course 2 as needed
	        }

	        return modules;
	    }

	    public List<Module> getModulesForBlock2() {
	        List<Module> modules = getModulesForBlock(Block.BLOCK_2);

	        // Compulsory modules for Block 2 in Course 1
	        if (this.getCourseName().equals("Course1")) {
	            modules.add(new Module("CTEC3702", "Big Data and Machine Learning", 30, true, Block.BLOCK_2));
	            modules.add(new Module("CTEC3703", "Mobile App Development and Big Data", 30, true, Block.BLOCK_2));
	            // Add other compulsory Block 2 modules for Course 1 as needed
	        }

	        // Compulsory modules for Block 2 in Course 2
	        if (this.getCourseName().equals("Course2")) {
	            modules.add(new Module("CTECYYYY", "Another Compulsory Module for Course 2", 30, true, Block.BLOCK_2));
	            // Add other compulsory Block 2 modules for Course 2 as needed
	        }

	        return modules;
	    }

	    public List<Module> getModulesForBlock3_4() {
	        List<Module> modules = getModulesForBlock(Block.BLOCK_3_4);

	        // Compulsory modules for Block 3/4 in Course 1
	        if (this.getCourseName().equals("Course1")) {
	            modules.add(new Module("CTEC3451", "Development Project", 30, true, Block.BLOCK_3_4));
	            // Add other compulsory Block 3/4 modules for Course 1 as needed
	        }

	        // Compulsory modules for Block 3/4 in Course 2
	        if (this.getCourseName().equals("Course2")) {
	            modules.add(new Module("CTECZZZZ", "Another Compulsory Module for Course 2", 30, true, Block.BLOCK_3_4));
	            // Add other compulsory Block 3/4 modules for Course 2 as needed
	        }

	        return modules;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    public List<Module> getCompulsoryModulesForBlock1() {
	        List<Module> compulsoryModules = new ArrayList<>();

	        // Compulsory modules for Block 1 in Course
	        if (this.getCourseName().equals("Computer Science")) {
	            compulsoryModules.add(new Module("CTEC3701", "Software Development: Methods & Standards", 30, true, Block.BLOCK_1));
	            // Add other compulsory Block 1 modules for Computer Science as needed
	        }

	        if (this.getCourseName().equals("Software Engineering")) {
	            compulsoryModules.add(new Module("CTEC3701", "Software Development: Methods & Standards", 30, true, Block.BLOCK_1));
	            // Add other compulsory Block 1 modules for Software Engineering as needed
	        }

	        return compulsoryModules;
	    }

	    public List<Module> getCompulsoryModulesForBlock2() {
	        List<Module> compulsoryModules = new ArrayList<>();

	        // Compulsory modules for Block 2 in Course
	        if (this.getCourseName().equals("Computer Science")) {
	            compulsoryModules.add(new Module("CTEC3702", "Big Data and Machine Learning", 30, true, Block.BLOCK_2));
	            
	            // Add other compulsory Block 2 modules for Computer Science as needed
	        }

	        if (this.getCourseName().equals("Software Engineering")) {
	            compulsoryModules.add(new Module("CTEC3703", "Mobile App Development and Big Data", 30, true, Block.BLOCK_2));
	            // Add other compulsory Block 2 modules for Software Engineering as needed
	        }

	        return compulsoryModules;
	    }


}
