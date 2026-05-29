# TEST-RESULTS — Calculator (Black-Box Testing)

**Repository:** https://github.com/dukki98/calculator-java-dusan  
**Datum testiranja:** 2026-05-29  
**Metoda:** Testiranje crne kutije (black-box / acceptance testing)  
**Tester:** Statička analiza koda + predviđeni rezultati izvršavanja

---

## 1. Osnovne aritmetičke operacije

| ID    | Ulaz       | Očekivano | Predviđeno | Status |
|-------|------------|-----------|------------|--------|
| TC-01 | `4+5`      | `9.0`     | `9.0`      | PASS   |
| TC-02 | `10-3`     | `7.0`     | `7.0`      | PASS   |
| TC-03 | `6*7`      | `42.0`    | `42.0`     | PASS   |
| TC-04 | `10/2`     | `5.0`     | `5.0`      | PASS   |
| TC-05 | `0+0`      | `0.0`     | `0.0`      | PASS   |

---

## 2. Prioritet računskih operacija

| ID    | Ulaz          | Očekivano | Predviđeno | Status |
|-------|---------------|-----------|------------|--------|
| TC-06 | `2+3*4`       | `14.0`    | `14.0`     | PASS   |
| TC-07 | `10+5*4+3`    | `33.0`    | `33.0`     | PASS   |
| TC-08 | `10/2+3*4`    | `17.0`    | `17.0`     | PASS   |
| TC-09 | `8-2*3+1`     | `3.0`     | `3.0`      | PASS   |
| TC-10 | `100/10/2`    | `5.0`     | `5.0`      | PASS   |

---

## 3. Rubni slučajevi — negativni brojevi i predznak

| ID    | Ulaz    | Očekivano | Predviđeno      | Status |
|-------|---------|-----------|-----------------|--------|
| TC-11 | `-5`    | `-5.0`    | `-5.0`          | PASS   |
| TC-12 | `-5+3`  | `-2.0`    | `-2.0`          | PASS   |
| TC-13 | `+5`    | `5.0`     | `5.0`           | PASS   |
| TC-14 | `5*-3`  | `-15.0`   | `ERROR`         | **FAIL** |
| TC-15 | `10+-3` | `7.0`     | `ERROR`         | **FAIL** |

**Napomena TC-14, TC-15:** Kalkulator ne podržava negativne operande unutar izraza (npr. `5*-3`). Metoda `evaluateExpression` raspoređuje broj prazninom `""` između dva operatora, što uzrokuje `Float.parseFloat("")` izuzetak i vraća `"ERROR"`.

---

## 4. Deljenje nulom

| ID    | Ulaz    | Očekivano             | Predviđeno | Status |
|-------|---------|-----------------------|------------|--------|
| TC-16 | `5/0`   | `ERROR` ili poruka    | `Infinity` | **FAIL** |
| TC-17 | `-5/0`  | `ERROR` ili poruka    | `-Infinity`| **FAIL** |
| TC-18 | `0/0`   | `ERROR` ili `NaN`     | `NaN`      | **FAIL** |

**Napomena TC-16–TC-18:** Java `float` aritmetika ne baca izuzetak za deljenje nulom — vraća `Infinity`, `-Infinity` ili `NaN`. Kalkulator propušta ove vrednosti korisniku bez ikakve poruke o grešci, što je neočekivano i zbunjujuće ponašanje.

---

## 5. Prazan unos i nevažeći izrazi

| ID    | Ulaz          | Očekivano | Predviđeno                         | Status   |
|-------|---------------|-----------|------------------------------------|----------|
| TC-19 | `""` (prazan) | `ERROR`   | `StringIndexOutOfBoundsException`  | **FAIL** |
| TC-20 | `abc`         | `ERROR`   | `ERROR`                            | PASS     |
| TC-21 | `5+`          | `ERROR`   | `ERROR`                            | PASS     |
| TC-22 | `*5`          | `ERROR`   | `ERROR`                            | PASS     |
| TC-23 | `++5`         | `ERROR`   | `ERROR`                            | PASS     |
| TC-24 | `5//2`        | `ERROR`   | `ERROR`                            | PASS     |

**Napomena TC-19:** Metoda `evaluateExpression` odmah poziva `expression.charAt(0)` bez provere da li je `expression` prazan string. Ovo izaziva nekontrolisano rušenje programa (`StringIndexOutOfBoundsException` nije uhvaćen).

---

## 6. Razmaci u izrazu

| ID    | Ulaz      | Očekivano | Predviđeno | Status   |
|-------|-----------|-----------|------------|----------|
| TC-25 | `5 + 3`   | `8.0`     | `ERROR`    | **FAIL** |
| TC-26 | `10 - 2`  | `8.0`     | `ERROR`    | **FAIL** |

**Napomena TC-25, TC-26:** Kalkulator ne vrši nikakvo trim/strip predobradu unosa. Razmaci postaju deo tokena koji se parsiraju kao broj, što uzrokuje `NumberFormatException` i vraćanje `"ERROR"`.

---

## 7. Decimalni brojevi

| ID    | Ulaz        | Očekivano    | Predviđeno   | Status |
|-------|-------------|--------------|--------------|--------|
| TC-27 | `3.14+2.0`  | `5.14`       | `5.14`       | PASS   |
| TC-28 | `1/3`       | `0.3333...`  | `0.33333334` | PASS   |
| TC-29 | `0.1+0.2`   | `0.3`        | `0.3`        | PASS   |

---

## 8. Veliki brojevi i preciznost

| ID    | Ulaz                    | Očekivano       | Predviđeno           | Status   |
|-------|-------------------------|-----------------|----------------------|----------|
| TC-30 | `999999999*999999999`   | `999999998000000001` | `1.0E18` (netačno) | **FAIL** |

**Napomena TC-30:** Tip `float` ima preciznost od svega ~7 decimalnih cifara. Za velike ili precizne proračune treba koristiti `double` ili `BigDecimal`. Korisnik nije upozoren na ograničenje preciznosti.

---

## 9. Statičko stanje (`static float finalResult`)

**ID: TC-31 — Deljeno statičko stanje**

`finalResult` je statička promenljiva klase `Calculator`. U multi-thread okruženju, ili ako se `Run()` poziva iz više niti istovremeno, rezultati se mogu međusobno mešati. Ovo nije greška u jednostavnoj CLI upotrebi, ali predstavlja arhitekturalni propust koji onemogućava bezbedno višenitno korišćenje.

---

## Rezime pronađenih grešaka i propusta

| Kategorija                        | ID grešaka                        | Ozbiljnost |
|-----------------------------------|-----------------------------------|------------|
| Rušenje programa na praznom unosu | TC-19                             | Kritično   |
| Deljenje nulom bez poruke greške  | TC-16, TC-17, TC-18               | Visoka     |
| Negativni operandi (`5*-3`)       | TC-14, TC-15                      | Visoka     |
| Razmaci u izrazu nisu podržani    | TC-25, TC-26                      | Srednja    |
| Gubitak preciznosti (float)       | TC-30                             | Srednja    |
| Statičko deljeno stanje           | TC-31                             | Srednja    |
| Iscurivanje resursa (Scanner)     | —                                 | Niska      |
| Konvencije imenovanja (Java)      | —                                 | Niska      |
