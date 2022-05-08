import {WISHLIST_ADD_PRODUCT, WISHLIST_REMOVE_PRODUCT} from "../actions/Types";

export default function(state = [], action) {
    var product = action.payload;
    switch (action.type) {
        case WISHLIST_ADD_PRODUCT:
            var exist = false;
            state.forEach(e => {
                if (e.id === product.id) {
                    exist = true;
                }
            });

            if (exist) {
                return state;
            } else {
                return [product, ...state];
            }

        case WISHLIST_REMOVE_PRODUCT:
            state = state.filter(function(e) {
                return e.id !== product.id;
            });
            return state;

        default:
            return state;
    }
}
