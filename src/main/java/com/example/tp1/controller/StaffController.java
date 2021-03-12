package com.example.tp1.controller;


import com.example.tp1.entities.Joueur;
import com.example.tp1.entities.Staff;
import com.example.tp1.repository.EquipeRepository;
import com.example.tp1.repository.StaffRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping("/api")
public class StaffController {
    private static final Logger logger = LogManager.getLogger(StaffController.class);
    @Autowired

    StaffRepository staffR;

    @Autowired
    EquipeRepository equipeR;


    @PostMapping("/staff/save")
    public RedirectView saveStaff(Staff staff) throws IOException {

        Staff savedUser = staffR.save(staff);

        return new RedirectView("/staff", true);
    }

    @GetMapping("/staff")
    public List<Staff> getAllStaff() {
        List <Staff> allStaff = staffR.findAll();

        for (Staff staff : allStaff) {
            logger.debug("log:     " + staff);
            System.out.println("sysout:   " + staff);

        }
        return allStaff;

    }

    @PostMapping("/staffadd")
    public Staff createStaff(@Valid @RequestBody Staff staff) {
        return staffR.save(staff);
    }

    @GetMapping("/staff/{id}")
    public Staff getStaffById(@PathVariable(value = "id") Long Id) {
        return staffR.findById(Id).orElseThrow(null);
    }

    @DeleteMapping("/staffdelete/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable(value = "id") Long staffId) {
        Staff staff = staffR.findById(staffId).orElseThrow(null);
        staffR.delete(staff);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/staffupdate/{id}")
    public Staff updateStaff(@PathVariable(value = "id") Long Id,
                               @Valid @RequestBody Staff staffDetails) {

        Staff staff = staffR.findById(Id).orElseThrow(null);
        staff.setEquipe(staffDetails.getEquipe());

        Staff updatedStaff = staffR.save(staff);
        return updatedStaff;
    }
}
