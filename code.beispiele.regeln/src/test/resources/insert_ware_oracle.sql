
insert into warenkategorie (name) values ('Elektro');
insert into warenkategorie (name) values ('Garten');
insert into warenkategorie (name) values ('TEST');

insert into warengruppe (name) values ('Handy');
insert into warengruppe (name) values ('Spülmaschine');
insert into warengruppe (name) values ('TEST');

insert into hersteller (id, name) values (1, 'Samsung');
insert into hersteller (id, name) values (2, 'Siemens');
insert into hersteller (id, name) values (10, 'HERSTELLER');

insert into lieferant (id, name) values (1, 'Steffens Kaufhaus');
insert into lieferant (id, name, schulnote) values (2, 'Ollis Laden', 1);

insert into ware (id, warennummer, name, beschreibung, warenkategorie_name, warengruppe_name, hersteller_id)
	values (1, '001', 'S5', 'Samsung S5 Handy', 'Elektro', 'Handy', 1);
	
insert into ware (id, warennummer, name, beschreibung, warenkategorie_name, warengruppe_name, hersteller_id)
	values (2, '002', 'SN45L580EU', 'Samsung Geschirrspüler', 'Elektro', 'Spülmaschine', 2);

-- Ware id = 10, warennummer = 111, name = TEST-WARE
-- warengruppe = TEST, warenkategorie = TEST
-- hersteller id = 10, name = HERSTELLER
insert into ware (id, warennummer, name, beschreibung, warenkategorie_name, warengruppe_name, hersteller_id)
	values (10, '111', 'TEST-WARE', 'TEST', 'TEST', 'TEST', 10);
	
--#One of the follow constants SQL_TSI_FRAC_SECOND, 
--#SQL_TSI_SECOND, SQL_TSI_MINUTE, 
--#SQL_TSI_HOUR, SQL_TSI_DAY, SQL_TSI_WEEK, 
--#SQL_TSI_MONTH, SQL_TSI_QUARTER, or 
--#SQL_TSI_YEAR

insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
  values (4, 1, 2, trunc(sysdate-1), trunc(sysdate+2), 620, 1);
insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
  values (2, 1, 1, trunc(sysdate-4), trunc(sysdate-2), 600, 1);
insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
  values (3, 1, 1, trunc(sysdate-10), trunc(sysdate-5), 549, 1);
insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
	values (1, 1, 1, trunc(sysdate-15), trunc(sysdate-11), 599, 1);
	
insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
	values (5, 2, 2, trunc(sysdate-15), trunc(sysdate-1), 611, 1);
insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
	values (6, 2, 2, trunc(sysdate), trunc(sysdate+20), 700, 1);
	
insert into kunde (id, name) values (1, 'Kunde-001');
insert into kunde (id, name) values (2, 'Kunde-002');


-- Eine Bestellung von vor 5 Tagen, 2 Bestellpositionen
-- fuer Kundennummer 1
insert into bestellung (id, kunde_id, bestellstatus, auftragsdatum)
	values (1, 1, 'BEAUFTRAGT', trunc(sysdate-5));
insert into bestellposition (id, bestellung_id, ware_id, anzahl)
	values (1, 1, 1, 10);
insert into bestellposition (id, bestellung_id, ware_id, anzahl)
	values (2, 1, 2, 2);
	
-- Eine Bestellung von vor 3 Tagen, 1 Bestellposition
insert into bestellung (id, kunde_id, bestellstatus, auftragsdatum)
	values (2, 2, 'BEAUFTRAGT', trunc(sysdate-3));
insert into bestellposition (id, bestellung_id, ware_id, anzahl)
	values (3, 2, 1, 10);