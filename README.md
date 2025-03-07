# eshop - Progjut/Adpro/Prolan
## Past works
- https://github.com/turpatur/eshop/wiki/Tutorial-1-Adpro
- https://github.com/turpatur/eshop/wiki/Tutorial-2-Adpro
- https://github.com/turpatur/eshop/wiki/Tutorial-3-Adpro
## URL 
- https://lesser-donella-turpatur-a11f09d0.koyeb.app/product/list
- https://lesser-donella-turpatur-a11f09d0.koyeb.app/car/listCar
## Tutorial 4
### Reflection
```
Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.
```
Berdasarkan pengalaman saya dalam menjalankan module week 4, flow dari TDD membantu saya lebih mudah untuk menemukan bug dan kesalahan dalam implementasi dari proyek ini, tetapi cukup memakan waktu lama. Misal, saya menemukan bug dalam implementasi PaymentService setelah membuat tests. Hal ini membuat saya lebih mudah dan lebih cepat dalam menemukan bug. Namun, hal ini memakan waktu yang cukup lama ketika membuat test karena banyaknya test yang perlu dibuat dan juga ketidakpastian test apa saja yang perlu dibuat. Kesimpulannya, TDD membantu dalam pembuatan proyek karena kemudahan untuk mendeteksi potensi bug pada implementasi software, tetapi dapat memakan waktu yang cukup lama ketika belum ada gambaran tentang method yang akan dibuat pada fitur yang akan dibuat testnya. 

```
You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.
```
Unit test yang saya buat hampir semuanya sesuai dengan tutorial pada modul ini yang sudah memenuhi FIRST principle. Namun, hal ini dapat diperinci lagi sebagai berikut:
- Fast
  - Test yang saya lakukan sudah cepat dan tidak memakan waktu lama dalam eksekusinya.  
- Isolated
  - Terpisah dengan main program dan tidak bergantung pada unit test lainnya.
- Repeatable
  - Test yang dikerjakan dapat dilakukan berulang kali dengan hasil yang konsisten, bahakan setelah dilakukan refactoring pada kode.  
- Self-validating
  - Test yang dibuat memiliki assertion yang tidak tergantung oleh assertion pada test lain. 
- Thorough/Timely
  - Menurut saya, hal ini masih menjadi kekurangan dalam unit test saya karena kurang yakinnya dengan unhappy path yang telah dibuat apakah sudah memenuhi SEMUA kemungkinan yang ada. Namun, saya yakin hal ini sudah memenuhi beberapa unhappy path yang sudah jelas kemungkinan unhappy path. Kedepannya dapat memverifikasi lagi ada unhappy path apa lagi yang dapat dibuat testnya berdasarkan tipe data dan proses. 
 
