# parts-unlimited
Primjer Spring Boot aplikacija koja koristi slojevitu arhitekturu: sigurne RESTful endpointe te odvojene CRUD klijente. Sastoji se od tipične slojevite arhitekture: API pozivi procesiraju se od kontrolera do perzistentnog sloja kroz servisni sloj.

Za pristup svim endpointima potrebna je autentikacija, gdje korisnik nakon što se prijavi, server mu šalje JSON Web Token koji on unosi u zaglavlju svakog sljedećeg zahtjeva koji upućuje. Također, da bi korisnik bio u mogućnosti da pristupi određenim resursima, potrebno je da za pristup istima ima odgovarajuće privilegije.


*Post* | http://localhost:8080/api/auth/login | | { "username":"warehouse","password":"skladiste"}
*Post* | http://localhost:8080/api/parts/add-part | header: { Authorization: Bearer XXX.XXX.XXX } | Request body: Part
*Get* | http://localhost:8080/api/parts/get-all-parts | header: { Authorization: Bearer XXX.XXX.XXX } | 
*Get* | http://localhost:8080/api/parts/get-part/{serial} | header:  { Authorization: Bearer XXX.XXX.XXX } |
*Get* | http://localhost:8080/api/parts/get-parts/{date} |  header:  { Authorization: Bearer XXX.XXX.XXX } |
*Get* | http://localhost:8080/api/parts/get-part-by/Audi/80 | header:  { Authorization: Bearer XXX.XXX.XXX } |
*Delete* | http://localhost:8080/api/parts/delete-part/{id} | header:  { Authorization: Bearer XXX.XXX.XXX } |
*Get* | http://localhost:8080/api/parts/get-part-count |  header:  { Authorization: Bearer XXX.XXX.XXX } |
