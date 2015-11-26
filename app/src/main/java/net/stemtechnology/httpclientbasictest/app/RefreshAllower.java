package net.stemtechnology.httpclientbasictest.app;

/**
 * Created by karthik on 8/22/14.
 */
public class RefreshAllower {
    boolean allowRefresh=true;
    public void setRefresh(boolean value){
        allowRefresh = value;
    }
    public boolean getRefreshValue(){
        if(allowRefresh == true || allowRefresh == false){
            return allowRefresh;
        }
        else{
            return true;
        }
    }
}
