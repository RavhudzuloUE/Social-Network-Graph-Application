/**
 * 
 */
package csc3a.graph.model;

import java.io.Serializable;

/**
 * @author  Ravhudzulo UE 219012509
 * @version MiniProject
 * 
 * * Programmer class records all the users informations/details when a profile is created on Graphical User Interface
 *
 */
public class Programmer implements Serializable, Comparable<Programmer> {
	
	/***/
	private static final long serialVersionUID = 1L;
	/**String representation of an ID*/
	private String strID          = null;
	/**The name of the project in IT*/
	private String strProjectName = null;
	/**The field of the expertise in the IT industry*/
	private String strITExpertise = null;
	/**Programmer entry level*/
	private LEVEL  enumLevel      = null;
	
	/**
	 * Default constructor 
	 */
	public Programmer() {
		
		strID = "12D";
		strProjectName = "SELF DRIVING CAR ALGORITHM";
		this.strITExpertise = "ARTIFICIAL INTELLIGENCE";
		enumLevel      = LEVEL.BEGINNER;		
		
	}
	
	/**
	 * Parameterized constructor
	 * 
	 * @param strID    
	 *        String ID of a programmer
	 * @param strProjectName
	 *        Project Name that is worked on 
	 * @param strITExpertise
	 *        Field of IT Expertise
	 * @param enumLevel
	 *        Entry Level of a programmer
	 */
	public Programmer(String strID, String strProjectName, String strITExpertise, LEVEL  enumLevel) {
		
		this.strID          = strID;
		this.strProjectName = strProjectName;
		this.strITExpertise = strITExpertise;
		this.enumLevel      = enumLevel;
		
	}
	
	@Override
	public int compareTo(Programmer o) {
		
		if(o.getStrProjectName() != this.strProjectName && o.getStrITExpertise() != this.strITExpertise && o.getEnumLevel() != this.enumLevel) {
			
			return -1;
		} else if(o.getStrProjectName() == this.strProjectName || o.getStrITExpertise() == this.strITExpertise || o.getEnumLevel() == this.enumLevel) {
			
			return 0;
		} else if(o.getEnumLevel() != this.enumLevel){
			
			return 1;
		}
		
		return 0;
	}

	/**
	 * Accessor method to set programmers ID
	 * 
	 * @return the strID
	 */
	public String getStrID() {
		return strID;
	}

	/**
	 * Mutator method to set programmers ID
	 * 
	 * @param strID the strID to set
	 */
	public void setStrID(String strID) {
		this.strID = strID;
	}

	/**
	 * Accessor method for project name currently working on
	 * 
	 * @return the strProjectName
	 */
	public String getStrProjectName() {
		return strProjectName;
	}

	/**
	 * Mutator method for project name currently working on
	 * 
	 * @param strProjectName the strProjectName to set
	 */
	public void setStrProjectName(String strProjectName) {
		this.strProjectName = strProjectName;
	}

	/**
	 * Accessor method for Field of IT Expertise
	 * 
	 * @return the strITExpertise
	 */
	public String getStrITExpertise() {
		return strITExpertise;
	}

	/**
	 * Mutator method for Field of IT Expertise
	 * 
	 * @param strITExpertise the strITExpertise to set
	 */
	public void setStrITExpertise(String strITExpertise) {
		this.strITExpertise = strITExpertise;
	}

	/**
	 * Accessor method for programmer entry level
	 * @return the enumLevel
	 */
	public LEVEL getEnumLevel() {
		return enumLevel;
	}

	/**
	 * Mutator method for programmer entry level
	 * 
	 * @param enumLevel the enumLevel to set
	 */
	public void setEnumLevel(LEVEL enumLevel) {
		this.enumLevel = enumLevel;
	}	

}
