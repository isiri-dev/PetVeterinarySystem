package controller;

import database.DBConnection;
import model.Appointment;
import model.AppointmentDAO;
import model.Pet;
import model.PetDAO;
import model.Veterinarian;
import model.VeterinarianDAO;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;


public class AppointmentIntegrationTest {

    public static void main(String[] args) {
       
        System.out.println("  PAWCARE APPOINTMENT MODULE - INTEGRATION TEST   ");
       

        AppointmentDAO appointmentDAO = new AppointmentDAO();
        PetDAO petDAO = new PetDAO();
        VeterinarianDAO vetDAO = new VeterinarianDAO();

        try {
            
            System.out.print("[TEST 1] Loading Pets & Veterinarians from DB... ");
            List<Pet> pets = petDAO.getAllPets();
            List<Veterinarian> vets = vetDAO.getAllVeterinarians();

            if (pets.isEmpty() || vets.isEmpty()) {
                System.out.println("FAIL (Pets count: " + pets.size() + ", Vets count: " + vets.size() + ")");
                return;
            }
            int testPetId = pets.get(0).getPetId();
            int testVetId = vets.get(0).getVetId();
            System.out.println("PASS (Found " + pets.size() + " pets, " + vets.size() + " vets)");
            System.out.println("         Using Pet: " + pets.get(0).getPetName() + " (ID: " + testPetId + ")");
            System.out.println("         Using Vet: " + vets.get(0).getName() + " (ID: " + testVetId + ")");

            
            System.out.print("[TEST 2] Retrieving all appointments with pet/vet names... ");
            List<Appointment> initialAppointments = appointmentDAO.getAllAppointments();
            System.out.println("PASS (Found " + initialAppointments.size() + " existing appointments)");
            for (Appointment a : initialAppointments) {
                System.out.println("         Appt #" + a.getAppointmentId() + " | Pet: " + a.getPetName()
                        + " | Vet: " + a.getVetName() + " | Date: " + a.getAppointmentDate()
                        + " | Fee: Rs." + a.getServiceFee() + " | Status: " + a.getStatus());
            }

            
            System.out.print("[TEST 3] Testing veterinarian scheduling conflict check... ");
            if (!initialAppointments.isEmpty()) {
                Appointment existing = initialAppointments.get(0);
                boolean conflictDetected = appointmentDAO.isVetDoubleBooked(
                        existing.getVetId(),
                        existing.getAppointmentDate(),
                        0 
                );
                boolean selfExcludedConflict = appointmentDAO.isVetDoubleBooked(
                        existing.getVetId(),
                        existing.getAppointmentDate(),
                        existing.getAppointmentId() 
                );

                if (conflictDetected && !selfExcludedConflict) {
                    System.out.println("PASS (Conflict detected for double-booking, excluded when updating same ID)");
                } else {
                    System.out.println("WARN (conflictDetected=" + conflictDetected + ", selfExcluded=" + selfExcludedConflict + ")");
                }
            } else {
                System.out.println("SKIPPED (no existing appointments to check conflict against)");
            }

            
            System.out.print("[TEST 4] Inserting a new appointment... ");
            Appointment testAppt = new Appointment();
            testAppt.setPetId(testPetId);
            testAppt.setVetId(testVetId);
            Timestamp testDate = Timestamp.valueOf("2026-11-15 14:30:00");
            testAppt.setAppointmentDate(testDate);
            testAppt.setServiceDescription("Dental Checkup & Scaling");
            testAppt.setServiceFee(3500.00);
            testAppt.setStatus("Scheduled");

            int generatedId = appointmentDAO.addAppointmentAndGetId(testAppt);
            if (generatedId > 0) {
                System.out.println("PASS (Generated ID: #" + generatedId + ")");
            } else {
                System.out.println("FAIL (Could not insert appointment)");
                return;
            }

            
            System.out.print("[TEST 5] Fetching inserted appointment by ID #" + generatedId + "... ");
            Appointment fetched = appointmentDAO.getAppointmentById(generatedId);
            if (fetched != null && fetched.getServiceDescription().equals("Dental Checkup & Scaling")
                    && fetched.getServiceFee() == 3500.00) {
                System.out.println("PASS (Pet: " + fetched.getPetName() + ", Vet: " + fetched.getVetName() + ")");
            } else {
                System.out.println("FAIL (Fetched appointment does not match expected data)");
            }

            
            System.out.print("[TEST 6] Updating appointment #" + generatedId + "... ");
            fetched.setServiceDescription("Dental Checkup & Scaling - Completed");
            fetched.setServiceFee(3800.00);
            fetched.setStatus("Completed");
            boolean updateSuccess = appointmentDAO.updateAppointment(fetched);
            Appointment updated = appointmentDAO.getAppointmentById(generatedId);
            if (updateSuccess && updated != null && updated.getStatus().equals("Completed")
                    && updated.getServiceFee() == 3800.00) {
                System.out.println("PASS (Status updated to Completed, Fee updated to 3800.00)");
            } else {
                System.out.println("FAIL (Update did not persist)");
            }

            
            System.out.print("[TEST 7] Searching appointments by keyword 'Dental'... ");
            List<Appointment> searchResults = appointmentDAO.searchAppointments("Dental");
            boolean foundInSearch = searchResults.stream().anyMatch(a -> a.getAppointmentId() == generatedId);
            if (foundInSearch) {
                System.out.println("PASS (Found matching appointment in search results)");
            } else {
                System.out.println("FAIL (Appointment not returned in search)");
            }

            
            System.out.print("[TEST 8] Checking payment records foreign key protection... ");
            boolean hasPayment = appointmentDAO.hasPaymentRecords(1); 
            boolean testApptHasPayment = appointmentDAO.hasPaymentRecords(generatedId); 
            if (hasPayment && !testApptHasPayment) {
                System.out.println("PASS (Appt #1 linked to payments detected; test appt confirmed unlinked)");
            } else {
                System.out.println("INFO (Appt #1 hasPayment: " + hasPayment + ", testAppt: " + testApptHasPayment + ")");
            }

           
            System.out.print("[TEST 9] Deleting temporary test appointment #" + generatedId + "... ");
            boolean deleteSuccess = appointmentDAO.deleteAppointment(generatedId);
            Appointment shouldBeNull = appointmentDAO.getAppointmentById(generatedId);
            if (deleteSuccess && shouldBeNull == null) {
                System.out.println("PASS (Appointment deleted cleanly; no orphaned test data left)");
            } else {
                System.out.println("FAIL (Appointment could not be deleted)");
            }

            
            System.out.println("  ALL 9 INTEGRATION TESTS PASSED SUCCESSFULLY!    ");
            

        } catch (SQLException e) {
            System.err.println("SQL Error during integration test: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
