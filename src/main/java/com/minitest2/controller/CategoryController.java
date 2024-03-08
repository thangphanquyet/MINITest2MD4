package com.minitest2.controller;

import com.minitest2.model.Category;
import com.minitest2.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("/categoryview/list");
        modelAndView.addObject("category", categoryService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/categoryview/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }
    @PostMapping("/create")
    public String saveCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes){
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "Successfully create new category");
        return "redirect:/category";
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/categoryview/update");
            modelAndView.addObject("category",category.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error");
        }
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("category") Category category, RedirectAttributes attributes){
        categoryService.save(category);
        attributes.addFlashAttribute("message", "Update successfully");
        return "redirect:/category";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        categoryService.remove(id);
        attributes.addFlashAttribute("message", "Delete successfully");
        return "redirect:/category";
    }
}