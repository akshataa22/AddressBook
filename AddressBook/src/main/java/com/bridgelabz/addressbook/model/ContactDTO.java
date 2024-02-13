package com.bridgelabz.addressbook.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="contacts")
public class ContactDTO {
	@Id
    private String id;
    private int seq;    
    
	public ContactDTO() {
		super();
	}
	
	public ContactDTO(String id, int seq) {
		super();
		this.id = id;
		this.seq = seq;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
}
