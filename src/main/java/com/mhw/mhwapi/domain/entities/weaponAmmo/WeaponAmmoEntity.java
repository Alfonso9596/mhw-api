package com.mhw.mhwapi.domain.entities.weaponAmmo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = WeaponAmmoEntity.TABLE_NAME)
@Getter
@Setter
public class WeaponAmmoEntity {

    static final String TABLE_NAME = "weapon_ammo";

    @Id
    private Integer id;
    private String deviation;
    private String specialAmmo;
    private Integer normal1Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean normal1Rapid;
    private Integer normal1Recoil;
    private String normal1Reload;
    private Integer normal2Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean normal2Rapid;
    private Integer normal2Recoil;
    private String normal2Reload;
    private Integer normal3Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean normal3Rapid;
    private Integer normal3Recoil;
    private String normal3Reload;
    private Integer pierce1Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean pierce1Rapid;
    private Integer pierce1Recoil;
    private String pierce1Reload;
    private Integer pierce2Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean pierce2Rapid;
    private Integer pierce2Recoil;
    private String pierce2Reload;
    private Integer pierce3Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean pierce3Rapid;
    private Integer pierce3Recoil;
    private String pierce3Reload;
    private Integer spread1Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean spread1Rapid;
    private Integer spread1Recoil;
    private String spread1Reload;
    private Integer spread2Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean spread2Rapid;
    private Integer spread2Recoil;
    private String spread2Reload;
    private Integer spread3Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean spread3Rapid;
    private Integer spread3Recoil;
    private String spread3Reload;
    private Integer sticky1Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean sticky1Rapid;
    private Integer sticky1Recoil;
    private String sticky1Reload;
    private Integer sticky2Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean sticky2Rapid;
    private Integer sticky2Recoil;
    private String sticky2Reload;
    private Integer sticky3Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean sticky3Rapid;
    private Integer sticky3Recoil;
    private String sticky3Reload;
    private Integer cluster1Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean cluster1Rapid;
    private Integer cluster1Recoil;
    private String cluster1Reload;
    private Integer cluster2Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean cluster2Rapid;
    private Integer cluster2Recoil;
    private String cluster2Reload;
    private Integer cluster3Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean cluster3Rapid;
    private Integer cluster3Recoil;
    private String cluster3Reload;
    private Integer recover1Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean recover1Rapid;
    private Integer recover1Recoil;
    private String recover1Reload;
    private Integer recover2Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean recover2Rapid;
    private Integer recover2Recoil;
    private String recover2Reload;
    private Integer poison1Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean poison1Rapid;
    private Integer poison1Recoil;
    private String poison1Reload;
    private Integer poison2Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean poison2Rapid;
    private Integer poison2Recoil;
    private String poison2Reload;
    private Integer paralysis1Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean paralysis1Rapid;
    private Integer paralysis1Recoil;
    private String paralysis1Reload;
    private Integer paralysis2Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean paralysis2Rapid;
    private Integer paralysis2Recoil;
    private String paralysis2Reload;
    private Integer sleep1Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean sleep1Rapid;
    private Integer sleep1Recoil;
    private String sleep1Reload;
    private Integer sleep2Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean sleep2Rapid;
    private Integer sleep2Recoil;
    private String sleep2Reload;
    private Integer exhaust1Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean exhaust1Rapid;
    private Integer exhaust1Recoil;
    private String exhaust1Reload;
    private Integer exhaust2Clip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean exhaust2Rapid;
    private Integer exhaust2Recoil;
    private String exhaust2Reload;
    private Integer flamingClip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean flamingRapid;
    private Integer flamingRecoil;
    private String flamingReload;
    private Integer waterClip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean waterRapid;
    private Integer waterRecoil;
    private String waterReload;
    private Integer freezeClip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean freezeRapid;
    private Integer freezeRecoil;
    private String freezeReload;
    private Integer thunderClip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean thunderRapid;
    private Integer thunderRecoil;
    private String thunderReload;
    private Integer dragonClip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean dragonRapid;
    private Integer dragonRecoil;
    private String dragonReload;
    private Integer slicingClip;
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean slicingRapid;
    private Integer slicingRecoil;
    private String slicingReload;
    private Integer wyvernClip;
    private String wyvernReload;
    private Integer demonClip;
    private Integer demonRecoil;
    private String demonReload;
    private Integer armorClip;
    private Integer armorRecoil;
    private String armorReload;
    private Integer tranqClip;
    private Integer tranqRecoil;
    private String tranqReload;
}
