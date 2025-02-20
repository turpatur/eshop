# eshop - Progjut/Adpro/Prolan
## Past works
> https://github.com/turpatur/eshop/wiki/Tutorial-1-Adpro
## URL 
> https://lesser-donella-turpatur-a11f09d0.koyeb.app/
## Tutorial 2
### Reflection
```
List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
```
Dari PMD Report terdapat beberpa isu-isu dalam kode saya, beberapa di antaranya adalah kesalahan dalam penulisan syntax dan access modifier. Untuk isu-isu tersebut cukup mudah untuk menyelesaikan karena hanya melibatkan penulisan ulang dari beberapa method dan variable tanpa mengubah fungsinya. Pertama, untuk kesalahan penulisan access modifier terdapat dalam file `ProductService.java` yang merupakan sebuah interface sehingga tidak perlu access modifier public karena pada dasarnya method pada interface berupa public, static, abstract, dan final. Saya hanya menghapus access modifier public pada method tersebut. Kemudian ada kesalahan penulisan syntax pada method yang berupa pascal case. Pada Java, penulisan method berupa camel case sehingga pmd mendeteksi kesalah pada penulisan method. Pada kasus ini, penamaan ulang method tidak mengubah fungsi dari kodenya sehingga perbaikan yang dilakukan cukup mudah. 

```
Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!
```
Menurut saya, CI/CD workfflow saya sudah sesuai dengan definisinya. CI memiliki definisi "praktik software development di mana perubahan dan pembaruan dalam codebase terintegrassis dan terverifiksai oleh script pembuatan otomatis dengan berbagai macam tools," (Swaraj, 2017). Penerapan CI pada proyek ini menggunakan github actions dengan gradle yang menjalankan unit test secara otomatis tiap kali push ke repository. Hal ini sejalan dengan defiisi dari CI. CD memiliki definisi "Praktik software development yang berperan dalam mendeploy aplikasi ke server," (Swaraj, 2017). Pada proyek ini, CD menggunakan fitur autodeploy dari Koyeb yang meendeploy aplikasi ke Koyeb setiap kali ada perubahan pada branch main sehingga hal ini sejalan dengan definisi CD. 
