package com.codewithprojects.spring.dto;


import com.codewithprojects.spring.entity.Reservation;
import lombok.Data;

@Data
public class ContratDao {
        private Long idContrat;
        private Reservation reservation;
        private String etat;
        private byte[] signatureImage;
}
