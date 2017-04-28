package com.geek7aggart.gestion2reclamation.Model;

/**
 * Created by BARA' on 07/04/2016.
 */
public class Personnel {
   private  int idPersonel;
   private String prenomPersonnel;
   private String nomPersonnel;
   private String loginPersonnel;
   private String passwordPersonnel;

   public Personnel(String prenomPersonnel, String nomPersonnel, String loginPersonnel, String passwordPersonnel, int idPersonel) {
      this.prenomPersonnel = prenomPersonnel;
      this.nomPersonnel = nomPersonnel;
      this.loginPersonnel = loginPersonnel;
      this.passwordPersonnel = passwordPersonnel;
      this.idPersonel = idPersonel;
   }

   public Personnel() {
   }

   public int getIdPersonel() {
      return idPersonel;
   }

   public void setIdPersonel(int idPersonel) {
      this.idPersonel = idPersonel;
   }

   public String getPrenomPersonnel() {
      return prenomPersonnel;
   }

   public void setPrenomPersonnel(String prenomPersonnel) {
      this.prenomPersonnel = prenomPersonnel;
   }

   public String getNomPersonnel() {
      return nomPersonnel;
   }

   public void setNomPersonnel(String nomPersonnel) {
      this.nomPersonnel = nomPersonnel;
   }

   public String getLoginPersonnel() {
      return loginPersonnel;
   }

   public void setLoginPersonnel(String loginPersonnel) {
      this.loginPersonnel = loginPersonnel;
   }

   public String getPasswordPersonnel() {
      return passwordPersonnel;
   }

   public void setPasswordPersonnel(String passwordPersonnel) {
      this.passwordPersonnel = passwordPersonnel;
   }

}
