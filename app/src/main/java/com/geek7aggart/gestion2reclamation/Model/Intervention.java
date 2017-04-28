
package com.geek7aggart.gestion2reclamation.Model;

/**
 * Created by BARA' on 09/05/2016.
 */
public class Intervention {
    private int _idInterv,_idInterRec;
    private String
            refInterv,
            _RG,
            _SR,
            _tete,
            _amorce,
            _couleur,
            description,
            datetimeIntervention,
            etatPane;

    public Intervention() {
    }

    public int get_idInterRec() {
        return _idInterRec;
    }

    public void set_idInterRec(int _idInterRec) {
        this._idInterRec = _idInterRec;
    }

    public Intervention(
            int _idInterv,
            String refInterv,
            String _RG,
            String _SR,
            String _tete,
            String _amorce,
            String _couleur,
            String description,
            String datetimeIntervention,
            String etatPane,int _idInterRec) {
        this._idInterv = _idInterv;
        this.refInterv = refInterv;
        this._RG = _RG;
        this._SR = _SR;
        this._tete = _tete;
        this._amorce = _amorce;
        this._couleur = _couleur;
        this.description = description;
        this.datetimeIntervention = datetimeIntervention;
        this.etatPane = etatPane;
        this._idInterRec=_idInterRec;

    }


    public int get_idInterv() {
        return _idInterv;
    }

    public void set_idInterv(int _idInterv) {
        this._idInterv = _idInterv;
    }

    public String getRefInterv() {
        return refInterv;
    }

    public void setRefInterv(String refInterv) {
        this.refInterv = refInterv;
    }

    public String get_RG() {
        return _RG;
    }

    public void set_RG(String _RG) {
        this._RG = _RG;
    }

    public String get_SR() {
        return _SR;
    }

    public void set_SR(String _SR) {
        this._SR = _SR;
    }

    public String get_tete() {
        return _tete;
    }

    public void set_tete(String _tete) {
        this._tete = _tete;
    }

    public String get_amorce() {
        return _amorce;
    }

    public void set_amorce(String _amorce) {
        this._amorce = _amorce;
    }

    public String get_couleur() {
        return _couleur;
    }

    public void set_couleur(String _couleur) {
        this._couleur = _couleur;
    }

   public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatetimeIntervention() {
        return datetimeIntervention;
    }

    public void setDatetimeIntervention(String datetimeIntervention) {
        this.datetimeIntervention = datetimeIntervention;
    }

    public String getEtatPane() {
        return etatPane;
    }

    public void setEtatPane(String etatPane) {
        this.etatPane = etatPane;
    }
}
