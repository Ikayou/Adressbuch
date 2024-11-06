package com.example.Adressbuch;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.annotation.PostConstruct;

@Controller
public class AdressbuchController {
    @Autowired
    UserRepository repos;

    @GetMapping("/login")
    public String loginForm() {
        return "users/login"; // Pfad zur Vorlage
    }

    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String tel) {
        List<User> users = repos.findByNameAndTel(name, tel);
        System.out.println("Anzahl der gefundenen Benutzer: " + users.size());
        if (users.size() == 1) {
            return "redirect:/users/list";
        } else {
            return "redirect:/add";
        }
    }

    @GetMapping("/users/list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView();
        List<User> list = repos.findAll();
        mav.setViewName("users/list");
        mav.addObject("data", list);
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView mav = new ModelAndView();
        User data = new User();
        mav.addObject("formModel", data);
        mav.setViewName("users/new");
        return mav;
    }

    @PostMapping("/save")
    @Transactional
    public String save(@RequestParam String name, @RequestParam String address, @RequestParam String tel) {
        User user = new User();
        user.setName(name);
        user.setAddress(address);
        user.setTel(tel);
        repos.save(user);
        return "redirect:/users/list";
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("users/edit"); // edit.html を指定
        Optional<User> data = repos.findById(id);
        data.ifPresent(user -> mav.addObject("formModel", user));
        return mav;
    }

    @PostMapping("/update")
    @Transactional
    public String update(@RequestParam Long id, @RequestParam String name, @RequestParam String address, @RequestParam String tel) {
        Optional<User> optionalUser = repos.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(name);
            user.setAddress(address);
            user.setTel(tel);
            
            // saveAndFlushで更新を即座に反映
            repos.saveAndFlush(user);
        }
        return "redirect:/users/list"; // 更新後にリストページにリダイレクト
    }

    @PostMapping("/delete")
    @Transactional
    public ModelAndView delete(@RequestParam Long id) {
        repos.deleteById(id);
        return new ModelAndView("redirect:/users/list");
    }

    @PostConstruct
    public void init(){
        if (repos.findByNameAndTel("Tomas Müller", "123456789").isEmpty()) {
            User user1 = new User();
            user1.setName("Tomas Müller");
            user1.setAddress("Musterstraße 1");
            user1.setTel("123456789");
            repos.save(user1);
        }

        if (repos.findByNameAndTel("Manuel Neuer", "987654321").isEmpty()) {
            User user2 = new User();
            user2.setName("Manuel Neuer");
            user2.setAddress("Musterstraße 2");
            user2.setTel("987654321");
            repos.save(user2);
        }
    }
}
