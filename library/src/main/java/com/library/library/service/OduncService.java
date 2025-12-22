package com.library.library.service;

import java.util.List;

import com.library.library.entity.Odunc;

public interface OduncService {
    List<Odunc> tumunuGetir();
    List<Odunc> aktifOduncleriGetir();

    Odunc oduncAl(Integer kullaniciId, Integer kopyaId);
    Odunc teslimEt(Integer oduncId);
}
