package com.library.library.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // EKLE

import com.library.library.entity.Odunc; // EKLE

@Repository
public interface OduncRepository extends JpaRepository<Odunc, Integer> {
    
    // 1. Duruma göre filtreleme (Örn: "ODUNCTE", "IADE_EDILDI")
    List<Odunc> findByDurum(String durum);
    
    // 2. ✅ AKTİF ÖDÜNÇLER: İade tarihi henüz girilmemiş (boş) olanları getirir
    // Bu metot, OduncServiceImpl içindeki aktifOduncleriGetir() için gereklidir.
    List<Odunc> findByIadeTarihiIsNull();

    // 3. ✅ HATAYI ÇÖZEN METOT: Kullanıcı ID'sine göre filtreleme
    // Logdaki "No property 'kullanici' found" hatasını bu isimlendirme düzeltir.
    List<Odunc> findByKullaniciId(Integer kullaniciId);

    // 4. ✅ KİTAP KONTROLÜ: Bir kitabın şu an birinde olup olmadığını sorgular
    boolean existsByKitapIdAndIadeTarihiIsNull(Integer kitapId);

    // 5. Teslim tarihi üzerinden sorgulama (Tablonuzda varsa kullanılır)
    List<Odunc> findByTeslimTarihiIsNull();
    
    // 6. Üyeye özel aktif emanet listesi
    List<Odunc> findByKullaniciIdAndTeslimTarihiIsNull(Integer kullaniciId);

    // OduncRepository.java

    // Belirli bir kullanıcıya ait ödünç kayıtlarını sayfalanmış olarak getirir
    Page<Odunc> findByKullaniciId(Integer kullaniciId, Pageable pageable);
}
