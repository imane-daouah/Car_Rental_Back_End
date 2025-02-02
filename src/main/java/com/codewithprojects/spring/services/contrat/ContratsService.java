package com.codewithprojects.spring.services.contrat;

import com.codewithprojects.spring.dto.ContratDao;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContratsService {
    List<ContratDao> getAllContrats();
    ContratDao getContratById(Long id);
    ContratDao getContratByIdReservation(Long id);
    ContratDao createContrat(Long id);
    void supprimer(Long id);
    byte[] generateContratPDFWithSignature(ContratDao contrat,byte[] signatureImage)throws Exception;
    void sendContratEmail(String recipientEmail, String subject, String body, byte[] contratPdf) throws Exception;
    void setImage(Long idcontrat, MultipartFile signatureImage)throws Exception;
}
