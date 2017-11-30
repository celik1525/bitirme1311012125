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
@Table(name = "KimlikTablo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KimlikTablo.findAll", query = "SELECT k FROM KimlikTablo k")
    , @NamedQuery(name = "KimlikTablo.findBySicil", query = "SELECT k FROM KimlikTablo k WHERE k.sicil = :sicil")
    , @NamedQuery(name = "KimlikTablo.findByTelefon", query = "SELECT k FROM KimlikTablo k WHERE k.telefon = :telefon")
    , @NamedQuery(name = "KimlikTablo.findByAdres", query = "SELECT k FROM KimlikTablo k WHERE k.adres = :adres")
    , @NamedQuery(name = "KimlikTablo.findByCinsiyet", query = "SELECT k FROM KimlikTablo k WHERE k.cinsiyet = :cinsiyet")
    , @NamedQuery(name = "KimlikTablo.findByMedenihal", query = "SELECT k FROM KimlikTablo k WHERE k.medenihal = :medenihal")
    , @NamedQuery(name = "KimlikTablo.findByDogumtarihi", query = "SELECT k FROM KimlikTablo k WHERE k.dogumtarihi = :dogumtarihi")
    , @NamedQuery(name = "KimlikTablo.findByDogumyeri", query = "SELECT k FROM KimlikTablo k WHERE k.dogumyeri = :dogumyeri")
    , @NamedQuery(name = "KimlikTablo.findByTCKimlik", query = "SELECT k FROM KimlikTablo k WHERE k.tCKimlik = :tCKimlik")})
public class KimlikTablo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "sicil")
    private Integer sicil;
    @Column(name = "telefon")
    private String telefon;
    @Column(name = "adres")
    private String adres;
    @Column(name = "cinsiyet")
    private String cinsiyet;
    @Column(name = "medenihal")
    private String medenihal;
    @Column(name = "dogumtarihi")
    private String dogumtarihi;
    @Column(name = "dogumyeri")
    private String dogumyeri;
    @Column(name = "TCKimlik")
    private String tCKimlik;

    public KimlikTablo() {
    }

    public KimlikTablo(Integer sicil) {
        this.sicil = sicil;
    }

    public Integer getSicil() {
        return sicil;
    }

    public void setSicil(Integer sicil) {
        this.sicil = sicil;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getMedenihal() {
        return medenihal;
    }

    public void setMedenihal(String medenihal) {
        this.medenihal = medenihal;
    }

    public String getDogumtarihi() {
        return dogumtarihi;
    }

    public void setDogumtarihi(String dogumtarihi) {
        this.dogumtarihi = dogumtarihi;
    }

    public String getDogumyeri() {
        return dogumyeri;
    }

    public void setDogumyeri(String dogumyeri) {
        this.dogumyeri = dogumyeri;
    }

    public String getTCKimlik() {
        return tCKimlik;
    }

    public void setTCKimlik(String tCKimlik) {
        this.tCKimlik = tCKimlik;
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
        if (!(object instanceof KimlikTablo)) {
            return false;
        }
        KimlikTablo other = (KimlikTablo) object;
        if ((this.sicil == null && other.sicil != null) || (this.sicil != null && !this.sicil.equals(other.sicil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.KimlikTablo[ sicil=" + sicil + " ]";
    }
    
}
