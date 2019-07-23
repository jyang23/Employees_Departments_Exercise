package com.jy.employee_department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping("/")
    public String listEmployees(Model model)
    {
        model.addAttribute("departments",departmentRepository.findAll());
        return "index";
    }
    //----------------------------------------------------------------------------------------------------------------------
    @GetMapping("/add")
    public String employeeForm(Model model)
    {
        model.addAttribute("department",new Department());
        return "employeeform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Department department, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "employeeform";
        }
        departmentRepository.save(department);
        return "redirect:/";
    }
    //----------------------------------------------------------------------------------------------------------------------
    @RequestMapping("detail/{id}")
    public String showTodo(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("department", departmentRepository.findById(id).get());
        return "details";
    }

    @RequestMapping("/update/{id}")
    public String updateTodo(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("department", departmentRepository.findById(id).get());
        return "employeeform";
    }

    @RequestMapping("/delete/{id}")
    public String delTodo(@PathVariable("id") long id)
    {
        departmentRepository.deleteById(id);
        return "redirect:/";
    }
    //----------------------------------------------------------------------------------------------------------------------
    @PostMapping("/processsearch")
    public String processForm(
                              @RequestParam(value = "field",required=false) String field,
                              @RequestParam(value = "title",required=false) String title, Model model){
        if(field != null)
        {
            model.addAttribute("departments", departmentRepository.findByField(field));
        }
        else if(title != null)
        {
            model.addAttribute("pets", departmentRepository.findByJobtitle(title));
        }
        return "show";
    }


}

