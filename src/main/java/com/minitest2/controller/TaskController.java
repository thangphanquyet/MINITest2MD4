package com.minitest2.controller;

import com.minitest2.model.Category;
import com.minitest2.model.Task;
import com.minitest2.service.category.ICategoryService;
import com.minitest2.service.task.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITaskService taskService;
    @Autowired
    private ICategoryService categoryService;
    @ModelAttribute("categories")
    public Iterable<Category> categoriesList(){
        return categoryService.findAll();
    }
    @GetMapping("")
    public ModelAndView showTask(){
        ModelAndView modelAndView = new ModelAndView("/taskview/list");
        modelAndView.addObject("task", taskService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/taskview/create");
        modelAndView.addObject("task", new Task());
        return modelAndView;
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("task") Task task, RedirectAttributes redirectAttributes){
        taskService.save(task);
        redirectAttributes.addFlashAttribute("message", "Successfully create new task!");
        return "redirect:/tasks";
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id){
        Optional<Task> task = taskService.findById(id);
        if (task.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/taskview/update");
            modelAndView.addObject("task", task.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error");
        }
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("task") Task task, RedirectAttributes redirectAttributes){
        taskService.save(task);
        redirectAttributes.addFlashAttribute("message", "Update task successfully");
        return "redirect:/tasks";
    }


}