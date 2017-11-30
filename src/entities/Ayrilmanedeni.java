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
@Table(name = "ayrilmanedeni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ayrilmanedeni.findAll", query = "SELECT a FROM Ayrilmanedeni a")
    , @NamedQuery(name = "Ayrilmanedeni.findById", query = "SELECT a FROM Ayrilmanedeni a WHERE a.id = :id")
    , @NamedQuery(name = "Ayrilmanedeni.findByDeger", query = "SELECT a FROM Ayrilmanedeni a WHERE a.deger = :deger")})
public class Ayrilmanedeni implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "deger")
    private String deger;

    public Ayrilmanedeni() {
    }

    public Ayrilmanedeni(Integer id) {
        this.id = id;
    }

    public Ayrilmanedeni(Integer id, String deger) {
        this.id = id;
        this.deger = deger;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeger() {
        return deger;
    }

    public void setDeger(String deger) {
        this.deger = deger;
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
        if (!(object instanceof Ayrilmanedeni)) {
            return false;
        }
        Ayrilmanedeni other = (Ayrilmanedeni) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ayrilmanedeni[ id=" + id + " ]";
    }
    
}
