package model;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.primefaces.component.message.Message;
import org.springframework.stereotype.Component;


@Component("productService")
public class ProductManager {
	
	

public void test(){
	
		Session session = HibernateUtil.getSessionFactory().openSession();

		boolean open = false;
		open = session.isOpen();
		
		System.out.println(open);
		
		session.close();
	
	
}

public List<Products> search(int category_id){
	
	
	Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;
    
    tx = session.beginTransaction();
    Criteria cr = null;
    
    cr = session.createCriteria(Products.class);
    
    cr.add(Restrictions.eq("category_id", category_id));
    List<Products> record;
    record = cr.list();
    FacesContext context = FacesContext.getCurrentInstance();
   
    if (record.isEmpty()==false){
    	
	    tx.commit();
	    session.close();
	    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ÜRÜNLER LİSTELENDİ","") );
	    
	    
    }
    else{
    	
    	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "GİRİLEN KATEGORİYE AİT ÜRÜN BULUNAMADI !","")); 

    }
 
    return record;
}
   
	
}



