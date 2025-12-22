package com.library.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.library.library.dto.OduncAlRequest;
import com.library.library.entity.Odunc;
import com.library.library.service.OduncService;

@RestController
@RequestMapping("/oduncler")
public class OduncController {

    private final OduncService oduncService;

    public OduncController(OduncService oduncService) {
        this.oduncService = oduncService;
    }

    @GetMapping
    public List<Odunc> tumunuGetir() {
        return oduncService.tumunuGetir();
    }

    @GetMapping("/aktif")
    public List<Odunc> aktifleriGetir() {
        return oduncService.aktifOduncleriGetir();
    }

    @PostMapping("/al")
    public Odunc oduncAl(@RequestBody OduncAlRequest req) {
        return oduncService.oduncAl(req.getKullaniciId(), req.getKopyaId());
    }

    @PostMapping("/teslim/{oduncId}")
    public Odunc teslimEt(@PathVariable Integer oduncId) {
        return oduncService.teslimEt(oduncId);
    }
}
