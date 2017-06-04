package model;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.stereotype.Component;

@Component("chartBean")
public class Chart  {



	
	private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    private PieChartModel pieModel1;
    
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
    
    private void createPieModels() {
        createPieModel1();
        
    }
    
    private void createPieModel1() {
        

		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = null;
	    
        tx = session.beginTransaction();
        Criteria cr3,cr4 = null;
        
       
        cr3 = session.createCriteria(Products.class);
        cr3.setProjection(Projections.sum("sale"));
        List totalSale = cr3.list();
        
        
        cr4 = session.createCriteria(Products.class);
        cr4.setProjection(Projections.sum("purchase"));
        List totalPurchase = cr4.list();
        
        
        double tSale = (double) totalSale.get(0);
        double tPurchase = (double) totalPurchase.get(0);
        double profit = tSale-tPurchase;
        
    	tx.commit();
    	session.close();
    	
    	
    	pieModel1 = new PieChartModel();
         
        pieModel1.set("Total Sale :", tSale);
        pieModel1.set("Total Purchase :", tPurchase);
        pieModel1.set("Profit :", profit);
        
         
        pieModel1.setTitle("Sale - Purchase - Profit");
        pieModel1.setLegendPosition("w");
    }
     
    
    
 
    @PostConstruct
    public void init() {
        createAnimatedModels();
        createPieModels();
        
    }
    
    public LineChartModel getAnimatedModel1(){
        return animatedModel1;
    }
 
    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }
 
    private void createAnimatedModels() {
    	
    	
        animatedModel1 = initLinearModel();
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);		
         
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Sales Analysis (Amount of Sale - Purchase)");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(400);
    }
     
    private BarChartModel initBarModel() {
    	
    	
    	
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = null;
	    
        tx = session.beginTransaction();
        Criteria cr1,cr2,cr3,cr4 = null;
        
        
        cr1 = session.createCriteria(Products.class);
        cr2 = session.createCriteria(Products.class);
       
        cr1.add(Restrictions.eq("category_id", 1));
        cr2.add(Restrictions.eq("category_id",2));
        
        cr3 = session.createCriteria(Products.class);
        cr3.setProjection(Projections.sum("sale"));
        List totalSale = cr3.list();
        System.out.println(totalSale.get(0));
        
        cr4 = session.createCriteria(Products.class);
        cr4.setProjection(Projections.sum("purchase"));
        List totalPurchase = cr4.list();
        System.out.println(totalPurchase.get(0));
        
        double tSale = (double) totalSale.get(0);
        double tPurchase = (double) totalPurchase.get(0);
        double profit = tSale-tPurchase;
        System.out.println(profit);
         
        
       
//        
        List<Products> cat1List;
        List<Products> cat2List;
        cat1List = cr1.list();
        cat2List = cr2.list();
        
        int cat1_size, cat2_size;
        cat1_size = cat1List.size();
        cat2_size = cat2List.size();
        
        
        
        tx.commit();
	    session.close();
    	
        BarChartModel model = new BarChartModel();
 
        ChartSeries product_1 = new ChartSeries();
        product_1.setLabel("Category_1");
        product_1.set("June", cat1_size);
       
        ChartSeries product_2 = new ChartSeries();
        product_2.setLabel("Category_2");
        product_2.set("June", cat2_size);
       
 
        
       
       
        model.addSeries(product_1);
        model.addSeries(product_2);
       
         
        return model;
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries product_1 = new LineChartSeries();
        product_1.setLabel("Product_1");
 
        product_1.set(1, 2);
        product_1.set(2, 1);
        product_1.set(3, 3);
        product_1.set(4, 6);
        product_1.set(5, 8);
 
        LineChartSeries product_2 = new LineChartSeries();
        product_2.setLabel("Product_2");
 
        product_2.set(1, 6);
        product_2.set(2, 3);
        product_2.set(3, 2);
        product_2.set(4, 7);
        product_2.set(5, 9);
 
        model.addSeries(product_1);
        model.addSeries(product_2);
        return model;
    }
    
	
}
