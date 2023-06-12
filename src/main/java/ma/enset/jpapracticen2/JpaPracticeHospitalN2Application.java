package ma.enset.jpapracticen2;

import ma.enset.jpapracticen2.entities.*;
import ma.enset.jpapracticen2.repositories.ConsultationRepository;
import ma.enset.jpapracticen2.repositories.MedecinRepository;
import ma.enset.jpapracticen2.repositories.PatientRepository;
import ma.enset.jpapracticen2.repositories.RendezVousRepository;
import ma.enset.jpapracticen2.services.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class JpaPracticeHospitalN2Application {

	public static void main(String[] args) {
		SpringApplication.run(JpaPracticeHospitalN2Application.class, args);
	}

	@Bean
	CommandLineRunner start(IHospitalService iHospitalService,
							PatientRepository patientRepository,
							MedecinRepository medecinRepository,
							RendezVousRepository rendezVousRepository) {
		return args -> {
			Stream.of("Ouail", "Aymane", "Fatima")
					.forEach(name-> {
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setDateNaissance(new Date());
						patient.setMalade(false);
						iHospitalService.savePatient(patient);
					});
			Stream.of("Asmae", "Ismail", "Mohammed")
					.forEach(name-> {
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setSpecialite(Math.random()>0.5?"Generaliste":"Cardiologue");
						medecin.setEmail(name+"@gmail.com");
						iHospitalService.saveMedecin(medecin);
					});

			Patient patient = patientRepository.findById(1L).orElse(null);
			Patient patient1 = patientRepository.findByNom("Aymane");

			Medecin medecin = medecinRepository.findByNom("asmae");

			RendezVous rendezVous = new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatusRDV(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			RendezVous savedRDV = iHospitalService.saveRDV(rendezVous);
			System.out.println(savedRDV.getId());
			iHospitalService.saveRDV(rendezVous);

			RendezVous rendezVous1 = rendezVousRepository.findAll().get(0);
			Consultation consultation = new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de la consultation");
			iHospitalService.saveConsultation(consultation);
		};
	}

}
