CREATE USER 'masjid_user'@'localhost' IDENTIFIED BY 'masjid_password';
GRANT ALL PRIVILEGES ON masjidsalgo.* TO 'masjid_user'@'localhost';

INSERT INTO masjidsalgo.masjids(id, name, address, created_at, updated_at) 
	VALUES (null, "Masjid Al Huda", "9540 Boul Gouin O, Pierrefonds, QC H8Y 2B1", now(), now());
INSERT INTO masjidsalgo.masjids(id, name, address, created_at, updated_at) 
	VALUES (null, "Masjid Makkah-Al-Mukkaramah", "11900 Boul Gouin O, Pierrefonds, QC H8Z 1V6", now(), now());
INSERT INTO masjidsalgo.masjids(id, name, address, created_at, updated_at) 
	VALUES (null, "Canadian Islamic Centre Aljamieh Mosque", "241 Avenue Anselme-Lavigne, Dollard-des-Ormeaux, QC H9A 3H6", now(), now());

SELECT * FROM masjidsalgo.masjids;

ALTER TABLE `masjidsalgo`.`timings` 
CHANGE COLUMN `prayer` `prayer` ENUM('FAJR', 'DHUHR', 'ASR', 'MAGHRIB', 'ISHA', 'JUMUAA') NULL DEFAULT NULL ;

INSERT INTO masjidsalgo.timings(id, prayer, time, start, end, created_at, updated_at)
	VALUES (null, "FAJR", NOW(), NOW(), NOW(), NOW(), NOW());
INSERT INTO masjidsalgo.timings(id, prayer, time, start, end, created_at, updated_at)
	VALUES (null, "DHUHR", NOW(), NOW(), NOW(), NOW(), NOW());
INSERT INTO masjidsalgo.timings(id, prayer, time, start, end, created_at, updated_at)
	VALUES (null, "ASR", NOW(), NOW(), NOW(), NOW(), NOW());
INSERT INTO masjidsalgo.timings(id, prayer, time, start, end, created_at, updated_at)
	VALUES (null, "MAGHRIB", NOW(), NOW(), NOW(), NOW(), NOW());
INSERT INTO masjidsalgo.timings(id, prayer, time, start, end, created_at, updated_at)
	VALUES (null, "ISHA", NOW(), NOW(), NOW(), NOW(), NOW());
INSERT INTO masjidsalgo.timings(id, prayer, time, start, end, created_at, updated_at)
	VALUES (null, "JUMUAA", NOW(), NOW(), NOW(), NOW(), NOW());
    
SELECT * FROM masjidsalgo.timings;
    
INSERT INTO masjidsalgo.masjids_timings(masjid_id, timing_id) VALUES (1, 1);
INSERT INTO masjidsalgo.masjids_timings(masjid_id, timing_id) VALUES (1, 2);
INSERT INTO masjidsalgo.masjids_timings(masjid_id, timing_id) VALUES (1, 3);
INSERT INTO masjidsalgo.masjids_timings(masjid_id, timing_id) VALUES (1, 4);
INSERT INTO masjidsalgo.masjids_timings(masjid_id, timing_id) VALUES (1, 5);
INSERT INTO masjidsalgo.masjids_timings(masjid_id, timing_id) VALUES (1, 6);

SELECT * FROM masjidsalgo.masjids_timings;