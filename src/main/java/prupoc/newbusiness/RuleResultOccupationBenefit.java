package prupoc.newbusiness;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class RuleResultOccupationBenefit implements java.io.Serializable
{

   static final long serialVersionUID = 1L;

   @org.kie.api.definition.type.Label(value = "Life")
   private java.lang.String life;
   @org.kie.api.definition.type.Label(value = "ADD")
   private java.lang.String ADD;
   @org.kie.api.definition.type.Label(value = "TPD")
   private java.lang.String TPD;
   @org.kie.api.definition.type.Label(value = "CC")
   private java.lang.String CC;
   @org.kie.api.definition.type.Label(value = "Health")
   private java.lang.String health;

   public RuleResultOccupationBenefit()
   {
   }

   public java.lang.String getLife()
   {
      return this.life;
   }

   public void setLife(java.lang.String life)
   {
      this.life = life;
   }

   public java.lang.String getADD()
   {
      return this.ADD;
   }

   public void setADD(java.lang.String ADD)
   {
      this.ADD = ADD;
   }

   public java.lang.String getTPD()
   {
      return this.TPD;
   }

   public void setTPD(java.lang.String TPD)
   {
      this.TPD = TPD;
   }

   public java.lang.String getCC()
   {
      return this.CC;
   }

   public void setCC(java.lang.String CC)
   {
      this.CC = CC;
   }

   public java.lang.String getHealth()
   {
      return this.health;
   }

   public void setHealth(java.lang.String health)
   {
      this.health = health;
   }

   public RuleResultOccupationBenefit(java.lang.String life,
         java.lang.String ADD, java.lang.String TPD, java.lang.String CC,
         java.lang.String health)
   {
      this.life = life;
      this.ADD = ADD;
      this.TPD = TPD;
      this.CC = CC;
      this.health = health;
   }

}