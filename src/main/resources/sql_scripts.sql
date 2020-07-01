CREATE USER 'masjid_user'@'localhost' IDENTIFIED BY 'masjid_password';
GRANT ALL PRIVILEGES ON masjidsalgo.* TO 'masjid_user'@'localhost';

INSERT INTO masjidsalgo.masjids(id, name, address) VALUES (null, "Masjid Al Huda", "9540 Boul Gouin O, Pierrefonds, QC H8Y 2B1");
INSERT INTO masjidsalgo.masjids(id, name, address) VALUES (null, "Masjid Makkah-Al-Mukkaramah", "11900 Boul Gouin O, Pierrefonds, QC H8Z 1V6");
INSERT INTO masjidsalgo.masjids(id, name, address) VALUES (null, "Canadian Islamic Centre Aljamieh Mosque", "241 Avenue Anselme-Lavigne, Dollard-des-Ormeaux, QC H9A 3H6");

SELECT * FROM masjidsalgo.masjids;

ALTER TABLE `masjidsalgo`.`masjid_prayer_times` 
CHANGE COLUMN `prayer` `prayer` ENUM('FAJR', 'DHUHR', 'ASR', 'MAGHRIB', 'ISHA', 'JUMUAA') NULL DEFAULT NULL ;

INSERT INTO masjidsalgo.masjid_prayer_times(id, masjid_id, prayer, time) VALUES (null, 1, "FAJR", NOW());
INSERT INTO masjidsalgo.masjid_prayer_times(id, masjid_id, prayer, time) VALUES (null, 1, "DHUHR", NOW());
INSERT INTO masjidsalgo.masjid_prayer_times(id, masjid_id, prayer, time) VALUES (null, 1, "ASR", NOW());
INSERT INTO masjidsalgo.masjid_prayer_times(id, masjid_id, prayer, time) VALUES (null, 1, "MAGHRIB", NOW());
INSERT INTO masjidsalgo.masjid_prayer_times(id, masjid_id, prayer, time) VALUES (null, 1, "ISHA", NOW());
INSERT INTO masjidsalgo.masjid_prayer_times(id, masjid_id, prayer, time) VALUES (null, 1, "JUMUAA", NOW());

SELECT * FROM masjidsalgo.masjid_prayer_times;