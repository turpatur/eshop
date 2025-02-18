# eshop - Progjut/Adpro/Prolan
## Tutorial 1 
### Reflection 1
```
You already implemented two new features using Spring Boot.
Check again your source code and evaluate the coding standards that you have learned in this module.
Write clean code principles and secure coding practices that have been applied to your code.
If you find any mistake in your source code, please explain how to improve your code.
```
Saya sudah menggunakan coding standard:
- Menamakan variabel sesuai fungsinya
- Setiap method hanya menjalankan satu fungsi
- Tidak ada commented out code
Untuk secore coding saya sudah menerapkan form yang wajib diisi dan validasi input. ID untuk tiap product sudah digenerate dengan UUID sehingga ID setiap product unik.
Untuk improvement kode saya, perlu adanya handling bagi attribute object yang berupa null dan handling untuk Integer overflow bagi atribut quantity.

### Reflection 2
```
1. After writing the unit test, how do you feel? How many unit tests should be made in a class?
How to make sure that our unit tests are enough to verify our program?
It would be good if you learned about code coverage.
Code coverage is a metric that can help you understand how much of your source is tested.
If you have 100% code coverage, does that mean your code has no bugs or errors?
````
Menurut saya, dalam suatu class perlu ada unit tests minimal satu untuk tiap method pada class tersebut. Pada kebanyakan kasus, satu saja tidak cukup, perlu adanya testing untuk berbagai macam skenario untuk suatu method tersebut. Hal ini berlaku untuk case positif dan case negatif. Misal, case positif untuk edit product adalah ketika inputnya sesuai dengan tipe datanya dan case negatif adalah ketika input tidak sesuai dengan tipe data object tersebut. Untuk code coverage 100% berlaku ketika semua line code dijalankan dalam suatu project, meskipun begitu tidak menjamin adanya bug dan error

```
2. Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list.
You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.
What do you think about the cleanliness of the code of the new functional test suite?
Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!
```
Menurut saya, hal itu melanggar prinsip clean code karena code yang digunakan mirip dengan functional test sebelumnya sehingga menyebabkan redundansi pada code tersebut. Karena ada bagian code identik, perlu diterapkan inheritance yang memiliki atribut dan method yang dimiliki oleh kedua functional tests, lalu pada masing-masing test sebagai child dari parent test dapat memiliki code yang berbeda sesuai dengan kebutuhan. 
