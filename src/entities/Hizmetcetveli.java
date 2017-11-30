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
@Table(name = "hizmetcetveli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hizmetcetveli.findAll", query = "SELECT h FROM Hizmetcetveli h")
    , @NamedQuery(name = "Hizmetcetveli.findById", query = "SELECT h FROM Hizmetcetveli h WHERE h.id = :id")
    , @NamedQuery(name = "Hizmetcetveli.findBySicil", query = "SELECT h FROM Hizmetcetveli h WHERE h.sicil = :sicil")
    , @NamedQuery(name = "Hizmetcetveli.findByBegin", query = "SELECT h FROM Hizmetcetveli h WHERE h.begin = :begin")
    , @NamedQuery(name = "Hizmetcetveli.findByEnd", query = "SELECT h FROM Hizmetcetveli h WHERE h.end = :end")
    , @NamedQuery(name = "Hizmetcetveli.findByUnvan", query = "SELECT h FROM Hizmetcetveli h WHERE h.unvan = :unvan")
    , @NamedQuery(name = "Hizmetcetveli.findByYer", query = "SELECT h FROM Hizmetcetveli h WHERE h.yer = :yer")
    , @NamedQuery(name = "Hizmetcetveli.findBySenesi", query = "SELECT h FROM Hizmetcetveli h WHERE h.senesi = :senesi")})
public class Hizmetcetveli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sicil")
    private Integer sicil;
    @Column(name = "begin")
    private String begin;
    @Column(name = "end")
    private String end;
    @Column(name = "unvan")
    private String unvan;
    @Column(name = "yer")
    private String yer;
    @Column(name = "senesi")
    private Integer senesi;

    public Hizmetcetveli() {
    }

    public Hizmetcetveli(Integer id) {
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

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getYer() {
        return yer;
    }

    public void setYer(String yer) {
        this.yer = yer;
    }

    public Integer getSenesi() {
        return senesi;
    }

    public void setSenesi(Integer senesi) {
        this.senesi = senesi;
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
        if (!(object instanceof Hizmetcetveli)) {
            return false;
        }
        Hizmetcetveli other = (Hizmetcetveli) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Hizmetcetveli[ id=" + id + " ]";
    }
    
}
