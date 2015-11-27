/**
 * 
 */
package fr.tbr.iamcore.datamodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Tom
 *
 */

@Entity
@Table(name="IDENTITIES")
public class Identity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	@Column(name ="FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "EMAIL", unique=true)
	private String email;
	
	@Column(name = "BIRTHDATE")
	private Date birthDate;
	
	
	public Identity() {
	}
	
	public Identity(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Identity [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email
				+ ", birthDate=" + birthDate + "]";
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
