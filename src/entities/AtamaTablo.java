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
@Table(name = "atamaTablo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AtamaTablo.findAll", query = "SELECT a FROM AtamaTablo a")
    , @NamedQuery(name = "AtamaTablo.findBySicil", query = "SELECT a FROM AtamaTablo a WHERE a.sicil = :sicil")
    , @NamedQuery(name = "AtamaTablo.findByName", query = "SELECT a FROM AtamaTablo a WHERE a.name = :name")
    , @NamedQuery(name = "AtamaTablo.findBySirname", query = "SELECT a FROM AtamaTablo a WHERE a.sirname = :sirname")
    , @NamedQuery(name = "AtamaTablo.findByAtamaTarihi", query = "SELECT a FROM AtamaTablo a WHERE a.atamaTarihi = :atamaTarihi")
    , @NamedQuery(name = "AtamaTablo.findByHizmetSinifi", query = "SELECT a FROM AtamaTablo a WHERE a.hizmetSinifi = :hizmetSinifi")
    , @NamedQuery(name = "AtamaTablo.findByBirim", query = "SELECT a FROM AtamaTablo a WHERE a.birim = :birim")
    , @NamedQuery(name = "AtamaTablo.findByUnvan", query = "SELECT a FROM AtamaTablo a WHERE a.unvan = :unvan")
    , @NamedQuery(name = "AtamaTablo.findByGorev", query = "SELECT a FROM AtamaTablo a WHERE a.gorev = :gorev")
    , @NamedQuery(name = "AtamaTablo.findByGorevsekli", query = "SELECT a FROM AtamaTablo a WHERE a.gorevsekli = :gorevsekli")
    , @NamedQuery(name = "AtamaTablo.findByAtamasekli", query = "SELECT a FROM AtamaTablo a WHERE a.atamasekli = :atamasekli")
    , @NamedQuery(name = "AtamaTablo.findByMemuriyetbaslama", query = "SELECT a FROM AtamaTablo a WHERE a.memuriyetbaslama = :memuriyetbaslama")})
public class AtamaTablo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "sicil")
    private Integer sicil;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "sirname")
    private String sirname;
    @Column(name = "atamaTarihi")
    private String atamaTarihi;
    @Column(name = "hizmetSinifi")
    private String hizmetSinifi;
    @Column(name = "Birim")
    private String birim;
    @Column(name = "unvan")
    private String unvan;
    @Column(name = "gorev")
    private String gorev;
    @Column(name = "gorevsekli")
    private String gorevsekli;
    @Column(name = "atamasekli")
    private String atamasekli;
    @Column(name = "memuriyetbaslama")
    private String memuriyetbaslama;

    public AtamaTablo() {
    }

    public AtamaTablo(Integer sicil) {
        this.sicil = sicil;
    }

    public AtamaTablo(Integer sicil, String name, String sirname) {
        this.sicil = sicil;
        this.name = name;
        this.sirname = sirname;
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

    public String getAtamaTarihi() {
        return atamaTarihi;
    }

    public void setAtamaTarihi(String atamaTarihi) {
        this.atamaTarihi = atamaTarihi;
    }

    public String getHizmetSinifi() {
        return hizmetSinifi;
    }

    public void setHizmetSinifi(String hizmetSinifi) {
        this.hizmetSinifi = hizmetSinifi;
    }

    public String getBirim() {
        return birim;
    }

    public void setBirim(String birim) {
        this.birim = birim;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getGorev() {
        return gorev;
    }

    public void setGorev(String gorev) {
        this.gorev = gorev;
    }

    public String getGorevsekli() {
        return gorevsekli;
    }

    public void setGorevsekli(String gorevsekli) {
        this.gorevsekli = gorevsekli;
    }

    public String getAtamasekli() {
        return atamasekli;
    }

    public void setAtamasekli(String atamasekli) {
        this.atamasekli = atamasekli;
    }

    public String getMemuriyetbaslama() {
        return memuriyetbaslama;
    }

    public void setMemuriyetbaslama(String memuriyetbaslama) {
        this.memuriyetbaslama = memuriyetbaslama;
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
        if (!(object instanceof AtamaTablo)) {
            return false;
        }
        AtamaTablo other = (AtamaTablo) object;
        if ((this.sicil == null && other.sicil != null) || (this.sicil != null && !this.sicil.equals(other.sicil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AtamaTablo[ sicil=" + sicil + " ]";
    }
    
}
