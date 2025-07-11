# Happy Places App

> [!note]
> Diese App sollte zwar einen grooßen Teil der Anforderungen erfüllen,
> da ich aber nach **20+ Stunden** absolut keine Lust mehr darauf habe **ALLE**
> der restlichen Annforderungen anzuhängen, belasse ich es auf dem aktuellen Stand.
> Aktuelle (Teil-)Anforderungen die noch fehlen:
> - Die Orte können nicht bearbeitet und verändert werden.
> - Die Orte müssen *explizit*  mit Longitude und Latitude deklariert werden.
> - Die GPS Funktion funktioniert nicht (verdammte 5+ Stunden damit verbracht es zu versuchen...)
> - Es können keine Fotos hinzugefügt werden.
>
> Was ich aber geschafft habe:
> - Die Orte werden in einer Datenbank gespeichert (Yay!) [hier](./app/src/main/java/com/example/places/MapMarker.kt)
> - Die Karte ist anstelle von XML eine Android Compose [hier](./app/src/main/java/com/example/places/openstreetmap/MapView.kt)
> - Die Orte werden auf der Karte angezeigt und es gibt einige andere Einstellungen in der Karte, zudem funktionier die Karte mit dem Android Activity Lifecycle ([was das ist](https://developer.android.com/guide/components/activities/activity-lifecycle)) [hier](./app/src/main/java/com/example/places/openstreetmap/MapLifecycle.kt)
> - Sonstige Compose Objekte wie die [BottomBar](./app/src/main/java/com/example/places/BottomBar.kt)
> - Zwei verschiedene Activitys neber der [MainActivity](./app/src/main/java/com/example/places/MainActivity.kt)
>   1. Die [NewActivity](./app/src/main/java/com/example/places/NewActivity.kt), die einen neuen Ort hinzufügt.
>   2. Die [ListActivity](./app/src/main/java/com/example/places/ListActivity.kt), die die volle Liste der Orte anzeigt, die man auch Löschen kann.
>  
> Die apk-datei ist im apk ornder und kann [hier](./apk) gefunden werden.
>
> SJ

Du entwickelst eine Happy Places App für Android mit Kotlin, die es Nutzenden ermöglicht, ihre
Lieblingsorte zu speichern, auf einer Karte anzuzeigen und mit persönlichen Notizen zu versehen.

Ihr erarbeitet die App im Team und jede*r gibt eine Textdatei ab, die auf das eigene Repository
verweist.

Erstelle eine Happy Places App, die folgende Funktionen umfasst:

- Orte speichern (mit Name, Beschreibung und Foto)
- Interaktive Karte zur Anzeige der Orte
- Persönliche Notizen zu den Orten hinzufügen

## Vorbereitung

- Erstelle ein neues Android-Projekt in Android Studio und wähle Kotlin als Programmiersprache.
- Nutze das OpenStreetMap SDK, um OSM in Deine Anwendung zu integrieren.

## Funktionale Anforderungen

- Orte speichern: Nutzende können Orte mit einem Titel, einer Beschreibung und einem Bild speichern
  und diese Orte werden in einer Liste angezeigt.
- Karte & Standort-Funktion: Die gespeicherten Orte werden auf einer interaktiven Karte angezeigt
  und es gibt die Möglichkeit, den aktuellen Standort zu nutzen, um neue Orte schneller
  hinzuzufügen.
- Persönliche Notizen: Jeder Ort kann mit individuellen Notizen versehen werden, die bearbeitet oder
  gelöscht werden können.

## Beispiel-Workflow:

1. Nutzende klicken auf „Neuen Ort hinzufügen“.
2. Namen und eine kurze Beschreibung eingeben.
3. Standort auf der Karte auswählen oder den aktuellen Standort nutzen.
4. Optional ein Foto hinzufügen.
5. Ort speichern und später ggf. Notizen ergänzen.

## Extra-Herausforderungen (optional)

Falls du die Grundfunktionen schnell umgesetzt hast, kannst du noch folgende Features einbauen:

1. Kategorien für Orte (z. B. Natur, Essen, Entspannung)
2. Navigation zu gespeicherten Orten mit Google Maps
3. Suchfunktion für gespeicherte Orte

## Genutzte externe Bibliotheken:

- [osmdroid](https://github.com/osmdroid/osmdroid) (archiviert & veraltet)
- ~~[osm-android-compose](https://github.com/utsmannn/osm-android-compose)~~
