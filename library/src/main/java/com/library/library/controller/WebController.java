package com.library.library.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.library.entity.Kitap;
import com.library.library.entity.Kullanici;
import com.library.library.entity.Odunc;
import com.library.library.service.KategoriService;
import com.library.library.service.KitapService;
import com.library.library.service.KullaniciService;
import com.library.library.service.OduncService;
import com.library.library.service.YazarService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WebController {
    private final KullaniciService kullaniciService;
    private final KitapService kitapService;
    private final YazarService yazarService;
    private final KategoriService kategoriService;
    private final OduncService oduncService;

    public WebController(KitapService kitapService, 
                         YazarService yazarService, 
                         KategoriService kategoriService, 
                         KullaniciService kullaniciService,
                         OduncService oduncService) {
        this.kitapService = kitapService;
        this.yazarService = yazarService;
        this.kategoriService = kategoriService;
        this.kullaniciService = kullaniciService;
        this.oduncService = oduncService;
    }

    @ModelAttribute
    public void ortakVerileriEkle(Model model, HttpSession session) {
        Kullanici kullanici = (Kullanici) session.getAttribute("oturum");
        if (kullanici != null) {
            model.addAttribute("girisYapanKullanici", kullanici);
        }
    }

    private boolean yetkiliMi(HttpSession session) {
        Kullanici k = (Kullanici) session.getAttribute("oturum");
        return k != null && ("ADMIN".equalsIgnoreCase(k.getRol()) || "PERSONEL".equalsIgnoreCase(k.getRol()));
    }

    // --- ANA SAYFA (DASHBOARD) ---
    @GetMapping("/")
    public String anaSayfa(Model model, HttpSession session) {
        Kullanici kullanici = (Kullanici) session.getAttribute("oturum");
        if (kullanici == null) return "redirect:/login";

        if ("ADMIN".equalsIgnoreCase(kullanici.getRol()) || "PERSONEL".equalsIgnoreCase(kullanici.getRol())) {
            model.addAttribute("toplamKitap", kitapService.tumKitaplariGetir().size());
            model.addAttribute("toplamKullanici", kullaniciService.tumKullanicilariGetir().size());
            model.addAttribute("aktifEmanet", oduncService.aktifOduncleriGetir().size());
            return "index";
        } else {
            List<Odunc> tumOduncler = oduncService.kullaniciyaGoreGetir(kullanici.getKullaniciId());
            LocalDateTime simdi = LocalDateTime.now();
            double toplamBorc = 0.0;

            // ✅ GÜNCEL BORÇ TOPLAMA MANTIĞI: Teslim edilmeyen her şeyi kapsar
            for (Odunc o : tumOduncler) {
                // 1. Veritabanındaki hali hazırda kayıtlı cezayı ekle
                toplamBorc += (o.getCezaMiktari() != null ? o.getCezaMiktari() : 0.0);

                // 2. CANLI HESAPLAMA: Kitap henüz gerçekten teslim edilmediyse (teslimTarihi null ise)
                if (o.getTeslimTarihi() == null && o.getBeklenenIadeTarihi() != null) {
                    if (simdi.isAfter(o.getBeklenenIadeTarihi())) {
                        long dk = ChronoUnit.MINUTES.between(o.getBeklenenIadeTarihi(), simdi);
                        toplamBorc += (dk * 5.0); 
                    }
                }
            }

            long uzerimdekilerCount = tumOduncler.stream()
                .filter(o -> o.getTeslimTarihi() == null)
                .count();
            
            model.addAttribute("uzerimdekiler", uzerimdekilerCount);
            model.addAttribute("okunanlar", tumOduncler.size());
            model.addAttribute("toplamBorc", toplamBorc);
            return "uye-paneli";
        }
    }

    // --- EMANET TAKİBİ (Canlı Ceza Listesi) ---
    @GetMapping("/emanetler")
    public String emanetlerSayfasi(Model model, 
                                   @RequestParam(defaultValue = "0") int page, 
                                   HttpSession session) {
        Kullanici loginUser = (Kullanici) session.getAttribute("oturum");
        if (loginUser == null) return "redirect:/login";

        int size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by("durum").descending().and(Sort.by("id").descending()));
        Page<Odunc> emanetSayfasi;

        if ("ADMIN".equalsIgnoreCase(loginUser.getRol()) || "PERSONEL".equalsIgnoreCase(loginUser.getRol())) {
            emanetSayfasi = oduncService.tumunuGetir(pageable);
        } else {
            emanetSayfasi = oduncService.kullaniciyaGoreGetir(loginUser.getKullaniciId(), pageable);
        }

        List<Odunc> emanetler = emanetSayfasi.getContent();
        LocalDateTime simdi = LocalDateTime.now();

        // ✅ LİSTEDE CANLI CEZA GÖSTERİMİ: Durum bağımsız, Teslim Tarihi kontrolü
        for (Odunc e : emanetler) {
            if (e.getTeslimTarihi() == null && e.getBeklenenIadeTarihi() != null) {
                if (simdi.isAfter(e.getBeklenenIadeTarihi())) {
                    long dk = ChronoUnit.MINUTES.between(e.getBeklenenIadeTarihi(), simdi);
                    double anlikCeza = dk * 5.0;
                    double mevcutCeza = (e.getCezaMiktari() != null ? e.getCezaMiktari() : 0.0);
                    e.setCezaMiktari(mevcutCeza + anlikCeza);
                }
            }
        }
        
        model.addAttribute("emanetler", emanetler);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", emanetSayfasi.getTotalPages());
        
        return "emanetler"; 
    }

    // --- KİTAP LİSTESİ ---
    @GetMapping("/kitap-listesi")
    public String kitapListesi(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "kategoriId", required = false) Integer kategoriId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            Model model, HttpSession session) {
        
        if (session.getAttribute("oturum") == null) return "redirect:/login";

        Pageable pageable = PageRequest.of(page, size, Sort.by("kitapId").descending());
        Page<Kitap> kitapSayfasi = kitapService.sayfaliKitapAra(keyword, kategoriId, pageable);

        model.addAttribute("kitaplar", kitapSayfasi.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", kitapSayfasi.getTotalPages());
        model.addAttribute("totalItems", kitapSayfasi.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedKategori", kategoriId);
        model.addAttribute("kategoriler", kategoriService.tumKategorileriGetir());
        
        return "kitaplar";
    }

    @GetMapping("/kitap-al/{id}")
    public String kitapAl(@PathVariable("id") Integer kitapId, HttpSession session) {
        Kullanici kullanici = (Kullanici) session.getAttribute("oturum");
        if (kullanici == null) return "redirect:/login";
        if (yetkiliMi(session)) return "redirect:/kitap-listesi?hata=yetkisiz_islem";
        
        oduncService.oduncAl(kullanici.getKullaniciId(), kitapId);
        return "redirect:/emanetler";
    }

    @GetMapping("/iade-talebi/{id}")
    public String iadeTalebi(@PathVariable("id") Integer oduncId, HttpSession session) {
        if (session.getAttribute("oturum") == null) return "redirect:/login";
        oduncService.iadeTalebiOlustur(oduncId);
        return "redirect:/emanetler";
    }

    @GetMapping("/iade-et/{id}")
    public String iadeEt(@PathVariable("id") Integer oduncId, HttpSession session) {
        if (!yetkiliMi(session)) return "redirect:/"; 
        oduncService.teslimEt(oduncId);
        return "redirect:/emanetler";
    }

    // --- YÖNETİCİ İŞLEMLERİ ---
    @GetMapping("/kullanicilar")
    public String kullaniciListesi(Model model, HttpSession session) {
        if (!yetkiliMi(session)) return "redirect:/";
        model.addAttribute("kullanicilar", kullaniciService.tumKullanicilariGetir());
        return "kullanicilar";
    }

    @GetMapping("/kullanici-ekle")
    public String kullaniciEkleForm(Model model, HttpSession session) {
        if (!yetkiliMi(session)) return "redirect:/";
        model.addAttribute("kullanici", new Kullanici());
        return "kullanici-ekle";
    }

    @PostMapping("/kullanici-kaydet")
    public String kullaniciKaydet(@ModelAttribute("kullanici") Kullanici kullanici, HttpSession session) {
        if (!yetkiliMi(session)) return "redirect:/";
        kullanici.setAktifMi(true);
        kullaniciService.kaydet(kullanici);
        return "redirect:/kullanicilar";
    }

    @GetMapping("/kullanici-sil/{id}")
    public String kullaniciSil(@PathVariable("id") Integer id, HttpSession session) {
        if (!yetkiliMi(session)) return "redirect:/";
        kullaniciService.sil(id);
        return "redirect:/kullanicilar";
    }

    @GetMapping("/kitap-ekle")
    public String kitapEkleForm(Model model, HttpSession session) {
        if (!yetkiliMi(session)) return "redirect:/";
        model.addAttribute("kitap", new Kitap());
        model.addAttribute("yazarlar", yazarService.tumYazarlariGetir());
        model.addAttribute("kategoriler", kategoriService.tumKategorileriGetir());
        return "kitap-ekle";
    }

    @PostMapping("/kitap-kaydet")
    public String kitapKaydet(Kitap kitap, HttpSession session) {
        if (!yetkiliMi(session)) return "redirect:/";
        kitap.setAktifMi(true);
        kitapService.kaydet(kitap);
        return "redirect:/kitap-listesi";
    }

    @GetMapping("/kitap-sil/{id}")
    public String kitapSil(@PathVariable("id") Integer id, HttpSession session) {
        if (!yetkiliMi(session)) return "redirect:/";
        kitapService.sil(id);
        return "redirect:/kitap-listesi";
    }

    @GetMapping("/odunc-ver")
    public String oduncVerForm(Model model, HttpSession session) {
        if (!yetkiliMi(session)) return "redirect:/";
        model.addAttribute("kitaplar", kitapService.aktifKitaplariGetir());
        model.addAttribute("kullanicilar", kullaniciService.tumKullanicilariGetir());
        return "odunc-ver";
    }

    @PostMapping("/odunc-kaydet")
    public String oduncKaydet(@RequestParam("kitapId") Integer kitapId, 
                             @RequestParam("kullaniciId") Integer kullaniciId, HttpSession session) {
        if (!yetkiliMi(session)) return "redirect:/";
        oduncService.oduncAl(kullaniciId, kitapId);
        return "redirect:/kitap-listesi"; 
    }
}