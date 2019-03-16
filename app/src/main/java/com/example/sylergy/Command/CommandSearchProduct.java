package com.example.sylergy.Command;

import com.example.sylergy.Integration.InterationFactory;
import com.example.sylergy.Objects.Context;
import com.example.sylergy.Objects.Events;
import com.example.sylergy.Objects.Product;

public class CommandSearchProduct implements Command {

    @Override
    public Context execute(Object data) {
        Product product = InterationFactory.getInstance().getDAOProduct().read((int)data);
        if(product != null)
            return new Context(Events.SEARCH_PRODUCT_OK,product);
        else
            return new Context(Events.SEARCH_PRODUCT_ERROR, null);
    }
}
