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
@Table(name = "yildurum")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Yildurum.findAll", query = "SELECT y FROM Yildurum y")
    , @NamedQuery(name = "Yildurum.findById", query = "SELECT y FROM Yildurum y WHERE y.id = :id")
    , @NamedQuery(name = "Yildurum.findByYil", query = "SELECT y FROM Yildurum y WHERE y.yil = :yil")
    , @NamedQuery(name = "Yildurum.findByDurum", query = "SELECT y FROM Yildurum y WHERE y.durum = :durum")})
public class Yildurum implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "yil")
    private Integer yil;
    @Column(name = "durum")
    private String durum;

    public Yildurum() {
    }

    public Yildurum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYil() {
        return yil;
    }

    public void setYil(Integer yil) {
        this.yil = yil;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
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
        if (!(object instanceof Yildurum)) {
            return false;
        }
        Yildurum other = (Yildurum) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Yildurum[ id=" + id + " ]";
    }
    
}
