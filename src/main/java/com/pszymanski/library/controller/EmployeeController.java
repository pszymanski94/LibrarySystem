package com.pszymanski.library.controller;

import com.pszymanski.library.dto.EmployeeDTO;
import com.pszymanski.library.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Secured("ROLE_ADMIN")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @RequestMapping(value = "/admin/employees", method = RequestMethod.GET)
    public String getEmployeePage(Model model) {

        model.addAttribute("employeeList", employeeService.findAll());

        return "employee/employees";
    }

    @RequestMapping(value = "/admin/employees/search", method = RequestMethod.GET)
    public String searchEmployee(@RequestParam(value = "value", required = false) String value, @RequestParam(value = "option", required = false) String option, Model model) {

        model.addAttribute("employeeList", employeeService.search(value, option));
        return "employee/employees";
    }


    @RequestMapping(value = "/admin/employee/add", method = RequestMethod.GET)
    public String addEmployee(Model model) {

        model.addAttribute("employee", new EmployeeDTO());
        return "employee/addEmployee";
    }


    @RequestMapping(value = "/admin/employee/save", method = RequestMethod.POST)
    public String saveEmployee(@Valid @ModelAttribute("employee") EmployeeDTO employee, BindingResult result) {

        if (result.hasErrors()) {
            return "/employee/addEmployee";
        }
        employeeService.dtoToEntity(employee);

        return "redirect:/admin/employees?adding";
    }

    @RequestMapping(value = "/admin/employee/cancel", method = RequestMethod.GET)
    public String cancel() {

        return "redirect:admin/employees";
    }

    @RequestMapping(value = "/admin/employee/edit/{id}", method = RequestMethod.GET)
    public String editEmployee(@PathVariable Integer id, Model model) {

        model.addAttribute("employee", employeeService.findOne(id));
        return "employee/addEmployee";
    }


    @RequestMapping(value = "/admin/employee/delete/{id}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable Integer id) {

        employeeService.delete(id);
        return "redirect:/admin/employees?delete";
    }
}


