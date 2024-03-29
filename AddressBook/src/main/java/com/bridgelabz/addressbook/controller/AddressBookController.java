package com.bridgelabz.addressbook.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.addressbook.model.Contact;
import com.bridgelabz.addressbook.response.Response;
import com.bridgelabz.addressbook.service.AddressBookService;
import com.bridgelabz.addressbook.service.SequenceGeneratorService;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
	@Autowired
	AddressBookService services;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@PostMapping("/create")
	private ResponseEntity<Response> createData(@RequestBody Contact contact) {
		contact.setId(sequenceGenerator.generateSequence(Contact.SEQUENCE_NAME));
		Response response = services.createContact(contact);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = { "", "/", "/get" })
	public ResponseEntity<List<Contact>> getContactsData() {
		List<Contact> list = new ArrayList<>();
		list = services.getAllContacts();
		if (!list.isEmpty()) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getcontactbyid/{id}")
	public Contact getContactById(@PathVariable String id) {
		return services.getContact(id);
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<String> updateContact(@PathVariable String id, @RequestBody Contact contact) {
        services.updateContactDetails(id, contact);
        return ResponseEntity.ok("Contact updated successfully.");
    }
	
	@PutMapping("/pin/{id}")
	public ResponseEntity<Response> pinContact(@PathVariable String id) {
		Response response = services.pinTheContact(id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public String deleteAllEmployee(){	
		services.deleteAllContacts();
		return "All Contacts Deleted successfully.";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable String id){	
		services.deleteById(id);
		return "Contact Deleted Successfully.";
	}
}
