import { combineReducers } from 'redux';

import { authentication } from './authentication.reducer';
import carReducer from "./carReducer";
import wishlistReducer from "./wishlistReducer";
import fetchedCarsReducer from "./fetchedCarsReducer";
import filterReducer from "./filterReducer";
import currentCarReducer from "./currentCarReducer";
import fetchSingleCar from "./singleCarReducer"
import brandReducer from "./brandReducer";
import productReducer from "./productReducer";
import currentProductReducer from "./currentProductReducer";
import carNamesReducer from "./carNamesReducer";
import basketReducer from "./basketReducer";
import creditCardReducer from "./creditCardReducer";
import transactionReducer from "./transactionReducer";
import discountReducer from "./discountReducer";

const rootReducer = combineReducers({
    authentication,
    cars:carReducer,
    wishlistproducts:wishlistReducer,
    fetchedCars: fetchedCarsReducer,
    homefilterActivated: filterReducer,
    products:productReducer,
    currentProduct: currentProductReducer,
    carNames: carNamesReducer,
    singleCar: fetchSingleCar,
    selectedCar: currentCarReducer,
    basketProducts: basketReducer,
    creditCard: creditCardReducer,
    transactions: transactionReducer,
    discount: discountReducer,
    brands: brandReducer
});

export default rootReducer;
