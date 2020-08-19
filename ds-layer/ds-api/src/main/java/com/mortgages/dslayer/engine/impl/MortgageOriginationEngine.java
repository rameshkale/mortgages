package com.mortgages.dslayer.engine.impl;

import com.mortgages.dslayer.engine.ApplicationStore;
import com.mortgages.dslayer.engine.StoreSorter;
import com.mortgages.dslayer.engine.entity.Application;
import com.mortgages.dslayer.engine.ProcessEngine;
import com.mortgages.dslayer.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rameshkale on 14/08/20.
 */
public class MortgageOriginationEngine implements ProcessEngine {

    private ApplicationStore store;

    public MortgageOriginationEngine(ApplicationStore store){
        this.store = store;
    }

    @Override
    public boolean validateAppication(Application app) {
        if(validateOfferDate(app.getOffrDt())){
            throw new BusinessException(BusinessException.BusinessError.ERROR_102,
                    "Mortgage application is older than six months");
        }
        return true;
    }

    private boolean validateOfferDate(String offerDate) {
        Boolean result = Boolean.FALSE;
        try {
            Date dtOfferDate= new SimpleDateFormat("dd/MM/yyyy").parse(offerDate);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH,-6);

            Date dtSixMonthOld = calendar.getTime();

            if(dtSixMonthOld.after(dtOfferDate)){
                result = Boolean.TRUE;
                System.out.println("MortgageOriginationEngine.validateOfferDate --- Mortgage application is older than six months. - " + offerDate + "");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public boolean insertApplication(Application app) {

        if(!replaceIfExistWithEqualVersion(app)) {
            store.add(app);
            System.out.println("MortgageOriginationEngine ------------------------------ " +
                    "Message : New Mortgage Application Inserted.");
        }else{
            System.out.println("MortgageOriginationEngine ------------------------------ " +
                    "Message : Existing Mortgage Application Replaced.");
        }
        return true;
    }

    private boolean replaceIfExistWithEqualVersion(Application app) {
        Application[] all = store.getAll();
        boolean result = false;
        for(int i=0; i < all.length ;i++){
            if(app.getId().equals(all[i].getId()) &&
                    app.getVer() < all[i].getVer()){
                throw new BusinessException(BusinessException.BusinessError.ERROR_101,
                        "Mortgage offer application submitted with older version");
            }else if(app.getId().equals(all[i].getId()) &&
                    app.getVer() < all[i].getVer()){
                store.replace(i,app);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean getApplication(String appId, int version) {
        return false;
    }

    @Override
    public Application[] getApplications(SortedBy field) {

        Application[] applications = store.getAll();

        StoreSorter sorter = null;
        if(field.toString().equals("OfferDateAsc")){
            sorter = new OfferDateAscSorter();
        }else if(field.toString().equals("OfferDateDesc")){
            sorter = new OfferDateDescSorter();
        }else if(field.toString().equals("CreatedDateAsc")){
            sorter = new CreatedDateAscSorter();
        }else if(field.toString().equals("CreatedDateDesc")){
            sorter = new CreatedDateDescSorter();
        }
        Application[] result = null;
        if(sorter!=null && applications.length > 0){
            try {
                result = sorter.sort(applications);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("BootRestController.getApplications ----------------------- " +
                    "Sorting is done using " + sorter.getClass().getTypeName());
        }else{
            result = applications;
        }
        return result;
    }

    @Override
    public void displayStore() {
        Application[] applications = store.getAll();
        System.out.println("MortgageOriginationEngine.displayStore ******************************************* Start *******************************************");
        System.out.println("[Mortgage ID\t|Version\t|Offer ID\t|Product ID\t|Offer Date\t|Created Date\t|Expired Flag\t]");

        for (Application app: applications) {
            System.out.println("["+app.getId()+"\t\t|"+app.getVer()+"\t\t|"+
                    app.getOffrId()+"\t\t|"+
                    app.getPrdId()+"\t\t|"+app.getOffrDt()+"\t|"+
                    app.getCreDt()+"\t|"+app.isExpired()+"\t]");
        }
        System.out.println("MortgageOriginationEngine.displayStore ******************************************* End *******************************************");
    }
}


