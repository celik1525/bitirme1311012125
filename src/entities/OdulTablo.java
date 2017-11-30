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
@Table(name = "odulTablo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OdulTablo.findAll", query = "SELECT o FROM OdulTablo o")
    , @NamedQuery(name = "OdulTablo.findById", query = "SELECT o FROM OdulTablo o WHERE o.id = :id")
    , @NamedQuery(name = "OdulTablo.findBySicil", query = "SELECT o FROM OdulTablo o WHERE o.sicil = :sicil")
    , @NamedQuery(name = "OdulTablo.findByDeger", query = "SELECT o FROM OdulTablo o WHERE o.deger = :deger")
    , @NamedQuery(name = "OdulTablo.findByNedeni", query = "SELECT o FROM OdulTablo o WHERE o.nedeni = :nedeni")
    , @NamedQuery(name = "OdulTablo.findByTarih", query = "SELECT o FROM OdulTablo o WHERE o.tarih = :tarih")})
public class OdulTablo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sicil")
    private Integer sicil;
    @Column(name = "deger")
    private String deger;
    @Column(name = "nedeni")
    private String nedeni;
    @Column(name = "tarih")
    private String tarih;

    public OdulTablo() {
    }

    public OdulTablo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSicil() {
        return sicil;
    }

    public void setSicil(Integer sicil) {
        this.sicil = sicil;
    }

    public String getDeger() {
        return deger;
    }

    public void setDeger(String deger) {
        this.deger = deger;
    }

    public String getNedeni() {
        return nedeni;
    }

    public void setNedeni(String nedeni) {
        this.nedeni = nedeni;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OdulTablo)) {
            return false;
        }
        OdulTablo other = (OdulTablo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OdulTablo[ id=" + id + " ]";
    }
    
}
