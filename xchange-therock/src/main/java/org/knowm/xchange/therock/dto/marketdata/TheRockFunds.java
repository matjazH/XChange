/*    */ package org.knowm.xchange.therock.dto.marketdata;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.knowm.xchange.currency.CurrencyPair;
/*    */ 
/*    */ public class TheRockFunds
/*    */ {
/*    */   private ArrayList<CurrencyPair> currencyPairs;
/*    */   private TheRockFund[] funds;
/*    */   
/*    */   public TheRockFund[] getFunds()
/*    */   {
/* 14 */     return this.funds;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public TheRockFunds(@com.fasterxml.jackson.annotation.JsonProperty("funds") TheRockFund[] funds)
/*    */   {
/* 21 */     this.funds = funds;
/*    */   }
/*    */   
/*    */   public List<CurrencyPair> getExchangeSymbols() {
/* 25 */     this.currencyPairs = new ArrayList();
/* 26 */     for (TheRockFund fund : this.funds) {
/* 27 */       this.currencyPairs.add(new CurrencyPair(fund.getTradeCurrency(), fund.getBaseCurrency()));
/*    */     }
/* 29 */     return this.currencyPairs;
/*    */   }
/*    */ }


/* Location:              /Users/developer/Documents/tabtrader-android/app/libs/xchange-therock.jar!/org/knowm/xchange/therock/dto/marketdata/TheRockFunds.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */