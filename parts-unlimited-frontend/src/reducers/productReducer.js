import {SAVE_PRODUCT, FETCH_PRODUCTS, DELETE_PRODUCT} from '../actions/Types';

export default function(state = null, action){
    switch(action.type){
        case FETCH_PRODUCTS :
            return action.payload;
        case SAVE_PRODUCT:
            return action.payload;
        case DELETE_PRODUCT:
            return action.payload;
        default :
            return state;
    }
}
