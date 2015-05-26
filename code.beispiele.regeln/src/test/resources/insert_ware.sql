
insert into warenkategorie (name) values ('Elektro');
insert into warenkategorie (name) values ('Garten');

insert into warengruppe (name) values ('Handy');
insert into warengruppe (name) values ('Spülmaschine');

insert into hersteller (id, name) values (1, 'Samsung');
insert into hersteller (id, name) values (2, 'Siemens');

insert into lieferant (id, name) values (1, 'Steffens Kaufhaus');
insert into lieferant (id, name, schulnote) values (2, 'Ollis Laden', 1);

insert into ware (id, warennummer, name, beschreibung, warenkategorie_name, warengruppe_name, hersteller_id)
	values (1, '001', 'S5', 'Samsung S5 Handy', 'Elektro', 'Handy', 1);
	
insert into ware (id, warennummer, name, beschreibung, warenkategorie_name, warengruppe_name, hersteller_id)
	values (2, '002', 'SN45L580EU', 'Samsung Geschirrspüler', 'Elektro', 'Spülmaschine', 2);
	
--#One of the follow constants SQL_TSI_FRAC_SECOND, 
--#SQL_TSI_SECOND, SQL_TSI_MINUTE, 
--#SQL_TSI_HOUR, SQL_TSI_DAY, SQL_TSI_WEEK, 
--#SQL_TSI_MONTH, SQL_TSI_QUARTER, or 
--#SQL_TSI_YEAR

--insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
--	values (4, 1, 2, date({fn TIMESTAMPADD(SQL_TSI_DAY, -1, CURRENT_TIMESTAMP)}), date({fn TIMESTAMPADD(SQL_TSI_DAY, 2, CURRENT_TIMESTAMP)}), 620, true);
--insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
--	values (2, 1, 1, date({fn TIMESTAMPADD(SQL_TSI_DAY, -4, CURRENT_TIMESTAMP)}), date({fn TIMESTAMPADD(SQL_TSI_DAY, -2, CURRENT_TIMESTAMP)}), 600, true);
--insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
--	values (3, 1, 1, date({fn TIMESTAMPADD(SQL_TSI_DAY, -10, CURRENT_TIMESTAMP)}), date({fn TIMESTAMPADD(SQL_TSI_DAY, -5, CURRENT_TIMESTAMP)}), 549, true);
--insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
--	values (1, 1, 1, date({fn TIMESTAMPADD(SQL_TSI_DAY, -15, CURRENT_TIMESTAMP)}), date({fn TIMESTAMPADD(SQL_TSI_DAY, -11, CURRENT_TIMESTAMP)}), 599, true);
	
--insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
--	values (5, 2, 2, date({fn TIMESTAMPADD(SQL_TSI_DAY, -20, CURRENT_TIMESTAMP)}), date({fn TIMESTAMPADD(SQL_TSI_DAY, -1, CURRENT_TIMESTAMP)}), 611, true);
--insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
--	values (6, 2, 2, date({fn TIMESTAMPADD(SQL_TSI_DAY, 0, CURRENT_TIMESTAMP)}), date({fn TIMESTAMPADD(SQL_TSI_DAY, 20, CURRENT_TIMESTAMP)}), 700, true);
	
insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
	values (4, 1, 2, date({fn TIMESTAMPADD(SQL_TSI_DAY, -1, CURRENT_TIMESTAMP)}), date({fn TIMESTAMPADD(SQL_TSI_DAY, 2, CURRENT_TIMESTAMP)}), 620, true);
insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
	values (2, 1, 1, date({fn TIMESTAMPADD(SQL_TSI_DAY, -4, CURRENT_TIMESTAMP)}), date({fn TIMESTAMPADD(SQL_TSI_DAY, -2, CURRENT_TIMESTAMP)}), 600, true);
insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
	values (3, 1, 1, date({fn TIMESTAMPADD(SQL_TSI_DAY, -10, CURRENT_TIMESTAMP)}), date({fn TIMESTAMPADD(SQL_TSI_DAY, -5, CURRENT_TIMESTAMP)}), 549, true);
insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
	values (1, 1, 1, date({fn TIMESTAMPADD(SQL_TSI_DAY, -15, CURRENT_TIMESTAMP)}), date({fn TIMESTAMPADD(SQL_TSI_DAY, -11, CURRENT_TIMESTAMP)}), 599, true);
	
insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
	values (5, 2, 2, date({fn TIMESTAMPADD(SQL_TSI_DAY, -20, CURRENT_TIMESTAMP)}), date({fn TIMESTAMPADD(SQL_TSI_DAY, -1, CURRENT_TIMESTAMP)}), 611, true);
insert into verkaufspreis (id, ware_id, lieferant_id, gueltigkeiterstertag, gueltigkeitletztertag, einzelpreis, gueltig)
	values (6, 2, 2, date({fn TIMESTAMPADD(SQL_TSI_DAY, 0, CURRENT_TIMESTAMP)}), date({fn TIMESTAMPADD(SQL_TSI_DAY, 20, CURRENT_TIMESTAMP)}), 700, true);
	
insert into kunde (id, name) values (1, 'Kunde-001');
insert into kunde (id, name) values (2, 'Kunde-002');


-- Eine Bestellung von vor 5 Tagen, 2 Bestellpositionen
insert into bestellung (id, kunde_id, bestellstatus, auftragsdatum)
	values (1, 1, 'BEAUFTRAGT', date({fn TIMESTAMPADD(SQL_TSI_DAY, -5, CURRENT_TIMESTAMP)}));
insert into bestellposition (id, bestellung_id, ware_id, anzahl)
	values (1, 1, 1, 10);
insert into bestellposition (id, bestellung_id, ware_id, anzahl)
	values (2, 1, 2, 2);
	
-- Eine Bestellung von vor 3 Tagen, 1 Bestellposition
insert into bestellung (id, kunde_id, bestellstatus, auftragsdatum)
	values (2, 1, 'BEAUFTRAGT', date({fn TIMESTAMPADD(SQL_TSI_DAY, -3, CURRENT_TIMESTAMP)}));
insert into bestellposition (id, bestellung_id, ware_id, anzahl)
	values (3, 2, 1, 10);