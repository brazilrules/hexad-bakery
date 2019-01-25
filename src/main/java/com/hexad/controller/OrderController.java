package com.hexad.controller;

import com.hexad.model.Product;
import com.hexad.model.ProductPack;
import com.hexad.persistence.ProductDao;
import com.hexad.persistence.ProductPackDao;
import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Leonardo Rodrigues
 */
public class OrderController {
    private ProductDao productDao;
    private ProductPackDao productPackDao;
    private static final Pattern VALID_ORDER_DESCRIPTION = Pattern.compile("^(\\d+) (\\w+)$");
    
    public OrderController(ProductDao productDao, ProductPackDao productPackDao) throws InvalidParameterException {
        this.productDao = productDao;
        this.productPackDao = productPackDao;
    }
    
    public Map<ProductPack, Integer> buildOrder(String orderDescription) {
        Matcher match = VALID_ORDER_DESCRIPTION.matcher(orderDescription);
        
        if (!match.matches()) {
            throw new InvalidParameterException("Invalid order description pattern. Should be: ## ABC#");
        }
        
        int ammount = Integer.parseInt(match.group(1));
        String productCode = match.group(2);
        
        Product product = productDao.find(productCode);
        List<ProductPack> productPacks = productPackDao.findProductPacksByProduct(product);
        
        return calculateOrderMakeup(productPacks, ammount);
    }
    
    private Map<ProductPack, Integer> calculateOrderMakeup(List<ProductPack> productPacks, Integer ammount) {
        Map<ProductPack, Integer> order = new HashMap<>();
        //Sort list in descending order of ammount
        productPacks.sort(Comparator.reverseOrder());
        int remainingAmmout = ammount;
        int currentAmmount;
        int i = 0;
        
        while(remainingAmmout > 0) {
            ProductPack largestSuitablePack = productPacks.get(i);
            boolean isCurrentTooLarge = false;
            if (i != productPacks.size() - 1) {
                ProductPack nextLargestPack = productPacks.get(i + 1);
                isCurrentTooLarge = largestSuitablePack.getAmmount() != remainingAmmout 
                    && (largestSuitablePack.getAmmount() + nextLargestPack.getAmmount()) > remainingAmmout;
            }
            if (remainingAmmout >= largestSuitablePack.getAmmount() && !isCurrentTooLarge) {
                if (order.containsKey(largestSuitablePack)) {
                    currentAmmount = order.get(largestSuitablePack) + 1;
                } else {
                    currentAmmount = 1;
                }
                order.put(largestSuitablePack, currentAmmount);
                remainingAmmout -= largestSuitablePack.getAmmount();
            } else {
                i++;
            }
        }
        
        return order;
    }
}