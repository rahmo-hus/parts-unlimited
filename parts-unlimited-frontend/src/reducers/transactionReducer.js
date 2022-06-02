import {FETCH_TRANSACTIONS, FETCH_TRANSACTIONS_BY_USER} from "../actions/Types";

export default function (state = null, action) {
    switch (action.type){
        case FETCH_TRANSACTIONS:
            return action.payload;
        case FETCH_TRANSACTIONS_BY_USER:
            return action.payload;
        default:
            return state;
    }
}
