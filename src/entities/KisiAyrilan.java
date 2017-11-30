/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ruhi ÇELİK
 */
@Entity
@Table(name = "KisiAyrilan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KisiAyrilan.findAll", query = "SELECT k FROM KisiAyrilan k")
    , @NamedQuery(name = "KisiAyrilan.findBySicil", query = "SELECT k FROM KisiAyrilan k WHERE k.sicil = :sicil")
    , @NamedQuery(name = "KisiAyrilan.findByName", query = "SELECT k FROM KisiAyrilan k WHERE k.name = :name")
    , @NamedQuery(name = "KisiAyrilan.findBySirname", query = "SELECT k FROM KisiAyrilan k WHERE k.sirname = :sirname")
    , @NamedQuery(name = "KisiAyrilan.findByUnvan", query = "SELECT k FROM KisiAyrilan k WHERE k.unvan = :unvan")
    , @NamedQuery(name = "KisiAyrilan.findByGiris", query = "SELECT k FROM KisiAyrilan k WHERE k.giris = :giris")
    , @NamedQuery(name = "KisiAyrilan.findByTerk", query = "SELECT k FROM KisiAyrilan k WHERE k.terk = :terk")
    , @NamedQuery(name = "KisiAyrilan.findByDerece", query = "SELECT k FROM KisiAyrilan k WHERE k.derece = :derece")
    , @NamedQuery(name = "KisiAyrilan.findByKademe", query = "SELECT k FROM KisiAyrilan k WHERE k.kademe = :kademe")
    , @NamedQuery(name = "KisiAyrilan.findByGosterge", query = "SELECT k FROM KisiAyrilan k WHERE k.gosterge = :gosterge")
    , @NamedQuery(name = "KisiAyrilan.findByEkgosterge", query = "SELECT k FROM KisiAyrilan k WHERE k.ekgosterge = :ekgosterge")
    , @NamedQuery(name = "KisiAyrilan.findByNedeni", query = "SELECT k FROM KisiAyrilan k WHERE k.nedeni = :nedeni")
    , @NamedQuery(name = "KisiAyrilan.findByYer", query = "SELECT k FROM KisiAyrilan k WHERE k.yer = :yer")
    , @NamedQuery(name = "KisiAyrilan.findByResim", query = "SELECT k FROM KisiAyrilan k WHERE k.resim = :resim")})
public class KisiAyrilan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "sicil")
    private Integer sicil;
    @Column(name = "name")
    private String name;
    @Column(name = "sirname")
    private String sirname;
    @Column(name = "unvan")
    private String unvan;
    @Column(name = "giris")
    private String giris;
    @Column(name = "terk")
    private String terk;
    @Column(name = "derece")
    private Integer derece;
    @Column(name = "kademe")
    private Integer kademe;
    @Column(name = "gosterge")
    private Integer gosterge;
    @Column(name = "ekgosterge")
    private Integer ekgosterge;
    @Column(name = "nedeni")
    private String nedeni;
    @Column(name = "yer")
    private String yer;
    @Column(name = "resim")
    private String resim;

    public KisiAyrilan() {
    }

    public KisiAyrilan(Integer sicil) {
        this.sicil = sicil;
    }

    public Integer getSicil() {
        return sicil;
    }

    public void setSicil(Integer sicil) {
        this.sicil = sicil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSirname() {
        return sirname;
    }

    public void setSirname(String sirname) {
        this.sirname = sirname;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getGiris() {
        return giris;
    }

    public void setGiris(String giris) {
        this.giris = giris;
    }

    public String getTerk() {
        return terk;
    }

    public void setTerk(String terk) {
        this.terk = terk;
    }

    public Integer getDerece() {
        return derece;
    }

    public void setDerece(Integer derece) {
        this.derece = derece;
    }

    public Integer getKademe() {
        return kademe;
    }

    public void setKademe(Integer kademe) {
        this.kademe = kademe;
    }

    public Integer getGosterge() {
        return gosterge;
    }

    public void setGosterge(Integer gosterge) {
        this.gosterge = gosterge;
    }

    public Integer getEkgosterge() {
        return ekgosterge;
    }

    public void setEkgosterge(Integer ekgosterge) {
        this.ekgosterge = ekgosterge;
    }

    public String getNedeni() {
        return nedeni;
    }

    public void setNedeni(String nedeni) {
        this.nedeni = nedeni;
    }

    public String getYer() {
        return yer;
    }

    public void setYer(String yer) {
        this.yer = yer;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sicil != null ? sicil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KisiAyrilan)) {
            return false;
        }
        KisiAyrilan other = (KisiAyrilan) object;
        if ((this.sicil == null && other.sicil != null) || (this.sicil != null && !this.sicil.equals(other.sicil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.KisiAyrilan[ sicil=" + sicil + " ]";
    }
    
}
