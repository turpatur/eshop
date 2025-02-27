# eshop - Progjut/Adpro/Prolan
## Past works
- https://github.com/turpatur/eshop/wiki/Tutorial-1-Adpro
- https://github.com/turpatur/eshop/wiki/Tutorial-2-Adpro
## URL 
- https://lesser-donella-turpatur-a11f09d0.koyeb.app/product/list
- https://lesser-donella-turpatur-a11f09d0.koyeb.app/car/listCar
## Tutorial 3
### Reflection
```
Explain what principles you apply to your project!
```
- Single Responsibility Principle
  - Pada awalnya, CarController menjadi satu dengan ProductController sebagai subclass. Namun, hal itu tidak sesuai dengan fungsi sebenarnya dan menyalahi SRP. CarController merupakan controller untuk kelas Car dan bukan merupakan controller untuk eklas Product sehingga saya menjadikan CarController bukan sebagai subclass dari ProductController dan memisahkan CarController ke file yang berbeda.  
- Open Close Principle
  - Class ProdycRepository dan CarRepository memiliki method yang identik, jadi untuk memudahkan penambahan dari repository-repository lainnya, saya membuat interface RepositoryInterface.     
- Liskov Substitution Principle
  - Pada awalnya, CarController merupakan subclass dari ProductController sehingga memiliki method dan atribut dari ProductController. Namun, hal ini tidak cocok dari desain karena seharusnya CarController tidak bisa berperan dalam control untuk Product sehingga hal ini melanggar LSP. Untuk yang sudah sesuai dengan LSP adalah file-file implementasi dari interfaces, yakni di package repository dan service. 
- Interface Segregation Principle
  - Semua method di implementasi dari interface yang ada pada package repository dan service relevan pada penggunaannya. 
- Dependency Inversion Principle
  - Dorongan untuk penggunaan abstraksi di proyek ini terletak pada atribut productService di ProductController dan carService di CarController yang menggunakan tipe data ProductService dan CarService yng merupakan interface sehingga apabila ada perubahan pada interfacenya, tidak perlu mengubah CarController. Sebaliknya terjadi apabila tipe data yang digunakan adalah implementasi dari interface tersebut. 

```
Explain the advantages of applying SOLID principles to your project with examples
```
- Maintanability yang baik
  - Kode menjadi _readable_ dan memiliki pemisahan tugas yang eksplisit memudahkan _debugging_ dan testing untuk proyek. 
- Pembagian tugas/tanggung jawab yang terpisah
  - Dari sini kita tahu bahwa satu method melakukan satu hal dan ini memperjelas penggunaan method yang terpisah-terpisah secara sederhana sehingga kode menjadi _readable_.
-   Memudahkan untuk menambahkan/menghapus fitur baru
  -  Apabila saya ingin menambhakan repository, hanya perlu melakukan implementasi dari repository tersebut tanpa membuat interface baru lagi.
- Logic kode yang rapi, sesuai, dan intuitf
  - Misal, pada LSP child harus bisa melakukan method-method dari parentnya karena child adalah spesialisasi dari parent yang terhubung dengan relationship is-a. Apabila child tidak bisa melakukan method dari parentnya, maka tidak sesuai dengan definisi dari inheritance.

```
 Explain the disadvantages of not applying SOLID principles to your project with examples.
```
- Kesulitan untuk maintain proyek
  - SOLID Membuat kode menjadi _readable_ dan mudah melakukan _maintainability_ apabbila tidak ada SOLID makan akan sulit untuk di-_maintain_. Misal, sulit debugging pada suatu method yang melakukan 100 hal yang bersamaan sehingga sulit untuk mencari letak kesalahannya. 
- Pembagian tugas/tanggung jawab yang menumpuk
  - Sesuai dengan penjelasan di atas suatu tugas harus bisa di-_handle_ oleh satu class/method saja karena apabila ditumpuk akan sausah untuk mengurusnya dan mengembangkan proyek tersebut. 
- Kesulitan untuk menambahkan/menghapus fitur baru
  - Misal untuk membuat XRepository perlu membuat method-method yang sama dengan repository lainnya sehingga mempersulit untuk mengembangkan fitur.
- Logic kode yang tidak sesuai
  - Hal ini dapat menyebabkan suatu proyek mengeksekusi program dengan tidak baik. 
