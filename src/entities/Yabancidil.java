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
@Table(name = "yabancidil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Yabancidil.findAll", query = "SELECT y FROM Yabancidil y")
    , @NamedQuery(name = "Yabancidil.findById", query = "SELECT y FROM Yabancidil y WHERE y.id = :id")
    , @NamedQuery(name = "Yabancidil.findByDeger", query = "SELECT y FROM Yabancidil y WHERE y.deger = :deger")})
public class Yabancidil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "deger")
    private String deger;

    public Yabancidil() {
    }

    public Yabancidil(Integer id) {
        this.id = id;
    }

    public Yabancidil(Integer id, String deger) {
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
        if (!(object instanceof Yabancidil)) {
            return false;
        }
        Yabancidil other = (Yabancidil) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Yabancidil[ id=" + id + " ]";
    }
    
}
