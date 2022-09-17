# Zadanie 10 - Hibernate
#### Zakres: Spring Web, JPA, Hibernate

### Zadanie "Domowa biblioteczka" cz.2  :books:

Zadanie polega na poprawnym zaimplementowaniu w aplikacji warstwy repozytorium, żeby wszystkie dane były zapisywane w bazie MySql.


##### Wymagania:
   - Należy dodać implementację interfejsu Dao (ze wszystkimi metodami), odpowiedzialną za komunikację z bazą danych.
   - Należy dodać min. dwie encje (BookEntity, AuthorEntity) dla mapowania danych z tabel ('book' i 'author')
   - Pomiędzy encjami AuthorEntity i BookEntity ma być relacja jeden-do-wielu (dla uproszczenia przyjmujemy, że książka ma jednego autora, a jeden autor mógł napisać wiele książek).
   - Kategoria ma być zapisywana w bazie danych jako tekst.
   - Dodanie nowej książki poprzez formularz na stronie web powinno skutkować zapisaniem wszystkich danych do tabel w bazie MySql.
   - Należy pamiętać o zasadach `clean code`.
   
<br/>

##### Korzystanie z docker-compose:
W module `Ex10` jest przygotowany plik `docker-compose.yml`, dzięki któremu możemy szybko uruchomić bazę danych MySql w kontenerze dockerowym. W tym celu należy użyć komendy:
```
docker-compose up db
```
Kontener z bazą danych można zatrzymać za pomocą komendy:
```
docker-compose down
```

Załączony plik `docker-compose.yml` pozwala również na uruchomienie jednocześnie aplikacji webowej Bookcase i bazy danych. Żeby uruchomić te kontenery po raz pierwszy, należy użyć komendy:
```
docker-compose up --build
```
Komenda ta buduje obraz dockerowy aplikacji Bookcase według instrukcji zamieszczonej w pliku `Dockerfile` (pierwszy proces budowy jest dłuższy ze względu na pobieranie plików), a następnie uruchamia kontener z tą aplikacją.  
:heavy_exclamation_mark: Przed zbudowaniem obrazu należy zmienić konfigurację w pliku `application.properties` (w tej chwili jest ustawiona konfiguracja do komunikacji z osobno uruchomioną bazą danych).  

Uruchomione kontenery (bookcase_app i bookcase_mysql_db) można zatrzymać za pomocą komendy:
```
docker-compose stop
```
i ponownie uruchomić za pomocą komendy:
```
docker-compose start
```
Komendę `docker-compose up --build` należy wykonać tylko wówczas, jeśli nanieśliśmy w kodzie jakieś zmiany i należy przebudować obraz dockerowy.
Jeśli zmian nie było, to do uruchomienia kontenerów wystarczy komenda:
```
docker-compose up
```
a do ich usunięcia komenda `docker-compose down`.

Warto zauważyć, że plik docker-compose.yml ma również konfigurację `volume`, dzięki której nie stracimy danych w bazie po usunięciu kontenera.
Jeśli chcielibyśmy usunąć razem z kontenerem wszystkie zapisane w bazie dane, to wystarczy użyć jednej z komend:
```
docker-compose down --volumes
docker-compose down -v
```

## Forma oddania zadania
Utwórz nowy branch o nazwie wg konwencji `10_imieNazwisko`, np. `10_MariuszSzymanski`.<br/>  
Pamiętaj, żeby nowy branch, utworzyć wychodząc z aktualnego brancha main.   
Wykonaj zadanie na tym nowym branchu. <br/>   
Utwórz Pull Request ze swojego brancha na main. <br/>   

:information_source: Jeśli **nie** chcesz korzystać z przygotowanej aplikacji `Bookcase` w module `Ex10`, tylko wolisz rozwijać swoje rozwiązanie z zadania `Ex6`, to nowy branch należy utworzyć, wychodząc z brancha, na którym masz rozwiązane zadanie `Ex6`.  
W tym wypadku utwórz Pull Request **nie na branch main**, tylko na tzw. feature branch Ex6  
Przykład Pull Requesta na GitHubie: `6_MariuszSzymanski` :arrow_left: `10_MariuszSzymanski`

Tylko tak zgłoszone zadanie podlega ocenie.  


:heavy_exclamation_mark: Nie trzeba tworzyć nowego projektu pod to zadanie.  


---

## Punktacja

Do zdobycia jest :five: pkt.  
Zadania oddane po terminie są oceniane na maksymalnie połowę punktów.

#### Powodzenia!

## :calendar: Termin oddania: wtorek, 22.02.2022
