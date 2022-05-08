import { combineReducers } from 'redux';

import { authentication } from './authentication.reducer';
import carReducer from "./carReducer";
import wishlistReducer from "./wishlistReducer";
import fetchedCarsReducer from "./fetchedCarsReducer";
import filterReducer from "./filterReducer";
import currentCarReducer from "./currentCarReducer";
import fetchSingleCar from "./singleCarReducer"

const rootReducer = combineReducers({
    authentication,
    cars:carReducer,
    wishlistproducts:wishlistReducer,
    fetchedCars: fetchedCarsReducer,
    homefilterActivated: filterReducer,
    singleCar: fetchSingleCar,
    selectedCar: currentCarReducer
});

export default rootReducer;
