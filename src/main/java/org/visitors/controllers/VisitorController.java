package org.visitors.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.visitors.models_and_repositories.appointment.Appointment;
import org.visitors.models_and_repositories.visitor.Visitor;
import org.visitors.services.AppointmentService;
import org.visitors.services.GenderService;
import org.visitors.services.VisitorService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

@RequestMapping("/visitors")
@Controller
public class VisitorController {

    @Autowired
    private VisitorService visitorService;
    @Autowired
    private GenderService genderService;
    @Autowired
    private AppointmentService appointmentService;

    public VisitorController(VisitorService visitorService, GenderService genderService, AppointmentService appointmentService) {
        super();
        this.visitorService = visitorService;
        this.genderService = genderService;
        this.appointmentService = appointmentService;
    }


    @RequestMapping("/view")
    public String listVisitors(Model model) {
        model.addAttribute("visitors", visitorService.listAllVisitors());
        return "visitors_view";
    }

    @GetMapping
    public String listAllVisitors(Model model) {
        model.addAttribute("visitors", visitorService.listAllVisitors());
        return "visitors_view";
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteVisitor(@PathVariable Long id, Model model) {
        visitorService.visitorDelete(id);
        model.addAttribute("visitors", visitorService.listAllVisitors());
        return new RedirectView("/visitors");
    }

    @GetMapping("/load/{id}")
    public String loadVisitor(@PathVariable Long id, Model model) {
        model.addAttribute("genders", genderService.listAllGenders());
        if (id > 0) {
            model.addAttribute("visitor", visitorService.getVisitorById(id));
        } else {
            model.addAttribute("visitor", visitorService.createVisitor());
        }
        return "visitor_edit";
    }

    //TODO
    @GetMapping("/appointments/load/{visitorId}")
    public String loadVisitorAppointments(@PathVariable Long visitorId, Model model) {
        List<Appointment> visitorAppointments = appointmentService.getAppointmentsByVisitorId(visitorId);
        model.addAttribute("visitorAppointments", visitorAppointments);
        return "appointments_view";
    }

    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView editVisitor(@RequestParam Long id,
                                    @RequestParam String firstName,
                                    @RequestParam String lastName,
                                    @RequestParam String middleName,
                                    @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date birthDate,
                                    @RequestParam String email,
                                    @RequestParam String password,
                                    @RequestParam String phoneNumber,
                                    @RequestParam long genderId,
                                    Model model) {

        visitorService.visitorEdit(id, firstName, lastName, middleName, password, email, phoneNumber, birthDate, genderId);
        System.out.println("successfully Saved!");
        return new RedirectView("/visitors");
    }

}