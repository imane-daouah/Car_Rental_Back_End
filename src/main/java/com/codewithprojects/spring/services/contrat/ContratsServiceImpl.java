package com.codewithprojects.spring.services.contrat;

import com.codewithprojects.spring.dto.ContratDao;
import com.codewithprojects.spring.entity.Contrat;
import com.codewithprojects.spring.entity.Reservation;
import com.codewithprojects.spring.repository.ContratRepository;
import com.codewithprojects.spring.repository.ReservationRepository;
import com.itextpdf.layout.properties.TextAlignment;
import jakarta.websocket.Session;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import com.itextpdf.layout.element.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.net.PasswordAuthentication;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.*;

@Service
public class ContratsServiceImpl implements ContratsService {

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
   /* public List<ContratDao> getAllContrats() {
        List<Contrat> contrats = contratRepository.findAll();
        List<ContratDao> contratDaos = new ArrayList<>();

        for (Contrat contrat : contrats) {
            ContratDao contratDao = new ContratDao();
            contratDao.setIdContrat(contrat.getId());
            contratDao.setReservation(contrat.getReservation());
            contratDaos.add(contratDao);
        }

        return contratDaos;
    }*/
    public List<ContratDao> getAllContrats() {
        LocalDate today = LocalDate.now();

        // Récupérer tous les contrats
        List<Contrat> contrats = contratRepository.findAll();

        // Liste pour stocker les ContratDao
        List<ContratDao> contratDaos = new ArrayList<>();

        for (Contrat contrat : contrats) {
            // Vérifier les critères
            if (contrat.getReservation() != null) {
                String statut = contrat.getReservation().getStatu();
                LocalDate dateFin = convertirDateEnLocalDate(contrat.getReservation().getDate_fin());

                // Vérifier le statut et si la date actuelle dépasse la date de fin par au moins un jour
                if (statut.equals("En litige")) {

                    ContratDao contratDao = this.convertTDao(contrat);

                    contratDaos.add(contratDao);
                }
                if(statut.equals("En cours")&&
                        dateFin != null && ChronoUnit.DAYS.between(dateFin, today) == 1 ) {

                    ContratDao contratDao = this.convertTDao(contrat);

                    contratDaos.add(contratDao);
                }

            }
        }

        return contratDaos;
    }
    private LocalDate convertirDateEnLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    @Override
    public ContratDao getContratById(Long id) {
        Optional<Contrat> contratOpt = contratRepository.findById(id);
        if (contratOpt.isPresent()) {
            Contrat contrat = contratOpt.get();
            ContratDao contratDao = new ContratDao();
            contratDao.setIdContrat(contrat.getId());
            contratDao.setReservation(contrat.getReservation());
            contratDao.setEtat(contrat.getEtat());
            contratDao.setSignatureImage(contrat.getSignatureImage());
            return contratDao;
        }
        return null;
    }

    @Override
    public ContratDao createContrat(Long id) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();


            Contrat contrat = new Contrat();
            contrat.setReservation(reservation);
            contrat.setEtat("valide");
            contratRepository.save(contrat);


            ContratDao contratDao = new ContratDao();
            contratDao.setIdContrat(contrat.getId());
            contratDao.setReservation(reservation);
            contratDao.setEtat(contrat.getEtat());
            return contratDao;
        }
        return null;
    }

    @Override
    public void supprimer(Long id) {

        contratRepository.deleteById(id);
        System.out.println("Contrat supprimé avec succès !");
    }
    @Override
    public byte[] generateContratPDFWithSignature(ContratDao contrat,byte[] signatureImage) throws Exception {
        // Création du flux de sortie pour le PDF
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Configuration de la police pour le titre
        PdfFont titleFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        PdfFont contentFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        // Titre du contrat
        Paragraph title = new Paragraph("Contrat de Location")
                .setFont(titleFont)
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(title);

        // Ajouter un espace après le titre
        document.add(new Paragraph("\n"));

        // Informations principales du contrat
        document.add(new Paragraph("Numéro de Contrat : " + contrat.getIdContrat()).setFont(contentFont));
        document.add(new Paragraph("Nom du Client : " + contrat.getReservation().getUser().getNom()).setFont(contentFont));
        document.add(new Paragraph("Prénom du Client : " + contrat.getReservation().getUser().getPrenom()).setFont(contentFont));
        document.add(new Paragraph("Email : " + contrat.getReservation().getUser().getEmail()).setFont(contentFont));
        document.add(new Paragraph("Téléphone : " + contrat.getReservation().getUser().getNumero_tel()).setFont(contentFont));
        document.add(new Paragraph("Voiture Louée : " + contrat.getReservation().getCar().getModele()).setFont(contentFont));
        document.add(new Paragraph("Date de Début : " + contrat.getReservation().getDate_debut()).setFont(contentFont));
        document.add(new Paragraph("Date de Fin : " + contrat.getReservation().getDate_fin()).setFont(contentFont));

        // Calcul du nombre de jours et montant total
        long daysBetween = ChronoUnit.DAYS.between(
                contrat.getReservation().getDate_debut().toInstant(),
                contrat.getReservation().getDate_fin().toInstant()
        );
        double montantTotal = contrat.getReservation().getCar().getTarif() * daysBetween;

        document.add(new Paragraph("Nombre de Jours : " + daysBetween).setFont(contentFont));
        document.add(new Paragraph("Montant Total : " + montantTotal + " MAD").setFont(contentFont));

        // Ajouter un espace avant les clauses
        document.add(new Paragraph("\n"));

        // Clauses du contrat
        document.add(new Paragraph("Clauses du Contrat :").setFont(titleFont).setFontSize(14));
        document.add(new Paragraph("1. Le locataire est responsable de tout dommage causé à la voiture.").setFont(contentFont));
        document.add(new Paragraph("2. La voiture doit être retournée avec le plein de carburant.").setFont(contentFont));
        document.add(new Paragraph("3. Tout retard dans le retour entraînera des frais supplémentaires.").setFont(contentFont));

        // Ajouter un espace avant la signature
        document.add(new Paragraph("\n"));
        // Ajout de la section signature
        document.add(new Paragraph("Signature :").setFontSize(14).setBold());


        // Insertion de l'image de la signature
        if (signatureImage != null) {
            ImageData imageData = ImageDataFactory.create(signatureImage);
            Image signature = new Image(imageData);
            signature.setWidth(150);  // Ajustez la taille
            signature.setHeight(50);
            document.add(signature);
        } else {
            document.add(new Paragraph("Aucune signature fournie."));
        }

        // Fermeture du document
        document.close();
        // this.sendContratEmail(contrat.getReservation().getUser().getEmail(), "contrat de location voiture", "", baos.toByteArray());
        return baos.toByteArray();
    }
    @Override
    // Nouvelle méthode pour envoyer le PDF par email
    public void sendContratEmail(String recipientEmail, String subject, String body, byte[] contratPdf) throws Exception {
        String host = "smtp.gmail.com"; // Utilisez votre serveur SMTP (ici Gmail)
        final String fromEmail = "smaildamouh47@gmail.com"; // Votre adresse email
        final String password = "ma3ine2002"; // Votre mot de passe (ou mot de passe d'application si nécessaire)

        // Configurer les propriétés du serveur SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        // Créer une session de mail avec authentification
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        // Créer le message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject(subject);

        // Créer le corps du message
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);

        // Ajouter le PDF en pièce jointe
        MimeBodyPart attachmentPart = new MimeBodyPart();
        ByteArrayDataSource source = new ByteArrayDataSource(contratPdf, "application/pdf");
        attachmentPart.setDataHandler(new DataHandler(source));
        attachmentPart.setFileName("contrat_location.pdf"); // Nom du fichier attaché

        // Créer une partie multipart et ajouter les éléments
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachmentPart);

        // Définir le contenu de l'email
        message.setContent(multipart);

        // Envoyer l'email
        Transport.send(message);
        System.out.println("Email envoyé avec succès!");
    }

    @Override
    public ContratDao getContratByIdReservation(Long id) {
        ContratDao contratDao = this.convertTDao( contratRepository.contratByreservationId(id));
        return contratDao;
    }
    private ContratDao convertTDao(Contrat contrat) {
        ContratDao contratDao = new ContratDao();
        contratDao.setIdContrat(contrat.getId());
        contratDao.setReservation(contrat.getReservation());
        contratDao.setEtat(contrat.getEtat());
        contratDao.setSignatureImage(contrat.getSignatureImage());
        return contratDao;
    }

    @Override
    public void setImage(Long idcontrat, MultipartFile signatureImage)throws Exception {
        Contrat contratOpt = contratRepository.findById(idcontrat).orElseThrow(() -> new RuntimeException("contrat introuvable avec l'ID " + idcontrat));
        contratOpt.setSignatureImage(signatureImage.getBytes());
        contratRepository.save(contratOpt);

    }
}
