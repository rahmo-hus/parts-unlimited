import { SAVE_PRODUCT, FETCH_PRODUCTS } from '../actions/Types';

export default function(state= [], action){
    switch(action.type){
        case FETCH_PRODUCTS :
            return action.payload;
        case SAVE_PRODUCT:
            return action.payload;
        default :
            return state;
    }
}
