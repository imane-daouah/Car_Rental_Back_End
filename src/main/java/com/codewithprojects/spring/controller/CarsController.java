package com.codewithprojects.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.codewithprojects.spring.dto.CarsDto;
import com.codewithprojects.spring.dto.CarsRequest;
import com.codewithprojects.spring.services.cars.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarsController {
    @Autowired
    private CarsService carsService;


    @PostMapping("/creer")
	/* public ResponseEntity<CarsDto> createCar(@RequestBody CarsRequest carsRequest) {
        CarsDto createdCar = carsService.creerCars(carsRequest);
        return ResponseEntity.ok(createdCar);
    }*/
    public ResponseEntity<CarsDto> createCar(
            @RequestParam("file") MultipartFile file,
            @RequestParam("marque") String marque,
            @RequestParam("modele") int modele,
            @RequestParam("annee") int annee,
            @RequestParam("etat") String etat,
            @RequestParam("tarif") double tarif,
            @RequestParam("type") String type,
            @RequestParam("description") String description) {

        try {
            // Sauvegarde de l'image dans le dossier "assets"
            String uploadDir = "C:\\Users\\21265\\Documents\\workspace-spring-tool-suite-4-4.26.0.RELEASE\\voiture_location_angular\\src\\assets\\";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            // Construction de la requête CarsRequest
            CarsRequest carsRequest = new CarsRequest();
            carsRequest.setMarque(marque);
            carsRequest.setModele(modele);
            carsRequest.setAnnee(annee);
            carsRequest.setEtat(etat);
            carsRequest.setTarif(tarif);
            carsRequest.setType(type);
            carsRequest.setDescription(description);
            carsRequest.setImage("assets/" + file.getOriginalFilename());

            // Appel du service pour sauvegarder la voiture
            CarsDto createdCar = carsService.creerCars(carsRequest);
            return ResponseEntity.ok(createdCar);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // Modifier une voiture existante
    @PutMapping("/modifier/{id}")
    public ResponseEntity<CarsDto> updateCar(@PathVariable Long id, @RequestBody CarsRequest carsRequest) {
        CarsDto updatedCar = carsService.modifierCars(id, carsRequest);
        return ResponseEntity.ok(updatedCar);
    }

    // Supprimer une voiture par ID
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carsService.supprimerCars(id);
        return ResponseEntity.noContent().build();
    }

    // Afficher toutes les voitures
    @GetMapping("/tous")
    public ResponseEntity<List<CarsDto>> getAllCars() {
        List<CarsDto> carsList = carsService.getTousCars();
        return ResponseEntity.ok(carsList);
    }
    @GetMapping("detail/{id}")
    public CarsDto getCarById(@PathVariable Long id) {
        return carsService.getCarsById(id);
    }

    @GetMapping("/search")
    public List<CarsDto> searchCars(

            @RequestParam(required = false) String type,
            @RequestParam(required = false) String marque,
            @RequestParam(required = false) Double tarif
    ) {
        return carsService.searchCars(type, marque, tarif);
    }
    @GetMapping()
    public long getNombrecars() {
        return carsService.getNombreCars();
    }
}
