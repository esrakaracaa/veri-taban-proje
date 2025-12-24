package com.library.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.library.entity.Kullanici;
import com.library.library.service.KullaniciService;

@Controller
public class AuthController {

    @Autowired
    private KullaniciService kullaniciService;

    @GetMapping("/login")
    public String loginSayfasi() {
        return "login";
    }

    @GetMapping("/kayit-ol")
    public String kayitSayfasi(Model model) {
        model.addAttribute("kullanici", new Kullanici());
        return "kayit-ol";
    }

    // TEK BİR KAYIT METODU OLMALI
    @PostMapping("/kayit-kaydet")
    public String kayitOl(@ModelAttribute("kullanici") Kullanici kullanici, Model model) {
        try {
            kullaniciService.kullaniciKaydet(kullanici);
            return "redirect:/login";
        } catch (RuntimeException e) {
            // E-posta zaten varsa burası çalışır
            model.addAttribute("hataMesaji", "Bu e-posta adresi zaten kullanımda!");
            return "kayit-ol";
        }
    }

    @PostMapping("/login")
    public String girisYap(@RequestParam String username, 
                           @RequestParam String password, 
                           jakarta.servlet.http.HttpSession session, 
                           Model model) {
        
        Kullanici kullanici = kullaniciService.girisKontrol(username, password);
        
        if (kullanici != null) {
            session.setAttribute("oturum", kullanici);
            return "redirect:/";
        } else {
            model.addAttribute("hata", "E-posta veya şifre hatalı!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String cikisYap(jakarta.servlet.http.HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}