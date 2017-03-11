package wrappers;

import java.util.ArrayList;
import java.util.List;

public class TotalSoldsProductsWrapper {
    private List<TotalSoldProductWrapper> soldProductList;

    public TotalSoldsProductsWrapper() {
        this.soldProductList = new ArrayList<TotalSoldProductWrapper>();
    }

    public List<TotalSoldProductWrapper> getSoldProductList() {
        return soldProductList;
    }

    public void setSoldProductList(List<TotalSoldProductWrapper> soldProductList) {
        this.soldProductList = soldProductList;
    }

    public void addSoldProductWrapper(TotalSoldProductWrapper soldProductList) {
        this.soldProductList.add(soldProductList);
    }

    @Override
    public String toString() {
        return "TotalSoldsProductsWrapper [soldProductList=" + soldProductList + "]";
    }

}
