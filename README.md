Projektbeschreibung und Herausforderungen
In diesem Projekt stieß ich auf einige Herausforderungen, insbesondere im Umgang mit der Datenbankverbindung und Datenaktualisierung. Mithilfe von offiziellen Dokumentationen, Stack Overflow und ChatGPT konnte ich schließlich Lösungen finden.

Wichtige Herausforderungen und Lösungsansätze
Datenbankverbindung: Die Verbindung zur Datenbank herzustellen und Daten korrekt zu verwalten, erwies sich als schwierig. Besonders beim Aufrufen neuer Datensätze erschienen immer wieder die Einträge „Tomas Müller“ und „Manuel Neuer“. Dies konnte ich durch die Verwendung der @PostConstruct-Methode lösen. Ich überprüfte mit if (repos.findByNameAndTel("Tomas Müller", "123456789").isEmpty()), ob diese Einträge bereits vorhanden waren, und fügte sie nur hinzu, wenn sie fehlten.

Login-Validierung: Anfangs wurde der Login unabhängig von den eingegebenen Namen und Telefonnummern immer akzeptiert und leitete zur Add-Seite weiter, selbst wenn die Daten korrekt waren. Durch Anpassungen in der Methode konnte ich sicherstellen, dass nur bei korrekten Login-Daten die Weiterleitung zur Adressbuch-Seite erfolgt.

Datenaktualisierung im Edit-Modus: Beim Bearbeiten von Daten blieben die alten Informationen weiterhin bestehen. Hier stellte sich saveAndFlush() als Lösung heraus, um die aktualisierten Daten sofort in die Datenbank zu übernehmen und sicherzustellen, dass die Änderungen sichtbar werden. deleteById() ermöglichte eine direkte Löschung von veralteten Einträgen.

Separate HTML-Datei für das Update-Formular: Für eine übersichtliche Benutzeroberfläche erstellte ich eine separate update.html-Datei, über die Änderungen einfach vorgenommen und sicher gespeichert werden können.

Nächste Schritte
Mein Ziel ist es, das Projekt um eine sichere Login-Funktion mit Spring Security zu erweitern. Die Navigation zwischen den Seiten funktioniert inzwischen gut, jedoch stellte sich der richtige Abruf und die Verwaltung der Datensätze aus der Datenbank als anspruchsvoll heraus – insbesondere bei identischen Namen.
