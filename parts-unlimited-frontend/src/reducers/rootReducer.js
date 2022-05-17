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
    brands: brandReducer
});

export default rootReducer;
