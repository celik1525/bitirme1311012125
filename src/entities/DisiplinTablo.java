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
@Table(name = "disiplinTablo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DisiplinTablo.findAll", query = "SELECT d FROM DisiplinTablo d")
    , @NamedQuery(name = "DisiplinTablo.findById", query = "SELECT d FROM DisiplinTablo d WHERE d.id = :id")
    , @NamedQuery(name = "DisiplinTablo.findBySicil", query = "SELECT d FROM DisiplinTablo d WHERE d.sicil = :sicil")
    , @NamedQuery(name = "DisiplinTablo.findByDeger", query = "SELECT d FROM DisiplinTablo d WHERE d.deger = :deger")
    , @NamedQuery(name = "DisiplinTablo.findByNedeni", query = "SELECT d FROM DisiplinTablo d WHERE d.nedeni = :nedeni")
    , @NamedQuery(name = "DisiplinTablo.findByTarih", query = "SELECT d FROM DisiplinTablo d WHERE d.tarih = :tarih")})
public class DisiplinTablo implements Serializable {

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

    public DisiplinTablo() {
    }

    public DisiplinTablo(Integer id) {
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
        if (!(object instanceof DisiplinTablo)) {
            return false;
        }
        DisiplinTablo other = (DisiplinTablo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DisiplinTablo[ id=" + id + " ]";
    }
    
}
