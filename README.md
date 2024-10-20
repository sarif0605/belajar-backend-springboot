<!-- @format -->

<!-- ### 📌 Task List
No | Task | Convention
| --- | --- | --- |
 | 1 | Persistance Technology | <ol><li>menambahkan dependency JPA dan MySQL Connector sehingga bisa mengakses database-nya yang akan dituju.</li><li> Jelaskan maksud dari apa yang teman2 buat pada konfigurasinya, sehingga bisa terhubung ke database teman2 </li><li>clone [git@github.com:SIBKM-Batch4-Java/S2-ServerApp.git]()</li><li>Buat branch nama teman2 dan push di branch teman2 Jangan ada yang push di branch main </li></ol> |
|2| Persistancy Technology 2 | <ol><li>menyamakan commit sampai terakhir, jangan clone ataupun pull</li><li>membuat model dengan relasi One to One - Employee to User, dengan atribut sebagai berikut:</li> <li>Employee (sebagai parent):</li><ul><li>id (auto increment)</li><li>name (length 50, not null)</li><li>email (unique, not null)</li><li>phone (length 13)</li></ul> <li>User (sebagai child):</li><ul><li>id</li><li>username (not null, unique)</li><li>password (not null)</li><li>isEnabled = true</li><li>isAccountNonLocked = true</li></ul><li>membuat model dengan relasi Many to Many - User to Role, dengan atribut sebagai berikut:</li><ul><li>Role: - id (auto increment) - name (not null)</li></ul></ol>| -->

### 📍 Dependencies

| No  | Dependency                   | Version |
| --- | ---------------------------- | ------- |
| 1   | spring-boot-starter-web      | 2.7.10  |
| 2   | spring-boot-devtools         | 2.7.10  |
| 3   | spring-boot-starter-test     | 2.7.10  |
| 4   | spring-boot-starter-data-jpa | 2.7.10  |
| 5   | mysql-connector-java         | 8.0.27  |
| 6   | lombok                       | 1.18.26 |
| 7   | modelmapper                  | 3.1.1   |
| 8   | spring-boot-starter-mail     | -       |
| 9   | mail                         | 1.4.7   |
| 10  | spring-boot-starter-security | 3.0.6   |

## <!-- `mvn dependency:tree` This command will display the complete dependency tree  -->
