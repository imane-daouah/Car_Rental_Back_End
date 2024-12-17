package com.codewithprojects.spring.services.facture;
import com.codewithprojects.spring.dto.FactureDto;
import com.codewithprojects.spring.dto.FactureRequest;
import com.codewithprojects.spring.entity.Facture;
import com.codewithprojects.spring.entity.Reservation;
import com.codewithprojects.spring.repository.FactureRepository;
import com.codewithprojects.spring.repository.ReservationRepository;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FactureServiceImpl implements FactureService {
	@Autowired
    private FactureRepository factureRepository;
	@Autowired
    private ReservationRepository reservationRepository;
	

    public FactureServiceImpl(FactureRepository factureRepository, ReservationRepository reservationRepository) {
        this.factureRepository = factureRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<FactureDto> getAllFactures() {
        return factureRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FactureDto getFactureById(long id) {
        Facture facture = factureRepository.findById(id).orElseThrow(() -> new RuntimeException("Facture not found"));
        return convertToDTO(facture);
    }

   /* @Override
    public FactureDto createFacture(FactureRequest factureRequest) {
        Reservation reservation = reservationRepository.findById(factureRequest.getId_reservation())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
      

        Facture facture = new Facture();
        facture.setReservation(reservation);
        facture.setNum_compte(factureRequest.getNum_compte());
        facture.setDate_paiement(LocalDate.now());

        long daysBetween = ChronoUnit.DAYS.between(reservation.getDate_debut().toInstant(), reservation.getDate_fin().toInstant());
        facture.setMontant(daysBetween * reservation.getCar().getTarif());

        Facture savedFacture = factureRepository.save(facture);
        reservation.setStatu("Termine");
        reservationRepository.save(reservation);
        return convertToDTO(savedFacture);
    } */
   @Override
   public FactureDto createFacture(FactureRequest factureRequest) {
       Reservation reservation = reservationRepository.findById(factureRequest.getId_reservation())
               .orElseThrow(() -> new RuntimeException("Reservation not found"));

       Facture facture = new Facture();
       facture.setReservation(reservation);
       facture.setNum_compte(factureRequest.getNum_compte());
       facture.setDate_paiement(LocalDate.now());

       // Convertir les dates en objets Calendar
       Calendar startCalendar = Calendar.getInstance();
       startCalendar.setTime(Date.from(reservation.getDate_debut().toInstant()));

       Calendar endCalendar = Calendar.getInstance();
       endCalendar.setTime(Date.from(reservation.getDate_fin().toInstant()));

       // Calculer la différence en jours entre les deux dates
       long millisBetween = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
       long daysBetween = millisBetween / (1000 * 60 * 60 * 24);

       facture.setMontant(daysBetween * reservation.getCar().getTarif());

       Facture savedFacture = factureRepository.save(facture);
       reservation.setStatu("Termine");
       reservationRepository.save(reservation);
       return convertToDTO(savedFacture);
   }

    @Override
    public FactureDto updateFacture(long id, FactureDto factureDTO) {
        Facture facture = factureRepository.findById(id).orElseThrow(() -> new RuntimeException("Facture not found"));
        facture.setDate_paiement(factureDTO.getDate_paiement());
        facture.setMontant(factureDTO.getMontant());
        facture.setNum_compte(factureDTO.getNum_compte());
        Facture updatedFacture = factureRepository.save(facture);
        return convertToDTO(updatedFacture);
    }

    @Override
    public void deleteFacture(long id) {

       factureRepository.deleteById(id);
    }

    /*private FactureDto convertToDTO(Facture facture) {
        FactureDto factureDTO = new FactureDto();

        factureDTO.setId_facture(facture.getId_facture());

        factureDTO.setReservation(facture.getReservation());
        factureDTO.setDate_paiement(facture.getDate_paiement());
        factureDTO.setMontant(facture.getMontant());
        factureDTO.setNum_compte(facture.getNum_compte());
        return factureDTO;
    }*/
    private FactureDto convertToDTO(Facture facture) {
        FactureDto factureDTO = new FactureDto();
        factureDTO.setId_facture(facture.getId_facture());
        factureDTO.setId_facture(facture.getId_facture());
        factureDTO.setDate_paiement(facture.getDate_paiement());
        factureDTO.setMontant(facture.getMontant());
        factureDTO.setNum_compte(facture.getNum_compte());
        return factureDTO;
    }
    @Override
    public double getSommeMontantsFactures() {
        Double sommeMontants = factureRepository.sumMontants();
        return (sommeMontants != null) ? sommeMontants : 0.0;
    }

	@Override
	public byte[] generateFacturePDF( FactureDto paiement) throws IOException {
        // Création du flux de sortie pour le PDF
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Configuration de la police pour le titre
        PdfFont titleFont = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD);
        Paragraph title = new Paragraph("Facture de Paiement")
                .setFont(titleFont)
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(title);

        // Informations générales sur la facture
        document.add(new Paragraph("ID Facture: " + paiement.getId_facture()));
        document.add(new Paragraph("Date Paiement: " + paiement.getDate_paiement()));

        // Informations sur le client
        document.add(new Paragraph("Client: " + paiement.getReservation().getUser().getNom() + " " + paiement.getReservation().getUser().getPrenom()));
        document.add(new Paragraph("Email: " + paiement.getReservation().getUser().getEmail()));
        document.add(new Paragraph("Telephone: " + paiement.getReservation().getUser().getNumero_tel()));

        // Informations sur la voiture
        document.add(new Paragraph("Voiture: " + paiement.getReservation().getCar().getMarque() + " " +
                paiement.getReservation().getCar().getModele() + " (" +
                paiement.getReservation().getCar().getAnnee() + ")"));
        document.add(new Paragraph("Type: " + paiement.getReservation().getCar().getType()));

        // Création du tableau des équipements et tarifs
        float[] columnWidths = {4, 4, 2, 2};
        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));
     // Entêtes du tableau
        table.addHeaderCell(new Cell().add(new Paragraph("Équipement")));
        table.addHeaderCell(new Cell().add(new Paragraph("Tarif / Jour (MAD)")));
        table.addHeaderCell(new Cell().add(new Paragraph("Nombre de Jours")));
        table.addHeaderCell(new Cell().add(new Paragraph("Montant (MAD)")));
        
        
        //long daysBetween = ChronoUnit.DAYS.between(paiement.getReservation().getDate_debut().toInstant(), paiement.getReservation().getDate_fin().toInstant());
        // Ajout des lignes au tableau
        Date dateDebut = paiement.getReservation().getDate_debut();
        Date dateFin = paiement.getReservation().getDate_fin();

        long daysBetween = java.time.Duration.between(
                dateDebut.toInstant(), dateFin.toInstant()
        ).toDays();


        table.addCell(new Cell().add(new Paragraph(paiement.getReservation().getCar().getMarque() + " " +
                    paiement.getReservation().getCar().getModele())));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(paiement.getReservation().getCar().getTarif()))));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(daysBetween))));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(paiement.getReservation().getCar().getTarif() * daysBetween))));
      

        document.add(table);

        // Montant total
        double montantTotal = paiement.getReservation().getCar().getTarif() * daysBetween;
        document.add(new Paragraph("Montant Total: " + montantTotal + " MAD"));

        // Fermeture du document
        document.close();

        return baos.toByteArray();
    }
	

	
	
}