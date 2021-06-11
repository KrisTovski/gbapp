package com.kristovski.gbapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/panel")
public class AdminController {

    private UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAll(Model model) {
        return getPaginated(1, "id", "asc", model);
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable(value = "id") long id, Model model) {
        User user = userService.getUserById(id);
        // pre-populate the form
        model.addAttribute("user", user);
        return "panel/updateUserForm";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.mergeWithExistingAndUpdate(user);
        return "panel/updateSuccess";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/panel/users";
    }

    @GetMapping("/page/{pageNo}")
    public String getPaginated(@PathVariable(value = "pageNo") int pageNo,
                               @RequestParam("sortField") String sortField,
                               @RequestParam("sortDir") String sortDir,
                               Model model) {
        int pageSize = 5;
        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> listUsers = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listUsers", listUsers);
        return "panel/usersList";

    }
}
