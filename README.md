# KouForum

KouForum, Kocaeli Üniversitesi öğrencileri ve akademisyenleri için geliştirilmiş bir forum uygulamasıdır. Kullanıcıların bilgi alışverişinde bulunmasını ve tartışmalarını kolaylaştırır.

## Özellikler

- **Konu Oluşturma ve Yanıtlama**: Kullanıcılar forumda yeni konular açabilir ve mevcut konulara yanıt verebilir.
- **Kategori Desteği**: Farklı konular için kategoriler oluşturularak tartışmalar düzenlenir.
- **Kullanıcı Profilleri**: Her kullanıcı kendi profilini oluşturabilir ve düzenleyebilir.

## Kurulum

### 1. Depoyu Klonlayın

```bash
git clone https://github.com/BatuhanKANBER/KouForum.git
```

### 2. Backend Bağımlılıklarını Yükleyin

Backend bağımlılıklarını yüklemek için `backend` dizinine gidin ve aşağıdaki komutu çalıştırın:

```bash
cd backend
# Bağımlılıkları yükleyin
# Örneğin:
mvn install
```

### 3. Frontend Bağımlılıklarını Yükleyin

Frontend bağımlılıklarını yüklemek için `frontend` dizinine gidin ve aşağıdaki komutu çalıştırın:

```bash
cd frontend
# Bağımlılıkları yükleyin
# Örneğin:
npm install
```

## Kullanım

1. **Backend Sunucusunu Başlatın:**

   ```bash
   # Örneğin:
   mvn spring-boot:run
   ```

2. **Frontend Sunucusunu Başlatın:**

   ```bash
   npm start
   ```

3. Tarayıcınızdan uygulamaya erişin:

   ```
   http://localhost:3000
   ```

## Katkıda Bulunma

Katkılarınızı bekliyoruz! Eğer projeye katkıda bulunmak isterseniz şu adımları izleyebilirsiniz:

1. Bu repoyu fork edin.
2. Yeni bir branch oluşturun:

   ```bash
   git checkout -b feature/yenilik
   ```

3. Yaptığınız değişiklikleri commit edin:

   ```bash
   git commit -m "Yeni bir özellik ekledim."
   ```

4. Branch'i push edin:

   ```bash
   git push origin feature/yenilik
   ```

5. Bir Pull Request oluşturun.

## Lisans

Bu proje **MIT Lisansı** ile lisanslanmıştır. Daha fazla bilgi için `LICENSE` dosyasına göz atabilirsiniz.
