
package com.geek7aggart.gestion2reclamation.Model;

/**
 * Created by BARA' on 07/04/2016.
 */
public class Reclamation {

    private int idReclamation,syncRec;
    private String
            titre,
            refRec ,
            _client,
            adresse,
            contactTel,
            dateRec,
            RG_Rec,
            SR_Rec,
            tete_TRec,
            amorce_TRec,
            couleur_TRec,
            tete_DRec,
            amorce_DRec,
            couleur_DRec,
            sgnlPar,
            observInit,
            observeTech,
            description,
            etat;


    public Reclamation() {
    }

    public Reclamation(int idReclamation, String titre, String dateRec, String etat) {
        this.idReclamation = idReclamation;
        this.titre = titre;
        this.dateRec = dateRec;
        this.etat = etat;
    }

    public Reclamation(String titre, String refRec, String _client, String adresse, String contactTel, String dateRec, String RG_Rec, String SR_Rec, String tete_TRec, String amorce_TRec, String couleur_TRec, String tete_DRec, String amorce_DRec, String couleur_DRec, String sgnlPar, String observInit, String observeTech, String description, String etat, int syncRec) {

        this.titre = titre;
        this.refRec = refRec;
        this._client = _client;
        this.adresse = adresse;
        this.contactTel = contactTel;
        this.dateRec = dateRec;
        this.RG_Rec = RG_Rec;
        this.SR_Rec = SR_Rec;
        this.tete_TRec = tete_TRec;
        this.amorce_TRec = amorce_TRec;
        this.couleur_TRec = couleur_TRec;
        this.tete_DRec = tete_DRec;
        this.amorce_DRec = amorce_DRec;
        this.couleur_DRec = couleur_DRec;
        this.sgnlPar = sgnlPar;
        this.observInit = observInit;
        this.observeTech = observeTech;
        this.description = description;
        this.etat = etat;
        this.syncRec=syncRec;
    }

    public Reclamation(int idReclamation, String titre, String refRec, String _client, String adresse,
                       String contactTel, String dateRec, String RG_Rec, String SR_Rec, String tete_TRec,
                       String amorce_TRec, String couleur_TRec,
                       String tete_DRec, String amorce_DRec, String couleur_DRec,
                       String sgnlPar, String observInit, String etat,int syncRec) {
        this.idReclamation = idReclamation;
        this.titre = titre;
        this.refRec = refRec;
        this._client = _client;
        this.adresse = adresse;
        this.contactTel = contactTel;
        this.dateRec = dateRec;
        this.RG_Rec = RG_Rec;
        this.SR_Rec = SR_Rec;
        this.tete_TRec = tete_TRec;
        this.amorce_TRec = amorce_TRec;
        this.couleur_TRec = couleur_TRec;
        this.tete_DRec = tete_DRec;
        this.amorce_DRec = amorce_DRec;
        this.couleur_DRec = couleur_DRec;
        this.sgnlPar = sgnlPar;
        this.observInit = observInit;
        this.etat = etat;
        this.syncRec = syncRec;

    }

    //////////////////////////////////////////////////////////////////////


    public Reclamation(
            int idReclamation,
            String titre,
            String dateRec,
            String description,
            String etat) {
        this.idReclamation = idReclamation;
        this.titre = titre;
        this.dateRec = dateRec;
        this.description = description;
        this.etat = etat;
    }

    ///////////////////////////////////////////////////////////////

    public String getRG_Rec() {
        return RG_Rec;
    }

    public void setRG_Rec(String RG_Rec) {
        this.RG_Rec = RG_Rec;
    }

    public String getSR_Rec() {
        return SR_Rec;
    }

    public void setSR_Rec(String RS_Rec) {
        this.SR_Rec = RS_Rec;
    }

    public int getSyncRec() {
        return syncRec;
    }

    public void setSyncRec(int syncRec) {
        this.syncRec = syncRec;
    }

    public String getTete_TRec() {
        return tete_TRec;
    }

    public void setTete_TRec(String tete_TRec) {
        this.tete_TRec = tete_TRec;
    }

    public String getAmorce_TRec() {
        return amorce_TRec;
    }

    public void setAmorce_TRec(String amorce_TRec) {
        this.amorce_TRec = amorce_TRec;
    }

    public String getCouleur_TRec() {
        return couleur_TRec;
    }

    public void setCouleur_TRec(String couleur_TRec) {
        this.couleur_TRec = couleur_TRec;
    }

    public String getTete_DRec() {
        return tete_DRec;
    }

    public void setTete_DRec(String tete_DRec) {
        this.tete_DRec = tete_DRec;
    }

    public String getAmorce_DRec() {
        return amorce_DRec;
    }

    public void setAmorce_DRec(String amorce_DRec) {
        this.amorce_DRec = amorce_DRec;
    }

    public String getCouleur_DRec() {
        return couleur_DRec;
    }

    public void setCouleur_DRec(String couleur_DRec) {
        this.couleur_DRec = couleur_DRec;
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getRefRec() {
        return refRec;
    }

    public void setRefRec(String refRec) {
        this.refRec = refRec;
    }

    public String get_client() {
        return _client;
    }

    public void set_client(String _client) {
        this._client = _client;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getDateRec() {
        return dateRec;
    }

    public void setDateRec(String dateRec) {
        this.dateRec = dateRec;
    }

    public String getSgnlPar() {
        return sgnlPar;
    }

    public void setSgnlPar(String sgnlPar) {
        this.sgnlPar = sgnlPar;
    }

    public String getObservInit() {
        return observInit;
    }

    public void setObservInit(String observInit) {
        this.observInit = observInit;
    }

    public String getObserveTech() {
        return observeTech;
    }

    public void setObserveTech(String observeTech) {
        this.observeTech = observeTech;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
