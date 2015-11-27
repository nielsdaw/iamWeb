/**

 * 
 */
package fr.tbr.iamcore.services.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.services.dao.IdentityDAOInterface;

/**
 * @author Tom
 *
 */
public class IdentityFileDAO implements IdentityDAOInterface {
	
	private static final String IDENTITY_FILE = "/identities/identities.txt";
	private PrintWriter writer;
	private Scanner reader;
	
	private static int idCounter = 0;
	
	
	public IdentityFileDAO(){
		File file  = createFile(IDENTITY_FILE);
		try {
			FileOutputStream fios = new FileOutputStream(file, true);
			this.writer = new PrintWriter(fios);
			this.reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void write(Identity identity){
		writeAnIdentity(identity, this.writer);
		this.writer.flush();
	}
	
	
	public List<Identity> readAll(){
		List<Identity> result = new ArrayList<Identity>();
		while (this.reader.hasNext()){
			Identity identity = readOneIdentity();
			result.add(identity);
		}
		return result;
		
	}

	private Identity readOneIdentity() {
		int id = Integer.valueOf(this.reader.nextLine());
		String firstName = this.reader.nextLine();
		String lastName = this.reader.nextLine();
		String email = this.reader.nextLine();
		String rawBirthDate = this.reader.nextLine();
		Identity identity = new Identity(firstName, lastName, email);
		identity.setId(id);
		return identity;
	}
	
	public List<Identity> search(Identity identity){
		
		List<Identity> result = new ArrayList<Identity>();
		while (this.reader.hasNext()){
			Identity foundIdentity = readOneIdentity();
			String firstName = foundIdentity.getFirstName();
			if (firstName != null && firstName.contains(identity.getFirstName())){
				result.add(foundIdentity);
			}
		}
		try {
			this.reader = new Scanner(createFile(IDENTITY_FILE));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	public void update(Identity identity){
		List<Identity> idList = readAll();
		int indexToRemove = -1;
		for(int i = 0, totalSize=idList.size(); i < totalSize; i++){
			Identity currentIdentity = idList.get(i);
			if (currentIdentity.getId() == identity.getId()){
				indexToRemove = i ;
				break;
				
			}
		}
		if (indexToRemove <0){
			return;
		}
		idList.remove(indexToRemove);
		idList.add(identity);
		
		this.writer.close();
		this.reader.close();
		File temporaryFile = createFile(IDENTITY_FILE);
		try {
			this.writer = new PrintWriter(temporaryFile);
			this.reader = new Scanner(temporaryFile);
		} catch (FileNotFoundException e) {
			//
			e.printStackTrace();
			return;
		}
		for (Identity id : idList){
			writeAnIdentity(id, writer);
		}
		this.writer.flush();
		
	}
	
	private static File createFile(String path) {
		File file = new File(path);

		if (!file.exists()){
			try {
				File parent = file.getParentFile();
				if (!parent.exists()){
					parent.mkdirs();
				}
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Something gone wrong in the file creation, see the following stack trace");
				e.printStackTrace();
			}
		}
		
		return file;
	}
	
	private static void writeAnIdentity(Identity identity, PrintWriter writer) {
		writer.println(idCounter++);
		writer.println(identity.getFirstName());
		writer.println(identity.getLastName());
		writer.println(identity.getEmail());
		writer.println(identity.getBirthDate());
	}

	@Override
	public void delete(Identity identity) {
		// TODO Auto-generated method stub
		
	}

}
