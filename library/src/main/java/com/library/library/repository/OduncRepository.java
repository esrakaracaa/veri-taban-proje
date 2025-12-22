package com.library.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.entity.Odunc;

public interface OduncRepository extends JpaRepository<Odunc, Integer> {
    List<Odunc> findByDurum(String durum);
    
    // iade edilmemiş (aktif) ödünçler
    List<Odunc> findByIadeTarihiIsNull();

    // Bu kopyaya ait iade edilmemiş aktif kayıt var mı?
    boolean existsByKopyaIdAndIadeTarihiIsNull(Integer kopyaId);

        // Henüz teslim edilmemiş (aktif) ödünçler
    List<Odunc> findByTeslimTarihiIsNull();

    // Kullanıcının aktif ödünçleri
    List<Odunc> findByKullaniciIdAndTeslimTarihiIsNull(Integer kullaniciId);

    // Kopyanın aktif ödünç kaydı var mı?
    boolean existsByKopyaIdAndTeslimTarihiIsNull(Integer kopyaId);

}
