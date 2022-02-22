package com.kemsuresh.data;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;

@NotNull
@NotBlank
@Size(min = 3, max = 32)
private String personName;

@NotNull
@NotBlank
@Digits(fraction = 0, integer = 8)
private String phoneNumber;

@NotNull
@NotBlank
@Email
private String email;

@NotNull
@JsonFormat
(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
@Future
private Date preferredInstallationDate;


@Min(9)
@Max(21)
private int timeslot;

@NotNull
private Address installationAddress;

@Min(1)
private int requiredproduct;

@Min(1)
private int packageId;

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}



public String getPersonName() {
	return personName;
}

public void setPersonName(String personName) {
	this.personName = personName;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Date getPreferredInstallationDate() {
	return preferredInstallationDate;
}

public void setPreferredInstallationDate(Date preferredInstallationDate) {
	this.preferredInstallationDate = preferredInstallationDate;
}

public int getTimeslot() {
	return timeslot;
}

public void setTimeslot(int timeslot) {
	this.timeslot = timeslot;
}

public Address getInstallationAddress() {
	return installationAddress;
}

public void setInstallationAddress(Address installationAddress) {
	this.installationAddress = installationAddress;
}

public int getRequiredproduct() {
	return requiredproduct;
}

public void setRequiredproduct(int requiredproduct) {
	this.requiredproduct = requiredproduct;
}

public int getPackageId() {
	return packageId;
}

public void setPackageId(int packageId) {
	this.packageId = packageId;
}

}
