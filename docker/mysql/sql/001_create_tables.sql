BEGIN;

CREATE TABLE item (
	id INTEGER NOT NULL,
	category VARCHAR(255),
	subcategory VARCHAR(255),
	rarity INTEGER,
	buy_price INTEGER,
	sell_price INTEGER,
	carry_limit INTEGER,
	points INTEGER,
	icon_name VARCHAR(255),
	icon_color VARCHAR(255),
	PRIMARY KEY (id)
);

CREATE TABLE language (
	id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	is_active TINYINT(1),
	PRIMARY KEY (id)
);

CREATE TABLE monster (
	id INTEGER NOT NULL,
	order_id INTEGER,
	size VARCHAR(255),
	icon VARCHAR(255),
	pitfall_trap TINYINT(1),
	shock_trap TINYINT(1),
	vine_trap TINYINT(1),
	has_weakness TINYINT(1),
	has_alt_weakness TINYINT(1),
	weakness_fire INTEGER,
	weakness_water INTEGER,
	weakness_ice INTEGER,
	weakness_thunder INTEGER,
	weakness_dragon INTEGER,
	weakness_poison INTEGER,
	weakness_sleep INTEGER,
	weakness_paralysis INTEGER,
	weakness_blast INTEGER,
	weakness_stun INTEGER,
	alt_weakness_fire INTEGER,
	alt_weakness_water INTEGER,
	alt_weakness_ice INTEGER,
	alt_weakness_thunder INTEGER,
	alt_weakness_dragon INTEGER,
	alt_weakness_poison INTEGER,
	alt_weakness_sleep INTEGER,
	alt_weakness_paralysis INTEGER,
	alt_weakness_blast INTEGER,
	alt_weakness_stun INTEGER,
	ailment_roar VARCHAR(255),
	ailment_wind VARCHAR(255),
	ailment_tremor VARCHAR(255),
	ailment_defensedown TINYINT(1),
	ailment_fireblight TINYINT(1),
	ailment_waterblight TINYINT(1),
	ailment_thunderblight TINYINT(1),
	ailment_iceblight TINYINT(1),
	ailment_dragonblight TINYINT(1),
	ailment_blastblight TINYINT(1),
	ailment_regional TINYINT(1),
	ailment_poison TINYINT(1),
	ailment_sleep TINYINT(1),
	ailment_paralysis TINYINT(1),
	ailment_bleed TINYINT(1),
	ailment_stun TINYINT(1),
	ailment_mud TINYINT(1),
	ailment_effluvia TINYINT(1),
	PRIMARY KEY (id),
	UNIQUE (order_id)
);

CREATE TABLE skilltree (
	id INTEGER NOT NULL,
	max_level INTEGER,
	icon_color VARCHAR(255),
	secret INTEGER,
	unlocks_id INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(unlocks_id) REFERENCES skilltree (id)
);

CREATE TABLE weapon_ammo (
	id INTEGER NOT NULL,
	deviation VARCHAR(255),
	special_ammo VARCHAR(255),
	normal1Clip INTEGER,
	normal1Rapid TINYINT(1),
	normal1Recoil INTEGER,
	normal1Reload VARCHAR(255),
	normal2Clip INTEGER,
	normal2Rapid TINYINT(1),
	normal2Recoil INTEGER,
	normal2Reload VARCHAR(255),
	normal3Clip INTEGER,
	normal3Rapid TINYINT(1),
	normal3Recoil INTEGER,
	normal3Reload VARCHAR(255),
	pierce1Clip INTEGER,
	pierce1Rapid TINYINT(1),
	pierce1Recoil INTEGER,
	pierce1Reload VARCHAR(255),
	pierce2Clip INTEGER,
	pierce2Rapid TINYINT(1),
	pierce2Recoil INTEGER,
	pierce2Reload VARCHAR(255),
	pierce3Clip INTEGER,
	pierce3Rapid TINYINT(1),
	pierce3Recoil INTEGER,
	pierce3Reload VARCHAR(255),
	spread1Clip INTEGER,
	spread1Rapid TINYINT(1),
	spread1Recoil INTEGER,
	spread1Reload VARCHAR(255),
	spread2Clip INTEGER,
	spread2Rapid TINYINT(1),
	spread2Recoil INTEGER,
	spread2Reload VARCHAR(255),
	spread3Clip INTEGER,
	spread3Rapid TINYINT(1),
	spread3Recoil INTEGER,
	spread3Reload VARCHAR(255),
	sticky1Clip INTEGER,
	sticky1Rapid TINYINT(1),
	sticky1Recoil INTEGER,
	sticky1Reload VARCHAR(255),
	sticky2Clip INTEGER,
	sticky2Rapid TINYINT(1),
	sticky2Recoil INTEGER,
	sticky2Reload VARCHAR(255),
	sticky3Clip INTEGER,
	sticky3Rapid TINYINT(1),
	sticky3Recoil INTEGER,
	sticky3Reload VARCHAR(255),
	cluster1Clip INTEGER,
	cluster1Rapid TINYINT(1),
	cluster1Recoil INTEGER,
	cluster1Reload VARCHAR(255),
	cluster2Clip INTEGER,
	cluster2Rapid TINYINT(1),
	cluster2Recoil INTEGER,
	cluster2Reload VARCHAR(255),
	cluster3Clip INTEGER,
	cluster3Rapid TINYINT(1),
	cluster3Recoil INTEGER,
	cluster3Reload VARCHAR(255),
	recover1Clip INTEGER,
	recover1Rapid TINYINT(1),
	recover1Recoil INTEGER,
	recover1Reload VARCHAR(255),
	recover2Clip INTEGER,
	recover2Rapid TINYINT(1),
	recover2Recoil INTEGER,
	recover2Reload VARCHAR(255),
	poison1Clip INTEGER,
	poison1Rapid TINYINT(1),
	poison1Recoil INTEGER,
	poison1Reload VARCHAR(255),
	poison2Clip INTEGER,
	poison2Rapid TINYINT(1),
	poison2Recoil INTEGER,
	poison2Reload VARCHAR(255),
	paralysis1Clip INTEGER,
	paralysis1Rapid TINYINT(1),
	paralysis1Recoil INTEGER,
	paralysis1Reload VARCHAR(255),
	paralysis2Clip INTEGER,
	paralysis2Rapid TINYINT(1),
	paralysis2Recoil INTEGER,
	paralysis2Reload VARCHAR(255),
	sleep1Clip INTEGER,
	sleep1Rapid TINYINT(1),
	sleep1Recoil INTEGER,
	sleep1Reload VARCHAR(255),
	sleep2Clip INTEGER,
	sleep2Rapid TINYINT(1),
	sleep2Recoil INTEGER,
	sleep2Reload VARCHAR(255),
	exhaust1Clip INTEGER,
	exhaust1Rapid TINYINT(1),
	exhaust1Recoil INTEGER,
	exhaust1Reload VARCHAR(255),
	exhaust2Clip INTEGER,
	exhaust2Rapid TINYINT(1),
	exhaust2Recoil INTEGER,
	exhaust2Reload VARCHAR(255),
	flaming_clip INTEGER,
	flaming_rapid TINYINT(1),
	flaming_recoil INTEGER,
	flaming_reload VARCHAR(255),
	water_clip INTEGER,
	water_rapid TINYINT(1),
	water_recoil INTEGER,
	water_reload VARCHAR(255),
	freeze_clip INTEGER,
	freeze_rapid TINYINT(1),
	freeze_recoil INTEGER,
	freeze_reload VARCHAR(255),
	thunder_clip INTEGER,
	thunder_rapid TINYINT(1),
	thunder_recoil INTEGER,
	thunder_reload VARCHAR(255),
	dragon_clip INTEGER,
	dragon_rapid TINYINT(1),
	dragon_recoil INTEGER,
	dragon_reload VARCHAR(255),
	slicing_clip INTEGER,
	slicing_rapid TINYINT(1),
	slicing_recoil INTEGER,
	slicing_reload VARCHAR(255),
	wyvern_clip INTEGER,
	wyvern_reload VARCHAR(255),
	demon_clip INTEGER,
	demon_recoil INTEGER,
	demon_reload VARCHAR(255),
	armor_clip INTEGER,
	armor_recoil INTEGER,
	armor_reload VARCHAR(255),
	tranq_clip INTEGER,
	tranq_recoil INTEGER,
	tranq_reload VARCHAR(255),
	PRIMARY KEY (id)
);

CREATE TABLE weapon_melody (
	id INTEGER NOT NULL,
	base_duration INTEGER,
	base_extension INTEGER,
	m1_duration INTEGER,
	m1_extension INTEGER,
	m2_duration INTEGER,
	m2_extension INTEGER,
	PRIMARY KEY (id)
);

CREATE TABLE tool (
	id INTEGER NOT NULL,
	order_id INTEGER,
	tool_type VARCHAR(255),
	duration INTEGER,
	duration_upgraded INTEGER,
	recharge INTEGER,
	slot1 INTEGER,
	slot2 INTEGER,
	slot3 INTEGER,
	icon_color VARCHAR(255),
	PRIMARY KEY (id)
);

CREATE TABLE item_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	description LONGTEXT,
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(id) REFERENCES item (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE item_combination (
	id INTEGER NOT NULL,
	result_id INTEGER,
	first_id INTEGER,
	second_id INTEGER,
	quantity INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(result_id) REFERENCES item (id),
	FOREIGN KEY(first_id) REFERENCES item (id),
	FOREIGN KEY(second_id) REFERENCES item (id)
);

CREATE TABLE location_text (
	id INTEGER NOT NULL,
	order_id INTEGER,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE monster_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	ecology VARCHAR(255),
	description LONGTEXT,
	alt_state_description LONGTEXT,
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(id) REFERENCES monster (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE monster_hitzone (
	id INTEGER NOT NULL,
	monster_id INTEGER,
	cut INTEGER,
	impact INTEGER,
	shot INTEGER,
	fire INTEGER,
	water INTEGER,
	ice INTEGER,
	thunder INTEGER,
	dragon INTEGER,
	ko INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(monster_id) REFERENCES monster (id)
);

CREATE TABLE monster_break (
	id INTEGER NOT NULL,
	monster_id INTEGER,
	flinch INTEGER,
	wound INTEGER,
	sever INTEGER,
	extract VARCHAR(255),
	PRIMARY KEY (id),
	FOREIGN KEY(monster_id) REFERENCES monster (id)
);

CREATE TABLE monster_reward_condition_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE skilltree_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	description LONGTEXT,
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(id) REFERENCES skilltree (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE skill (
    id INTEGER NOT NULL AUTO_INCREMENT,
	skilltree_id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	level INTEGER NOT NULL,
	description LONGTEXT,
	PRIMARY KEY (id),
	FOREIGN KEY(skilltree_id) REFERENCES skilltree (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE armorset (
	id INTEGER NOT NULL,
	class VARCHAR(255),
	monster_id INTEGER,
	armorset_bonus_id INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(monster_id) REFERENCES monster (id)
);

CREATE TABLE armorset_bonus_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	description LONGTEXT,
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE armorset_bonus_skill (
	setbonus_id INTEGER NOT NULL,
	skilltree_id INTEGER NOT NULL,
	required INTEGER,
	PRIMARY KEY (setbonus_id, skilltree_id),
	FOREIGN KEY(skilltree_id) REFERENCES skilltree (id)
);

CREATE TABLE weapon_melody_notes (
	id INTEGER NOT NULL,
	notes VARCHAR(255) NOT NULL,
	PRIMARY KEY (id, notes),
	FOREIGN KEY(id) REFERENCES weapon_melody (id)
);

CREATE TABLE weapon_melody_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	effect1 VARCHAR(255),
	effect2 VARCHAR(255),
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(id) REFERENCES weapon_melody (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE decoration (
	id INTEGER NOT NULL,
	slot INTEGER,
	rarity INTEGER,
	icon_color VARCHAR(255),
	skilltree_id INTEGER NOT NULL,
	skilltree_level INTEGER NOT NULL,
	skilltree2_id INTEGER,
	skilltree2_level INTEGER,
	mysterious_feystone_percent FLOAT,
	glowing_feystone_percent FLOAT,
	worn_feystone_percent FLOAT,
	warped_feystone_percent FLOAT,
	ancient_feystone_percent FLOAT,
	carved_feystone_percent FLOAT,
	sealed_feystone_percent FLOAT,
	PRIMARY KEY (id),
	FOREIGN KEY(skilltree_id) REFERENCES skilltree (id),
	FOREIGN KEY(skilltree2_id) REFERENCES skilltree (id)
);

CREATE TABLE recipe_item (
	recipe_id INTEGER NOT NULL,
	item_id INTEGER NOT NULL,
	quantity INTEGER,
	PRIMARY KEY (recipe_id, item_id),
	FOREIGN KEY(item_id) REFERENCES item (id)
);

CREATE TABLE tool_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	name_base VARCHAR(255),
	description LONGTEXT,
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(id) REFERENCES tool (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE location_item (
	id INTEGER NOT NULL,
	location_id INTEGER,
	area INTEGER,
	class VARCHAR(255),
	item_id INTEGER,
	stack INTEGER,
	percentage INTEGER,
	nodes INTEGER NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY(location_id) REFERENCES location_text (id),
	FOREIGN KEY(item_id) REFERENCES item (id)
);

CREATE TABLE location_camp_text (
	id INTEGER NOT NULL,
	location_id INTEGER,
	lang_id VARCHAR(255),
	name VARCHAR(255),
	area INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(location_id) REFERENCES location_text (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE monster_habitat (
	id INTEGER NOT NULL,
	monster_id INTEGER,
	location_id INTEGER,
	start_area VARCHAR(255),
	move_area VARCHAR(255),
	rest_area VARCHAR(255),
	PRIMARY KEY (id),
	UNIQUE (monster_id, location_id),
	FOREIGN KEY(monster_id) REFERENCES monster (id),
	FOREIGN KEY(location_id) REFERENCES location_text (id)
);

CREATE TABLE monster_hitzone_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(id) REFERENCES monster_hitzone (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE monster_break_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	part_name VARCHAR(255),
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(id) REFERENCES monster_break (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE monster_reward (
	id INTEGER NOT NULL,
	monster_id INTEGER,
	condition_id INTEGER,
	class VARCHAR(255),
	item_id INTEGER,
	stack INTEGER,
	percentage INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(monster_id) REFERENCES monster (id),
	FOREIGN KEY(condition_id) REFERENCES monster_reward_condition_text (id),
	FOREIGN KEY(item_id) REFERENCES item (id)
);

CREATE TABLE armorset_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(id) REFERENCES armorset (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE armor (
	id INTEGER NOT NULL,
	order_id INTEGER,
	rarity INTEGER,
	class VARCHAR(255),
	armor_type VARCHAR(255),
	armorset_id INTEGER,
	armorset_bonus_id INTEGER,
	recipe_id INTEGER,
	male TINYINT(1),
	female TINYINT(1),
	slot_1 INTEGER,
	slot_2 INTEGER,
	slot_3 INTEGER,
	defense_base INTEGER,
	defense_max INTEGER,
	defense_augment_max INTEGER,
	fire INTEGER,
	water INTEGER,
	thunder INTEGER,
	ice INTEGER,
	dragon INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(armorset_id) REFERENCES armorset (id),
	FOREIGN KEY(recipe_id) REFERENCES recipe_item (recipe_id)
);

CREATE TABLE weapon (
	id INTEGER NOT NULL,
	order_id INTEGER,
	weapon_type VARCHAR(255),
	rarity INTEGER,
	category VARCHAR(255),
	previous_weapon_id INTEGER,
	create_recipe_id INTEGER,
	upgrade_recipe_id INTEGER,
	attack INTEGER,
	attack_true INTEGER,
	affinity INTEGER,
	defense INTEGER,
	slot_1 INTEGER,
	slot_2 INTEGER,
	slot_3 INTEGER,
	element1 VARCHAR(255),
	element1_attack INTEGER,
	element2 VARCHAR(255),
	element2_attack INTEGER,
	element_hidden TINYINT(1),
	elderseal VARCHAR(255),
	sharpness VARCHAR(255),
	sharpness_maxed TINYINT(1),
	craftable TINYINT(1),
	final TINYINT(1),
	kinsect_bonus VARCHAR(255),
	phial VARCHAR(255),
	phial_power INTEGER,
	shelling VARCHAR(255),
	shelling_level INTEGER,
	notes VARCHAR(255),
	coating_close INTEGER,
	coating_power INTEGER,
	coating_paralysis INTEGER,
	coating_poison INTEGER,
	coating_sleep INTEGER,
	coating_blast INTEGER,
	ammo_id INTEGER,
	armorset_bonus_id INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(previous_weapon_id) REFERENCES weapon (id),
	FOREIGN KEY(create_recipe_id) REFERENCES recipe_item (recipe_id),
	FOREIGN KEY(upgrade_recipe_id) REFERENCES recipe_item (recipe_id),
	FOREIGN KEY(ammo_id) REFERENCES weapon_ammo (id)
);

CREATE TABLE decoration_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(id) REFERENCES decoration (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE charm (
	id INTEGER NOT NULL,
	order_id INTEGER,
	rarity INTEGER,
	previous_id INTEGER,
	recipe_id INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(previous_id) REFERENCES charm (id),
	FOREIGN KEY(recipe_id) REFERENCES recipe_item (recipe_id)
);

CREATE TABLE kinsect (
	id INTEGER NOT NULL,
	rarity INTEGER,
	previous_kinsect_id INTEGER,
	recipe_id INTEGER,
	attack_type VARCHAR(255),
	dust_effect VARCHAR(255),
	power INTEGER,
	speed INTEGER,
	heal INTEGER,
	final TINYINT(1),
	PRIMARY KEY (id),
	FOREIGN KEY(previous_kinsect_id) REFERENCES kinsect (id),
	FOREIGN KEY(recipe_id) REFERENCES recipe_item (recipe_id)
);

CREATE TABLE quest (
	id INTEGER NOT NULL,
	order_id INTEGER,
	category VARCHAR(255),
	class VARCHAR(255),
	stars INTEGER,
	stars_raw INTEGER,
	quest_type VARCHAR(255),
	location_id INTEGER,
	zenny INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(location_id) REFERENCES location_text (id)
);

CREATE TABLE armor_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(id) REFERENCES armor (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE armor_skill (
	armor_id INTEGER NOT NULL,
	skilltree_id INTEGER NOT NULL,
	level INTEGER,
	PRIMARY KEY (armor_id, skilltree_id),
	FOREIGN KEY(armor_id) REFERENCES armor (id),
	FOREIGN KEY(skilltree_id) REFERENCES skilltree (id)
);

CREATE TABLE weapon_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(id) REFERENCES weapon (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE weapon_skill (
	weapon_id INTEGER NOT NULL,
	skilltree_id INTEGER NOT NULL,
	level INTEGER,
	PRIMARY KEY (weapon_id, skilltree_id),
	FOREIGN KEY(weapon_id) REFERENCES weapon (id),
	FOREIGN KEY(skilltree_id) REFERENCES skilltree (id)
);

CREATE TABLE charm_skill (
	charm_id INTEGER NOT NULL,
	skilltree_id INTEGER NOT NULL,
	level INTEGER,
	PRIMARY KEY (charm_id, skilltree_id),
	FOREIGN KEY(charm_id) REFERENCES charm (id),
	FOREIGN KEY(skilltree_id) REFERENCES skilltree (id)
);

CREATE TABLE charm_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	description LONGTEXT,
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(id) REFERENCES charm (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE kinsect_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(id) REFERENCES kinsect (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE quest_text (
	id INTEGER NOT NULL,
	lang_id VARCHAR(255) NOT NULL,
	name VARCHAR(255),
	objective VARCHAR(255),
	description LONGTEXT,
	PRIMARY KEY (id, lang_id),
	FOREIGN KEY(id) REFERENCES quest (id),
	FOREIGN KEY(lang_id) REFERENCES language (id)
);

CREATE TABLE quest_monster (
    id INTEGER NOT NULL AUTO_INCREMENT,
	quest_id INTEGER NOT NULL,
	monster_id INTEGER NOT NULL,
	quantity INTEGER,
	is_objective TINYINT(1),
	PRIMARY KEY (id),
	FOREIGN KEY(quest_id) REFERENCES quest (id),
	FOREIGN KEY(monster_id) REFERENCES monster (id)
);

CREATE TABLE quest_reward (
	id INTEGER NOT NULL,
	quest_id INTEGER,
	category VARCHAR(1),
	item_id INTEGER,
	stack INTEGER,
	percentage INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(quest_id) REFERENCES quest (id),
	FOREIGN KEY(item_id) REFERENCES item (id)
);

CREATE INDEX ix_monster_hitzone_monster_id ON monster_hitzone (monster_id);
CREATE INDEX ix_monster_break_monster_id ON monster_break (monster_id);
CREATE INDEX ix_location_item_item_id ON location_item (item_id);
CREATE INDEX ix_monster_habitat_location_id ON monster_habitat (location_id);
CREATE INDEX ix_monster_habitat_monster_id ON monster_habitat (monster_id);
CREATE INDEX ix_monster_reward_monster_id ON monster_reward (monster_id);
CREATE INDEX ix_monster_reward_item_id ON monster_reward (item_id);
CREATE INDEX ix_quest_reward_quest_id ON quest_reward (quest_id);
CREATE INDEX ix_quest_reward_item_id ON quest_reward (item_id);

COMMIT;