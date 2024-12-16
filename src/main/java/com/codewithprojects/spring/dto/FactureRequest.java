package com.codewithprojects.spring.dto;



import lombok.Data;

@Data
public class FactureRequest {
    private long id_reservation;
    private String num_compte;
   
	public long getId_reservation() {
		return id_reservation;
	}
	public void setId_reservation(long id_reservation) {
		this.id_reservation = id_reservation;
	}
	public String getNum_compte() {
		return num_compte;
	}
	public void setNum_compte(String num_compte) {
		this.num_compte = num_compte;
	}
	
    
    
}