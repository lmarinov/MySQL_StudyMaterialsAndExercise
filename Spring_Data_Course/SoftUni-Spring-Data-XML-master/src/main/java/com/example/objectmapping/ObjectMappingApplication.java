package com.example.objectmapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** ----------- Лаба от 15.07.2021 (чт) ------------------
 * - При команда "find X" -> да върне JSON с информацията за Мениджъра + неговите подчинени
 * - При команда "findAll" -> да върне масив (JSON) с информацията за Мениджърите + техните подчинени
 * - При команда "save {firstName: "...", lastName: "...", salary: xxxx.x, address: "..."}" -> да го запишем в базата + да върнем това + id-то
 * - При команда "save-from-file (filePath)" да прочете json file и да запише в базата
 * - При команда "findAll-to (filePath)" да запише всички мениджъри във файл
 *
 *
 *        --------- ПОЧИВКА ДО 19:37 ------------
 *
 *
 *  ----------- Лаба за днес 19.07.2021 (пн) ------------------
 * - При команда "find X" -> да върне XML с информацията за Мениджъра + неговите подчинени
 * - При команда "findAll" -> да върне масив (XML) с информацията за Мениджърите + техните подчинени
 * - При команда "save <employee><firstName>...</firstName><lastName>....</lastName><salary>...</salary><address></address></employee>" -> да го запишем в базата + да върнем това + id-то
 * - При команда "save-from-file (filePath)" да прочете xml file и да запише в базата
 * - При команда "findAll-to (filePath)" да запише всички мениджъри във файл
 *
 *
 *
 *
 *
 *
 *  ----------- Бонус задача ------------------
 *    - Да се направи абстрактен mapper, на който какъвто и обект да дадеш да го прави на XML
 * подобно на това как работеше JSON mapper-a (GSON).
 *    - Да се направи обединяващ обект (factory pattern), на който като му подадем настройка за JSON
 * да превръща от и в JSON, а ако му дадем XML - да го прави в XML (така ще минаваме през общ интерфейс)
 */
@SpringBootApplication
public class ObjectMappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectMappingApplication.class, args);
    }

}
